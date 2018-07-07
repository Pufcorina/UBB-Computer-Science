import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MoviesComponent} from './movies/movies.component';
import {NewMovieComponent} from './movies/new-movie/new-movie.component';
import {MovieDetailsComponent} from './movies/movie-details/movie-details.component';
import {ClientsComponent} from './clients/clients.component';
import {RentalsComponent} from './rentals/rentals.component';

const routes: Routes = [
  { path: 'movies', component: MoviesComponent },
  { path: 'movies/new', component: NewMovieComponent },
  { path: 'movies/details/:id', component: MovieDetailsComponent},
  { path: 'clients', component: ClientsComponent },
  { path: 'clients/manageRentals/:id', component: RentalsComponent }
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
