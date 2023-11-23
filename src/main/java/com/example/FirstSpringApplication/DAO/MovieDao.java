package com.example.FirstSpringApplication.DAO;

import com.example.FirstSpringApplication.DTO.MovieById;
import com.example.FirstSpringApplication.DTO.MovieByYear;
import com.example.FirstSpringApplication.DTO.MovieFilter;
import com.example.FirstSpringApplication.Entity.Movie;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDao extends EntityDao<Movie>{
    @Override
    public Movie getById(int id) {
        return em.find(Movie.class, id);
    }

    @Override
    public List<Movie> getAll() {
        return em.createQuery("from Movie", Movie.class).getResultList();
    }

    @Override
    public void update(Movie entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Movie entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Movie entity) {
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<Movie> getTop10(){
        List<Movie> movies = em
                .createQuery("from Movie m order by m.rating desc", Movie.class)
                .setMaxResults(10)
                .getResultList();
        return movies;
    }
    public  List<Movie> filterByYears(MovieByYear movieByYear){
        List<Movie> movies = em
                .createQuery("from Movie m where m.year between :start and :end", Movie.class)
                .setParameter("start",movieByYear.getStartYear())
                .setParameter("end", movieByYear.getEndYear())
                .setMaxResults(10)
                .getResultList();
        return movies;
    }

    public Movie Id(MovieById movieById){
        Movie movies = em
                .createQuery("from Movie m where m.id =:id", Movie.class)
                .setParameter("id", movieById.getIdMovie())
                .getSingleResult();
        return movies;
    }

    public List<Movie> filterByDirector(String directorName){
        List<Movie> movies = em
                .createQuery("from Movie m left join fetch m.director d where d.name =:directorName", Movie.class)
                .setParameter("directorName", directorName)
                .getResultList();
        return movies;
    }
    public List<Movie> filterByActor(String actorName){
        List<Movie> movies = em
                .createQuery("from Movie m left join fetch m.actor a where a.name =:actorName", Movie.class)
                .setParameter("actorName", actorName)
                .getResultList();
        return movies;
    }
    public List<Movie> filterByTitle(String title){
        List<Movie> movies = em
                .createQuery("from Movie m where m.name =:title", Movie.class)
                .setParameter("title", title)
                .getResultList();
        return movies;
    }

    @Override
    public void close() throws Exception {

    }


    public List<Movie> filterByCriteria(MovieFilter filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Movie> query = cb.createQuery(Movie.class);

        Root<Movie> root = query.from(Movie.class);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getName() !=null && !filter.getName().equals("")){
            predicates.add(cb.equal(root.get("name"), filter.getName()));
        }
        if (filter.getYearTo() !=null){
            predicates.add(cb.lt(root.get("year"), filter.getYearTo()));
        }
        if (filter.getYearFrom() !=null){
            predicates.add(cb.gt(root.get("year"), filter.getYearFrom()));
        }
        if (filter.getRating() !=null){
            predicates.add(cb.gt(root.get("rating"), filter.getRating()));
        }
        if (filter.getActorName() !=null && !filter.getActorName().equals("")){
            Join join = root.join("actor");
            predicates.add(cb.equal(join.get("name"), filter.getActorName()));
        }
        if (filter.getActorSurname() !=null && !filter.getActorSurname().equals("")){
            Join join = root.join("actor");
            predicates.add(cb.equal(join.get("surname"), filter.getActorSurname()));
        }
        if (filter.getDirectorName() !=null && !filter.getDirectorName().equals("")){
            Join join = root.join("director");
            predicates.add(cb.equal(join.get("name"), filter.getDirectorName()));
        }
        if (filter.getDirectorSurname() !=null && !filter.getDirectorSurname().equals("")){
            Join join = root.join("director");
            predicates.add(cb.equal(join.get("surname"), filter.getDirectorSurname()));
        }

        query.select(root);

        Predicate finalPredicate = null;
        for (int i = 0; i < predicates.size(); i++){
            if (i == 0){
                finalPredicate = predicates.get(i);
            }else {
                finalPredicate = cb.and(finalPredicate, predicates.get(i));
            }
        }
        query.where(finalPredicate);
        query.orderBy(cb.asc(root.get("name")));

        TypedQuery<Movie> tQuery = em.createQuery(query);
        return tQuery.getResultList();
    }
}
