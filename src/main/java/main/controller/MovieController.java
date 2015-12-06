package main.controller;

import com.fasterxml.jackson.annotation.JsonView;
import main.entity.Actor;
import main.entity.Director;
import main.entity.Genre;
import main.entity.Movie;
import main.json.View;
import main.service.ActorService;
import main.service.DirectorService;
import main.service.GenreService;
import main.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    DirectorService directorService;

    @Autowired
    ActorService actorService;

    @Autowired
    GenreService genreService;

    @JsonView(View.Movie.class)
    @RequestMapping(value = "/api/movies/", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        if (movies.isEmpty())
            return new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
    }

    @JsonView(View.Movie.class)
    @RequestMapping(value = "/api/movies/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getMovie(@PathVariable("id") long id) {
        Movie movie = movieService.findMovieById(id);
        if (movie == null)
            return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }

    @JsonView(View.Movie.class)
    @RequestMapping(value = "/api/movies/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> addMovie(@RequestBody Movie movie, UriComponentsBuilder ucBuilder) {
        if (movieService.isMoviesExist(movie))
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);

        Director director = directorService.findByName(movie.getDirector().getName());

        if (director == null) {
            movie.getDirector().getMovies().add(movie);
            directorService.addDirector(movie.getDirector());
        } else {
            movie.setDirector(director);
            director.getMovies().add(movie);
        }

        for (Actor actor : movie.getActors()) {
            Actor currentActor = actorService.findByName(actor.getName());
            if (currentActor == null) {
                actor.getMovies().add(movie);
                actorService.addActor(actor);
            } else {
                actor = currentActor;
                actor.getMovies().add(movie);
            }
        }

        for (Genre genre : movie.getGenres()) {
            Genre currentGenre = genreService.findByTitle(genre.getTitle());
            if (currentGenre == null) {
                genre.getMovies().add(movie);
                genreService.addGenre(genre);
            } else {
                genre = currentGenre;
                genre.getMovies().add(movie);
            }
        }

        movieService.addMovie(movie);
        movieService.flush();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/movies/{id}").buildAndExpand(movie.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @JsonView(View.Movie.class)
    @RequestMapping(value = "/api/movies/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") long id, @RequestBody Movie movie) {
        Movie currentMovie = movieService.findMovieById(id);
        currentMovie.setActors(new HashSet<Actor>());
        currentMovie.setGenres(new HashSet<Genre>());

        if (currentMovie == null)
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);

        Director director = directorService.findByName(currentMovie.getDirector().getName());

        if (director == null) {
            currentMovie.getDirector().getMovies().add(currentMovie);
            directorService.addDirector(currentMovie.getDirector());
        } else {
            director.getMovies().add(currentMovie);
            currentMovie.setDirector(director);
        }

        for (Actor actor : movie.getActors()) {
            Actor currentActor = actorService.findByName(actor.getName());
            if (currentActor == null) {
                actor.getMovies().add(currentMovie);
                currentMovie.getActors().add(actor);
                actorService.addActor(actor);
            } else {
                actor = currentActor;
                actor.getMovies().add(currentMovie);
                currentMovie.getActors().add(actor);
            }
        }

        for (Genre genre : movie.getGenres()) {
            Genre currentGenre = genreService.findByTitle(genre.getTitle());
            if (currentGenre == null) {
                genre.getMovies().add(movie);
                currentMovie.getGenres().add(genre);
                genreService.addGenre(genre);
            } else {
                genre = currentGenre;
                genre.getMovies().add(movie);
                currentMovie.getGenres().add(genre);
            }
        }

        currentMovie = movieService.updateMovie(currentMovie);
        return new ResponseEntity<Movie>(currentMovie, HttpStatus.OK);
    }

    @JsonView(View.Movie.class)

    @RequestMapping(value = "/api/movies/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Movie> deleteMovie(@PathVariable("id") long id) {
        Movie movie = movieService.findMovieById(id);
        if (movie == null)
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);

        movieService.removeMovieById(id);
        return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
    }

    @JsonView(View.Movie.class)
    @RequestMapping(value = "/api/movies/", method = RequestMethod.DELETE)
    public ResponseEntity<Movie> deleteAllMovie() {
        movieService.removeAllMovie();
        return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
    }
}
