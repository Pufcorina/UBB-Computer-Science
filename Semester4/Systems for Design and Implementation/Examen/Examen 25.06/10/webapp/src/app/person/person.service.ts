import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import {catchError, tap, map} from "rxjs/internal/operators";
import { Person } from './person-shared/person.module';

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  path = 'http://localhost:8080/api/persons';

  constructor(public httpClient: HttpClient) { }

  getPersons(): Observable<Array<Person>> {
    return this.httpClient.get<Array<Person>>(this.path);
  }

  getBooksByPid(pid): Observable<Array<Book>> {
    return this.httpClient.get<Array<Book>>(this.path + '/books/' + pid);
  }
}
