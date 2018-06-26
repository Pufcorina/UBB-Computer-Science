package ro.ubb.core.repository;

import ro.ubb.core.domain.Movie;

import java.util.List;
import java.util.Optional;

public interface CustomMovieRepository {
    List<Movie> findAllWithRentalsAndClients();
    Optional<Movie> findByIdWithRentalsAndClients(Long movieId);
}
