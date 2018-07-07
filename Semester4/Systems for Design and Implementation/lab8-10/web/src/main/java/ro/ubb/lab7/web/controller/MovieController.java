package ro.ubb.lab7.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.lab7.core.domain.Movie;
import ro.ubb.lab7.core.service.MovieService;
import ro.ubb.lab7.web.converter.MovieConverter;
import ro.ubb.lab7.web.dto.EmptyJsonResponse;
import ro.ubb.lab7.web.dto.MovieDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieConverter movieConverter;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public List<MovieDto> getAllMovies() {
        log.trace("getAllMovies() --- method entered");
        List<Movie> movies = movieService.getAllMovies();
        log.trace("getAllMovies() --- method exit movies{}", movies);
        return new ArrayList<MovieDto>(movieConverter.convertModelsToDtos(movies));
    }
    @RequestMapping(value = "/movies/{movieId}", method = RequestMethod.GET)
    public MovieDto getMovie(
            @PathVariable final Long movieId
    ) {
        log.trace("getMovie() --- method entered movieId={}", movieId);
        Movie movie = movieService.getMovie(movieId);
        MovieDto result = movieConverter.convertModelToDto(movie);
        log.trace("getMovie() --- method exit result={}", result);
        return result;
    }

    @RequestMapping(value = "/movies/{movieId}", method = RequestMethod.PUT)
    public MovieDto updateMovie(
            @PathVariable final Long movieId,
            @RequestBody final MovieDto movieDto) {
        log.trace("updateMovie --- method entered movieId {}, movieDto {}", movieId, movieDto);
        movieDto.setId(movieId);
        Movie movie = Movie.builder().title(movieDto.getTitle())
                .director(movieDto.getDirector())
                .genre(movieDto.getGenre())
                .year(movieDto.getYear())
                .duration(movieDto.getDuration())
                .build();
        movie.setId(movieId);
        Movie m = movieService.updateMovie(
                movie
        );
        MovieDto result = movieConverter.convertModelToDto(m);
        log.trace("updateMovie --- method exit result {}", result);

        return result;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public MovieDto saveMovie(
            @RequestBody final MovieDto movieDto) {
        log.trace("saveMovie --- method entered movieDto {}", movieDto);
        Movie movie = Movie.builder().title(movieDto.getTitle())
                .director(movieDto.getDirector())
                .genre(movieDto.getGenre())
                .year(movieDto.getYear())
                .duration(movieDto.getDuration())
                .build();
        Movie m = movieService.addMovie(
                movie
        );
        System.out.println(m);
        System.out.println(m.getRentals());
        MovieDto result = movieConverter.convertModelToDto(m);
        log.trace("saveMovie --- method exit result {}", result);
        return result;
    }

    @RequestMapping(value = "movies/{movieId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMovie(
            @PathVariable final Long movieId){
        log.trace("deleteMovie --- method entered movieId {}", movieId);
        movieService.deleteMovie(movieId);
        log.trace("deleteMovie --- method exit");
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}





