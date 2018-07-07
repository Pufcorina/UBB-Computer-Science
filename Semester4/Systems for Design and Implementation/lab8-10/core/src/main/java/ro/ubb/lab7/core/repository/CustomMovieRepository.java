package ro.ubb.lab7.core.repository;

import ro.ubb.lab7.core.domain.Movie;

import java.util.List;
import java.util.Optional;

public interface CustomMovieRepository {
    List<Movie> findAllWithRentalsAndClients();
    Optional<Movie> findByIdWithRentalsAndClients(Long movieId);
}
