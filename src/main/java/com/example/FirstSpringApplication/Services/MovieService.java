package com.example.FirstSpringApplication.Services;

import com.example.FirstSpringApplication.DAO.ActorDao;
import com.example.FirstSpringApplication.DAO.CommentDao;
import com.example.FirstSpringApplication.DAO.MovieDao;
import com.example.FirstSpringApplication.DTO.MovieDTO;
import com.example.FirstSpringApplication.DTO.MovieFilter;
import com.example.FirstSpringApplication.Entity.Actor;
import com.example.FirstSpringApplication.Entity.Comment;
import com.example.FirstSpringApplication.Entity.Director;
import com.example.FirstSpringApplication.Entity.Movie;
import com.example.FirstSpringApplication.Repositories.MoviePageRepository;
import com.example.FirstSpringApplication.Repositories.MovieRepositories;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.metamodel.mapping.ordering.OrderByFragment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
@Getter
@Setter
public class MovieService {
    @Autowired
    MovieRepositories movieRepositories;

    @Autowired
    MoviePageRepository moviePageRepository;

    @Autowired
    MovieDao movieDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    Movie movie;
    public void index(Model model){
        Pageable pageable = PageRequest.of(0, 3, Sort.by("id"));
        Page<Movie> movies = moviePageRepository.findAllByOrderByNameAsc(pageable);

        model.addAttribute("number", movies.getNumber());
        model.addAttribute("totalPages", movies.getTotalPages());
        model.addAttribute("movies", movies.getContent());

        //Iterable<Movie> movies = movieRepositories.findAll();

    }

    public void page(Model model, int id){
        Pageable pageable = PageRequest.of(id-1, 100, Sort.by("id"));
        Page<Movie> movies = moviePageRepository.findAllByOrderByNameAsc(pageable);

        model.addAttribute("number", movies.getNumber());
        model.addAttribute("totalPages", movies.getTotalPages());
        model.addAttribute("movies", movies.getContent());

    }



    public void edit(Model model, MovieDTO movie){
        //Movie movie = movieRepositories.findById(id).get();
        //Movie movie = movieDao.getById(id);
        //model.addAttribute("movie", movie);

        boolean hasError = false;
        System.out.println("movie_id " + movie.getId());
        System.out.println("movie_title " + movie.getName());
        System.out.println("movie_year " + movie.getYear());
        System.out.println("movie_genre " + movie.getGenre());
        System.out.println("movie_rating " + movie.getRating());
        System.out.println("movie_count " + movie.getCount());
        System.out.println("actor " + movie.getActor());
        System.out.println("director " + movie.getDirector());

        if(movie.getRating() < 0 || movie.getRating() > 10){
            model.addAttribute("errorRating", "Rating must be in the range from 1 to 10");
        }
        if (!hasError){
            Movie dbMovie = movieRepositories.findById(movie.getId()).get();
            dbMovie.setName(movie.getName());
            dbMovie.setYear(movie.getYear());
            dbMovie.setRating(movie.getRating());
            dbMovie.setCount(movie.getCount());
            for (int i = 0; i < movie.getActor().size(); i++){
                String[] actorData = movie.getActor().get(i).split(" ", 2);
                Actor actor = dbMovie.getActor().get(i);
                actor.setName(actorData[0]);
                actor.setSurname(actorData[1]);
            }
            for (int i = 0; i < movie.getDirector().size(); i++){
                String[] directorData = movie.getDirector().get(i).split(" ", 2);
                Director director = dbMovie.getDirector().get(i);
                director.setName(directorData[0]);
                director.setSurname(directorData[1]);
            }

            movieRepositories.save(dbMovie);
            model.addAttribute("movie",dbMovie);
        }else{
            model.addAttribute("movie", movie);
        }


    }
    public void filtered(MovieFilter filter, Model model) {
        List<Movie> movies = movieDao.filterByCriteria(filter);

        model.addAttribute("number", 1);
        model.addAttribute("totalPages", 1);
        model.addAttribute("movies", movies);
    }

    public void show(Model model, int id){
        Movie movie = movieRepositories.findById(id).get();
        //Movie movie = movieDao.getById(id);
        model.addAttribute("movie", movie);
        List<Comment> comment = commentDao.getAll();
        model.addAttribute("comment", comment);

    }

    public void top(Model model){
        //List<Movie> movie = movieDao.getTop10();
        List<Movie> movie = movieRepositories.getTop10();
        model.addAttribute("movie", movie);
        model.addAttribute("id", movie);

        //return model;
    }
    public Model comment(Model model){
        List<Comment> comment = commentDao.getAll();
        model.addAttribute("comment", comment);
        return model;
    }

    public void delete(Integer id){
        Movie movie = movieRepositories.findById(id).get();
        movieRepositories.delete(movie);
    }

    /*public void addMovie(Model model, MovieDTO movies) {
        model.addAttribute("movie", movies);

        if(movies.getName() !=null && !movies.getName().equals("")) {

            Movie saveMovie = new Movie();
            saveMovie.setName(movies.getName());
            saveMovie.setYear(movies.getYear());
            saveMovie.setGenre(movies.getGenre());
            saveMovie.setRating(movies.getRating());
            saveMovie.setCount(movies.getCount());

            //saveMovie.setActor(movies.getActor());
            //saveMovie.setDirector(movies.getDirector());
            if(movies.getRating() < 0 || movies.getRating() > 10){
                model.addAttribute("errorRating", "Rating must be in the range from 1 to 10");
            }

            for (int i = 0; i < movies.getActor().size(); i++){
                String[] actorData = movies.getActor().get(i).split(" ", 2);
                Actor actor = saveMovie.getActor().get(i);
                actor.setName(actorData[0]);
                actor.setSurname(actorData[1]);
            }
            for (int i = 0; i < movies.getDirector().size(); i++){
                String[] directorData = movies.getDirector().get(i).split(" ", 2);
                Director director = saveMovie.getDirector().get(i);
                director.setName(directorData[0]);
                director.setSurname(directorData[1]);
            }

            movieRepositories.save(saveMovie);
            model.addAttribute("movie", saveMovie);
        }else {
            model.addAttribute("movie", movies);
        }

    }*/

    public void addMovieEdit(Model model, MovieDTO movies) {
        model.addAttribute("movie", movies);

/*        movies.setName(movies.getName());
        movies.setYear(movies.getYear());
        movies.setGenre(movies.getGenre());
        movies.setRating(movies.getRating());
        movies.setCount(movies.getCount());*/
    }


    public void addMovieSave(Model model, MovieDTO movies) {

        boolean has = false;

        if (!has){
            if (movies.getName() !=null && !movies.getName().equals("") && movies.getRating() !=null){
                Movie saveMovie = new Movie();
                saveMovie.setName(movies.getName());
                saveMovie.setYear(movies.getYear());
                saveMovie.setGenre(movies.getGenre());
                saveMovie.setRating(movies.getRating());
                saveMovie.setCount(movies.getCount());
                //saveMovie.setActor(movies.getActor());
                //saveMovie.setDirector(movies.getDirector());

                if(movies.getRating() < 0.1 || movies.getRating() > 10){
                    model.addAttribute("errorRating", "Rating must be in the range from 1 to 10");
                }

                for (int i = 0; i < movies.getActor().size(); i++){
                    String[] actorData = movies.getActor().get(i).split(" ",2);
                    Actor actor = new Actor();
                    actor.setName(actorData[0]);
                    actor.setSurname(actorData[1]);
                    actor.setMovie(saveMovie);
                    saveMovie.getActor().add(actor);
                }

                for (int i = 0; i < movies.getDirector().size(); i++){
                    String[] directorData = movies.getDirector().get(i).split(" ", 2);
                    Director director = new Director();
                    director.setName(directorData[0]);
                    director.setSurname(directorData[1]);
                    director.setMovie(saveMovie);
                    saveMovie.getDirector().add(director);
                }

                movieRepositories.save(saveMovie);
                model.addAttribute("movie", saveMovie);
            }else {
                model.addAttribute("movie", movies);
        }

        }
    }
}

