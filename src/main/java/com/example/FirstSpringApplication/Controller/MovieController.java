package com.example.FirstSpringApplication.Controller;

import com.example.FirstSpringApplication.DAO.MovieDao;
import com.example.FirstSpringApplication.DTO.MovieDTO;
import com.example.FirstSpringApplication.DTO.MovieFilter;
import com.example.FirstSpringApplication.Entity.Actor;
import com.example.FirstSpringApplication.Entity.Comment;
import com.example.FirstSpringApplication.Entity.Movie;
import com.example.FirstSpringApplication.Repositories.MoviePageRepository;
import com.example.FirstSpringApplication.Repositories.MovieRepositories;
import com.example.FirstSpringApplication.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;


    @RequestMapping({"/", "/movies"})
    public String index(Model model){
        movieService.index(model);
        model.addAttribute("filter", new MovieFilter());
        return "index";
    }

    @RequestMapping("/movies/{id}")
    public String page(@PathVariable Integer id, Model model){
        movieService.page(model, id);
        model.addAttribute("filter", new MovieFilter());
        return "index";
    }

    @GetMapping("/movie/edit/{id}")
    public String edit(@PathVariable Integer id,
                       Model model){
        movieService.show(model, id);
        return "edit_movie";
    }

    @PostMapping("/movie/edit/{id}")
    public String save(@PathVariable Integer id,
                       MovieDTO movie,
                       Model model){

        movieService.edit(model, movie);
        return "edit_movie";
    }

    @RequestMapping("/movie/delete/{id}")
    public RedirectView delete(@PathVariable Integer id,
                               Model model){
        movieService.delete(id);
        return new RedirectView("/movies");
    }

    @RequestMapping("/movies/filter")
    public String filtered(MovieFilter filter, Model model){
        movieService.filtered(filter, model);
        model.addAttribute("filter", filter);
        return "index";
    }

    @RequestMapping("/movie/{id}")
    public String show(@PathVariable Integer id, Model model){
        movieService.show(model, id);
        return "show_movie";
    }

    @RequestMapping("/top")
    public String top(Model model){
        movieService.top(model);
        return "top10";
    }

    @RequestMapping("/comment")
    public String comment(Model model){
        movieService.comment(model);
        return "comment";
    }

/*    @RequestMapping("/addMovie")
    public String addMovie(MovieDTO movies, Model model){
        movieService.addMovie(model, movies);
        return "addMovie";
    }*/

    @GetMapping("/addMovie")
    public String addMovieEdit(MovieDTO movies, Model model){
        movieService.addMovieEdit(model, movies);
        return "addMovie";
    }

    @PostMapping("/addMovie")
    public String addMovieSave(MovieDTO movies, Model model){
        movieService.addMovieSave(model, movies);
        return "addMovie";
    }




}

/*
spring.thymeleaf.enabled=false
spring.thymeleaf.check-template-location=false
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
security.basic.enabled=false*/
