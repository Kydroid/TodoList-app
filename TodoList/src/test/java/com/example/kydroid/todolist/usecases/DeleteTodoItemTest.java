package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.ports.output.TodoItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteTodoItemTest {

    @Mock
    private TodoItemRepository todoItemRepository;
    @Mock
    private ExistsTodoItemImpl existsTodoItem;
    @InjectMocks
    private DeleteTodoItemImpl deleteTodoItem;

    @Test
    void deleteTodoItem_whenTodoItemIdValid() throws ResourceNotFoundException {
        when(existsTodoItem.byId(anyInt()))
                .thenReturn(true);
        deleteTodoItem.byId(1);
    }

    @Test
    void throwResourceNotFoundException_whenTodoItemIdNotFound() throws ResourceNotFoundException {
        when(existsTodoItem.byId(anyInt()))
                .thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> deleteTodoItem.byId(0));
    }
}