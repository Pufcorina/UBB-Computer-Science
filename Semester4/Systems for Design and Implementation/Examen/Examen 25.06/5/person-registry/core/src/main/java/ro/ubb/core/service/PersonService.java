package ro.ubb.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.core.domain.Person;
import ro.ubb.core.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    private static final Logger log = LoggerFactory.getLogger( PersonService.class );

    public List<Person> findAll(){
        return personRepository.findAll();
    }


    public Person addPerson(Person person) {
        return personRepository.save( person );
    }
}

