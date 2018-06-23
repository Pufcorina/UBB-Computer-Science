package ro.ubb.core.domain;

import lombok.*;

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
