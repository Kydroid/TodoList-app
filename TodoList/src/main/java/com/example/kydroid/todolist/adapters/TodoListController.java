package com.example.kydroid.todolist.adapters;

import com.example.kydroid.todolist.domain.common.ResourceCreationConflictException;
import com.example.kydroid.todolist.domain.common.ResourceCreationFailedException;
import com.example.kydroid.todolist.domain.common.ResourceNotFoundException;
import com.example.kydroid.todolist.domain.todolist.TodoItem;
import com.example.kydroid.todolist.domain.todolist.TodoList;
import com.example.kydroid.todolist.ports.input.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Api(tags = "API TodoList v1.0")
@CrossOrigin
@RestController
@RequestMapping("todolists")
public class TodoListController {

    private final CreateTodoList createTodoList;
    private final FindTodoList findTodoList;
    private final UpdateTodoList updateTodoList;
    private final DeleteTodoList deleteTodoList;
    private final FindTodoItem findTodoItem;
    private final CreateTodoItem createTodoItem;

    @Autowired
    public TodoListController(CreateTodoList createTodoList, FindTodoList findTodoList, UpdateTodoList updateTodoList,
                              DeleteTodoList deleteTodoList, FindTodoItem findTodoItem, CreateTodoItem createTodoItem) {
        this.createTodoList = createTodoList;
        this.findTodoList = findTodoList;
        this.updateTodoList = updateTodoList;
        this.deleteTodoList = deleteTodoList;
        this.createTodoItem = createTodoItem;
        this.findTodoItem = findTodoItem;
    }

    @GetMapping
    public ResponseEntity<List<TodoList>> getAllTodoLists() {
        List<TodoList> todoListsFounded = findTodoList.all();
        return ResponseEntity.ok(todoListsFounded);
    }

    @GetMapping("{todoListId}")
    public ResponseEntity<TodoList> getTodoList(@PathVariable("todoListId") Integer todoListId)
            throws ResourceNotFoundException {
        TodoList todoListFounded = findTodoList.byId(todoListId);
        return ResponseEntity.ok(todoListFounded);
    }

    @PostMapping
    public ResponseEntity<TodoList> addTodoList(@RequestBody TodoList todoList)
            throws ResourceCreationConflictException, ResourceCreationFailedException {

        TodoList todoListAdded = createTodoList.add(todoList);

        URI locationTodoListAdded = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(todoListAdded.getId())
                .toUri();

        return ResponseEntity.created(locationTodoListAdded).body(todoListAdded);
    }

    @PutMapping("{todoListId}")
    public ResponseEntity<TodoList> updateTodoList(@PathVariable("todoListId") Integer todoListId,
                                                   @RequestBody TodoList todoList)
            throws ResourceNotFoundException {
        todoList.setId(todoListId);
        TodoList todoListUpdated = updateTodoList.by(todoList);
        return ResponseEntity.ok(todoListUpdated);
    }

    @DeleteMapping("{todoListId}")
    public ResponseEntity deleteTodoList(@PathVariable("todoListId") Integer todoListId)
            throws ResourceNotFoundException {
        deleteTodoList.byId(todoListId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("{todoListId}/todoitems")
    public ResponseEntity<List<TodoItem>> getTodoItemsByTodoList(@PathVariable("todoListId") Integer todoListId) {
        List<TodoItem> todoItemsByTodoList = findTodoItem.byTodoListId(todoListId);
        return ResponseEntity.ok(todoItemsByTodoList);
    }

    @PostMapping("{todoListId}")
    public ResponseEntity<TodoItem> addTodoItemOnTodoList(@RequestBody TodoItem todoItem,
                                                          @PathVariable("todoListId") Integer todoListId)
            throws ResourceCreationConflictException, ResourceCreationFailedException, ResourceNotFoundException {

        TodoItem todoItemAdded = createTodoItem.add(todoItem, todoListId);

        URI locationTodoItemAdded = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/v1.0/todoitems/{id}")
                .buildAndExpand(todoItemAdded.getId())
                .toUri();

        return ResponseEntity.created(locationTodoItemAdded).body(todoItemAdded);
    }
}
