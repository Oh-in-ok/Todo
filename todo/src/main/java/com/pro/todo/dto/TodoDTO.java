package com.pro.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TodoDTO {

  private Long tno;
  private String title;
  private String writer;
  private String dueDate;
  private Boolean finished;

}
