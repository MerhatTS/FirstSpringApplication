package com.example.FirstSpringApplication.Repositories;

import com.example.FirstSpringApplication.Entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(exported = false)
public interface MoviePageRepository extends PagingAndSortingRepository<Movie, Integer> {
    Page<Movie> findAllByOrderByNameAsc(Pageable pageable);
}
