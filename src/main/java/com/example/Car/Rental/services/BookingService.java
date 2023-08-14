package com.example.Car.Rental.services;

import com.example.Car.Rental.entities.Booking;
import com.example.Car.Rental.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Booking> listSearchedBookings(LocalDate dateFrom,LocalDate dateTo) {
        return (List<Booking>) bookingRepository.findBookingByEndDateBetween(dateFrom,dateTo);
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

    public List<Booking> listAllForCurrentDate(LocalDate date){
        List<Booking> allBookings = bookingRepository.findAll();
//        List<Booking> currentDateBookings = null;
//        for(Booking booking:allBookings){
//            if(booking.getBookedFor()==date){
//                currentDateBookings.add(booking);
//            }
//        }
        return allBookings.stream().filter(booking -> booking.getBookedFor().plusDays(booking.getDaysBooked()).isEqual(date)).collect(Collectors.toList());
    }

}
