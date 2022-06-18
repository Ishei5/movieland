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
    @GeneratedValue/*(strategy = )*/
    private int id;
    @Column(name = "name_russian")
    private String nameRussian;
    @Column(name = "name_native")
    private String nameNative;
    @JsonFormat(pattern = "yyyy")
    @Column(name = "year_of_release")
    private LocalDate yearOfRelease;
    private double rating;
    private double price;
    @Column(name = "picture_path")
    private String picturePath;
}
