package com.message.messageboard.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(value = { ResponseStatusException.class })
  public ResponseEntity<Object> generateException(ResponseStatusException ex) {

    ErrorException errorException = new ErrorException();
    errorException.setStatus(ex.getStatus().value());
    errorException.setMessage(ex.getMessage());
    log.error("ResponseStatusException Error : ", ex);

    return new ResponseEntity<>(errorException, ex.getStatus());
  }

  @ExceptionHandler(value = { RecordNotFoundException.class })
  public ResponseEntity<Object> generateException(RecordNotFoundException ex) {

    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());

    ErrorException errorException = new ErrorException();
    errorException.setStatus(404);
    errorException.setMessage(ex.getMessage());
    errorException.setDetails(details);
    log.error("RecordNotFoundException Error : ", ex);

    return new ResponseEntity<>(errorException, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = { RuntimeException.class })
  public ResponseEntity<Object> generateException(RuntimeException ex) {

    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());

    ErrorException errorException = new ErrorException();
    errorException.setStatus(500);
    errorException.setMessage(ex.getMessage());
    errorException.setDetails(details);
    log.error("RuntimeException Error : ", ex);

    return new ResponseEntity<>(errorException, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = { Exception.class })
  public ResponseEntity<Object> generateAllException(Exception ex, WebRequest request) {

    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());

    ErrorException errorException = new ErrorException();
    errorException.setStatus(500);
    errorException.setMessage(ex.getLocalizedMessage());
    errorException.setDetails(details);

    log.error("Exception Error : ", ex);

    return new ResponseEntity<>(errorException, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  protected ResponseEntity<Object> generateMethodException(MethodArgumentNotValidException ex, WebRequest request) {

    List<String> details = new ArrayList<>();
    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      details.add(error.getDefaultMessage());
    }

    ErrorException errorException = new ErrorException();
    errorException.setStatus(400);
    errorException.setMessage("Validation Field");
    errorException.setDetails(details);

    log.error("MethodArgumentNotValidException Error : ", ex);

    return new ResponseEntity<>(errorException, HttpStatus.BAD_REQUEST);
  }
}
