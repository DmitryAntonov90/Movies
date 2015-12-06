package main.entity;


import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "ACTORS")
public class Actor {

    private Long id;
    private String name;
    private Set<Movie> movies;

    public Actor() {
    }

    public Actor(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTOR_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "ACTOR_NAME", columnDefinition = "nvarchar2 (100)")
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
        int value = new BigDecimal(getId()).intValueExact();
        return prime * value;
    }

    @Override
    public boolean equals(Object obj) {
        Actor other = (Actor)obj;
        if(other == null)
            return false;
        if(hashCode() != other.hashCode())
            return false;
        if(getId() == other.getId() && getName() == other.getName())
            return true;
        return true;
    }
}
