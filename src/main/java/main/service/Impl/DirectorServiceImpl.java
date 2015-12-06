package main.service.Impl;

import main.entity.Director;
import main.repository.DirectorRepository;
import main.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public Director findByName(String name) {
        return directorRepository.getByName(name);
    }

    @Override
    public Director findById(Long id) {
        return directorRepository.getById(id);
    }

    @Override
    public Director addDirector(Director director) {
        return directorRepository.save(director);

    }

    @Override
    public boolean isDirectorExist(String name) {
        Director director = directorRepository.getByName(name);
        return director != null ? true : false;
    }

    @Override
    public boolean isDirectorExist(Long id) {
        Director director = directorRepository.getById(id);
        return director != null ? true : false;
    }
}
