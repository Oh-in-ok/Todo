package com.pro.todo.service;

import com.pro.todo.dto.TodoDTO;
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

  public TodoEntity saveTodo(TodoEntity todo) {
    return todoRepository.save(todo);
  }

  public Long saveTodo(TodoFormDTO todoFormDTO) {
    TodoEntity todo = todoFormDTO.createTodo();
    todoRepository.save(todo);

    return todo.getTno();
  }

  public List<TodoEntity> getList() {
    return todoRepository.findAll();
  }

  public TodoFormDTO getTodoDtl(Long tno) {
    TodoEntity todo = todoRepository.findById(tno).orElseThrow(EntityNotFoundException::new);
//    TodoFormDTO todoFormDTO = TodoFormDTO.of(todo);

    return TodoFormDTO.of(todo);
  }

  public Long updateTodo(TodoFormDTO todoFormDTO) {
    TodoEntity todo = todoRepository.findById(todoFormDTO.getTno()).orElseThrow(EntityNotFoundException::new);
    todo.updateTodo(todoFormDTO);

    return todo.getTno();
  }
//
//  public Long deleteTodo(TodoFormDTO todoFormDTO) {
//    TodoEntity todo = todoRepository.findById(todoFormDTO.getTno()).orElseThrow(EntityNotFoundException::new);
//    if(todo != null) {
//      todoRepository.delete(todo);
//    }
//
//    return null;
//  }
}
