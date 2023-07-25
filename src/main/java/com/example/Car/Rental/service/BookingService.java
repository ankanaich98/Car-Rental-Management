package com.example.Car.Rental.service;

import com.example.Car.Rental.entity.Booking;
import com.example.Car.Rental.entity.Branch;
import com.example.Car.Rental.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    @Autowired
    public BookingService(BookingRepository repository) {
        this.bookingRepository = repository;
    }

    public List<Booking> listAllBookings() {
        return (List<Booking>) bookingRepository.findAll();
    }

    public void save(Booking booking){bookingRepository.save(booking);};
    public Booking get(Long id) {
        Optional<Booking> optionalUser = bookingRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        else {
            return null;
        }
    }
    public void delete(Long id){
        bookingRepository.deleteById(id);
    }

}
