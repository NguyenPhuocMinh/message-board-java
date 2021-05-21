package com.message.messageboard.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResponsesDto {
  private List<BoardDto> result;
  private Long total;
}
