package com.pankov.roadtosenior.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue/*(strategy = )*/
    @Setter(value = AccessLevel.NONE)
    private int id;
    @Column(name = "name_russian")
    private String nameRussian;
    @Column(name = "name_native")
    private String nameNative;
    @JsonFormat(pattern = "yyyy")
    @Column(name = "year_of_release")
    private LocalDate yearOfRelease;
    private String description;
    private double rating;
    private double price;
    @Column(name = "picture_path")
    private String picturePath;
    @ManyToMany
    @JoinTable(
            name = "movie_2_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;
}
