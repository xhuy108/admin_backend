package dev.dxhuy.admin_backend.controller;

import dev.dxhuy.admin_backend.domain.Movie;
import dev.dxhuy.admin_backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;

import java.util.UUID;

@Component
public class MovieGenerator implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        for (int i = 0; i < 100; i++) {
            Movie movie = new Movie();
            movie.setId(UUID.randomUUID().toString());
            movie.setTitle(faker.book().title());
            movie.setDescription(faker.lorem().paragraph());
            movie.setDirector(faker.name().fullName());
            movie.setProducer(faker.name().fullName());
            movie.setReleaseDate(faker.date().birthday().toString());
            movie.setRating(String.valueOf(faker.number().numberBetween(1, 10)));
            movie.setGenre(faker.book().genre());
            movie.setDuration(faker.number().numberBetween(80, 180) + " minutes");
            movie.setPoster(faker.internet().url());

            movieRepository.save(movie);
        }
    }
}