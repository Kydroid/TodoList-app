package com.example.kydroid.todolist.ports.output;

import com.example.kydroid.todolist.domain.todolist.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
}
