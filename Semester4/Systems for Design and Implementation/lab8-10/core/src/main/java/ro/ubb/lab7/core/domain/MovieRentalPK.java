package ro.ubb.lab7.core.domain;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
class MovieRentalPK implements Serializable{
    private Movie movie;
    private Client client;
}
