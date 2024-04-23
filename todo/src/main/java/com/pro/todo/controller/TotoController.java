package com.pro.todo.controller;

import com.pro.todo.dto.TodoDTO;
import com.pro.todo.dto.TodoFormDTO;
import com.pro.todo.entity.TodoEntity;
import com.pro.todo.repository.TodoRepository;
import com.pro.todo.service.TodoService;
import groovy.lang.GString;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TotoController {

  private final TodoService todoService;

  @RequestMapping(value = "/list")
  public String list(TodoDTO todoDTO, Model model) {
    List<TodoEntity> todoList = todoService.getList();
    model.addAttribute("todos", todoList);

    return "page/list";
  }

  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("todoFormDTO", new TodoFormDTO());

    return "page/register";
  }

  @PostMapping("/register")
  public String saveTodo(TodoFormDTO todoFormDTO, Model model) {
    todoService.saveTodo(todoFormDTO);

    return "redirect:/todo/list";
  }

  @GetMapping("/{tno}")
  public String todoDtl(@PathVariable Long tno, Model model) {
    TodoFormDTO todoFormDTO = todoService.getTodoDtl(tno);
    model.addAttribute("todo", todoFormDTO);

    return "page/show";
  }

  @GetMapping("/modify/{tno}")
  public String todoModify(@PathVariable Long tno, Model model) {
    TodoFormDTO todoFormDTO = todoService.getTodoDtl(tno);
    model.addAttribute("todo", todoFormDTO);

    return "page/modify";
  }

  @PostMapping("/modify/{tno}")
  public String updateTodo(@PathVariable Long tno, Model model) {
    TodoFormDTO todoFormDTO = todoService.getTodoDtl(tno);
    todoService.updateTodo(todoFormDTO);

    return "redirect:/todo/modify/{tno}";
  }
//
//  @GetMapping("/todo/{tno}/delete")
//  public String delete(@PathVariable Long tno) {
//    TodoFormDTO todoFormDTO = todoService.getTodoDtl(tno);
//    todoService.deleteTodo(todoFormDTO);
//
//    return null;
//  }
}
