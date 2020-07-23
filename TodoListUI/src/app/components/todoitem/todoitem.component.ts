import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {TodoItem} from '../../entities/todo-item';
import {TodoItemService} from '../../services/todo-item.service';
import {TodoList} from '../../entities/todo-list';

@Component({
  selector: 'app-todoitem',
  templateUrl: './todoitem.component.html',
  styleUrls: ['./todoitem.component.css']
})
export class TodoitemComponent implements OnInit, OnChanges {
  private _todoItems: Array<TodoItem> = [];
  private _todoItemCurrent: TodoItem;
  @Input() private _todoList: TodoList | null = null;

  constructor(private todoItemService: TodoItemService) {
  }

  ngOnInit(): void {
    this.resetTodoItemCurrent();
  }

  ngOnChanges(): void {
    this.loadTodoItemsByTodoList();
  }

  private resetTodoItemCurrent(): void {
    this._todoItemCurrent = {};
  }

  private loadTodoItemsByTodoList(): void {
    if (this.todoList) {
      this.todoItemService.getTodoItemsByTodoList(this.todoList)
          .subscribe(todoItemsPersisted => {
            this._todoItems = todoItemsPersisted;
          });
    }
  }

  submitTodoItem(): void {
    if (this._todoItemCurrent.id > 0) {
      this.updateTodoItem();
    } else {
      this.addTodoItem();
    }
  }

  private toUpdateTodoItem(todoItemToUpdate: TodoItem): void {
    this._todoItemCurrent = Object.assign({}, todoItemToUpdate);
  }

  private addTodoItem(): void {
    this.todoItemService.addTodoItem(this.todoItemCurrent, this.todoList)
        .subscribe(todoItemCreated => {
          this.todoItems.push(todoItemCreated);
          this.resetTodoItemCurrent();
        });
  }

  private updateTodoItem(): void {
    this.todoItemCurrent.todoList = this.todoList;
    this.todoItemService.updateTodoItem(this.todoItemCurrent)
        .subscribe(todoItemUpdated => {
          const indexOfTodoItemUpdated = this.todoItems.findIndex(todoItem => todoItem.id === todoItemUpdated.id);
          if (indexOfTodoItemUpdated >= 0) {
            this.todoItems.splice(indexOfTodoItemUpdated, 1, todoItemUpdated);
          }
          this.resetTodoItemCurrent();
        });
  }

  private deleteTodoItem(todoItemToDelete: TodoItem): void {
    this.todoItemService.deleteTodoItem(todoItemToDelete)
        .subscribe(() => {
          const indexOfTodoItemToDelete = this.todoItems.indexOf(todoItemToDelete);
          if (indexOfTodoItemToDelete >= 0) {
            this.todoItems.splice(indexOfTodoItemToDelete, 1);
          }
        });
  }

  get todoItems(): Array<TodoItem> {
    return this._todoItems;
  }

  get todoItemCurrent(): TodoItem {
    return this._todoItemCurrent;
  }

  get todoList(): TodoList {
    return this._todoList;
  }
}
