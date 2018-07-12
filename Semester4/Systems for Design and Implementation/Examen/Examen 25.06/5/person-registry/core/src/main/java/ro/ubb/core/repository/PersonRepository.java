package ro.ubb.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.core.domain.Person;

@Repository
public interface PersonRepository extends IRepository<Person, Long>{
}
