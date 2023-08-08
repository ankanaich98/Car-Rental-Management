package com.example.Car.Rental.controller;

import com.example.Car.Rental.entity.Booking;
import com.example.Car.Rental.entity.Branch;
import com.example.Car.Rental.entity.Car;
import com.example.Car.Rental.entity.Customer;
import com.example.Car.Rental.service.BookingService;
import com.example.Car.Rental.service.BranchService;
import com.example.Car.Rental.service.CarService;
import com.example.Car.Rental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller

public class HomeController {
    private final CarService carService;
    private final CustomerService customerService;
    private final BranchService branchService;
    private final BookingService bookingService;

    @Autowired
    public HomeController(CarService carService, CustomerService customerService, BranchService branchService, BookingService bookingService) {
        this.carService = carService;
        this.customerService = customerService;
        this.branchService = branchService;
        this.bookingService = bookingService;
    }

    @RequestMapping("/")
    public String mainPage(Model model){
        model.addAttribute("formTitle", "Dashboard");
        List<Car> listAllCars = carService.listAllCars();
        model.addAttribute("cars",listAllCars.size());
        model.addAttribute("availableCars",listAllCars.stream().filter(car -> car.isAvailability()==true).collect(Collectors.toList()).size());
        List<Customer> listAllCustomers = customerService.listAllCustomers();
        model.addAttribute("customers",listAllCustomers.size());
        List<Branch> listAllBranches = branchService.listAllBranches();
        model.addAttribute("branches",listAllBranches.size());
        List<Booking> listAllBookings = bookingService.listAllBookings();
        List<String> destinations = listAllBookings.stream().map(booking -> booking.getDestination()).collect(Collectors.toList());
        List<String> cars =listAllBookings.stream().map(booking -> booking.getCar().getMake()+" "+booking.getCar().getModel()+" "+booking.getCar().getYear()).collect(Collectors.toList());




        Map<String, Long> frequencyMapDestinations = destinations.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        //Optional<Map.Entry<String, Long>> topDestination = frequencyMap.entrySet().stream().max(Map.Entry.comparingByValue());
        List<Map.Entry<String, Long>> sortedDestinations = frequencyMapDestinations.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
        Map.Entry<String, Long> firstDestination = sortedDestinations.get(0);
        Map.Entry<String, Long> secondDestination = sortedDestinations.get(1);
        Map.Entry<String, Long> thirdDestination = sortedDestinations.get(2);

        model.addAttribute("firstDestinationName",firstDestination.getKey());
        model.addAttribute("firstDestinationCount",firstDestination.getValue());
        model.addAttribute("secondDestinationName",secondDestination.getKey());
        model.addAttribute("secondDestinationCount",secondDestination.getValue());
        model.addAttribute("thirdDestinationName",thirdDestination.getKey());
        model.addAttribute("thirdDestinationCount",thirdDestination.getValue());




        Map<String, Long> frequencyMapCars = cars.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println(cars);
        System.out.println(frequencyMapCars);
        List<Map.Entry<String, Long>> sortedCars = frequencyMapCars.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
        Map.Entry<String, Long> firstCar = sortedCars.get(0);
        Map.Entry<String, Long> secondCar = sortedCars.get(1);
        Map.Entry<String, Long> thirdCar = sortedCars.get(2);

        model.addAttribute("firstCarName",firstCar.getKey());
        model.addAttribute("firstCarCount",firstCar.getValue());
        model.addAttribute("secondCarName",secondCar.getKey());
        model.addAttribute("secondCarCount",secondCar.getValue());
        model.addAttribute("thirdCarName",thirdCar.getKey());
        model.addAttribute("thirdCarCount",thirdCar.getValue());


        return "home";
    }
}
