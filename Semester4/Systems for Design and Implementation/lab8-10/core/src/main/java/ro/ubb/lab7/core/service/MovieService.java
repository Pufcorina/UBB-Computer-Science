package ro.ubb.lab7.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.lab7.core.domain.Movie;
import ro.ubb.lab7.core.repository.MovieJpaRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MovieJpaRepository movies;

    public Movie addMovie(@Valid Movie m) {
        String title = m.getTitle();
        String director = m.getDirector();
        String genre = m.getGenre();
        Integer year = m.getYear();
        Integer duration = m.getDuration();
        m = Movie.builder().title(title).director(director).genre(genre).year(year).duration(duration).build();
        LOG.trace("addMovie --- method entered - movie {}", m);
        m = movies.save(m);
        LOG.trace("addMovie --- method exit movie {}", m);
        return m;
    }

    @Transactional
    public Movie updateMovie(@Valid Movie movie){
        Long movieId = movie.getId();
        String title = movie.getTitle();
        String director = movie.getDirector();
        String genre = movie.getGenre();
        Integer year = movie.getYear();
        Integer duration = movie.getDuration();
        LOG.trace("updateMovie --- method entered - movieId {}, title {}, director {}, genre {}, year {}, duration {}",
                movieId, title, director, genre, year, duration);
        Optional<Movie> mov = movies.findById(movieId);
        mov.ifPresent(m ->{
            m.setTitle(title);
            m.setDirector(director);
            m.setGenre(genre);
            m.setYear(year);
            m.setDuration(duration);
        });
        LOG.trace("updateMovie --- method exit movie {}", mov.get());
        return mov.orElse(null);
    }

    public void deleteMovie(Long id){
        LOG.trace("deleteMovie --- method entered - id {}", id);
        movies.deleteById(id);
        LOG.trace("deleteMovie --- method exit");
    }

    public List<Movie> getAllMovies(){
        LOG.trace("getAllMovies --- method entered");
        List<Movie> m = movies.findAllWithRentalsAndClients();
        LOG.trace("getAllMovies --- method exit result {}", m);
        return m;
    }

    public Movie getMovie(Long movieId){
        LOG.trace("getMovie --- method entered movieId={}", movieId);
        Optional<Movie> m = movies.findByIdWithRentalsAndClients(movieId);
        LOG.trace("getMovie --- method exit movie={}", m);
        return m.orElse(null);
    }

}
