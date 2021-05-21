package com.message.messageboard.service.Impl;

import com.message.messageboard.dto.BoardDto;
import com.message.messageboard.dto.ResponseDto;
import com.message.messageboard.dto.ResponsesDto;
import com.message.messageboard.entity.Board;
import com.message.messageboard.exception.RecordNotFoundException;

import java.util.Date;
import java.util.Optional;

import com.message.messageboard.component.ConvertBoard;
import com.message.messageboard.repository.BoardRepository;
import com.message.messageboard.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  ConvertBoard converter;

  @Override
  public ResponseEntity<ResponseDto> create(BoardDto dto) {
    log.debug("Create board service begin");

    Board board = converter.dtoToEntity(dto);
    board.setUpdatedAt(new Date());
    board = boardRepository.save(board);

    log.debug("Create board service end");

    ResponseDto response = new ResponseDto();
    response.setData(converter.entityToDto(board));
    response.setMessage("Đã đăng ký");

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ResponseDto> getBydId(Long id) {
    Board board = boardRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Board not found"));

    ResponseDto response = new ResponseDto();
    response.setData(converter.entityToDto(board));
    response.setMessage("Successfully!");

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ResponsesDto> list(Integer skip, Integer limit, String sortBy, String orderBy) {
    log.debug("Board list service implement begin");

    int page = skip / 10;
    int size = limit - skip;

    Order sortOrder = new Order(getOrderByDirect(orderBy), sortBy);
    Pageable paging = PageRequest.of(page, size, Sort.by(sortOrder));

    Page<Board> result = boardRepository.findAll(paging);

    Long totals = boardRepository.countByDeleted(false);

    ResponsesDto response = new ResponsesDto();

    response.setResult(converter.entityToDtoList(result.getContent()));
    response.setTotal(totals);

    log.debug("Board list service implement begin");

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<BoardDto> updateById(Long id, BoardDto boardDto) {
    Optional<Board> board = boardRepository.findById(id);

    if (board.isPresent()) {

      Board dto = converter.dtoToEntity(boardDto);

      Board _board = board.get();
      _board.setName(dto.getName());
      _board.setTitle(dto.getTitle());
      _board.setDescription(dto.getDescription());

      Board response = boardRepository.save(_board);

      return new ResponseEntity<>(converter.entityToDto(response), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<Object> deleteById(Long id) {
    boardRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  private Sort.Direction getOrderByDirect(String orderBy) {
    if (orderBy.equals("DESC")) {
      return Sort.Direction.DESC;
    } else if (orderBy.equals("ASC")) {
      return Sort.Direction.ASC;
    }

    return Sort.Direction.DESC;
  }
}