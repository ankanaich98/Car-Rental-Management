package com.example.Car.Rental.repository;
import com.example.Car.Rental.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBookingByEndDateBetween(LocalDate dateFrom,LocalDate dateTo);

}
