package com.example.kydroid.todolist.ports.input;

import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoItem;

import java.util.List;

public interface FindTodoItem {
    TodoItem byId(Integer todoItemId) throws ResourceNotFoundException;

    List<TodoItem> byTodoListId(Integer todoListId);
}
