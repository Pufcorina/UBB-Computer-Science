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

  savePerson(person): Observable<Person> {
    return this.httpClient.post<Person>(this.path + '/add', person);
  }
}
