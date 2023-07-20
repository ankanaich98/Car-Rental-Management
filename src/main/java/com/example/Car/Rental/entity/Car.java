package com.example.Car.Rental.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 20)
    private String make;
    @Column(nullable = false,length = 20)
    private String model;
    @Column(nullable = false,length = 10)
    private String year;
    @Column(nullable = false,length =10)
    private String registration;
    @Column(nullable = false)
    private boolean availability;
    @Column(nullable = false)
    private Long rate;
}
