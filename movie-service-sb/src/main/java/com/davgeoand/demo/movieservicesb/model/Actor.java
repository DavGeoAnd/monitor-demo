package com.davgeoand.demo.movieservicesb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class Actor {
    private String name;
}
