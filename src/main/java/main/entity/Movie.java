package main.entity;

import com.fasterxml.jackson.annotation.JsonView;
import main.json.View;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "MOVIES")
public class Movie implements Serializable {

    private Long id;
    private String title;
    private Long year;
    private Director director;
    private Set<Actor> actors = new HashSet<Actor>();
    private Set<Genre> genres = new HashSet<Genre>();

    public Movie() {
    }

    public Movie(String title, Long year) {
        this.title = title;
        this.year = year;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "MOVIE_ID", unique = true, nullable = false)
    @JsonView(View.Movie.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "TITLE", columnDefinition = "nvarchar2 (100)", unique = true, nullable = false)
    @JsonView(View.Movie.class)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "MOVIE_YEAR")
    @JsonView(View.Movie.class)
    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "DIRECTOR_ID", nullable = false)
    @JsonView(View.Movie.class)
    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @ManyToMany
    @JoinTable(name = "MOVIE_ACTOR",
            joinColumns = @JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "ACTOR_ID", referencedColumnName = "ACTOR_ID", nullable = false, updatable = false))
    @JsonView(View.Movie.class)
    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    @ManyToMany
    @JoinTable(name = "MOVIE_GENRE",
            joinColumns = @JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "GENRE_ID", referencedColumnName = "GENRE_ID", nullable = false, updatable = false))
    @JsonView(View.Movie.class)
    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        if (title == null && year == null) return 265187132;
        return prime * title.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (hashCode() != obj.hashCode()) {
            return false;
        }
        Movie other = (Movie) obj;
        if (id != other.id)
            return false;
        if (title != other.title)
            return false;
        if (year != other.year)
            return false;
        return true;
    }
}
