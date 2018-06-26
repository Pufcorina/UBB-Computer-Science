package ro.ubb.core.domain;

import lombok.*;
import org.springframework.stereotype.Component;
import ro.ubb.core.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;
    @Column(name = "ssn", unique = true)
    private String ssn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(ssn, person.ssn);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, ssn);
    }
}
