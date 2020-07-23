package com.example.kydroid.todolist.ports.input;

import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;

public interface DeleteTodoList {
    void byId(Integer todoListId) throws ResourceNotFoundException;
}
