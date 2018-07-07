package ro.ubb.lab7.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.lab7.core.domain.MovieRental;
import ro.ubb.lab7.web.dto.MovieRentalDto;

@Component
public class MovieRentalConverter extends BaseConverter<MovieRental, MovieRentalDto> {
    @Override
    public MovieRental convertDtoToModel(MovieRentalDto dto) {
        throw new RuntimeException("Not yet implemented!");
        /*
        MovieRentalPK m = new MovieRentalPK(
                dto.getMovieId(),
                dto.getClientId()
        );
        m.setId(dto.getId());
        return m;
        */
    }

    @Override
    public MovieRentalDto convertModelToDto(MovieRental movieRental) {
        return new MovieRentalDto(
                movieRental.getMovie().getId(),
                movieRental.getClient().getId(),
                movieRental.getRating(),
                movieRental.getMovie().getTitle()
        );
    }
}
