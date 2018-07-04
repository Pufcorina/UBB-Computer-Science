package ro.ubb.core.service;

import ro.ubb.core.domain.Author;
import ro.ubb.core.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.core.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    private static final Logger log = LoggerFactory.getLogger( PersonService.class );

    public List<Author> findAll(){
        return personRepository.getAuthorsWithBooks();
    }

    public Book findBook(Long personId, Long bookId){
        Optional<Author> authors = personRepository.getAuthorsWithBooks().stream()
                .filter( person -> person.getId().equals( personId ) ).findFirst();
        Optional<Book> theBook = authors.get().getBooks().stream()
                .filter( book -> book.getId().equals( bookId ) )
                .findFirst();
        return theBook.orElse( null );
    }

}
