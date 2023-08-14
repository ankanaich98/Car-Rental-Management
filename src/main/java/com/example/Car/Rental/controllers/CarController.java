package com.example.Car.Rental.controllers;

import com.example.Car.Rental.services.CarService;
import com.example.Car.Rental.entities.Car;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String showCarList(@RequestParam(value = "isAvailable", required = false) boolean isAvailable, Model model){
        List<Car> listAllCars;

        if (isAvailable) {
            listAllCars = carService.listAllCars().stream().filter(car -> car.isAvailability() == true).collect(Collectors.toList());
        } else {
            listAllCars = carService.listAllCars();
        }
        //listAllCars = (isAvailable) ? carService.listAllCars().stream().filter(car -> car.isAvailability() == true).collect(Collectors.toList()):carService.listAllCars();
        model.addAttribute("cars",listAllCars);
        model.addAttribute("formTitle", "Car List");
        return "cars";
    }
    @GetMapping("/car/show-form")
    public String showForm(Model model){
        model.addAttribute("cars", new Car());
        model.addAttribute("formTitle", "Car Entry");
        return "addCar";
    }
    @PostMapping("car/save")
    public String saveForm(Car car,RedirectAttributes redirectAttributes) {
        String Message = (car.getId()!=null) ? "Entry updated Successfully" : "Entry created Successfully";
        carService.save(car);
        redirectAttributes.addFlashAttribute("Message",Message);
        return "redirect:/cars";
    }
    @GetMapping("/car/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
        Car cars= carService.get(id);
        model.addAttribute("cars", cars);
        model.addAttribute("formTitle", "Car Update");
        return "addCar";
    }
    @PostMapping("/car/delete/{id}")
    public String deleteCar(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        carService.delete(id);
        String Message ="Entry deleted Successfully";
        redirectAttributes.addFlashAttribute("Message",Message);
        return "redirect:/cars";
    }

}