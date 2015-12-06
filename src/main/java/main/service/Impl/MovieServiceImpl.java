package main.service.Impl;

import main.entity.Movie;
import main.repository.MovieRepository;
import main.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie findMovieById(long id) {
        return movieRepository.getById(id);
    }

    @Override
    public Movie findMovieByTitle(String title) {
        return movieRepository.getByTitle(title);
    }

    @Override
    public Set<Movie> findMovieByDirectorId(Long id) {
        Set<Movie> movies = movieRepository.getByDirectorId(id);
        return movies;
    }

    @Override
    public Set<Movie> findMovieByDirectorName(String name) {
        Set<Movie> movies = movieRepository.getByDirectorName(name);
        return movies;
    }

    @Override
    public Set<Movie> findMovieByActorName(String name) {
        Set<Movie> movies = movieRepository.getByActorName(name);
        return movies;
    }

    @Override
    public Set<Movie> findMovieByGenreTitle(String title) {
        Set<Movie> movies = movieRepository.getByGenreTitle(title);
        return movies;
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepository.saveAndFlush(movie);
    }

    @Override
    public void removeMovieById(Long id) {
        movieRepository.delete(id);
    }

    @Override
    public void removeAllMovie() {
        movieRepository.deleteAll();
    }

    @Override
    public boolean isMoviesExist(Movie movie) {
        Movie result = movieRepository.getById(movie.getId());
        return result != null ? true : false;
    }

    @Override
    public void flush() {
        movieRepository.flush();
    }
}
