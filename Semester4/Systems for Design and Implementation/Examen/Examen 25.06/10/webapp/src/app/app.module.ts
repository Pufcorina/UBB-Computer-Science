import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpClientModule }    from '@angular/common/http';

import { AppComponent } from './app.component';
import { PersonComponent } from './person/person.component';
import { BookComponent } from './book/book.component';
import { PersonListComponent } from './person/person-list/person-list.component';
import { BookListComponent } from './book/book-list/book-list.component';
import { BookDetailComponent } from './book/book-detail/book-detail.component';

import { AppRoutingModule }     from './app-routing/app-routing.module';

import { PersonService } from './person/person.service';

@NgModule({
  declarations: [
    AppComponent,
    PersonComponent,
    BookComponent,
    PersonListComponent,
    BookListComponent,
    BookDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    PersonService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
