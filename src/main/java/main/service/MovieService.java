package main.service;


import main.entity.Movie;

import java.util.List;

public interface MovieService {
    Movie findMovieById(long id);
    Movie findMovieByTitle(String title);
    void addUser(Movie movie);
    List<Movie> getAllMovies();
    void updateMovie(Movie movie);
    void removeMovieById(Long id);
    void removeAllMovie();
    boolean isMoviesExist(Movie movie);
}
