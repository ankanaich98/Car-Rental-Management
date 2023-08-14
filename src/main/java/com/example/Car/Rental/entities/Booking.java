package com.example.Car.Rental.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookedFor;

    @Column(nullable = false)
    private Integer daysBooked;

    @Column(nullable = false, length = 30)
    private String pickup;

    @Column(nullable = false, length = 30)
    private String destination;

    @Column(nullable = false)
    private Long charge;

    @Column(nullable = false)
    private LocalDate endDate;

    @ManyToOne()
    private Customer customer;

    @ManyToOne()
    private Car car;

    @PrePersist
    public void calculateChargeAndEndDate() {
        charge = getCar().getRate()*daysBooked;
        endDate=bookedFor.plusDays(daysBooked);
    }
    @PreUpdate
    public void calculateUpdatedChargeAndEndDate() {
        charge = getCar().getRate()*daysBooked;
        endDate=bookedFor.plusDays(daysBooked);
    }

}
