package com.example.Car.Rental.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime bookedFor;

    @Column(nullable = false)
    private Integer daysBooked;

    @Column(nullable = false, length = 30)
    private String pickup;

    @Column(nullable = false, length = 30)
    private String destination;

    @Column(nullable = false)
    private Long charge;

    @OneToOne()
    private Customer customer;

    @OneToOne()
    private Car car;

    @PrePersist
    public void calculateCharge() {
        charge = getCar().getRate()*daysBooked;
    }
    @PreUpdate
    public void calculateUpdatedCharge() {
        charge = getCar().getRate()*daysBooked;
    }
}
