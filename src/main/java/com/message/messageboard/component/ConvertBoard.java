package com.message.messageboard.component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.message.messageboard.dto.BoardDto;
import com.message.messageboard.entity.Board;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvertBoard {

  @Autowired
  private ModelMapper mapper;

  public ConvertBoard(ModelMapper mapper) {
    this.mapper = mapper;
  }

  public BoardDto entityToDto(Board board) {
    return mapper.map(board, BoardDto.class);
  }

  public Board dtoToEntity(BoardDto dtoBoard) {
    return mapper.map(dtoBoard, Board.class);
  }

  public List<BoardDto> entityToDtoList(List<Board> boards) {
    return IntStream.range(0, boards.size())
            .mapToObj(index -> {
              Board board = boards.get(index);
              return convertDto(board, index);
            })
            .collect(Collectors.toList());
  }

  public BoardDto convertDto(Board board, int index) {
    BoardDto dto = new BoardDto();
    dto.setId(board.getId());
    dto.setName(board.getName());
    dto.setTitle(board.getTitle());
    dto.setDescription(board.getDescription());
    dto.setRegisterDate(board.getRegisterDate());
    dto.setIndex(index);

    return dto;
  }
}
