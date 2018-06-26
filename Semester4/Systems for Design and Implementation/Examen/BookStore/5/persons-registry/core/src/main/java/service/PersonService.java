package service;

import domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PersonJpaRepository;

import java.util.List;

@Service
public class PersonService {
    private static final Logger LOG = LoggerFactory.getLogger(PersonService.class);
    @Autowired
    private PersonJpaRepository persons;

    public List<Person> getAllPersons(){
        LOG.trace("getAllPersons --- method entered");
        List<Person> p = persons.getAll();
        LOG.trace("getAllPersons --- method exit result {}", p);
        return p;
    }
}
