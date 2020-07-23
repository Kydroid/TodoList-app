package com.example.kydroid.todolist.ports.input;

import com.example.kydroid.todolist.domain.common.ResourceInvalidException;
import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoItem;

public interface UpdateTodoItem {
    TodoItem by(TodoItem todoItem) throws ResourceNotFoundException, ResourceInvalidException;
}
