package com.message.messageboard.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.message.messageboard.dto.BoardDto;
import com.message.messageboard.dto.ResponseDto;
import com.message.messageboard.dto.ResponsesDto;
import com.message.messageboard.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/boards")
public class BoardController {

  @Autowired
  BoardService boardService;

  @GetMapping
  public ResponseEntity<ResponsesDto> getBoards(@RequestParam(defaultValue = "0") int _start,
                                                @RequestParam(defaultValue = "1000") int _end,
                                                @RequestParam(defaultValue = "registerDate") String sortBy,
                                                @RequestParam(defaultValue = "DESC") String orderBy) {
    return boardService.list(_start, _end, sortBy, orderBy);
  }

  @PostMapping
  public ResponseEntity<ResponseDto> createBoard(@Valid @RequestBody BoardDto boardDto) {
    return boardService.create(boardDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getBoardById(@NotNull @PathVariable(value = "id") Long id) {
    return boardService.getBydId(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BoardDto> updateBoard(@Valid @NotNull @PathVariable(value = "id") Long id,
                                              @Valid @RequestBody BoardDto boardDto) {
    return boardService.updateById(id, boardDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deletedBoard(@NotNull @PathVariable(value = "id") Long id) {
    return boardService.deleteById(id);
  }
}
