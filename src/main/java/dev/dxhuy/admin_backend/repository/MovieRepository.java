package dev.dxhuy.admin_backend.repository;

import dev.dxhuy.admin_backend.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "Select * from movies where title = :title", nativeQuery = true)
    public List<Movie> findAllByFirstName(@Param("title") String title);

}
