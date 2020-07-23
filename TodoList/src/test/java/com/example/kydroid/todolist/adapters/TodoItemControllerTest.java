package com.example.kydroid.todolist.adapters;

import com.example.kydroid.todolist.domain.todolist.TodoItem;
import com.example.kydroid.todolist.domain.todolist.TodoItemStatus;
import com.example.kydroid.todolist.domain.todolist.TodoList;
import com.example.kydroid.todolist.ports.output.TodoItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TodoItemControllerTest {

    String todoItemUrl = "/todoitems";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoItemRepository todoItemRepository;

    @Autowired
    private ObjectMapper mapperJson;

    @Test
    void returnTodoItemById_whenFindTodoItemByIdValid() throws Exception {
        TodoList todoList = new TodoList();
        todoList.setId(1);
        TodoItem todoItem1 = new TodoItem("item1", TodoItemStatus.OPEN, todoList);
        todoItem1.setId(1);

        when(todoItemRepository.findById(anyInt()))
                .thenReturn(java.util.Optional.ofNullable(todoItem1));

        mvc.perform(get(todoItemUrl + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(mapperJson.writeValueAsString(todoItem1)))
                .andReturn();
    }

    @Test
    void throwResourceNotFoundException_whenFindTodoItemByIdInvalid() throws Exception {
        mvc.perform(get(todoItemUrl + "/1"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
