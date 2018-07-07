package ro.ubb.lab7.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRentalsDto {
    private Set<MovieRentalDto> rentals;
}
