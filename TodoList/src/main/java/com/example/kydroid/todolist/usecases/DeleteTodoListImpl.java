package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoList;
import com.example.kydroid.todolist.ports.input.DeleteTodoList;
import com.example.kydroid.todolist.ports.output.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class DeleteTodoListImpl implements DeleteTodoList {

    private final TodoListRepository todoListRepository;
    private final ExistsTodoListImpl existsTodoList;

    @Autowired
    public DeleteTodoListImpl(TodoListRepository todoListRepository, ExistsTodoListImpl existsTodoList) {
        this.todoListRepository = todoListRepository;
        this.existsTodoList = existsTodoList;
    }

    public void byId(Integer id) throws ResourceNotFoundException {
        if (!existsTodoList.byId(id)) {
            throw new ResourceNotFoundException(TodoList.class.getSimpleName(), id);
        }
        todoListRepository.deleteById(id);
    }
}
