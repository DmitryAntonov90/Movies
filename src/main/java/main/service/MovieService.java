package main.service;


import main.entity.Movie;

import java.util.List;
import java.util.Set;

public interface MovieService {
    Movie findMovieById(long id);
    Movie findMovieByTitle(String title);
    Set<Movie> findMovieByDirectorId(Long id);
    Set<Movie> findMovieByDirectorName(String name);
    Set<Movie> findMovieByActorName(String name);
    Set<Movie> findMovieByGenreTitle(String title);
    Movie addMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie updateMovie(Movie movie);
    void removeMovieById(Long id);
    void removeAllMovie();
    boolean isMoviesExist(Movie movie);
    void flush();
}
