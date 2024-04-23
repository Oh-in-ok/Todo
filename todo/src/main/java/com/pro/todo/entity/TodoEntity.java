package com.pro.todo.entity;

import com.pro.todo.dto.TodoFormDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "todo")
public class TodoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tno;

  private String title;

  private String writer;
  private Timestamp dueDate;

  private boolean finished;

  public static TodoEntity createTodo(TodoFormDTO todoFormDTO) {
    TodoEntity todo = new TodoEntity();
    todo.setTitle(todoFormDTO.getTitle());
    todo.setWriter(todoFormDTO.getWriter());
    todo.setFinished(todoFormDTO.isFinished());
    return todo;
  }

  public void updateTodo(TodoFormDTO todoFormDTO) {
    this.tno = todoFormDTO.getTno();
    this.title = todoFormDTO.getTitle();
    this.writer = todoFormDTO.getWriter();
    this.finished = todoFormDTO.isFinished();
  }
}
