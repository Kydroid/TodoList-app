package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoItem;
import com.example.kydroid.todolist.domain.todolist.TodoItemStatus;
import com.example.kydroid.todolist.domain.todolist.TodoList;
import com.example.kydroid.todolist.ports.output.TodoItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindTodoItemTest {

    @Mock
    private TodoItemRepository todoItemRepository;
    @InjectMocks
    private FindTodoItemImpl findTodoItem;

    private List<TodoItem> todoItems;
    private TodoItem todoItem1;
    private TodoItem todoItem2;
    private TodoItem todoItem3;

    @BeforeEach
    void setup() {
        todoItem1 = new TodoItem("item1", TodoItemStatus.OPEN, new TodoList());
        todoItem1.setId(1);
        todoItem2 = new TodoItem("item2", TodoItemStatus.CLOSED, new TodoList());
        todoItem2.setId(2);
        todoItem3 = new TodoItem("item3", TodoItemStatus.OPEN, new TodoList());
        todoItem3.setId(3);
        todoItems = Arrays.asList(todoItem1, todoItem2, todoItem3);
    }

    @Test
    void returnAllTodoItem_whenFindAllTodoItem() {
        when(todoItemRepository.findAll())
                .thenReturn(todoItems);

        List<TodoItem> todoItemsFounded = findTodoItem.all();
        assertNotNull(todoItemsFounded);
        assertEquals(todoItems.size(), todoItemsFounded.size());
    }

    @Test
    void returnEmptyList_whenFindAllTodoItemNoResult() {
        when(todoItemRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<TodoItem> todoItemsFounded = findTodoItem.all();
        assertNotNull(todoItemsFounded);
        assertTrue(todoItemsFounded.isEmpty());
    }

    @Test
    void returnTodoItemById_whenFindTodoItemByIdValid() throws ResourceNotFoundException {
        int todoItemIdToFind = todoItem1.getId();
        when(todoItemRepository.findById(todoItemIdToFind))
                .thenReturn(java.util.Optional.ofNullable(todoItem1));

        TodoItem todoItemFounded = findTodoItem.byId(todoItemIdToFind);
        assertNotNull(todoItemFounded);
        assertEquals(todoItemIdToFind, todoItemFounded.getId());

    }

    @Test
    void throwResourceNotFoundException_whenFindTodoItemByIdInvalid() throws ResourceNotFoundException {
        when(todoItemRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(null));

        assertThrows(ResourceNotFoundException.class,
                () -> findTodoItem.byId(0));
    }
}
