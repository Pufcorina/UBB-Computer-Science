import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class RentalService {
  path = 'http://localhost:8080/api/movieRentals';

  constructor(public httpClient: HttpClient) { }

  getRentalsForAClient(clientId): Observable<Array<Rental>> {
    console.log('getRentalsForAClient() method entered -- clientId=' + clientId);
    const result = this.httpClient.get<Array<Rental>>(this.path + '/' + clientId);
    console.log('getRentalsForAClient() method exit -- result=' + result);
    return result;
  }

  addRental(clientId, movieId): Observable<Object> {
    console.log('addRental() method entered clientId=' + clientId + ', movieId=' + movieId);
    const o = this.httpClient.post(this.path + '/' + clientId, movieId);
    console.log('addRental() method exit');
    return o;
  }

  updateRental(rental): Observable<Object> {
    console.log('updateRental() method entered rental=' + rental);
    const o = this.httpClient.put(this.path, rental);
    console.log('updateRental() method exit ');
    return o;
  }
}
