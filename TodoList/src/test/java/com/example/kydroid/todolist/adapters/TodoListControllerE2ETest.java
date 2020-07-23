package com.example.kydroid.todolist.adapters;

import com.example.kydroid.todolist.domain.todolist.TodoList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoListControllerE2ETest {

    String todolistUrl = "/todolists";

    @Autowired
    private ObjectMapper mapperJson;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void returnTodoList_whenCreateTodoListValid() {
        TodoList todoList1 = new TodoList("list1", new ArrayList<>());

        HttpEntity<TodoList> productHttpEntity = new HttpEntity<>(todoList1);
        ResponseEntity<TodoList> responsePostProduct = restTemplate.postForEntity(todolistUrl, productHttpEntity, TodoList.class);
        assertEquals(HttpStatus.CREATED, responsePostProduct.getStatusCode());
        TodoList todoListCreated = responsePostProduct.getBody();
        assertNotNull(todoListCreated);
        assertNotNull(todoListCreated.getId());
        assertEquals(todoList1.getName(), todoListCreated.getName());
    }

    @Test
    void throwResourceCreationConflictException_whenCreateTodoListInvalid() {
        TodoList todoList1 = new TodoList("list1", new ArrayList<>());
        todoList1.setId(1);

        HttpEntity<TodoList> productHttpEntity = new HttpEntity<>(todoList1);
        ResponseEntity<TodoList> responsePostProduct = restTemplate.postForEntity(todolistUrl, productHttpEntity, TodoList.class);
        assertEquals(HttpStatus.CONFLICT, responsePostProduct.getStatusCode());
    }
}
