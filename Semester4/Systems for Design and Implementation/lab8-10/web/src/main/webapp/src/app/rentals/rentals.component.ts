import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ClientService} from '../clients/shared/client.service';
import {RentalService} from './shared/rental.service';
import {MovieService} from '../movies/shared/movie.service';
import {Movie} from '../movies/shared/movie.model';
import {ErrorComponent} from '../error/error.component';

@Component({
  selector: 'app-rentals',
  templateUrl: './rentals.component.html',
  styleUrls: ['./rentals.component.css']
})
export class RentalsComponent implements OnInit {
  id: number;
  rentals: Rental[];
  private movies: Array<Movie>;
  private selectedRental: Rental;

  constructor(
    private route: ActivatedRoute,
    public rentalService: RentalService,
    public movieService: MovieService
  ) { }

  ngOnInit() {
    this.getClient();
    this.getMovies();
  }

  getClient(): void {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.rentalService.getRentalsForAClient(this.id)
      .subscribe(rentals => this.rentals = rentals,
    error => ErrorComponent.addError(error)
      );
  }

  getMovies(): void {
    this.movieService.getMovies()
      .subscribe(movies => this.movies = movies,
        error => ErrorComponent.addError(error)
        );
  }

  onAllMovieClick(movie): void {
    this.rentalService.addRental(this.id, movie.id)
      .subscribe(a => {
        this.getClient();
        console.log(a);
      },
        error => ErrorComponent.addError(error)
      );
  }

  onMyMovieClick(rental): void {
    this.selectedRental = rental;
  }

  submitRating(rental): void {
    this.rentalService.updateRental(rental)
      .subscribe(x => {},
        error => ErrorComponent.addError(error)
        );
  }

}
