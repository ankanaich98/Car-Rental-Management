package com.example.Car.Rental;

import com.example.Car.Rental.entities.*;
import com.example.Car.Rental.repositories.*;
import com.example.Car.Rental.enums.Authority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class CarRentalApplicationTests {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    void contextLoads() {
    }

    @Test
    public void addNewBranch() {
        Branch branch = new Branch();
        branch.setAddress("New Street");
        branch.setName("London");
        branchRepository.save(branch);
    }

    @Test
    public void addNewCar() {
        Car car = new Car();
        car.setMake("Nissan");
        car.setModel("GTR");
        car.setYear("2000");
        car.setRate(5000L);
        carRepository.save(car);
    }

    @Test
    public void addNewCustomer() {
        Customer customer = new Customer();
        customer.setName("Ankan Aich");
        customer.setLicense("888221");
        customer.setContact("01845682170");
        customer.setEmail("ankan98aich@gmail.com");
        customerRepository.save(customer);
    }

    @Test
    public void addNewBooking() {
        Booking booking = new Booking();
//		booking.setCar(carRepository.getReferenceById(4L));
//		booking.setCustomer(customerRepository.getReferenceById(7L));
        booking.setPickup("Dhaka");
        booking.setDestination("Chittagong");
        booking.setDaysBooked(2);
//        booking.setBookedFor(LocalDateTime.now());
        Optional<Customer> customer = customerRepository.findById(7L);
        Customer presentCustomer = (customer.isPresent()) ? customer.get() : null;
        booking.setCustomer(presentCustomer);
        Optional<Car> car = carRepository.findById(4L);
        Car presentCar = (car.isPresent()) ? car.get() : null;
        booking.setCar(presentCar);
        booking.setCharge(2 * (presentCar.getRate()));
        bookingRepository.save(booking);
    }
    @Test
    public void addNewUser(){
        User user = new User();
        user.setName("Ankan");
        user.setPassword("Aich");
        user.setRole(Authority.Admin);
        Optional<Branch> branch = branchRepository.findById(13L);
        Branch presentBranch = (branch.isPresent()) ? branch.get() : null;
        user.setBranch(presentBranch);
        userRepository.save(user);
    }

}
