package com.example.FirstSpringApplication.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MovieDTO {
    private Integer id;
    private String name;
    private Integer year;
    private String genre;
    private Double rating;
    private Integer count;

    private List<String> actor = new ArrayList<>();
    private List<String> director = new ArrayList<>();
}
