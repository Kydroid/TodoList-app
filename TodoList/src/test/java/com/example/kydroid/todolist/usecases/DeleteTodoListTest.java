package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.ports.output.TodoListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteTodoListTest {

    @Mock
    private TodoListRepository todoListRepository;
    @Mock
    private ExistsTodoListImpl existsTodoList;
    @InjectMocks
    private DeleteTodoListImpl deleteTodoList;

    @Test
    void deleteTodoItem_whenTodoItemIdValid() throws ResourceNotFoundException {
        when(existsTodoList.byId(anyInt()))
                .thenReturn(true);
        deleteTodoList.byId(1);
    }

    @Test
    void throwResourceNotFoundException_whenTodoListIdNotFound() throws ResourceNotFoundException {
        when(existsTodoList.byId(anyInt()))
                .thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> deleteTodoList.byId(0));
    }
}