package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceInvalidException;
import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoItem;
import com.example.kydroid.todolist.domain.todolist.TodoItemStatus;
import com.example.kydroid.todolist.domain.todolist.TodoList;
import com.example.kydroid.todolist.ports.output.TodoItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateTodoItemTest {

    @Mock
    private TodoItemRepository todoItemRepository;
    @Mock
    private ExistsTodoItemImpl existsTodoItem;
    @InjectMocks
    private UpdateTodoItemImpl updateTodoItem;

    @Test
    void returnTodoItemUpdated_whenTodoItemValid() throws ResourceNotFoundException, ResourceInvalidException {
        TodoItem todoItemToUpdate = new TodoItem("item1", TodoItemStatus.CLOSED, new TodoList());
        todoItemToUpdate.setId(1);

        when(existsTodoItem.byId(any(Integer.class))).thenReturn(true);
        when(todoItemRepository.saveAndFlush(any(TodoItem.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        TodoItem todoItemUpdated = updateTodoItem.by(todoItemToUpdate);
        assertNotNull(todoItemUpdated, "Creation failed : null object");
        assertEquals(1, todoItemUpdated.getId());
        assertEquals(TodoItemStatus.CLOSED, todoItemUpdated.getTodoItemStatus());
    }

    @Test
    void throwResourceNotFoundException_whenTodoItemInvalid() throws ResourceNotFoundException {
        assertThrows(ResourceNotFoundException.class,
                () -> updateTodoItem.by(null));
    }

    @Test
    void throwResourceNotFoundException_whenTodoItemOrphan() throws ResourceNotFoundException {
        TodoItem todoItemOrphan = new TodoItem("todoitem without list", TodoItemStatus.OPEN, null);
        todoItemOrphan.setId(1);

        when(existsTodoItem.byId(any(Integer.class))).thenReturn(true);

        assertThrows(ResourceInvalidException.class,
                () -> updateTodoItem.by(todoItemOrphan));
    }
}
