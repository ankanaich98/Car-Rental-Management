package com.example.Car.Rental.service;

import com.example.Car.Rental.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    @Autowired
    public BookingService(BookingRepository repository) {
        this.bookingRepository = repository;
    }


}
