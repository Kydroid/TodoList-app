package com.example.kydroid.todolist.usecases;

import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoItem;
import com.example.kydroid.todolist.domain.todolist.TodoItemStatus;
import com.example.kydroid.todolist.domain.todolist.TodoList;
import com.example.kydroid.todolist.ports.output.TodoListRepository;
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
class FindTodoListTest {

    @Mock
    private TodoListRepository todoListRepository;
    @InjectMocks
    private FindTodoListImpl findTodoList;

    private List<TodoList> todoLists;

    @BeforeEach
    void setup() {
        List<TodoItem> todoItemsForList1 = new ArrayList<>();
        TodoList todoList1 = new TodoList("list1", todoItemsForList1);
        todoList1.setId(1);
        TodoItem todoItem1 = new TodoItem("item1", TodoItemStatus.OPEN, todoList1);
        todoItem1.setId(1);
        TodoItem todoItem2 = new TodoItem("item2", TodoItemStatus.CLOSED, todoList1);
        todoItem2.setId(2);
        TodoItem todoItem3 = new TodoItem("item3", TodoItemStatus.OPEN, todoList1);
        todoItem3.setId(3);
        todoItemsForList1.addAll(Arrays.asList(todoItem1, todoItem2, todoItem3));

        TodoList todoList2 = new TodoList("list2", new ArrayList<>());
        todoList2.setId(2);

        todoLists = new ArrayList<>();
        todoLists.add(todoList1);
        todoLists.add(todoList2);
    }

    @Test
    void returnAllTodoList_whenFindAllTodoList() {
        when(todoListRepository.findAll()).thenReturn(todoLists);

        List<TodoList> todoListsFounded = findTodoList.all();
        assertNotNull(todoListsFounded);
        assertEquals(todoLists.size(), todoListsFounded.size());
    }

    @Test
    void returnEmptyList_whenFindAllTodoItemNoResult() {
        when(todoListRepository.findAll()).thenReturn(new ArrayList<>());

        List<TodoList> todoListsFounded = findTodoList.all();
        assertNotNull(todoListsFounded);
        assertTrue(todoListsFounded.isEmpty());
    }

    @Test
    void returnTodoListById_whenFindTodoListByIdValid() throws ResourceNotFoundException {
        Integer todoListIdToFound = todoLists.get(0).getId();
        when(todoListRepository.findById(todoListIdToFound))
                .thenReturn(java.util.Optional.ofNullable(todoLists.get(0)));

        TodoList todoListFounded = findTodoList.byId(todoListIdToFound);
        assertNotNull(todoListFounded);
        assertEquals(todoListIdToFound, todoListFounded.getId());
    }

    @Test
    void throwResourceNotFoundException_whenFindTodoListByIdInvalid() throws ResourceNotFoundException {
        when(todoListRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(null));

        assertThrows(ResourceNotFoundException.class,
                () -> findTodoList.byId(0));
    }
}