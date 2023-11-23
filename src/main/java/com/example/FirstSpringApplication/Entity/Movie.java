package com.example.FirstSpringApplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "movie")
@NamedQueries({
        @NamedQuery(name = "Movie.getFilteredByYear", query = "from Movie m where m.year between :start and :end"),//именованные запросы
        @NamedQuery(name = "Movie.getCount", query = "select count(m) from Movie m")
        })
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer year;
    private String genre;
    private Double rating;
    private Integer count;

//    @ManyToOne
//    @JoinColumn(name = "actor_id")
//    private Entity.Actor actor;
//
//    @ManyToOne
//    @JoinColumn(name = "director_id")
//    private Entity.Director director;

//    private List<Entity.Director> director;
//    private List<Entity.Actor> actor;

    @ManyToMany
    @JoinTable(
            name = "movie_to_genre",
            joinColumns = { @JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")}
    )
    //@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Genre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, orphanRemoval = true)//ленивая подгрузка / orphanRemoval = true - при удалении сущности из списка также удаляется из базы
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Actor> actor = new ArrayList<>();//проитись по списку и если есть какие нибудь сущности, то их нужно тоже сохранить

    @OneToMany(mappedBy = "movie")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Director> director = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", count=" + count +
                '}';
    }
}
