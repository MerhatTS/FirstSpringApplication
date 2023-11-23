package com.example.FirstSpringApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    public Integer id;
    private String name;
    private Boolean isAvailable;
    private Double price;

}
