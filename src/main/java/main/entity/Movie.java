package main.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "MOVIES")
@NamedQuery(name = "Movie.getAll()",
        query = "SELECT m FROM Movie m")
public class Movie implements Serializable {

    private Long id;
    private String title;
    private Long year;
    private Director director;
    private Set<Actor> actors;
    private Set<Genre> genres;

    public Movie() {
    }

    public Movie(Long id, String title, Long year, Director director, Set<Actor> actors, Set<Genre> genres) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.actors = actors;
        this.genres = genres;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIE_ID", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "TITLE", columnDefinition = "nvarchar2 (100)")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "MOVIE_YEAR")
    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    @ManyToOne
    @JoinColumn(name = "DIRECTOR_ID")
    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @ManyToMany
    @JoinTable(name = "MOVIE_ACTOR",
    joinColumns = @JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID"),
    inverseJoinColumns = @JoinColumn(name = "ACTOR_ID", referencedColumnName = "ACTOR_ID"))
    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "MOVIE_GENRE",
    joinColumns = @JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID"),
    inverseJoinColumns = @JoinColumn(name = "GENRE_ID", referencedColumnName = "GENRE_ID"))
    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        int value = new BigDecimal(getId()).intValueExact();
        return prime * value;
    }

    @Override
    public boolean equals(Object obj) {
        Movie other = (Movie) obj;
        if (other == null)
            return false;
        if(this.hashCode() != other.hashCode())
            return false;
        if(this.getId() == other.getId()
                && this.getTitle() == other.getTitle()
                && this.getYear() == other.getYear())
            return true;
        return true;
    }
}
