import {TodoList} from './todo-list';

export interface TodoItem {
  id?: number;
  name?: string;
  todoItemStatus?: string;
  todoList?: TodoList;
}
