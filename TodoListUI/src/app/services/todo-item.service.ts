import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {TodoItem} from '../entities/todo-item';
import {Observable} from 'rxjs';
import {TodoList} from '../entities/todo-list';

@Injectable({
  providedIn: 'root'
})
export class TodoItemService {

  constructor(private http: HttpClient) {
  }

  static readonly httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  private urlTodoItems = 'http://localhost:8080/api/v1.0/todoitems';
  private urlTodoLists = 'http://localhost:8080/api/v1.0/todolists';

  getTodoItemsByTodoList(todoList: TodoList): Observable<Array<TodoItem>> {
    return this.http.get<Array<TodoItem>>(this.urlTodoLists + '/' + todoList.id + '/todoitems');
  }

  addTodoItem(todoItem: TodoItem, todoList: TodoList): Observable<TodoItem> {
    return this.http.post<TodoItem>(this.urlTodoLists + '/' + todoList.id, todoItem);
  }

  updateTodoItem(todoItem: TodoItem): Observable<TodoItem> {
    return this.http.put<TodoItem>(this.urlTodoItems + '/' + todoItem.id, todoItem);
  }

  deleteTodoItem(todoItem: TodoItem): Observable<any> {
    return this.http.delete(this.urlTodoItems + '/' + todoItem.id, TodoItemService.httpOptions);
  }
}
