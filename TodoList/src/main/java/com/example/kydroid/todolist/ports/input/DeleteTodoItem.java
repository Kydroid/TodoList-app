package com.example.kydroid.todolist.ports.input;

import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;

public interface DeleteTodoItem {
    void byId(Integer todoItemId) throws ResourceNotFoundException;
}
