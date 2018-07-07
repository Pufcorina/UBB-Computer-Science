import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Movie} from '../shared/movie.model';
import {MovieService} from '../shared/movie.service';
import {ErrorComponent} from '../../error/error.component';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {
  movie: Movie;

  constructor(
    private route: ActivatedRoute,
    private movieService: MovieService
  ) { }

  ngOnInit() {
    this.getMovie();
  }

  getMovie(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.movieService.getMovie(id)
      .subscribe(mv => this.movie = mv,
    error => ErrorComponent.addError(error)
      );
  }

  buttonClicked(): void {
    this.movieService.updateMovie(this.movie)
      .subscribe(mv => this.movie = mv,
        error => ErrorComponent.addError(error)
        );
  }

}
