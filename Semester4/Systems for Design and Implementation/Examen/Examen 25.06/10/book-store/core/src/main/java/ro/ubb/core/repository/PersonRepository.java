package ro.ubb.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.core.domain.Author;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Author, Long> {
    @Query("select distinct a from Author a")
    @EntityGraph(value = "authorWithBooks", type = EntityGraph.EntityGraphType.LOAD)
    List<Author> getAuthorsWithBooks();
}
