package com.example.Car.Rental.component;

import com.example.Car.Rental.entity.Booking;
import com.example.Car.Rental.entity.Car;
import com.example.Car.Rental.entity.Customer;
import com.example.Car.Rental.repository.BookingRepository;
import com.example.Car.Rental.repository.CarRepository;
import com.example.Car.Rental.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    private final BookingRepository bookingRepository;


    @Autowired
    public DataInitializer(CarRepository carRepository, CustomerRepository customerRepository, BookingRepository bookingRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (bookingRepository.count() == 0) {
            // Insert fixed day names into the database
            for(int i=0;i<5;i++) {
                Customer customer = new Customer();
                customer.setName("Dummy Name");
                customer.setLicense("Dummy License");
                customer.setContact("01*********");
                customer.setEmail("dummy@gmail.com");
                customerRepository.save(customer);
                Car car = new Car();
                car.setMake("Dummy Make");
                car.setModel("Dummy Model");
                car.setYear("1998 :" +(i+1));
                car.setRate(1000L);
                car.setAvailability(false);
                car.setRegistration("Dummy");
                carRepository.save(car);
                Booking booking = new Booking();
                booking.setCustomer(customer);
                booking.setCar(car);
                booking.setPickup("Dummy");
                booking.setDestination("Dummy "+(i+1));
                booking.setBookedFor(LocalDate.now());
                booking.setDaysBooked(1+i);
                bookingRepository.save(booking);
            }
        }
    }

//    private void insertFixedDays() {
//        List<String> fixedDayNames = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
//        for (String dayName : fixedDayNames) {
//            Day day = new Day();
//            day.setName(dayName);
//            dayRepository.save(day);
//        }
//    }
}