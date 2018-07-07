package ro.ubb.lab7.core.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true, exclude = {"rentals"})
@EqualsAndHashCode(callSuper = true, exclude = {"rentals"})
public class Movie extends BaseEntity<Long> {
    @NotEmpty
    private String title;
    @NotEmpty
    private String director;
    @NotEmpty
    private String genre;
    @Max(3000)
    private Integer year;
    @Max(999)
    private Integer duration;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MovieRental> rentals = new HashSet<>();

    public Set<Client> getClients() {
        return Collections.unmodifiableSet(rentals.stream().
                map(MovieRental::getClient).
                collect(Collectors.toSet()));
    }

    public void addClient(Client c) {
        rentals.add(
                MovieRental.builder().
                        client(c).
                        movie(this).
                        build()
        );
    }
}
