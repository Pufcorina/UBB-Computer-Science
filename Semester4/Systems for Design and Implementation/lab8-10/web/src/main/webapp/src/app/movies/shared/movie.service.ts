import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Movie} from './movie.model';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class MovieService {
  path = 'http://localhost:8080/api/movies';

  constructor(public httpClient: HttpClient) { }

  getMovie(movieId): Observable<Movie> {
    console.log('getMovie() enter ' + movieId);
    const x = this.path + '/' + movieId;
    console.log('getMovie() path ' + x);
    const a = this.httpClient.get<Movie>(x);
    console.log('getMovie() exit ' + a);
    return a;
  }

  getMovies(): Observable<Array<Movie>> {
    console.log('getMovies() enter ');
    const a = this.httpClient.get<Array<Movie>>(this.path);
    console.log('getMovies() exit ' + a);
    return a;
  }

  addMovie(movie): Observable<Movie> {
    console.log('addMovie entered' + movie);
    const a = this.httpClient.post<Movie>(this.path, movie);
    console.log('addMovie exit' + a);
    return a;
  }

  removeMovie(movie): Observable<Movie> {
    console.log('removeMovie enter ' + movie);
    const x = this.path + '/' + movie.id;
    const result = this.httpClient.delete<Movie>(x);
    console.log('removeMovie exit ' + x);
    return result;
  }

  updateMovie(movie): Observable<Movie> {
    console.log('updateMovie entered' + movie);
    const x = this.path + '/' + movie.id;
    const a = this.httpClient.put<Movie>(x, movie);
    console.log('updateMovie exit' + a);
    return a;
  }

}
