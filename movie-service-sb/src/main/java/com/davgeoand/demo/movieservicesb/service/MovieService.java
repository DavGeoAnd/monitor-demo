package com.davgeoand.demo.movieservicesb.service;

import com.davgeoand.demo.movieservicesb.model.Movie;
import com.davgeoand.demo.movieservicesb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public List<Movie> findAllMovies() {
        return movieRepository.getMovies();
    }

    public String createMovie(Movie bodyMovie) {
        if (movieRepository.addMovie(bodyMovie)) {
            return "Added";
        } else {
            return "Not Added";
        }
    }

    public Movie findMovie(String movieTitle) {
        return movieRepository.getMovie(movieTitle);
    }

    public String updateMovie(String movieTitle, Movie bodyMovie) {
        if (movieRepository.updateMovie(movieTitle, bodyMovie)) {
            return "Updated";
        } else {
            return "Not Updated";
        }
    }

    public void removeMovie(String movieTitle) {
        movieRepository.removeMovie(movieTitle);
    }
}
