package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoItem;
import com.example.kydroid.todolist.ports.input.FindTodoItem;
import com.example.kydroid.todolist.ports.output.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class FindTodoItemImpl implements FindTodoItem {

    private final TodoItemRepository todoItemRepository;

    @Autowired
    public FindTodoItemImpl(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    public List<TodoItem> all() {
        return todoItemRepository.findAll();
    }

    public TodoItem byId(Integer id) throws ResourceNotFoundException {
        Optional<TodoItem> todoItemOptional = todoItemRepository.findById(id);
        TodoItem todoItemFounded = todoItemOptional.orElseThrow(
                () -> new ResourceNotFoundException(TodoItem.class.getSimpleName(), id)
        );
        return todoItemFounded;
    }

    @Override
    public List<TodoItem> byTodoListId(Integer todoListId) {
        return todoItemRepository.findByTodoListId(todoListId);
    }
}
