package main.service;

import main.entity.Genre;


public interface GenreService {
    Genre findByTitle(String title);
    Genre findById(Long id);
    Genre addGenre(Genre genre);
    boolean isGenreExist(String title);
    boolean isGenreExist(Long id);
}
