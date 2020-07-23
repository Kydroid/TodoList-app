package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceCreationConflictException;
import com.example.kydroid.todolist.domain.common.ResourceCreationFailedException;
import com.example.kydroid.todolist.domain.todolist.TodoList;
import com.example.kydroid.todolist.ports.input.CreateTodoList;
import com.example.kydroid.todolist.ports.output.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CreateTodoListImpl implements CreateTodoList {

    private final TodoListRepository todoListRepository;

    @Autowired
    public CreateTodoListImpl(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public TodoList add(TodoList todoList) throws ResourceCreationFailedException, ResourceCreationConflictException {
        if (todoList.isAlreadyPersisted()) {
            throw new ResourceCreationConflictException(TodoList.class.getSimpleName());
        }

        TodoList todoListAdded = todoListRepository.saveAndFlush(todoList);
        if (todoListAdded == null) {
            throw new ResourceCreationFailedException(TodoList.class.getSimpleName());
        }
        return todoListAdded;
    }
}
