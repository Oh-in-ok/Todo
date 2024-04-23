package com.pro.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TodoDTO {

  private Long tno;
  private String title;
  private String writer;
  private Timestamp dueDate;
  private boolean finished;
}
