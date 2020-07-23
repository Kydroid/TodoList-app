package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.ports.output.TodoItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExistsTodoItemTest {
    @Mock
    private TodoItemRepository todoItemRepository;
    @InjectMocks
    private ExistsTodoItemImpl existsTodoItem;

    @Test
    void returnTrue_whenTodoItemExistbyId() {
        when(todoItemRepository.existsById(anyInt()))
                .thenReturn(true);

        boolean existTodoItem = existsTodoItem.byId(1);
        assertTrue(existTodoItem);
    }

    @Test
    void returnFalse_whenTodoItemDoesNotExistbyId() {
        when(todoItemRepository.existsById(any(Integer.class)))
                .thenReturn(false);

        boolean existTodoItem = existsTodoItem.byId(0);
        assertFalse(existTodoItem);
    }
}