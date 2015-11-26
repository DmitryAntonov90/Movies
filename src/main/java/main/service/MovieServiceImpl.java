package main.service;

import main.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MovieServiceImpl implements MovieService {

    private static final AtomicLong counter = new AtomicLong();

    private static List<Movie> movies;

    static {
        movies = new ArrayList<Movie>();
    }

    @Override
    public Movie findMovieById(long id) {
        for (Movie movie : movies)
            if (movie.getId() == id)
                return movie;
        return null;
    }

    @Override
    public Movie findMovieByTitle(String title) {
        for (Movie movie : movies)
            if (movie.getTitle().equalsIgnoreCase(title))
                return movie;
        return null;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movies;
    }

    @Override
    public void addUser(Movie movie) {
        movie.setId(counter.incrementAndGet());
        movies.add(movie);
    }

    @Override
    public void updateMovie(Movie movie) {
        int index = movies.indexOf(movie);
        movies.set(index, movie);
    }

    @Override
    public void removeMovieById(Long id) {
        for (Iterator<Movie> iterator = movies.iterator(); iterator.hasNext(); ) {
            Movie movie = iterator.next();
            if (movie.getId() == id)
                iterator.remove();
        }
    }

    @Override
    public void removeAllMovie() {
        movies.clear();
    }

    @Override
    public boolean isMoviesExist(Movie movie) {
        return findMovieByTitle(movie.getTitle()) != null;
    }
}
