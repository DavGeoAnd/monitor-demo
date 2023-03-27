package com.davgeoand.demo.tvservicesb.service;

import com.davgeoand.demo.tvservicesb.model.Tv;
import com.davgeoand.demo.tvservicesb.repository.TvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvService {
    @Autowired
    TvRepository tvRepository;

    public List<Tv> findAllTvs() {
        return tvRepository.getTvs();
    }

    public String createTv(Tv bodyTv) {
        if (tvRepository.addTv(bodyTv)) {
            return "Added";
        } else {
            return "Not Added";
        }
    }

    public Tv findTv(String tvTitle) {
        return tvRepository.getTv(tvTitle);
    }

    public String updateTv(String tvTitle, Tv bodyTv) {
        if (tvRepository.updateTv(tvTitle, bodyTv)) {
            return "Updated";
        } else {
            return "Not Updated";
        }
    }

    public void removeTv(String tvTitle) {
        tvRepository.removeTv(tvTitle);
    }
}
