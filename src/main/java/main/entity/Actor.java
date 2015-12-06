package main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import main.json.View;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ACTORS")
public class Actor {

    private Long id;
    private String name;
    private Set<Movie> movies = new HashSet<Movie>();

    public Actor() {
    }

    public Actor(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ACTOR_ID", unique = true, nullable = false)
    @JsonView(View.Movie.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "ACTOR_NAME", columnDefinition = "nvarchar2 (100)", nullable = false, unique = true)
    @JsonView(View.Movie.class)
    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "actors")
    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int hashCode() {
        final int prime = 12;
        if(id == null && name == null)
            return 64378212;
        return prime + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Actor other = (Actor) obj;
        if (other == null)
            return false;
        if (hashCode() != other.hashCode())
            return false;
        if (getId() == other.getId() && getName() == other.getName())
            return true;
        return true;
    }
}
