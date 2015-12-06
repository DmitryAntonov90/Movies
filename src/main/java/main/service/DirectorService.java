package main.service;


import main.entity.Director;

public interface DirectorService {
    Director findByName(String name);
    Director findById(Long id);
    Director addDirector(Director director);
    boolean isDirectorExist(String name);
    boolean isDirectorExist(Long id);
}
