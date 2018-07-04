package ro.ubb.core.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "book")
public class Book extends BaseEntity<Long> implements Serializable {
    private String title;
    private Integer year;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private Author author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(year, book.year);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, year);
    }
}