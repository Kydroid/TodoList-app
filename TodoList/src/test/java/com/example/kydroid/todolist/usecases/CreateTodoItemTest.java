package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceCreationConflictException;
import com.example.kydroid.todolist.domain.common.ResourceCreationFailedException;
import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoItem;
import com.example.kydroid.todolist.domain.todolist.TodoItemStatus;
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
class CreateTodoItemTest {

    @Mock
    private TodoItemRepository todoItemRepository;
    @Mock
    private FindTodoListImpl findTodoList;
    @InjectMocks
    private CreateTodoItemImpl createTodoItem;

    @Test
    void returnTodoItemCreated_whenNewTodoItemValid()
            throws ResourceCreationFailedException, ResourceCreationConflictException, ResourceNotFoundException {
        TodoItem todoItemToAdd = new TodoItem();
        todoItemToAdd.setName("item1");

        when(todoItemRepository.saveAndFlush(any(TodoItem.class)))
                .thenAnswer(invocationOnMock ->
                {
                    TodoItem todoItemPersisted = invocationOnMock.getArgument(0);
                    todoItemPersisted.setId(1);
                    return todoItemPersisted;
                });

        TodoItem todoItemAdded = createTodoItem.add(todoItemToAdd, 1);
        assertNotNull(todoItemAdded, "Creation failed : null object");
        assertEquals(1, todoItemAdded.getId());
        assertEquals(TodoItemStatus.OPEN, todoItemAdded.getTodoItemStatus());
    }

    @Test
    void throwResourceCreationFailedException_whenTodoItemEmpty() {
        TodoItem todoItemToAdd = new TodoItem();
        assertThrows(ResourceCreationFailedException.class,
                () -> createTodoItem.add(todoItemToAdd, 1));
    }
}
