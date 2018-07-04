package ro.ubb.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.core.domain.Book;
import ro.ubb.web.dto.BookDto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookConverter implements Converter<Book, BookDto> {
    private static final Logger log = LoggerFactory.getLogger(BookConverter.class);


    @Override
    public BookDto convertModelToDto(Book book) {
        log.info("Converting book" + book + " to dto");
        return BookDto.builder()
                .authorId(book.getAuthor().getId())
                .title(book.getTitle())
                .year(book.getYear())
                .build();
    }

    @Override
    public Set<BookDto> convertModelsToDtos(Collection<Book> books) {
        log.info("Converting books " + books + " to dtos");
        return books.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toSet());
    }
}
