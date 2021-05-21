package com.message.messageboard.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto {
  private BoardDto data;
  private String message;
}
