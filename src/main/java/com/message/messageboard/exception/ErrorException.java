package com.message.messageboard.exception;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorException {
  private int status;
  private String message;
  private List<String> details;
}
