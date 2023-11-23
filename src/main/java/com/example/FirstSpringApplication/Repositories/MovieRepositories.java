package com.example.FirstSpringApplication.Repositories;

import com.example.FirstSpringApplication.Entity.Actor;
import com.example.FirstSpringApplication.Entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;

//@Repository
public interface MovieRepositories extends CrudRepository<Movie, Integer> {
    List<Movie> findByName(String name);
    List<Movie> findByNameOrderById(String name);
    List<Movie> findByNameOrderByIdDesc(String name);
    List<Movie> findByCount(Integer count);
    List<Movie> findByCountAndRating(Integer count, Double rating);
    List<Movie> findByCountOrRating(Integer count, Double rating);
    List<Movie> findByYearBetween(Integer start, Integer end);
    List<Movie> findByYearLessThan(Integer year);
    List<Movie> findByYearGreaterThan(Integer year);

    List<Movie> findByCountIsNull();
    List<Movie> findByNameLike(String title);
    List<Movie> findByNameNotLike(String title);
    List<Movie> findByNameStartingWith(String title);
    List<Movie> findByNameEndingWith(String title);
    List<Movie> findByIdIn(List<Integer> ids);
    List<Movie> findByIdNotIn(List<Integer> ids);

    @Query(value = "from Movie m order by m.rating desc limit 10")
    List<Movie> getTop10();

    //findByAvailableTrue
    //findByAvailableFalse

    @Query("select m from Movie m join fetch m.actor a where a.name = :name")
    List<Movie> findByActorName(@Param("name") String name);

    @Query(value = "select count(*) from actor", nativeQuery = true)
    Long getCountActor();

    List<Movie> getFilteredByYear(@Param("start") Integer start, @Param("end") Integer end);

    @Query(value = "select count(*) from movie", nativeQuery = true)
    Long getCount();

 }
