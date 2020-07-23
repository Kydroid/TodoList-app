import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {TodoList} from '../entities/todo-list';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TodoListService {

  constructor(private http: HttpClient) {
  }

  static readonly httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  private urlTodoLists = 'http://localhost:8080/api/v1.0/todolists';

  getTodoLists(): Observable<Array<TodoList>> {
    return this.http.get<Array<TodoList>>(this.urlTodoLists);
  }

  addTodoList(todoList: TodoList): Observable<TodoList> {
    return this.http.post<TodoList>(this.urlTodoLists, todoList);
  }

  updateTodoList(todoList: TodoList): Observable<TodoList> {
    return this.http.put<TodoList>(this.urlTodoLists + '/' + todoList.id, todoList);
  }

  deleteTodoList(todoList: TodoList): Observable<any> {
    return this.http.delete(this.urlTodoLists + '/' + todoList.id, TodoListService.httpOptions);
  }
}
