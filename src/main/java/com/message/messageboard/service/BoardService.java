package com.message.messageboard.service;

import com.message.messageboard.dto.BoardDto;
import com.message.messageboard.dto.ResponseDto;
import com.message.messageboard.dto.ResponsesDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {

  ResponseEntity<ResponsesDto> list(Integer skip, Integer limit, String sortBy, String orderBy);

  ResponseEntity<ResponseDto> create(BoardDto boardDto);

  ResponseEntity<ResponseDto> get(Long id);

  ResponseEntity<BoardDto> update(Long id, BoardDto boardDto);

  ResponseEntity<Object> delete(Long id);
}
