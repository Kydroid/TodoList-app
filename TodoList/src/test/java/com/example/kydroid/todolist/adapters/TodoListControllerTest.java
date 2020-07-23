package com.example.kydroid.todolist.adapters;

import com.example.kydroid.todolist.domain.todolist.TodoList;
import com.example.kydroid.todolist.ports.output.TodoListRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TodoListControllerTest {

    String todolistUrl = "/todolists";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoListRepository todoListRepository;

    @Autowired
    private ObjectMapper mapperJson;

    @Test
    void returnTodoListById_whenFindTodoListByIdValid() throws Exception {

        TodoList todoList1 = new TodoList("list1", new ArrayList<>());
        todoList1.setId(1);

        when(todoListRepository.findById(anyInt()))
                .thenReturn(java.util.Optional.ofNullable(todoList1));

        mvc.perform(get(todolistUrl + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(mapperJson.writeValueAsString(todoList1)))
                .andReturn();
    }

    @Test
    void throwResourceNotFoundException_whenFindTodoListByIdInvalid() throws Exception {
        mvc.perform(get(todolistUrl + "/1"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}