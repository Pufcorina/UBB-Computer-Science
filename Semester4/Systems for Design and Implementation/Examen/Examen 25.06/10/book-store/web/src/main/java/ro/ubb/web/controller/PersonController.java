package ro.ubb.web.controller;

import org.springframework.web.bind.annotation.*;
import ro.ubb.core.domain.Author;
import ro.ubb.core.domain.Book;
import ro.ubb.web.converter.BookConverter;
import ro.ubb.web.converter.PersonConverter;
import ro.ubb.web.dto.BookDto;
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

    @Autowired
    private BookConverter bookConverter;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public Set<PersonDto> getAll(){

        log.info( "Retrieving all authors from service" );
        return personConverter.convertModelsToDtos(personService.findAll());
    }

    @RequestMapping(value = "/persons/books/{pid}", method = RequestMethod.GET)
    public Set<BookDto> getAllBooksByAuthor(@PathVariable Long pid){
        log.info( "Retrieving all books from service" );
        Optional<Author> authors = personService.findAll().stream()
                .filter( person -> person.getId().equals( pid ) ).findFirst();
        if (authors.isPresent())
            return bookConverter.convertModelsToDtos( authors.get().getBooks() );
        return new HashSet<>();
    }

    @RequestMapping(value = "/persons/books/{pid}/{bid}", method = RequestMethod.GET)
    public BookDto getBookByAuthor(@PathVariable Long pid, @PathVariable Long bid){
        log.info( "Retrieving all books from service" );
        Book book = personService.findBook( pid, bid );
        if (book != null)
            return bookConverter.convertModelToDto(book);
        return null;
    }
}
