import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import {catchError, tap, map} from "rxjs/internal/operators";
import { Person } from './/person.module';

@Injectable()
export class PersonService {
  path = 'http://localhost:8080/api/persons';

  constructor(public httpClient: HttpClient) { }

  getPersonsByName(name): Observable<Array<Person>> {
    console.log('getPersonsByName() enter ' + name);
    const x = this.path + '/' + name;
    const a = this.httpClient.get<Array<Person>>(x);
    console.log('getPersonsByName() exit ' + a);
    return a;
  }

  getPersons(): Observable<Array<Person>> {
    return this.httpClient.get<Array<Person>>(this.path);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string) {
    this.messageService.add('PizzaService: ' + message);
  }

  addPerson(person: Person): Observable<Person> {
    return this.httpClient.post<Person>(this.path + "/add", person).pipe(
      tap((person: Person) => this.log(`added person`)),
      catchError(this.handleError<Person>('addPerson'))
    );
  }

  checkUnique(ssn: string): Observable<String> {
      return this.httpClient
        .get<String>(this.path + '/ssn/' + ssn);
    }
}
