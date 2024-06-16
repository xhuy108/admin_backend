package dev.dxhuy.admin_backend.controller;

import dev.dxhuy.admin_backend.domain.Movie;
import dev.dxhuy.admin_backend.domain.Response;
import dev.dxhuy.admin_backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;
    @Autowired
    private CacheManager cacheManager;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;

    }



    @GetMapping()
    public ResponseEntity<Response> getMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int limit) {
        PageRequest pageable = PageRequest.of(page, limit);
        Page<Movie> movies = movieService.getAllMovies(pageable);
        if (movies.getContent().size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response("Movie data is not available!"));
        }

        System.out.println(movies.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response("Get Movie data successfully!", movies.getTotalElements(), movies.getTotalPages(), movies.getNumber(), movies.getContent()));

    }

    @Cacheable(value = "movies", key = "#title")
    @GetMapping("/title")
    public List<Movie> getEmployeesByFirstName(@RequestParam String title) {
        if (!cacheHit(title)) {
            System.out.println("Cache miss for Employee with firstName: " + title);
        }
        return movieService.getMovieByTitle(title);
    }

    private boolean cacheHit(String name) {
        Cache cache = cacheManager.getCache("movies");
        if (cache != null) {
            Cache.ValueWrapper valueWrapper = cache.get(name);
            return valueWrapper != null;
        }
        return false;
    }

}
