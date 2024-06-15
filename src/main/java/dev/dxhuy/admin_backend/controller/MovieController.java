package dev.dxhuy.admin_backend.controller;

import dev.dxhuy.admin_backend.domain.Movie;
import dev.dxhuy.admin_backend.domain.Response;
import dev.dxhuy.admin_backend.service.MovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }



    @GetMapping("/")
    public ResponseEntity<Response> getMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int limit) {
        PageRequest pageable = PageRequest.of(page, limit);
        Page<Movie> movies = movieService.getAllMovies(pageable);
        if (movies.getContent().size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response("Employee data is not available!"));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response("Get Employee data successfully!", movies.getTotalElements(), movies.getTotalPages(), movies.getNumber(), movies.getContent()));
    }

}
