package com.example.FirstSpringApplication.Repositories;

import com.example.FirstSpringApplication.Entity.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepositories extends CrudRepository<Actor, Integer> {

}
