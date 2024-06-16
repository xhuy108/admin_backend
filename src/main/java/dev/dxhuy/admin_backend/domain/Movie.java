package dev.dxhuy.admin_backend.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
@Table(name = "movies")
public class Movie {
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "director")
    private String director;

    @Column(name = "producer")
    private String producer;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "rating")
    private String rating;

    @Column(name = "genre")
    private String genre;

    @Column(name = "duration")
    private String duration;

    @Column(name = "poster")
    private String poster;


}
