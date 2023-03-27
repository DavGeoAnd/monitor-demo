package com.davgeoand.demo.tvservicesb.repository;

import com.davgeoand.demo.tvservicesb.model.Actor;
import com.davgeoand.demo.tvservicesb.model.Tv;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TvRepository {
    private final List<Tv> tvs;

    public TvRepository() {
        tvs = new ArrayList<>();
        tvs.add(Tv.builder().title("Stranger Things").seasons(4).actors(List.of(new Actor("Millie Bobby Brown"), new Actor("Finn Wolfhard"), new Actor("Winona Ryder"))).build());
        tvs.add(Tv.builder().title("House of the Dragon").seasons(1).actors(List.of(new Actor("Rhys Ifans"), new Actor("Matt Smith"), new Actor("Fabien Frankel"))).build());
        tvs.add(Tv.builder().title("Better Call Saul").seasons(6).actors(List.of(new Actor("Bob Odenkirk"), new Actor("Rhea Seehorn"), new Actor("Jonathan Banks"))).build());
    }

    public List<Tv> getTvs() {
        return tvs;
    }

    public boolean addTv(Tv tv) {
        return tvs.add(tv);
    }

    public Tv getTv(String tvTitle) {
        Tv foundTv = null;
        for (Tv m : tvs) {
            if (m.getTitle().equals(tvTitle)) {
                foundTv = m;
            }
        }
        return foundTv;
    }

    public boolean updateTv(String tvTitle, Tv bodyTv) {
        boolean updated = false;
        for (Tv m : tvs) {
            if (m.getTitle().equals(tvTitle)) {
                tvs.remove(m);
                tvs.add(bodyTv);
                updated = true;
                break;
            }
        }
        return updated;
    }

    public void removeTv(String tvTitle) {
        tvs.removeIf(m -> m.getTitle().equals(tvTitle));
    }
}