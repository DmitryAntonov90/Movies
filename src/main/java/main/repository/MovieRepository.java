package main.repository;


import main.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m WHERE m.title = :title")
    Movie getByTitle(@Param("title")String title);

    @Query("SELECT m FROM Movie m WHERE m.id = :id")
    Movie getById(@Param("id")Long id);

    @Query("SELECT m FROM Movie m WHERE m.director.id = :id")
    Set<Movie> getByDirectorId(@Param("id")Long id);

    @Query("SELECT m FROM Movie m WHERE m.director.name = :name")
    Set<Movie> getByDirectorName(@Param("name")String name);


    @Query("SELECT a.movies FROM Actor a WHERE a.name = :name")
    Set<Movie> getByActorName(@Param("name")String name);

    @Query("SELECT g.movies FROM Genre g WHERE g.title = :title")
    Set<Movie> getByGenreTitle(@Param("title")String title);




}
