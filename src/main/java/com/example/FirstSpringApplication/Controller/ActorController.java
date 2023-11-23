package com.example.FirstSpringApplication.Controller;

import com.example.FirstSpringApplication.Entity.Actor;
import com.example.FirstSpringApplication.Entity.Movie;
import com.example.FirstSpringApplication.Repositories.ActorRepositories;
import com.example.FirstSpringApplication.Repositories.MovieRepositories;
import com.example.FirstSpringApplication.Services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    ActorService actorService;

/*    @RequestMapping(path = {"/actor", "/actors"})
    public String actor(Model model){
        actorService.actorAll(model);
        actorService.index(model);
        return "actor";
   }*/

    @RequestMapping({"","{id}"})
    public String page(@PathVariable(required = false) Integer id,
                       Model model){
        actorService.page(model, id == null ? 0 : id - 1);
        return "actor";
    }
}
