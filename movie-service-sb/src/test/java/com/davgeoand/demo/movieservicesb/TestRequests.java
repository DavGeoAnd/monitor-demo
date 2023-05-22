package com.davgeoand.demo.movieservicesb;

import com.davgeoand.demo.movieservicesb.model.Actor;
import com.davgeoand.demo.movieservicesb.model.Movie;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class TestRequests {
    public static void main(String[] args) throws InterruptedException {
        //String baseUrl = "http://localhost:12000/movies";
        String baseUrl = "http://localhost:12001/movies";
        Random random = new Random();

        List<Movie> otherMovies = new ArrayList<>();
        otherMovies.add(Movie.builder().title("Glass Onion").year(2022).actors(List.of(new Actor("Daniel Craig"), new Actor("Edward Norton"), new Actor("Kate Hudson"))).build());
        otherMovies.add(Movie.builder().title("Boiling Point").year(2022).actors(List.of(new Actor("Stephen Graham"), new Actor("Vinette Robinson"), new Actor("Alice Feetham"))).build());
        otherMovies.add(Movie.builder().title("The Banshees of Inisherin").year(2022).actors(List.of(new Actor("Colin Farrell"), new Actor("Brendan Gleeson"), new Actor("Kerry Condon"))).build());
        otherMovies.add(Movie.builder().title("Aftersun").year(2022).actors(List.of(new Actor("Paul Mescal"), new Actor("Frankie Corio"), new Actor("Celia Rowlson-Hall"))).build());
        otherMovies.add(Movie.builder().title("Tár").year(2022).actors(List.of(new Actor("Cate Blanchett"), new Actor("Noémie Merlant"), new Actor("Nina Hoss"))).build());
        otherMovies.add(Movie.builder().title("The Fabelmans").year(2022).actors(List.of(new Actor("Michelle Williams"), new Actor("Gabriel LaBelle"), new Actor("Paul Dano"))).build());
        otherMovies.add(Movie.builder().title("Avatar: The Way of Water").year(2022).actors(List.of(new Actor("Sam Worthington"), new Actor("Zoe Saldana"), new Actor("Sigourney Weaver"))).build());
        otherMovies.add(Movie.builder().title("All Quiet on the Western Front").year(2022).actors(List.of(new Actor("Felix Kammerer"), new Actor("Albrecht Schuch"), new Actor("Aaron Hilmer"))).build());
        otherMovies.add(Movie.builder().title("Decision to Leave").year(2022).actors(List.of(new Actor("Park Hae-il"), new Actor("Tang Wei"), new Actor("Lee Jung-hyun"))).build());
        otherMovies.add(Movie.builder().title("The Menu").year(2022).actors(List.of(new Actor("Ralph Fiennes"), new Actor("Anya Taylor-Joy"), new Actor("Nicholas Hoult"))).build());

        for (Movie m : otherMovies) {
            log.info("Get all movies");
            List<Movie> movies = Unirest.get(baseUrl).asObject(new GenericType<List<Movie>>() {
            }).getBody();
            log.info(movies.toString());

            Thread.sleep(5000);

            log.info("Add movie");
            String addResult = Unirest.post(baseUrl).contentType("application/json").body(m).asString().getBody();
            log.info(addResult);

            Thread.sleep(5000);

            log.info("Get all movies");
            movies = Unirest.get(baseUrl).asObject(new GenericType<List<Movie>>() {
            }).getBody();
            log.info(movies.toString());

            Thread.sleep(5000);

            log.info("Get one movie");
            int randomInt = random.nextInt(movies.size());
            Movie foundMovie = Unirest.get(baseUrl + "/" + movies.get(randomInt).getTitle()).asObject(Movie.class).getBody();
            log.info(foundMovie.toString());

            Thread.sleep(5000);

            log.info("Update one movie");
            String updateResult = Unirest.put(baseUrl + "/" + movies.get(randomInt).getTitle()).contentType("application/json").body(foundMovie.toBuilder().year(2021).build()).asString().getBody();
            log.info(updateResult);

            Thread.sleep(5000);

            log.info("Get all movies");
            movies = Unirest.get(baseUrl).asObject(new GenericType<List<Movie>>() {
            }).getBody();
            log.info(movies.toString());

            Thread.sleep(5000);

            log.info("Remove one movie");
            Unirest.delete(baseUrl + "/" + movies.get(random.nextInt(movies.size())).getTitle()).asEmpty();

            Thread.sleep(5000);
        }
    }
}
