package com.example.FirstSpringApplication.Repositories;

import com.example.FirstSpringApplication.Entity.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActorPageRepository extends PagingAndSortingRepository<Actor, Integer> {
    @Query(value = "select distinct a.name, a.surname, a.id from Actor a order by a.name")
    Page<Object[]> findDistinctByNameAndSurnameOrderByNameAsc(Pageable pageable);
}
