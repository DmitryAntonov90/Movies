package main.repository;

import main.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DirectorRepository extends JpaRepository<Director,Long> {

    @Query("SELECT d FROM Director d WHERE d.id = :id")
    Director getById(@Param("id")Long id);

    @Query("SELECT d FROM Director d WHERE d.name = :name ")
    Director getByName(@Param("name")String name);

}
