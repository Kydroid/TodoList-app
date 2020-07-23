import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TodolistComponent} from './components/todolist/todolist.component';

const routes: Routes = [
  {path: '', component: TodolistComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule {
}
