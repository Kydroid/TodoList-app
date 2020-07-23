package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.ports.output.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ExistsTodoListImpl {

    private final TodoListRepository todoListRepository;

    @Autowired
    public ExistsTodoListImpl(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public boolean byId(Integer id) {
        return todoListRepository.existsById(id);
    }
}
