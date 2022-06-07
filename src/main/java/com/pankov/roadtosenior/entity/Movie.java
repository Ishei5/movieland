package com.pankov.roadtosenior.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "movie")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Movie {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private String nameRussian;
    @Column(name = "original_name")
    private String nameNative;
    @JsonFormat(pattern = "yyyy")
    @Column(name = "year_release")
    private LocalDate yearOfRelease;
    private double rating;
    private double price;
    @Column(name = "poster_url")
    private String picturePath;
}
