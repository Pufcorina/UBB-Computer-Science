import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { MoviesComponent } from './movies/movies.component';
import {PrintMovieComponent} from './movies/print-movie/print-movie.component';
import {MovieService} from './movies/shared/movie.service';
import { NewMovieComponent } from './movies/new-movie/new-movie.component';
import { MovieDetailsComponent } from './movies/movie-details/movie-details.component';
import { ClientsComponent } from './clients/clients.component';
import { PrintClientsComponent } from './clients/print-clients/print-clients.component';
import {ClientService} from './clients/shared/client.service';
import { RentalsComponent } from './rentals/rentals.component';
import {RentalService} from './rentals/shared/rental.service';
import { ErrorComponent } from './error/error.component';

@NgModule({
  declarations: [
    AppComponent,
    PrintMovieComponent,
    MoviesComponent,
    NewMovieComponent,
    MovieDetailsComponent,
    ClientsComponent,
    PrintClientsComponent,
    RentalsComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    MovieService,
    RentalService,
    ClientService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
