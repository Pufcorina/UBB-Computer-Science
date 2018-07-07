package ro.ubb.lab7.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.lab7.core.domain.Client;
import ro.ubb.lab7.core.domain.Movie;
import ro.ubb.lab7.web.dto.MovieDto;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto> {
    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        throw new RuntimeException("Not yet implemented!");
        /*
        Movie m = new Movie(
                dto.getTitle(),
                dto.getDirector(),
                dto.getGenre(),
                dto.getYear(),
                dto.getDuration()
        );
        m.setId(dto.getId());
        return m;
        */
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        if(movie.getRentals() == null)
            movie.setRentals(new HashSet<>());

        MovieDto m = new MovieDto(
                movie.getTitle(),
                movie.getDirector(),
                movie.getGenre(),
                movie.getYear(),
                movie.getDuration(),
                movie.getClients().stream().map(Client::getId).collect(Collectors.toSet())
        );
        m.setId(movie.getId());
        return m;
    }
}
