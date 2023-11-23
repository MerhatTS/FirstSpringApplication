package com.example.FirstSpringApplication.RestController;

import com.example.FirstSpringApplication.DAO.MovieDao;
import com.example.FirstSpringApplication.DTO.IdName;
import com.example.FirstSpringApplication.Entity.Movie;
import com.example.FirstSpringApplication.Repositories.MovieRepositories;
import com.example.FirstSpringApplication.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring6.expression.Mvc;

import java.util.List;


@RestController
@RequestMapping(path = "/api/movies")
public class MovieRestController {

/*    @PersistenceContext
    EntityManager em;*/

    @Autowired
    MovieDao movieDao;
    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepositories movieRepositories;

//    @RequestMapping(path = "/{id}")
//    public Movie getBiId(@PathVariable("id") Integer id){

/*    @RequestMapping(path = "/ok")
    public Movie ok(
            @RequestParam("id") Integer id
    ){*/
        //Movie movie = movieRepositories.findById(id).orElse(null);
        //Movie movie = movieRepositories.findByName(id).
        //Iterable<Movie> movies= movieRepositories.findAllById(List.of(62,63,65));
//        movies.forEach(x ->{
//            System.out.println(x.getName());
//        });
        //movieRepositories.delete(movie);
        //movieRepositories.save(movie);
        //формирование запроса на основе метода Movie findByName(String name);
        //ovie movie1 = movieRepositories.findByName("Terminator");
        //List<Movie> movies = movieRepositories.findByCount(10);
        //List<Movie> movies = movieRepositories.findByCountAndRating(10, 7.0);
        //List<Movie> movies = movieRepositories.findByYearBetween(1990, 1995);
        //List<Movie> movies = movieRepositories.findByNameLike("%ermina%");
        //List<Movie> movies = movieRepositories.findByNameOrderByIdDesc("Terminator");
        //List<Movie> movies = movieRepositories.findByIdIn(List.of(55,66,77));
        //List<Movie> movies = movieRepositories.findByActorName("Arnold");
        //List<Movie> movies = movieRepositories.getFilteredByYear(2010,2020);

        //Long count = movieRepositories.getCount();//нативные запросы

        //System.out.println("OK");

        //return movie;
        //return movieRepositories.findById(id).get();


    @PostMapping(path = "/ok")
    //PostMapping - параметры передаются не в строке, а в теле запроса - то используется @RequestBody
    //@GetMapping(path = "/ok")
    //@RequestMapping(path = "/ok/{id}")
    public Movie ok(@RequestBody IdName params){
        System.out.println(params.getId() + " : " + params.getName());
        return movieDao.getById(params.getId());
        //Moonlight,42


        //return em.find(Movie.class, 1);
        /*List<Movie> movies = List.of(
          new Movie(1,"Split",true, 10.10),
          new Movie(2,"Test",false, 1.99),
          new Movie(3,"IT",false, 3.33)
        );
        return movies;*/
    }

    /*@GetMapping(path = "/gettest")
    public String get(){
        return "GET OK";
    }*/

/*    @PostMapping(path = "/gettest")
    public String get(){
        return "GET OK";
    }*/

}
