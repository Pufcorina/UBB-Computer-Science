package ro.ubb.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.core.domain.Author;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select distinct  a from Author a")
    @EntityGraph(value = "authorWithBooks", type = EntityGraph.EntityGraphType.LOAD)
    List<Author> getAuthorsWithBooks();
}

