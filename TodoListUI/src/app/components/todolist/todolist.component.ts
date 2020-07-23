import {Component, OnInit} from '@angular/core';
import {TodoList} from '../../entities/todo-list';
import {TodoListService} from '../../services/todo-list.service';

@Component({
  selector: 'app-todolist',
  templateUrl: './todolist.component.html',
  styleUrls: ['./todolist.component.css']
})
export class TodolistComponent implements OnInit {
  private _todoLists: Array<TodoList> = [];
  private _todoListCurrent: TodoList;
  private _todoListSelected: TodoList;

  constructor(private todoListService: TodoListService) {
  }

  ngOnInit(): void {
    this.resetTodoListCurrent();
    this.loadTodoLists();
  }

  private resetTodoListCurrent(): void {
    this._todoListCurrent = {};
  }

  private loadTodoLists(): void {
    this.todoListService.getTodoLists()
        .subscribe(todoListsPersisted => {
          this._todoLists = todoListsPersisted;
        });
  }

  submitTodoList(): void {
    if (this.todoListCurrent.id > 0) {
      this.updateTodoList();
    } else {
      this.addTodoList();
    }
  }

  private toUpdateTodoList(todoListToUpdate: TodoList): void {
    this._todoListCurrent = Object.assign({}, todoListToUpdate);
  }

  private addTodoList(): void {
    this.todoListService.addTodoList(this.todoListCurrent)
        .subscribe((todoListCreated) => {
          this.todoLists.push(todoListCreated);
          this.resetTodoListCurrent();
        });
  }

  private updateTodoList(): void {

    this.todoListService.updateTodoList(this.todoListCurrent)
        .subscribe(todoListUpdated => {
          const indexOfTodoListUpdated = this.todoLists.findIndex(todoList => todoList.id === todoListUpdated.id);
          if (indexOfTodoListUpdated >= 0) {
            this.todoLists.splice(indexOfTodoListUpdated, 1, todoListUpdated);
          }
          this.resetTodoListCurrent();
        });
  }

  private deleteTodoList(todoListToDelete: TodoList): void {

    if (!confirm('Are you sure to delete the "' + todoListToDelete.name + '" todolist ?')) {
      return;
    }

    this.todoListService.deleteTodoList(todoListToDelete)
        .subscribe(() => {
          const indexOfTodoListDeleted = this.todoLists.indexOf(todoListToDelete);
          if (indexOfTodoListDeleted >= 0) {
            this.todoLists.splice(indexOfTodoListDeleted, 1);
            if (todoListToDelete.id === this._todoListSelected.id) {
              this._todoListSelected = null;
            }
          }
        });
  }

  private selectTodoList(todolistSelected: TodoList): void {
    this._todoListSelected = todolistSelected;
  }

  get todoLists(): Array<TodoList> {
    return this._todoLists;
  }

  get todoListCurrent(): TodoList {
    return this._todoListCurrent;
  }

  get todoListSelected(): TodoList {
    return this._todoListSelected;
  }
}
