package main.repository;


import main.entity.Actor;
import main.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActorRepository extends JpaRepository<Actor,Long> {

    @Query("SELECT a FROM Actor a WHERE a.name = :name")
    Actor getByName(@Param("name")String name);

    @Query("SELECT a FROM Actor a WHERE a.id = :id")
    Actor getById(@Param("id")Long id);

}
