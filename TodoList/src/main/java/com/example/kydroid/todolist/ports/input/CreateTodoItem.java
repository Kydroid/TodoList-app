package com.example.kydroid.todolist.ports.input;

import com.example.kydroid.todolist.domain.common.ResourceCreationConflictException;
import com.example.kydroid.todolist.domain.common.ResourceCreationFailedException;
import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoItem;

public interface CreateTodoItem {
    TodoItem add(TodoItem todoItem, Integer todoListId)
            throws ResourceCreationFailedException, ResourceCreationConflictException, ResourceNotFoundException;
}
