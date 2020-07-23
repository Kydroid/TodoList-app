package com.example.kydroid.todolist.ports.input;

import com.example.kydroid.todolist.domain.common.ResourceCreationConflictException;
import com.example.kydroid.todolist.domain.common.ResourceCreationFailedException;
import com.example.kydroid.todolist.domain.todolist.TodoList;

public interface CreateTodoList {
    TodoList add(TodoList todoList) throws ResourceCreationFailedException, ResourceCreationConflictException;
}
