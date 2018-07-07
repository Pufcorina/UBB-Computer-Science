package ro.ubb.lab7.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.ubb.lab7.core.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientJpaRepository extends IRepository<Client, Long> {

    @Query("select distinct c from Client c")
    @EntityGraph(value = "clientWithRentalsWithMovies", type = EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllWithRentalsAndMovies();

    @Query("select distinct c from Client c where c.id = :clientId")
    @EntityGraph(value = "clientWithRentalsWithMovies", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Client> findByIdWithRentalsWithAndMovies(@Param("clientId") Long clientId);
}
