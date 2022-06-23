package com.pankov.roadtosenior.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    @Setter(value = AccessLevel.NONE)
    private int id;
    private String nameRussian;
    private String nameNative;
    @JsonFormat(pattern = "yyyy")
    private LocalDate yearOfRelease;
    private double rating;
    private double price;
    private String picturePath;
}
