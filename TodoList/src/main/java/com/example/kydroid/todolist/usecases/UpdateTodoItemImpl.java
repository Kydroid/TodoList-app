package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceInvalidException;
import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoItem;
import com.example.kydroid.todolist.ports.input.UpdateTodoItem;
import com.example.kydroid.todolist.ports.output.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UpdateTodoItemImpl implements UpdateTodoItem {

    private final TodoItemRepository todoItemRepository;
    private final ExistsTodoItemImpl existsTodoItem;

    @Autowired
    public UpdateTodoItemImpl(TodoItemRepository todoItemRepository, ExistsTodoItemImpl existsTodoItem) {
        this.todoItemRepository = todoItemRepository;
        this.existsTodoItem = existsTodoItem;
    }

    public TodoItem by(TodoItem todoItem) throws ResourceNotFoundException, ResourceInvalidException {
        if (todoItem == null || !existsTodoItem.byId(todoItem.getId())) {
            Integer todoItemId = (todoItem != null)
                    ? todoItem.getId() : null;
            throw new ResourceNotFoundException(TodoItem.class.getSimpleName(), todoItemId);
        } else if (todoItem.getTodoList() == null) {
            throw new ResourceInvalidException(TodoItem.class.getSimpleName(), todoItem.getId());
        }
        return todoItemRepository.saveAndFlush(todoItem);
    }
}
