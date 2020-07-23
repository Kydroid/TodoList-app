package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoList;
import com.example.kydroid.todolist.ports.input.FindTodoList;
import com.example.kydroid.todolist.ports.output.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class FindTodoListImpl implements FindTodoList {

    private final TodoListRepository todoListRepository;

    @Autowired
    public FindTodoListImpl(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<TodoList> all() {
        return todoListRepository.findAll();
    }

    public TodoList byId(Integer id) throws ResourceNotFoundException {
        Optional<TodoList> todoListOptional = todoListRepository.findById(id);
        TodoList todoListFounded = todoListOptional.orElseThrow(
                () -> new ResourceNotFoundException(TodoList.class.getSimpleName(), id));
        return todoListFounded;
    }
}
