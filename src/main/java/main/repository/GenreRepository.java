package main.repository;


import main.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("SELECT g FROM Genre g WHERE g.title = :title")
    Genre getByTitle(@Param("title")String title);

    @Query("SELECT g FROM Genre g WHERE g.id = :id")
    Genre getById(@Param("id")Long id);
}
