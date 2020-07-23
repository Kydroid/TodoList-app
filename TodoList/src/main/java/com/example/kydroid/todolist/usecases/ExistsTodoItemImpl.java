package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.ports.output.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ExistsTodoItemImpl {

    private final TodoItemRepository todoItemRepository;

    @Autowired
    public ExistsTodoItemImpl(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    public boolean byId(Integer id) {
        return todoItemRepository.existsById(id);
    }
}
