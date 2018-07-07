package ro.ubb.lab7.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.lab7.core.domain.Movie;
import ro.ubb.lab7.core.domain.MovieRental;
import ro.ubb.lab7.core.service.RentalService;
import ro.ubb.lab7.web.converter.MovieConverter;
import ro.ubb.lab7.web.converter.MovieRentalConverter;
import ro.ubb.lab7.web.dto.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
public class MovieRentalController {
    private static final Logger log = LoggerFactory.getLogger(MovieRentalController.class);

    @Autowired
    private RentalService rentalService;
    @Autowired
    private MovieRentalConverter movieRentalConverter;
    @Autowired
    private MovieConverter movieConverter;

    @RequestMapping(value = "/movieRentals/{clientId}", method = RequestMethod.GET)
    public Set<MovieRentalDto> getAllRentalsForAClient(
            @PathVariable final Long clientId
    ) {
        log.trace("getAllRentalsForAClient() --- method entered clientId {}", clientId);
        Set<MovieRental> rentals = rentalService.getAllRentalsForAClient(clientId);
        Set<MovieRentalDto> result = movieRentalConverter.convertModelsToDtos(rentals);
        log.trace("getAllRentalsForAClient() --- method exit result {}", result);
        return result;
    }
    @RequestMapping(value = "/movieRentals/{clientId}", method = RequestMethod.POST)
    public ResponseEntity addRental(
            @PathVariable final Long clientId,
            @RequestBody final Long movieId
    ) {
        log.trace("addRental() --- method entered clientId {}, movieId {}", clientId, movieId);
        rentalService.addRental(clientId, movieId);
        log.trace("addRental() --- method exit");
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

    @RequestMapping(value = "/movieRentals", method = RequestMethod.PUT)
    public ResponseEntity updateRental(
            @RequestBody @Valid final MovieRentalDto movieRental
    ) {
        log.trace("updateRental() --- method entered movieRental {}", movieRental);
        rentalService.updateRental(movieRental.getClientId(), movieRental.getMovieId(), movieRental.getRating());
        log.trace("updateRental() --- method exit");
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }


    /*
    @RequestMapping(value = "/movieRentals/{movieRentalId}", method = RequestMethod.PUT)
    public ResponseEntity updateMovieRental(
            @PathVariable final Long movieRentalId,
            @RequestBody final MovieRentalDto movieRentalDto) {
        movieRentalDto.setId(movieRentalId);
        rentalService.update(movieRentalConverter.convertDtoToModel(movieRentalDto));
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

    @RequestMapping(value = "/movieRentals", method = RequestMethod.POST)
    public ResponseEntity saveMovieRental(
            @RequestBody final MovieRentalDto movieRentalDto) {
        rentalService.addRental(movieRentalConverter.convertDtoToModel(movieRentalDto));
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

    @RequestMapping(value = "movieRentals/{movieRentalId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMovieRental(
            @PathVariable final Long movieRentalId){
        rentalService.deleteRental(movieRentalId);
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
    */
}
