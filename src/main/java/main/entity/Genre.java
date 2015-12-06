package main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import main.json.View;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GENRES")
public class Genre implements Serializable {

    private Long id;
    private String title;
    private Set<Movie> movies = new HashSet<Movie>();

    public Genre() {
    }

    public Genre(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "GENRE_ID", unique = true, nullable = false)
    @JsonView(View.Movie.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "TITLE", columnDefinition = "nvarchar2 (50)", unique = true, nullable = false)
    @JsonView(View.Movie.class)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "genres")
    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int hashCode() {
        final int prime = 5;
        if(title == null && id == null)
            return 11718561;
        return prime + title.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Genre genre = (Genre) obj;

        if (genre == null)
            return false;
        if (hashCode() != genre.hashCode())
            return false;
        if (getId() == genre.getId() && getTitle() == genre.getTitle())
            return true;
        return true;
    }
}
