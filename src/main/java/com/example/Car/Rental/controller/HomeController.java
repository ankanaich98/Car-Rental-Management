package com.example.Car.Rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller

public class HomeController {
    @RequestMapping("/")
    public String mainPage(){
        return "home";
    }
}
