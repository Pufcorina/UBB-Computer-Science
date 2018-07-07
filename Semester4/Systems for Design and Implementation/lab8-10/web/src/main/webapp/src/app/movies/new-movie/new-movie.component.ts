import { Component, OnInit } from '@angular/core';
import {Movie} from '../shared/movie.model';
import {MovieService} from '../shared/movie.service';
import {ErrorComponent} from '../../error/error.component';

@Component({
  selector: 'app-new-movie',
  templateUrl: './new-movie.component.html',
  styleUrls: ['./new-movie.component.css']
})
export class NewMovieComponent implements OnInit {
  str = 'Not submitted';
  movie: Movie = {
    id: 1,
    title: 'titlul',
    director: 'director',
    genre: 'genre',
    duration: 120,
    year: 1999,
    clients: []
  };

  constructor(public movieService: MovieService) { }

  ngOnInit() {
  }

  buttonClicked(): void {
    this.str = JSON.stringify(this.movie);
    this.movieService.addMovie(this.movie)
      .subscribe(movie => {
        this.movie = movie;
        this.str = 'Inserted into the database!' + JSON.stringify(this.movie);
      },
        error => ErrorComponent.addError(error)
      );
  }

}
