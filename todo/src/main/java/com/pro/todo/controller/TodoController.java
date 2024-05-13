package com.pro.todo.controller;

import com.pro.todo.dto.TodoDTO;
import com.pro.todo.dto.TodoFormDTO;
import com.pro.todo.entity.TodoEntity;
import com.pro.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

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
  public String saveTodo(TodoFormDTO todoFormDTO) {
    todoService.saveTodo(todoFormDTO);

    return "redirect:/todo/list";
  }

  @GetMapping("/{tno}")
  public String todoDtl(@PathVariable Long tno, Model model) {
    TodoFormDTO todoFormDTO = todoService.getTodoDtl(tno);
    model.addAttribute("todo", todoFormDTO);

    return "page/show";
  }

//  @PostMapping("/{tno}")
//  public String updateDtl(@PathVariable Long tno, Model model) {
//    TodoFormDTO todoFormDTO = todoService.getTodoDtl(tno);
//    model.addAttribute("todo", todoFormDTO);
//    return "page/show";
//  }

  @GetMapping("/modify/{tno}")
  public String todoModify(@PathVariable Long tno, Model model) {
    TodoFormDTO todoFormDTO = todoService.getTodoDtl(tno);
    model.addAttribute("todo", todoFormDTO);

    return "page/modify";
  }

  @PostMapping({"/{tno}", "/modify/{tno}"})
  public String updateTodo(@PathVariable Long tno, Model model) {
    TodoFormDTO todoFormDTO = todoService.getTodoDtl(tno);
    todoService.updateTodo(tno, todoFormDTO);

    return "redirect:/todo/modify/{tno}";
  }

  @PostMapping("/remove/{tno}")
  public String remove(@PathVariable Long tno) {
    todoService.removeTodo(tno);
    return "redirect:/todo/list";
  }
}
