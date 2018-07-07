import { Component, OnInit } from '@angular/core';
import {MovieService} from '../shared/movie.service';
import {Movie} from '../shared/movie.model';
import {ErrorComponent} from '../../error/error.component';

@Component({
  selector: 'app-print-movie',
  templateUrl: './print-movie.component.html',
  styleUrls: ['./print-movie.component.css']
})
export class PrintMovieComponent implements OnInit {
  movies: Array<Movie>;
  selectedMovie: Movie;

  constructor(public movieService: MovieService) { }

  ngOnInit() {
    this.getMovies();
  }

  getMovies(): void {
    this.movieService.getMovies()
      .subscribe(movies => this.movies = movies,
        error => ErrorComponent.addError(error)
      );
  }

  removeMovie(movie): void {
    this.movieService.removeMovie(movie)
      .subscribe(m => {
        this.movies = this.movies.filter(mov => mov.id !== movie.id);
      },
    error => ErrorComponent.addError(error)
      );
  }

  onSelect(movie): void {
    this.selectedMovie = movie;
  }

}
