package repository;

import domain.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CustomPersonRepositoryImpl extends CustomRepositorySupport implements CustomPersonRepository{
    @Override
    @Transactional
    public List<Person> findAllPersonsNameContaining(String text) {
        return null;
    }

    @Override
    public List<Person> getAll() {
        return null;
    }
}
