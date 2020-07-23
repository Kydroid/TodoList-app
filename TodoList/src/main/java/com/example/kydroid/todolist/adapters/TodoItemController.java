package com.example.kydroid.todolist.adapters;

import com.example.kydroid.todolist.domain.common.ResourceInvalidException;
import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoItem;
import com.example.kydroid.todolist.ports.input.DeleteTodoItem;
import com.example.kydroid.todolist.ports.input.FindTodoItem;
import com.example.kydroid.todolist.ports.input.UpdateTodoItem;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "API TodoItem v1.0")
@CrossOrigin
@RestController
@RequestMapping("todoitems")
public class TodoItemController {

    private final FindTodoItem findTodoItem;
    private final UpdateTodoItem updateTodoItem;
    private final DeleteTodoItem deleteTodoItem;

    @Autowired
    public TodoItemController(FindTodoItem findTodoItem, UpdateTodoItem updateTodoItem, DeleteTodoItem deleteTodoItem) {
        this.findTodoItem = findTodoItem;
        this.updateTodoItem = updateTodoItem;
        this.deleteTodoItem = deleteTodoItem;
    }

    @GetMapping("{todoItemId}")
    public ResponseEntity<TodoItem> getTodoItem(@PathVariable("todoItemId") Integer todoItemId)
            throws ResourceNotFoundException {
        TodoItem todoItemFounded = findTodoItem.byId(todoItemId);
        return ResponseEntity.ok(todoItemFounded);
    }

    @PutMapping("{todoItemId}")
    public ResponseEntity<TodoItem> updateTodoItem(@PathVariable("todoItemId") Integer todoItemId,
                                                   @RequestBody TodoItem todoItem)
            throws ResourceNotFoundException, ResourceInvalidException {
        todoItem.setId(todoItemId);
        TodoItem todoItemUpdated = updateTodoItem.by(todoItem);
        return ResponseEntity.ok(todoItemUpdated);
    }

    @DeleteMapping("{todoItemId}")
    public ResponseEntity<TodoItem> deleteTodoItem(@PathVariable("todoItemId") Integer todoItemId)
            throws ResourceNotFoundException {
        deleteTodoItem.byId(todoItemId);
        return ResponseEntity.noContent().build();
    }

}
