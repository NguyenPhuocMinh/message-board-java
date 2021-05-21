package com.message.messageboard.service;

import com.message.messageboard.dto.BoardDto;
import com.message.messageboard.dto.ResponseDto;
import com.message.messageboard.dto.ResponsesDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {

  ResponseEntity<ResponsesDto> list(Integer skip, Integer limit, String sortBy, String orderBy);

  ResponseEntity<ResponseDto> create(BoardDto boardDto);

  ResponseEntity<ResponseDto> getBydId(Long id);

  ResponseEntity<BoardDto> updateById(Long id, BoardDto boardDto);

  ResponseEntity<Object> deleteById(Long id);
}
