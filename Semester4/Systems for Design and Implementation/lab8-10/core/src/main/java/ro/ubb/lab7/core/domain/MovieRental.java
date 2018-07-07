package ro.ubb.lab7.core.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Table(name = "MovieRentals")
@IdClass(MovieRentalPK.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"movie", "client"})
public class MovieRental implements Serializable{
    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId")
    private Movie movie;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "clientId")
    private Client client;

    @Column(name = "rating")
    @Max(10)
    @Min(0)
    private Integer rating;
}
