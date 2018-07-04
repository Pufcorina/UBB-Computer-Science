package ro.ubb.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.ubb.core.domain.Author;
import ro.ubb.web.dto.BookDto;
import ro.ubb.web.dto.PersonDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PersonConverter implements Converter<Author, PersonDto> {
    private static final Logger log = LoggerFactory.getLogger( PersonConverter.class );
    @Override
    public PersonDto convertModelToDto(Author person) {
        log.info( "Converting person" + person + " to dto");
        return PersonDto.builder()
                .id( person.getId() )
                .name( person.getName() )
                .publisher( person.getPublisher() )
                .books( person.getBooks()
                        .stream()
                        .map( book -> new BookDto().builder()
                                .authorId( person.getId() )
                                .title( book.getTitle() )
                                .year( book.getYear() )
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Set<PersonDto> convertModelsToDtos(Collection<Author> persons) {
        log.info( "Converting persons" + persons + " to dtos");
        return persons.stream().map( this::convertModelToDto )
                .collect( Collectors.toSet() );
    }
}
