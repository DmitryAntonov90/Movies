package main.service.Impl;

import main.entity.Genre;
import main.repository.GenreRepository;
import main.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre findByTitle(String title) {
        Genre genre = genreRepository.getByTitle(title);
        return genre;
    }

    @Override
    public Genre findById(Long id) {
        Genre genre = genreRepository.getById(id);
        return genre;
    }

    @Override
    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public boolean isGenreExist(String title) {
        Genre genre = findByTitle(title);
        return genre != null ? true : false;
    }

    @Override
    public boolean isGenreExist(Long id) {
        Genre genre = findById(id);
        return genre != null ? true : false;
    }
}
