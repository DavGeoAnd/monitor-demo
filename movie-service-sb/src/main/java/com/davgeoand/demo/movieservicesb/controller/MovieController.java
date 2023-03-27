package com.davgeoand.demo.movieservicesb.controller;

import com.davgeoand.demo.movieservicesb.model.Movie;
import com.davgeoand.demo.movieservicesb.monitor.event.handler.AppEventHandler;
import com.davgeoand.demo.movieservicesb.monitor.event.type.Sample;
import com.davgeoand.demo.movieservicesb.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;


    @PostMapping(consumes = MediaType.ALL_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String postMovieRequest(@RequestBody Movie movie) {
        log.info("Posting a new movie request");
        return "Movie saved: " + movieService.createMovie(movie);
    }

    @GetMapping(value = "/{movieTitle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Movie getMovieByTitleRequest(@PathVariable("movieTitle") String movieTitle) {
        log.info("Getting movie by title request");
        return movieService.findMovie(movieTitle);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getAllMoviesRequest() {
        log.info("Getting all movies request");
        return movieService.findAllMovies();
    }

    @PutMapping(value = "/{movieTitle}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String putMovieByNameRequest(@PathVariable("movieTitle") String movieTitle, @RequestBody Movie movie) {
        log.info("Putting movie by name request");
        return "Movie updated: " + movieService.updateMovie(movieTitle, movie);
    }

    @DeleteMapping(value = "/{movieTitle}")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteMovieByTitleRequest(@PathVariable("movieTitle") String movieTitle) {
        log.info("Deleting movie by title request");
        movieService.removeMovie(movieTitle);
        return "Movie removed: " + movieTitle;
    }
}
