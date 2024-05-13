package com.pro.todo.service;

import com.pro.todo.dto.TodoFormDTO;
import com.pro.todo.entity.TodoEntity;
import com.pro.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

  private final TodoRepository todoRepository;

  public void saveTodo(TodoEntity todo) {
    todoRepository.save(todo);
  }

  public Long saveTodo(TodoFormDTO todoFormDTO) {
    TodoEntity todo = todoFormDTO.createTodo();
    todoRepository.save(todo);

    return todo.getTno();
  }

  public List<TodoEntity> getList() {
    return todoRepository.findAllByOrderByDueDateAsc();
  }

  public TodoFormDTO getTodoDtl(Long tno) {
    TodoEntity todo = todoRepository.findById(tno).orElseThrow(EntityNotFoundException::new);
//    TodoFormDTO todoFormDTO = TodoFormDTO.of(todo);

    return TodoFormDTO.of(todo);
  }

//  public Long updateTodo(TodoFormDTO todoFormDTO) {
//    TodoEntity todo = todoRepository.findById(todoFormDTO.getTno()).orElseThrow(EntityNotFoundException::new);
//    todo.updateTodo(todoFormDTO);
//
//    return todo.getTno();
//  }
//  public void updateTodo(Long tno, String title) {
//    TodoEntity todo = todoRepository.findById(tno).orElseThrow(EntityNotFoundException::new);
//    todo.setTitle(title);
//    this.todoRepository.save(todo);
//  }

  public void updateTodo(Long tno, TodoFormDTO todoFormDTO) {
    TodoEntity todo = todoRepository.findById(tno).orElseThrow(EntityNotFoundException::new);

    todo.setTitle(todoFormDTO.getTitle());
    todo.setDueDate(todoFormDTO.getDueDate());
    this.todoRepository.save(todo);
  }

  public void removeTodo(Long tno) {
    TodoEntity todo = todoRepository.findById(tno).orElseThrow(EntityNotFoundException::new);
    if(todo.getTno() != null) {
      todoRepository.delete(todo);
      System.out.println("====삭제된 목록 : " + todo);
    } else {
      System.out.println("========삭제에 실패했습니다========");
    }
  }
}
