package com.davgeoand.demo.tvservicesb;

import com.davgeoand.demo.tvservicesb.model.Actor;
import com.davgeoand.demo.tvservicesb.model.Tv;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class TestRequests {
    public static void main(String[] args) throws InterruptedException {
        //String baseUrl = "http://localhost:11000/tvs";
        String baseUrl = "http://localhost:11001/tvs";
        Random random = new Random();

        List<Tv> otherTvs = new ArrayList<>();
        otherTvs.add(Tv.builder().title("The Lord of the Rings: The Rings of Power").seasons(1).actors(List.of(new Actor("Morfydd Clark"), new Actor("Ismael Cruz Cordova"), new Actor("Charlie Vickers"))).build());
        otherTvs.add(Tv.builder().title("Euphoria").seasons(3).actors(List.of(new Actor("Zendaya"), new Actor("Hunter Schafer"), new Actor("Angus Cloud"))).build());
        otherTvs.add(Tv.builder().title("The Boys").seasons(4).actors(List.of(new Actor("Karl Urban"), new Actor("Jack Quaid"), new Actor("Antony Starr"))).build());
        otherTvs.add(Tv.builder().title("Moon Knight").seasons(1).actors(List.of(new Actor("Oscar Isaac"), new Actor("Ethan Hawke"), new Actor("May Calamawy"))).build());
        otherTvs.add(Tv.builder().title("The Sandman").seasons(2).actors(List.of(new Actor("Tom Sturridge"), new Actor("Boyd Holbrook"), new Actor("Patton Oswalt"))).build());
        otherTvs.add(Tv.builder().title("Ozark").seasons(4).actors(List.of(new Actor("Jason Bateman"), new Actor("Laura Linney"), new Actor("Sofia Hublitz"))).build());
        otherTvs.add(Tv.builder().title("Inventing Anna").seasons(1).actors(List.of(new Actor("Anna Chlumsky"), new Actor("Julia Garner"), new Actor("Arian Moayed"))).build());
        otherTvs.add(Tv.builder().title("The Walking Dead").seasons(11).actors(List.of(new Actor("Andrew Lincoln"), new Actor("Norman Reedus"), new Actor("Melissa McBride"))).build());
        otherTvs.add(Tv.builder().title("Yellowstone").seasons(5).actors(List.of(new Actor("Kevin Costner"), new Actor("Luke Grimes"), new Actor("Kelly Reilly"))).build());
        otherTvs.add(Tv.builder().title("Reacher").seasons(1).actors(List.of(new Actor("Alan Ritchson"), new Actor("Malcolm Goodwin"), new Actor("Willa Fitzgerald"))).build());

        for (Tv t : otherTvs) {
            log.info("Get all tvs");
            List<Tv> tvs = Unirest.get(baseUrl).asObject(new GenericType<List<Tv>>() {
            }).getBody();
            log.info(tvs.toString());

            Thread.sleep(5000);

            log.info("Add tv");
            String addResult = Unirest.post(baseUrl).contentType("application/json").body(t).asString().getBody();
            log.info(addResult);

            Thread.sleep(5000);

            log.info("Get all tvs");
            tvs = Unirest.get(baseUrl).asObject(new GenericType<List<Tv>>() {
            }).getBody();
            log.info(tvs.toString());

            Thread.sleep(5000);

            log.info("Get one tv");
            int randomInt = random.nextInt(tvs.size());
            Tv foundTv = Unirest.get(baseUrl + "/" + tvs.get(randomInt).getTitle()).asObject(Tv.class).getBody();
            log.info(foundTv.toString());

            Thread.sleep(5000);

            log.info("Update one tv");
            String updateResult = Unirest.put(baseUrl + "/" + tvs.get(randomInt).getTitle()).contentType("application/json").body(foundTv.toBuilder().seasons(5).build()).asString().getBody();
            log.info(updateResult);

            Thread.sleep(5000);

            log.info("Get all tvs");
            tvs = Unirest.get(baseUrl).asObject(new GenericType<List<Tv>>() {
            }).getBody();
            log.info(tvs.toString());

            Thread.sleep(5000);

            log.info("Remove one tv");
            Unirest.delete(baseUrl + "/" + tvs.get(random.nextInt(tvs.size())).getTitle()).asEmpty();

            Thread.sleep(5000);
        }
    }
}
