package com.example.Car.Rental.components;

import com.example.Car.Rental.entities.*;
import com.example.Car.Rental.enums.Authority;
import com.example.Car.Rental.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CarRepository carRepository;

    private final BranchRepository branchRepository;

    private final CustomerRepository customerRepository;

    private final BookingRepository bookingRepository;

    private  final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public DataInitializer(CarRepository carRepository, BranchRepository branchRepository, CustomerRepository customerRepository, BookingRepository bookingRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.carRepository = carRepository;
        this.branchRepository = branchRepository;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        if (bookingRepository.count() == 0) {
            // Insert fixed day names into the database
            //insertFixedDays();
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

        if(userRepository.findUserByUsername("ankan") == null) {
            Branch branch = new Branch();
            branch.setName("Main Branch");
            branch.setAddress("Dhaka");
            branch = branchRepository.saveAndFlush(branch);

            User newUser = new User();
            newUser.setName("Ankan Aich");
            newUser.setUsername("ankan");
            newUser.setBranch(branch);
            newUser.setRole(Authority.ADMIN);

            newUser.setPassword(passwordEncoder.encode("123456"));

            userRepository.save(newUser);
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