package ro.ubb.lab7.core.repository;

import ro.ubb.lab7.core.domain.Movie;

public interface MovieJpaRepository extends IRepository<Movie, Long>, CustomMovieRepository {
}
