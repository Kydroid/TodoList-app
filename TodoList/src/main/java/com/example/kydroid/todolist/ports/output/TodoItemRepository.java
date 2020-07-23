package com.example.kydroid.todolist.ports.output;

import com.example.kydroid.todolist.domain.todolist.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItem, Integer> {

    List<TodoItem> findByTodoListId(Integer todoListId);
}
