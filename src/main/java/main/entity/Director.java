package main.entity;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DIRECTORS")
public class Director {

    private Long id;
    private String name;
    private Set<Movie> movies;

    public Director() {
    }

    public Director(Long id, String name) {
        this.id = id;
        this.name = name;
        this.movies = new HashSet<Movie>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIRECTOR_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "DIRECTOR_NAME", columnDefinition = "nvarchar2 (30)")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "director")
    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        if (id == null)
            return 11 * 11371;
        int value = new BigDecimal(getId()).intValueExact();
        return prime * value;
    }

    @Override
    public boolean equals(Object obj) {
        Director other = (Director) obj;
        if (other == null)
            return false;
        if (hashCode() != other.hashCode())
            return false;
        if (this.id == other.id && this.name == other.name)
            return true;
        return true;
    }
}
