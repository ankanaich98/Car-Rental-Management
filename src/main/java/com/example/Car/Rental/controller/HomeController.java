package com.example.Car.Rental.controller;

import com.example.Car.Rental.entity.Branch;
import com.example.Car.Rental.entity.Car;
import com.example.Car.Rental.entity.Customer;
import com.example.Car.Rental.service.BranchService;
import com.example.Car.Rental.service.CarService;
import com.example.Car.Rental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller

public class HomeController {
    private final CarService carService;
    private final CustomerService customerService;

    private final BranchService branchService;
    @Autowired
    public HomeController(CarService carService, CustomerService customerService, BranchService branchService) {
        this.carService = carService;
        this.customerService = customerService;
        this.branchService = branchService;
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
        return "home";
    }
}
