package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoList;
import com.example.kydroid.todolist.ports.input.UpdateTodoList;
import com.example.kydroid.todolist.ports.output.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UpdateTodoListImpl implements UpdateTodoList {

    private final TodoListRepository todoListRepository;
    private final ExistsTodoListImpl existsTodoList;

    @Autowired
    public UpdateTodoListImpl(TodoListRepository todoListRepository, ExistsTodoListImpl existsTodoList) {
        this.todoListRepository = todoListRepository;
        this.existsTodoList = existsTodoList;
    }

    public TodoList by(TodoList todoList) throws ResourceNotFoundException {
        if (todoList == null || !existsTodoList.byId(todoList.getId())) {
            Integer todoListId = todoList != null
                    ? todoList.getId() : null;
            throw new ResourceNotFoundException(TodoList.class.getSimpleName(), todoListId);
        }
        return todoListRepository.saveAndFlush(todoList);
    }
}
