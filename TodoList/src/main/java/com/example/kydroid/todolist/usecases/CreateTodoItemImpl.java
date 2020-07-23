package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceCreationConflictException;
import com.example.kydroid.todolist.domain.common.ResourceCreationFailedException;
import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoItem;
import com.example.kydroid.todolist.domain.todolist.TodoItemStatus;
import com.example.kydroid.todolist.domain.todolist.TodoList;
import com.example.kydroid.todolist.ports.input.CreateTodoItem;
import com.example.kydroid.todolist.ports.output.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CreateTodoItemImpl implements CreateTodoItem {

    private final TodoItemRepository todoItemRepository;
    private final FindTodoListImpl findTodoList;

    @Autowired
    public CreateTodoItemImpl(TodoItemRepository todoItemRepository, FindTodoListImpl findTodoList) {
        this.todoItemRepository = todoItemRepository;
        this.findTodoList = findTodoList;
    }

    public TodoItem add(TodoItem todoItem, Integer todoListId)
            throws ResourceCreationFailedException, ResourceCreationConflictException, ResourceNotFoundException {
        if (todoItem.isAlreadyPersisted()) {
            throw new ResourceCreationConflictException(TodoItem.class.getSimpleName());
        }

        TodoList todoListParentOfTodoItem = findTodoList.byId(todoListId);
        todoItem.setTodoList(todoListParentOfTodoItem);

        todoItem.setTodoItemStatus(TodoItemStatus.OPEN);
        TodoItem todoItemAdded = todoItemRepository.saveAndFlush(todoItem);
        if (todoItemAdded == null) {
            throw new ResourceCreationFailedException(TodoItem.class.getSimpleName());
        }
        return todoItemAdded;
    }
}
