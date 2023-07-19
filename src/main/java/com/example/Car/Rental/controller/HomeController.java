package com.example.Car.Rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller

public class HomeController {
    @RequestMapping("/")
    public String mainPage(Model model){
        model.addAttribute("formTitle", "Dashboard");
        return "home";
    }
}
