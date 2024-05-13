package com.pro.todo.dto;

import com.pro.todo.entity.TodoEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class TodoFormDTO {

  private Long tno;
  @NotBlank(message = "제목을 입력 해주세요.")
  private String title;
  @NotBlank(message = "작성자를 입력 해주세요.")
  private String writer;
//  @NotBlank(message = "기한을 입력 해주세요.")
  private String dueDate;

  private Boolean finished;

  static ModelMapper modelMapper = new ModelMapper();

  public TodoEntity createTodo() {
    return modelMapper.map(this, TodoEntity.class);
  }


  public static TodoFormDTO of(TodoEntity todo) {
    return modelMapper.map(todo, TodoFormDTO.class);
  }
}
