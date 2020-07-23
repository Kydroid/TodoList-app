package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoItem;
import com.example.kydroid.todolist.ports.input.DeleteTodoItem;
import com.example.kydroid.todolist.ports.output.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class DeleteTodoItemImpl implements DeleteTodoItem {

    private final TodoItemRepository todoItemRepository;

    private final ExistsTodoItemImpl existsTodoItem;

    @Autowired
    public DeleteTodoItemImpl(TodoItemRepository todoItemRepository, ExistsTodoItemImpl existsTodoItem) {
        this.todoItemRepository = todoItemRepository;
        this.existsTodoItem = existsTodoItem;
    }

    public void byId(Integer id) throws ResourceNotFoundException {
        if (!existsTodoItem.byId(id)) {
            throw new ResourceNotFoundException(TodoItem.class.getSimpleName(), id);
        }

        todoItemRepository.deleteById(id);
    }
}
