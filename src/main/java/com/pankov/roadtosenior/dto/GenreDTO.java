package com.pankov.roadtosenior.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {
    @Setter(value = AccessLevel.NONE)
    private int id;
    private String name;
}
