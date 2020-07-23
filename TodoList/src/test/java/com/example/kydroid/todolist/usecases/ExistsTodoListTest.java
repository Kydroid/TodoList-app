package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.ports.output.TodoListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExistsTodoListTest {

    @Mock
    private TodoListRepository todoListRepository;
    @InjectMocks
    private ExistsTodoListImpl existsTodoList;

    @Test
    void returnTrue_whenTodoListExistbyId() {
        when(todoListRepository.existsById(anyInt()))
                .thenReturn(true);

        boolean existTodoList = existsTodoList.byId(1);
        assertTrue(existTodoList);
    }

    @Test
    void returnFalse_whenTodoItemDoesNotExistbyId() {
        when(todoListRepository.existsById(anyInt()))
                .thenReturn(false);

        boolean existTodoList = existsTodoList.byId(0);
        assertFalse(existTodoList);
    }
}