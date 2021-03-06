package com.example.kydroid.todolist.ports.input;

import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoList;

public interface UpdateTodoList {
    TodoList by(TodoList todoList) throws ResourceNotFoundException;
}
