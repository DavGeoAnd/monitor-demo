package com.davgeoand.demo.movieservicesb.repository;

import com.davgeoand.demo.movieservicesb.model.Actor;
import com.davgeoand.demo.movieservicesb.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {
    private final List<Movie> movies;

    public MovieRepository() {
        movies = new ArrayList<>();
        movies.add(Movie.builder().title("Everything Everywhere All at Once").year(2022).actors(List.of(new Actor("Michelle Yeoh"), new Actor("Stephanie Hsu"), new Actor("Jamie Lee Curtis"))).build());
        movies.add(Movie.builder().title("Top Gun: Maverick").year(2022).actors(List.of(new Actor("Tom Cruise"), new Actor("Jennifer Connelly"), new Actor("Miles Teller"))).build());
        movies.add(Movie.builder().title("The Batman").year(2022).actors(List.of(new Actor("Robert Pattinson"), new Actor("Zoë Kravitz"), new Actor("Jeffrey Wright"))).build());
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public boolean addMovie(Movie movie) {
        return movies.add(movie);
    }

    public Movie getMovie(String movieTitle) {
        Movie foundMovie = null;
        for (Movie m : movies) {
            if (m.getTitle().equals(movieTitle)) {
                foundMovie = m;
            }
        }
        return foundMovie;
    }

    public boolean updateMovie(String movieTitle, Movie bodyMovie) {
        boolean updated = false;
        for (Movie m : movies) {
            if (m.getTitle().equals(movieTitle)) {
                movies.remove(m);
                movies.add(bodyMovie);
                updated = true;
                break;
            }
        }
        return updated;
    }

    public void removeMovie(String movieTitle) {
        movies.removeIf(m -> m.getTitle().equals(movieTitle));
    }
}
