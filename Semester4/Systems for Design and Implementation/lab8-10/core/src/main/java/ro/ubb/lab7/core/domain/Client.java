package ro.ubb.lab7.core.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "clientWithRentalsWithMovies",
                attributeNodes = @NamedAttributeNode(value = "rentals", subgraph = "rentalWithMovie"),
                subgraphs = @NamedSubgraph(name = "rentalWithMovie",
                        attributeNodes = @NamedAttributeNode(value = "movie")))
})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true, exclude = {"rentals"})
@EqualsAndHashCode(callSuper = true, exclude = {"rentals"})
public class Client extends BaseEntity<Long> {
    @NotEmpty
    private String name;
    @Length(max = 12, min = 3)
    private String phoneNumber;
    @Email
    private String email;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MovieRental> rentals = new HashSet<>();

    public Set<Movie> getMovies(){
        return Collections.unmodifiableSet(
                rentals.stream().map(MovieRental::getMovie).collect(Collectors.toSet())
        );
    }

    public void addMovie(Movie m){
        rentals.add(
                MovieRental
                        .builder()
                        .movie(m)
                        .client(this)
                        .build()
        );
    }
}
