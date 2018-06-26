package ro.ubb.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.core.domain.Author;
import ro.ubb.web.dto.AuthorDto;
import ro.ubb.web.dto.BookDto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthorConverter implements Converter<Author, AuthorDto> {
    private static final Logger log = LoggerFactory.getLogger(AuthorConverter.class);


    @Override
    public AuthorDto convertModelToDto(Author author) {
        log.info("Converting author" + author + " to dto");
        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .ssn(author.getSsn())
                .publisher(author.getPublisher())
                .books(author.getBooks()
                        .stream()
                        .map(book -> new BookDto().builder()
                                .authorId(author.getId())
                                .title(book.getTitle())
                                .year(book.getYear())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Set<AuthorDto> convertModelsToDtos(Collection<Author> authors) {
        log.info("Converting authors " + authors + " to dtos");
        return authors.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toSet());
    }
}

