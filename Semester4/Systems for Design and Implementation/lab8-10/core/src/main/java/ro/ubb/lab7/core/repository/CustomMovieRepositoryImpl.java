package ro.ubb.lab7.core.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.lab7.core.domain.Client;
import ro.ubb.lab7.core.domain.Movie;
import ro.ubb.lab7.core.domain.Movie_;
import ro.ubb.lab7.core.domain.MovieRental;
import ro.ubb.lab7.core.domain.MovieRental_;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class CustomMovieRepositoryImpl extends CustomRepositorySupport implements CustomMovieRepository {
    //@Override
    public List<Movie> findAllWithRentalsAndClientsCriteria() {
        //criteria
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = criteriaBuilder.createQuery(Movie.class);
        query.distinct(Boolean.TRUE);
        Root<Movie> root = query.from(Movie.class);
        Fetch<Movie, MovieRental> movieRentalFetch = root.fetch(Movie_.rentals);
        movieRentalFetch.fetch(MovieRental_.client);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    @Transactional
    public List<Movie> findAllWithRentalsAndClients() {
        //sql
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        org.hibernate.Query query = session.createSQLQuery("select distinct {m.*}, {r.*}, {l.*} " +
                "from movie m " +
                "left join movierentals r on m.id = r.movieid " +
                "left join client l on r.clientid = l.id ")
                .addEntity("m", Movie.class)
                .addJoin("r", "m.rentals")
                .addJoin("l", "r.client")
                .addEntity("m", Movie.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return query.getResultList();
    }

    @Override
    public Optional<Movie> findByIdWithRentalsAndClients(Long movieId) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct m from Movie m " +
                "left join fetch m.rentals r " +
                "left join fetch r.client " +
                "where m.id = " + movieId);
        List<Movie> list = query.getResultList();
        if(list.size() > 0)
            return Optional.of(list.get(0));
        return Optional.empty();
    }
}
