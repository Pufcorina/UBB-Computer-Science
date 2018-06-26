package ro.ubb.core.repository;

import ro.ubb.core.domain.Movie;

public interface MovieJpaRepository extends IRepository<Movie, Long>, CustomMovieRepository {
}
