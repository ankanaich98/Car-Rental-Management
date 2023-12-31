package com.example.Car.Rental.controllers;

import com.example.Car.Rental.dtos.PopularRevenueDTO;
import com.example.Car.Rental.entities.Booking;
import com.example.Car.Rental.entities.Branch;
import com.example.Car.Rental.entities.Car;
import com.example.Car.Rental.entities.Customer;
import com.example.Car.Rental.repositories.UserRepository;
import com.example.Car.Rental.services.BookingService;
import com.example.Car.Rental.services.BranchService;
import com.example.Car.Rental.services.CarService;
import com.example.Car.Rental.services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller

public class HomeController {
    private final CarService carService;
    private final CustomerService customerService;
    private final BranchService branchService;
    private final BookingService bookingService;

    private final UserRepository userRepository;

    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @Autowired
    public HomeController(CarService carService, CustomerService customerService, BranchService branchService, BookingService bookingService, UserRepository userRepository) {
        this.carService = carService;
        this.customerService = customerService;
        this.branchService = branchService;
        this.bookingService = bookingService;
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping("/")
    public String mainPage(Model model,HttpServletRequest request,Authentication authentication) {
        request.getSession().setAttribute("loggedInAs",userRepository.findUserByUsername(authentication.getName()).getName());
        model.addAttribute("formTitle", "Dashboard");
        List<Car> listAllCars = carService.listAllCars();
        model.addAttribute("cars", listAllCars.size());
        model.addAttribute("availableCars", listAllCars.stream().filter(car -> car.isAvailability() == true).collect(Collectors.toList()).size());
        List<Customer> listAllCustomers = customerService.listAllCustomers();
        model.addAttribute("customers", listAllCustomers.size());
        List<Branch> listAllBranches = branchService.listAllBranches();
        model.addAttribute("branches", listAllBranches.size());
        List<Booking> listAllBookings = bookingService.listAllBookings();
        List<String> destinations = listAllBookings.stream().map(booking -> booking.getDestination()).collect(Collectors.toList());
        List<String> cars = listAllBookings.stream().map(booking -> booking.getCar().getMake() + " " + booking.getCar().getModel() + " " + booking.getCar().getYear()).collect(Collectors.toList());


        NumberFormat revenueFormat = NumberFormat.getInstance();


        Map<String, Long> frequencyMapDestinations = destinations.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        List<Map.Entry<String, Long>> sortedDestinations = frequencyMapDestinations.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(5)
                .collect(Collectors.toList());


        List<PopularRevenueDTO> popularDestinationsList = new ArrayList<>();
        for (Map.Entry<String, Long> destinationMap :
                sortedDestinations) {
            Double destinationRevenue = listAllBookings.stream().filter(s -> s.getDestination().equals(destinationMap.getKey())).mapToDouble(s -> s.getCharge()).sum();
            PopularRevenueDTO popularDestination = new PopularRevenueDTO(destinationMap.getKey(), destinationMap.getValue(), revenueFormat.format(destinationRevenue));
            popularDestinationsList.add(popularDestination);
        }
        model.addAttribute("popularDestinationsList", popularDestinationsList);

        /*Map.Entry<String, Long> firstDestination = sortedDestinations.get(0);
        Map.Entry<String, Long> secondDestination = sortedDestinations.get(1);
        Map.Entry<String, Long> thirdDestination = sortedDestinations.get(2);
        Double firstDestinationRevenue =listAllBookings.stream().filter(s-> s.getDestination().equals(firstDestination.getKey())).mapToDouble(s -> s.getCharge()).sum();
        Double secondDestinationRevenue =listAllBookings.stream().filter(s-> s.getDestination().equals(secondDestination.getKey())).mapToDouble(s -> s.getCharge()).sum();
        Double thirdDestinationRevenue =listAllBookings.stream().filter(s-> s.getDestination().equals(thirdDestination.getKey())).mapToDouble(s -> s.getCharge()).sum();*/



        /*List<PopularRevenueDTO> popularDestinationsList = new ArrayList<>();
        PopularRevenueDTO popularDestinationsOne = new PopularRevenueDTO(firstDestination.getKey(), firstDestination.getValue(),revenueFormat.format(firstDestinationRevenue));
        PopularRevenueDTO popularDestinationsTwo = new PopularRevenueDTO(secondDestination.getKey(), secondDestination.getValue(),revenueFormat.format(secondDestinationRevenue));
        PopularRevenueDTO popularDestinationsThree = new PopularRevenueDTO(thirdDestination.getKey(), thirdDestination.getValue(),revenueFormat.format(thirdDestinationRevenue));
        popularDestinationsList.add(popularDestinationsOne);
        popularDestinationsList.add(popularDestinationsTwo);
        popularDestinationsList.add(popularDestinationsThree);*/

//        model.addAttribute("firstDestinationRevenue",revenueFormat.format(firstDestinationRevenue));
//        model.addAttribute("secondDestinationRevenue",revenueFormat.format(secondDestinationRevenue));
//        model.addAttribute("thirdDestinationRevenue",revenueFormat.format(thirdDestinationRevenue));
//        model.addAttribute("firstDestinationName",firstDestination.getKey());
//        model.addAttribute("firstDestinationCount",firstDestination.getValue());
//        model.addAttribute("secondDestinationName",secondDestination.getKey());
//        model.addAttribute("secondDestinationCount",secondDestination.getValue());
//        model.addAttribute("thirdDestinationName",thirdDestination.getKey());
//        model.addAttribute("thirdDestinationCount",thirdDestination.getValue());


        Map<String, Long> frequencyMapCars = cars.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        List<Map.Entry<String, Long>> sortedCars = frequencyMapCars.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(5)
                .collect(Collectors.toList());


        List<PopularRevenueDTO> popularCarsList = new ArrayList<>();
        for (Map.Entry<String, Long> carMap :
                sortedCars) {
            Double carRevenue = listAllBookings.stream().filter(s -> carMap.getKey().equals(s.getCar().getMake() + " " + s.getCar().getModel() + " " + s.getCar().getYear())).mapToDouble(s -> s.getCharge()).sum();
            PopularRevenueDTO popularDestination = new PopularRevenueDTO(carMap.getKey(), carMap.getValue(), revenueFormat.format(carRevenue));
            popularCarsList.add(popularDestination);
        }
        model.addAttribute("popularCarsList", popularCarsList);
//        Map.Entry<String, Long> firstCar = sortedCars.get(0);
//        Map.Entry<String, Long> secondCar = sortedCars.get(1);
//        Map.Entry<String, Long> thirdCar = sortedCars.get(2);
//       Double firstCarRevenue =listAllBookings.stream().filter(s -> firstCar.getKey().equals(s.getCar().getMake()+" "+s.getCar().getModel()+" "+s.getCar().getYear())).mapToDouble(s -> s.getCharge()).sum();
//        Double secondCarRevenue =listAllBookings.stream().filter(s -> secondCar.getKey().equals(s.getCar().getMake()+" "+s.getCar().getModel()+" "+s.getCar().getYear())).mapToDouble(s -> s.getCharge()).sum();
//        Double thirdCarRevenue =listAllBookings.stream().filter(s -> thirdCar.getKey().equals(s.getCar().getMake()+" "+s.getCar().getModel()+" "+s.getCar().getYear())).mapToDouble(s -> s.getCharge()).sum();
//
//        model.addAttribute("firstCarRevenue",revenueFormat.format(firstCarRevenue));
//        model.addAttribute("secondCarRevenue",revenueFormat.format(secondCarRevenue));
//        model.addAttribute("thirdCarRevenue",revenueFormat.format(thirdCarRevenue));
//        model.addAttribute("firstCarName",firstCar.getKey());
//        model.addAttribute("firstCarCount",firstCar.getValue());
//        model.addAttribute("secondCarName",secondCar.getKey());
//        model.addAttribute("secondCarCount",secondCar.getValue());
//        model.addAttribute("thirdCarName",thirdCar.getKey());
//        model.addAttribute("thirdCarCount",thirdCar.getValue());


        return "home";
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/pie-chart", method = RequestMethod.GET)
    @ResponseBody
    public List<PopularRevenueDTO> getLocationChart() {
        NumberFormat revenueFormat = NumberFormat.getInstance();

        List<Booking> listAllBookings = bookingService.listAllBookings();
        List<String> destinations = listAllBookings.stream().map(booking -> booking.getDestination()).collect(Collectors.toList());


        Map<String, Long> frequencyMapDestinations = destinations.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        List<Map.Entry<String, Long>> sortedDestinations = frequencyMapDestinations.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(5)
                .collect(Collectors.toList());


        List<PopularRevenueDTO> popularDestinationsList = new ArrayList<>();
        for (Map.Entry<String, Long> destinationMap :
                sortedDestinations) {
            Double destinationRevenue = listAllBookings.stream().filter(s -> s.getDestination().equals(destinationMap.getKey())).mapToDouble(s -> s.getCharge()).sum();
            PopularRevenueDTO popularDestination = new PopularRevenueDTO(destinationMap.getKey(), destinationMap.getValue(), revenueFormat.format(destinationRevenue));
            popularDestinationsList.add(popularDestination);
        }

        return popularDestinationsList;
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/bar-chart", method = RequestMethod.GET)
    @ResponseBody
    public List<PopularRevenueDTO> getCarChart() {
        NumberFormat revenueFormat = NumberFormat.getInstance();

        List<Booking> listAllBookings = bookingService.listAllBookings();
        List<String> cars = listAllBookings.stream().map(booking -> booking.getCar().getMake() + " " + booking.getCar().getModel() + " " + booking.getCar().getYear()).collect(Collectors.toList());


        Map<String, Long> frequencyMapCars = cars.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        List<Map.Entry<String, Long>> sortedCars = frequencyMapCars.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(5)
                .collect(Collectors.toList());


        List<PopularRevenueDTO> popularCarsList = new ArrayList<>();
        for (Map.Entry<String, Long> carMap :
                sortedCars) {
            Double carRevenue = listAllBookings.stream().filter(s -> carMap.getKey().equals(s.getCar().getMake() + " " + s.getCar().getModel() + " " + s.getCar().getYear())).mapToDouble(s -> s.getCharge()).sum();
            PopularRevenueDTO popularDestination = new PopularRevenueDTO(carMap.getKey(), carMap.getValue(), revenueFormat.format(carRevenue));
            popularCarsList.add(popularDestination);
        }

        return popularCarsList;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/sign-out")
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        // .. perform logout
        this.logoutHandler.logout(request, response, authentication);
        redirectAttributes.addFlashAttribute("logout", "You have been logged out");
        return "redirect:/login";
    }
}
