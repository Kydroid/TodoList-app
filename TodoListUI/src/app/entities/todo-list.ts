import {TodoItem} from './todo-item';

export interface TodoList {
  id?: number;
  name?: string;
  todoItems?: Array<TodoItem>;
}
