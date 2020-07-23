package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceCreationConflictException;
import com.example.kydroid.todolist.domain.common.ResourceCreationFailedException;
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
class CreateTodoListTest {

    @Mock
    private TodoListRepository todoListRepository;
    @InjectMocks
    private CreateTodoListImpl createTodoList;

    @Test
    void returnTodoListCreated_whenNewTodoListValid() throws ResourceCreationFailedException, ResourceCreationConflictException {
        ArrayList<TodoItem> items = new ArrayList<TodoItem>();
        TodoList todoListToAdd = new TodoList("list1", items);
        TodoItem item1 = new TodoItem("item1", TodoItemStatus.OPEN, todoListToAdd);
        items.add(item1);
        TodoItem item2 = new TodoItem("item2", TodoItemStatus.CLOSED, todoListToAdd);
        items.add(item2);

        when(todoListRepository.saveAndFlush(any(TodoList.class)))
                .thenAnswer(invocationOnMock -> {
                    TodoList todoListPersisted = invocationOnMock.getArgument(0);
                    todoListPersisted.setId(1);
                    return todoListPersisted;
                });

        TodoList todoListAdded = createTodoList.add(todoListToAdd);
        assertNotNull(todoListAdded);
        assertEquals(1, todoListAdded.getId());
        assertEquals(todoListToAdd.getTodoItems().size(), todoListAdded.getTodoItems().size());
    }

    @Test
    void throwResourceCreationFailedException_whenTodoItemEmpty() {
        TodoList todoListToAdd = new TodoList();
        assertThrows(ResourceCreationFailedException.class,
                () -> createTodoList.add(todoListToAdd));
    }
}