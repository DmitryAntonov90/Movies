package main.entity;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GENRES")
public class Genre {

    private Long id;
    private String title;
    private Set<Movie> movies;

    public Genre(){
    }

    public Genre(Long id, String title){
        this.id = id;
        this.title = title;
        this.movies = new HashSet<Movie>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GENRE_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "TITLE", columnDefinition = "nvarchar2 (50)")
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
        int value = new BigDecimal(getId()).intValueExact();
        return prime * value;
    }

    @Override
    public boolean equals(Object obj) {
        Genre genre = (Genre)obj;

        if(genre == null)
            return false;
        if(hashCode() != genre.hashCode())
            return false;
        if(getId() == genre.getId() && getTitle() == genre.getTitle())
            return true;
        return true;
    }
}
