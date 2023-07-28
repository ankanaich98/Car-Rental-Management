package com.example.Car.Rental.controller;

import com.example.Car.Rental.entity.Car;
import com.example.Car.Rental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

public class HomeController {
    private final CarService carService;

    @Autowired
    public HomeController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping("/")
    public String mainPage(Model model){
        model.addAttribute("formTitle", "Dashboard");
        List<Car> listAllCars = carService.listAllCars();
        model.addAttribute("cars",listAllCars);
        return "home";
    }
}
