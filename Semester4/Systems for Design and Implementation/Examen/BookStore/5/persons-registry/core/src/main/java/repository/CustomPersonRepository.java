package repository;

import domain.Person;

import java.util.List;

public interface CustomPersonRepository {
    List<Person> findAllPersonsNameContaining(String text);

    List<Person> getAll();
}
