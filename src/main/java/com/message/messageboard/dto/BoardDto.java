package com.message.messageboard.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class BoardDto implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  @NotEmpty(message = "Name must be not empty")
  @Length(min = 0, max = 50, message = "Name must be less than 50 character!")
  private String name;

  @NotEmpty(message = "Title must be not empty")
  @Length(min = 0, max = 100, message = "Title must be less than 100 character!")
  private String title;

  @NotEmpty(message = "Description must be not empty")
  @Length(min = 0, max = 1000, message = "Description must be less than 1000 character!")
  private String description;

  @NotNull(message = "RegisterDate must be not empty")
  private Date registerDate;

  private int index;
}
