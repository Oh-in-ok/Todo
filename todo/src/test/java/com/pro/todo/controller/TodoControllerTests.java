package com.pro.todo.controller;

import com.pro.todo.dto.TodoFormDTO;
import com.pro.todo.entity.TodoEntity;
import com.pro.todo.service.TodoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
//@Transactional
public class TodoControllerTests {

  @Autowired
  private TodoService todoService;

  @Autowired
  private MockMvc mockMvc;

  public TodoEntity createTodo(String title, String writer, Boolean finished) {
    TodoFormDTO todoFormDTO = new TodoFormDTO();
    todoFormDTO.setTno(1L);
    todoFormDTO.setTitle(title);
    todoFormDTO.setWriter(writer);
//    todoFormDTO.setDueDate();
    todoFormDTO.setFinished(finished);
    TodoEntity todo = TodoEntity.createTodo(todoFormDTO);
    return  todo;
  }

  @Test
  @DisplayName("데이터 저장")
//  @Transactional
  public void savedTest() {
    for(int i = 1; i < 6; i++) {

      String title = "Test" + i;
      String writer = "User" +i;
      if(i % 2 == 0)
      {
        Boolean finished = false;
        TodoEntity todo = this.createTodo(title, writer, finished);
        todoService.saveTodo(todo);
        System.out.println("===========================" + createTodo(title, writer, finished));
      } else {
        Boolean finished = true;
        TodoEntity todo = this.createTodo(title, writer, finished);
        todoService.saveTodo(todo);
        System.out.println("===========================" + createTodo(title, writer, finished));
      }
    }
  }

  @Test
  @DisplayName("데이터 목록")
  public void readTest() {
    List<TodoEntity> todoLists = todoService.getList();
    for(TodoEntity todo : todoLists) {
      System.out.println("==============" + todo);
    }
  }

  @Test
  @DisplayName("데이터 수정")
  public void updateTodo() {
//    Long updateTno = todoService.getTodoDtl(3L).getTno();
//    TodoFormDTO todo = TodoFormDTO.of(this.createTodo("과제하기", "홍길동", false));
//    todoService.updateTodo(3L, "할일");
  }
}
