package main.entity;

import com.fasterxml.jackson.annotation.JsonView;
import main.json.View;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DIRECTORS")
public class Director {

    private Long id;
    private String name;
    private Set<Movie> movies = new HashSet<Movie>();

    public Director() {
    }

    public Director(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "DIRECTOR_ID", columnDefinition = "NUMBER", unique = true, nullable = false)
    @JsonView(View.Movie.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "DIRECTOR_NAME", columnDefinition = "nvarchar2 (30)", unique = true, nullable = false)
    @JsonView(View.Movie.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "director")
    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        if (id == null && name == null)
            return 154326765;
        return prime + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (hashCode() != obj.hashCode()) return false;
        Director other = (Director) obj;
        if (id != other.id)
            return false;
        if (name != other.name)
            return false;
        return true;
    }
}
