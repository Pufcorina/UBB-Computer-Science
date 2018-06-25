import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
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
}
