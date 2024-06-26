package com.pro.todo.repository;

import com.pro.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

  // 목록 조회(날짜순 정렬)
  List<TodoEntity> findAllByOrderByDueDateAsc();
}
