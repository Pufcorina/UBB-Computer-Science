package controller;

import converter.PersonConverter;
import domain.Person;
import dto.PersonDto;
import dto.PersonsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.PersonService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {
    private static final Logger log =  LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonConverter personConverter;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<PersonDto> getAllPersons() {
        log.trace("getAllPersons() -- method entered");
        List<Person> persons = personService.getAllPersons();
        log.trace("getAllPersons() -- method exit clients {}", persons);
        return new ArrayList<>(new PersonsDto(personConverter.convertModelsToDtos(persons))
                .getPersons());
    }
}
