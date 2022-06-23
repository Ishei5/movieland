package com.pankov.roadtosenior.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "genre")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue
    @Setter(value = AccessLevel.NONE)
    @NonNull
    private int id;
    @NonNull
    private String name;
    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies;


}
