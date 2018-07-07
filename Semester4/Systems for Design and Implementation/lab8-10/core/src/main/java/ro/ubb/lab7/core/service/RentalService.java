package ro.ubb.lab7.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.lab7.core.domain.Client;
import ro.ubb.lab7.core.domain.Movie;
import ro.ubb.lab7.core.domain.MovieRental;
import ro.ubb.lab7.core.repository.ClientJpaRepository;
import ro.ubb.lab7.core.repository.MovieJpaRepository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private static final Logger LOG = LoggerFactory.getLogger(RentalService.class);

    @Autowired
    private MovieJpaRepository movies;
    @Autowired
    private ClientJpaRepository clients;


    @Transactional
    public void addRental(Long clientId, Long movieId){
        LOG.trace("addRental --- method entered! - movieId {}, clientId {}", movieId, clientId);
        Optional<Movie> movie = movies.findByIdWithRentalsAndClients(movieId);
        Optional<Client> client = clients.findByIdWithRentalsWithAndMovies(clientId);
        if(movie.isPresent() && client.isPresent()){
            LOG.trace("Rental added!");
            movie.get().addClient(client.get());
        }
        LOG.trace("addRental: method exit");
    }

    @Transactional
    public void deleteRental(Long clientId, Long movieId) {
        LOG.trace("deleteRental --- method entered! - movieId {}, clientId {}", movieId, clientId);
        movies.findByIdWithRentalsAndClients(movieId).ifPresent(movie ->
            movie.getRentals().stream()
                    .filter(rental -> rental.getClient().getId().equals(clientId))
                    .forEach(movieRental -> movie.getRentals().remove(movieRental))
        );
        LOG.trace("deleteRental: exit");
    }

    @Transactional
    public void updateRental(Long clientId, Long movieId, Integer rating){
        LOG.trace("updateRental --- method entered! - movieId {}, clientId {}, rating {}",
                movieId, clientId, rating);
        movies.findByIdWithRentalsAndClients(movieId)
                .ifPresent(m ->
                        m.getRentals()
                                .stream()
                                .filter(r -> r.getClient().getId().equals(clientId))
                                .forEach(r -> r.setRating(rating))
                );

        LOG.trace("updateRental --- method exit!");
    }


    public Set<MovieRental> getAllRentalsForAClient(Long clientId){
        LOG.trace("getAllRentalsForAClient --- method entered");
        Optional<Client> client = clients.findByIdWithRentalsWithAndMovies(clientId);
        Set<MovieRental> rentals = new HashSet<>();
        if(client.isPresent())
            rentals = client.get().getRentals();
        LOG.trace("getAllRentalsForAClient --- method exit, rentals {}", rentals);
        return rentals;
    }
/*
    public List<Movie> getAllRentalsOfAClient(Long id){
        LOG.trace("getAllRentalsOfAClient --- method entered");
        List<Movie> m = StreamSupport.stream(rentals.findAll().spliterator(), false)
                .filter(r->r.getClientID().equals(id))
                .map(r->movies.findById(r.getMovieID()))
                .filter(o->o.isPresent())
                .map(o->o.get())
                .collect(Collectors.toList());
        LOG.trace("getAllRentalsOfAClient: result{} ", m);
        return m;
    }


    public List<Client> getAllClientsOfRentedMovie(Long movieId) {
        LOG.trace("getAllClientsOfRentedMovie--- method entered");
        List<Client> m = StreamSupport.stream(rentals.findAll().spliterator(), false)
                .filter(r -> r.getMovieID().equals(movieId))
                .map(r -> clients.findById(r.getClientID()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        LOG.trace("getAllClientsOfRentedMovie: result{} ", m);
        return m;
    }

    public Movie getMostRentedMovie() {
        LOG.trace("getMostRentedMovie--- method entered");
        Movie m = movies.findById(
                StreamSupport.stream(rentals.findAll().spliterator(), false)
                .map(MovieRentalPK::getMovieID)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new))
                .keySet().stream().findFirst().get()
        ).get();
        LOG.trace("getMostRentedMovie: result{} ", m);
        return m;
    }
    */
}
