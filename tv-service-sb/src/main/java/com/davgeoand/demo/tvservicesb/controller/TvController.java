package com.davgeoand.demo.tvservicesb.controller;

import com.davgeoand.demo.tvservicesb.model.Tv;
import com.davgeoand.demo.tvservicesb.service.TvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tvs")
public class TvController {
    @Autowired
    TvService tvService;

    @PostMapping(consumes = MediaType.ALL_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String postTvRequest(@RequestBody Tv tv) {
        log.info("Posting a new tv request");
        return "Tv saved: " + tvService.createTv(tv);
    }

    @GetMapping(value = "/{tvTitle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Tv getTvByTitleRequest(@PathVariable("tvTitle") String tvTitle) {
        log.info("Getting tv by title request");
        return tvService.findTv(tvTitle);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Tv> getAllTvsRequest() {
        log.info("Getting all tvs request");
        return tvService.findAllTvs();
    }

    @PutMapping(value = "/{tvTitle}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String putTvByNameRequest(@PathVariable("tvTitle") String tvTitle, @RequestBody Tv tv) {
        log.info("Putting tv by name request");
        return "Tv updated: " + tvService.updateTv(tvTitle, tv);
    }

    @DeleteMapping(value = "/{tvTitle}")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteTvByTitleRequest(@PathVariable("tvTitle") String tvTitle) {
        log.info("Deleting tv by title request");
        tvService.removeTv(tvTitle);
        return "Tv removed: " + tvTitle;
    }
}
