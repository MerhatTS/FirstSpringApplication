package com.example.FirstSpringApplication.Services;

import com.example.FirstSpringApplication.Entity.Actor;
import com.example.FirstSpringApplication.Entity.Movie;
import com.example.FirstSpringApplication.Repositories.ActorPageRepository;
import com.example.FirstSpringApplication.Repositories.ActorRepositories;
import com.example.FirstSpringApplication.Repositories.MoviePageRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
public class ActorService {

/*    @Autowired
    ActorRepositories actorRepositories;*/

    @Autowired
    ActorPageRepository actorPageRepository;

/*    public void index(Model model){
        Pageable pageable = PageRequest.of(0, 15, Sort.by("name"));
        Page<Object[]> actor = actorPageRepository.finDistinctByNameAndSurnameOrderByNameAsc(pageable);

        model.addAttribute("number", actor.getNumber());
        model.addAttribute("totalPages", actor.getTotalPages());
        model.addAttribute("actor", actor.getContent());
    }*/

    public void page(Model model, int id){
        Pageable pageable = PageRequest.of(id, 15, Sort.by(Sort.Direction.ASC, "name"));
        Page<Object[]> actor = actorPageRepository.findDistinctByNameAndSurnameOrderByNameAsc(pageable);

        model.addAttribute("number", actor.getNumber());
        model.addAttribute("totalPages", actor.getTotalPages());
        model.addAttribute("actor", actor.getContent().stream().map(x ->{
            Actor actor1 = new Actor();
            actor1.setName((String)x[0]);
            actor1.setSurname((String)x[1]);
            actor1.setId((Integer) x[2]);
            return actor1;
        }).toList());
    }

/*    public void actorAll(Model model){
        Iterable<Actor> actors = actorRepositories.findAll();
        model.addAttribute("actor", actors);
    }*/

}
