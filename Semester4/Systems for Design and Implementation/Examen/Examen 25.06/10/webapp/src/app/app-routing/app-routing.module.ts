import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonComponent } from '../person/person.component';
import { BookComponent } from '../book/book.component';

const routes: Routes = [
  { path: 'persons', component: PersonComponent}
  { path: 'detail/:pid', component: BookComponent}
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
