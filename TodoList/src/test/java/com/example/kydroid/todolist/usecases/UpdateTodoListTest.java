package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoItem;
import com.example.kydroid.todolist.domain.todolist.TodoItemStatus;
import com.example.kydroid.todolist.domain.todolist.TodoList;
import com.example.kydroid.todolist.ports.output.TodoListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateTodoListTest {

    @Mock
    private TodoListRepository todoListRepository;
    @Mock
    private ExistsTodoListImpl existsTodoList;
    @InjectMocks
    private UpdateTodoListImpl updateTodoList;

    @Test
    void returnTodoListUpdated_whenTodoListValid() throws ResourceNotFoundException {
        ArrayList<TodoItem> items = new ArrayList<TodoItem>();
        TodoList todoListToUpdate = new TodoList("list1", items);
        todoListToUpdate.setId(1);
        TodoItem item1 = new TodoItem("item1", TodoItemStatus.OPEN, todoListToUpdate);
        items.add(item1);
        TodoItem item2 = new TodoItem("item2", TodoItemStatus.CLOSED, todoListToUpdate);
        items.add(item2);

        when(existsTodoList.byId(any(Integer.class))).thenReturn(true);
        when(todoListRepository.saveAndFlush(any(TodoList.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        TodoList todoListUpdated = updateTodoList.by(todoListToUpdate);
        assertNotNull(todoListUpdated);
        assertEquals(todoListToUpdate.getId(), todoListUpdated.getId());
        assertEquals(todoListToUpdate.getTodoItems().size(), todoListUpdated.getTodoItems().size());

    }

    @Test
    void throwResourceNotFoundException_whenTodoListInvalid() throws ResourceNotFoundException {
        assertThrows(ResourceNotFoundException.class,
                () -> updateTodoList.by(null));
    }
}