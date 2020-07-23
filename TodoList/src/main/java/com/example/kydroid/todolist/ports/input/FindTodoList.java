package com.example.kydroid.todolist.ports.input;

import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoList;

import java.util.List;

public interface FindTodoList {
    List<TodoList> all();

    TodoList byId(Integer todoListId) throws ResourceNotFoundException;
}
