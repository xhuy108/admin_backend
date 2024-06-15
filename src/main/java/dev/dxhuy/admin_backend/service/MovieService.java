package dev.dxhuy.admin_backend.service;

import dev.dxhuy.admin_backend.domain.Movie;
import dev.dxhuy.admin_backend.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final Logger log = LoggerFactory.getLogger(MovieService.class);

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<Movie> getAllMovies(Pageable pageable) {
        Page<Movie> emps = movieRepository.findAll(pageable);
        log.info("Get all employees pagenable");
        return new PageImpl<>(emps.getContent(), pageable, emps.getTotalElements());
    }

}
