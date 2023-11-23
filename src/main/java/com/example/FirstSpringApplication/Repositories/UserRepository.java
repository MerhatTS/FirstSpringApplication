package com.example.FirstSpringApplication.Repositories;

import com.example.FirstSpringApplication.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByLogin(String login);
}
