package ro.ubb.web.controller;

import org.springframework.web.bind.annotation.*;
import ro.ubb.core.domain.Person;
import ro.ubb.web.converter.PersonConverter;
import ro.ubb.web.dto.PersonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.core.service.PersonService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
public class PersonController {
    private static final Logger log = LoggerFactory.getLogger( PersonController.class );

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonConverter personConverter;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public Set<PersonDto> getAll(){

        log.info( "Retrieving all persons from service" );
        return personConverter.convertModelsToDtos(personService.findAll());
    }

    @RequestMapping(value = "/persons/add", method = RequestMethod.POST)
    public PersonDto savePerson(@RequestBody final PersonDto personDto){
        log.trace( "savePerson --- method entered personDto {}", personDto );
        Person person = Person.builder()
                .name( personDto.getName() )
                .ssn( personDto.getSsn() )
                .build();

        Person p = personService.addPerson(person);

        PersonDto result = personConverter.convertModelToDto( p );


        log.trace( "savePerson --- method exit personDto {}", result );
        return result;
    }
}

