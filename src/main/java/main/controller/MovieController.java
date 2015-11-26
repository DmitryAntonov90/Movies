package main.controller;

import main.entity.Movie;
import main.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @RequestMapping(value = "/movies/", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        if (movies.isEmpty())
            return new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getMovie(@PathVariable("id") long id) {
        Movie movie = movieService.findMovieById(id);
        if (movie == null)
            return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }

    @RequestMapping(value = "/movies/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> addMovie(@RequestBody Movie movie, UriComponentsBuilder ucBuilder) {
        if (movieService.isMoviesExist(movie))
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);

        movieService.addUser(movie);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/movies/{id}").buildAndExpand(movie.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") long id, @RequestBody Movie movie) {
        Movie currentMovie = movieService.findMovieById(id);
        if (currentMovie == null)
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);

        currentMovie.setTitle(movie.getTitle());
        currentMovie.setDirector(movie.getDirector());
        currentMovie.setYear(movie.getYear());

        return new ResponseEntity<Movie>(currentMovie, HttpStatus.OK);
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Movie> deleteMovie(@PathVariable("id") long id) {
        Movie movie = movieService.findMovieById(id);
        if (movie == null)
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);

        movieService.removeMovieById(id);
        return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/movies/", method = RequestMethod.DELETE)
    public ResponseEntity<Movie> deleteAllMovie() {
        movieService.removeAllMovie();
        return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
    }
}
