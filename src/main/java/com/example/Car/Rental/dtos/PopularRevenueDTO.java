package com.example.Car.Rental.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class PopularRevenueDTO {
    private String name;
    private Long count;
    private String revenue;
}
