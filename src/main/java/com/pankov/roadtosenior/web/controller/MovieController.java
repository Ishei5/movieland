package com.pankov.roadtosenior.web.controller;

import com.pankov.roadtosenior.dto.MovieDTO;
import com.pankov.roadtosenior.enums.SortingOrder;
import com.pankov.roadtosenior.enums.SortingParameter;
import com.pankov.roadtosenior.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieDTO> getAllMovie(@RequestParam(required = false) Map<String, String> params) {
        validateParams(params);
        return movieService.getAllMovie(params);
    }

    @GetMapping(value = "/random")
    public List<MovieDTO> getThreeRandomMovie() {
        return movieService.getThreeRandomMovie();
    }

    @GetMapping(value = "/genre/{genreId}")
    public List<MovieDTO> getMovieByGenre(@PathVariable int genreId,
                                          @RequestParam(required = false) Map<String, String> params) {
        validateParams(params);
        return movieService.getMoviesByGenre(genreId, params);
    }

    boolean validateParams(Map<String, String> params) {
        for (Map.Entry<String, String> param : params.entrySet()) {
            Map.Entry<String, String> unAllowed = Map.entry("rating", "asc");
            if (param.equals(unAllowed)) {
                throw new RuntimeException("ASC not allowed for rating");
            }
            if (SortingParameter.findByValue(param.getKey()) == null) {
                throw new RuntimeException("Parameter for sorting not allowed " + param.getKey());
            }
            if (SortingOrder.findByValue(param.getValue()) == null) {
                throw new RuntimeException("Invalid sorting order " + param.getValue());
            }
        }

        return true;
    }


}
