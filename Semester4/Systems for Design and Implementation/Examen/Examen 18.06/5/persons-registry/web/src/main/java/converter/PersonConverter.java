package converter;

import domain.Person;
import dto.PersonDto;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter extends BaseConverter<Person, PersonDto> {

    @Override
    public Person convertDtoToModel(PersonDto personDto) {
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public PersonDto convertModelToDto(Person person) {
        PersonDto p = new PersonDto(
                person.getName(),
                person.getSsn()
        );
        p.setId(person.getId());
        return p;
    }
}
