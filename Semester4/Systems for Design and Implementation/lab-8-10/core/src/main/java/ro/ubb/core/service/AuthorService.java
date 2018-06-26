package ro.ubb.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.core.domain.Author;
import ro.ubb.core.domain.Book;
import ro.ubb.core.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    private static final Logger log = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAll() {
        log.info("Retrieving all authors");
        return new ArrayList<>(authorRepository.getAuthorsWithBooks());
    }

    public List<Book> findBooksByAuthorAndYear(Long authorId, Integer year) {
        log.info("Filtering books by authorId="+authorId+" and year="+year);
        List<Author> authors;
        if(authorId == -1)
            authors = this.getAll();
        else
            authors = this.getAll().stream().filter(author -> author.getId().equals(authorId)).collect(Collectors.toList());
        List<Book> books = new ArrayList<>();
        for (Author a : authors)
            for (Book b : a.getBooks())
                books.add(b);
        if (year == -1)
            return books;
        return books.stream().filter(book -> book.getYear().equals(year)).collect(Collectors.toList());
    }

    public Author get(Long id) {
        log.info("Retrieving author with id: " + id);
        Optional<Author> authorOptional = authorRepository.findById(id);

        return authorOptional.orElse(null);
    }

    public Optional<Author> optionalGet(Long id) {
        log.trace("Retrieving optional author with id: " + id);
        return authorRepository.findById(id);
    }

    public Author addOrUpdate(Author author) {
        log.trace("Trying to add or update author " + author);
        return authorRepository.save(author);
    }

    public void delete(Author author) {
        log.info("Deleting author" + author);
        authorRepository.delete(author);
    }

    public void delete(Long id) {
        log.info("Deleting author with id: " + id);
        authorRepository.deleteById(id);
    }
}


