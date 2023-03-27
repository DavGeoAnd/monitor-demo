package com.davgeoand.demo.tvservicesb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class Tv {
    private String title;
    private List<Actor> actors;
    private int seasons;
}
