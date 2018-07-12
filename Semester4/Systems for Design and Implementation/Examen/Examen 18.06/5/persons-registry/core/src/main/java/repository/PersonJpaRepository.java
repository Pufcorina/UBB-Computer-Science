package repository;

import domain.Person;

public interface PersonJpaRepository extends IRepository<Person, Long>, CustomPersonRepository{
}
