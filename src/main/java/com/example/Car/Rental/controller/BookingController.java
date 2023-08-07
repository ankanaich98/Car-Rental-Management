package com.example.Car.Rental.controller;

import com.example.Car.Rental.entity.Booking;
import com.example.Car.Rental.entity.Car;
import com.example.Car.Rental.service.BookingService;
import com.example.Car.Rental.service.CarService;
import com.example.Car.Rental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookingController {
    private final BookingService bookingService;
    private final CustomerService customerService;
    private final CarService carService;
    @Autowired
    public BookingController(BookingService bookingService, CustomerService customerService, CarService carService) {
        this.bookingService = bookingService;
        this.customerService = customerService;
        this.carService = carService;
    }
    @GetMapping("/bookings")
    public String showBookingList(@RequestParam(value = "dateFrom",required = false) LocalDate dateFrom,
                                  @RequestParam(value = "dateTo",required = false) LocalDate dateTo,
                                  Model model){
        List<Booking> listAllBookings;
        if(dateFrom!=null && dateTo!=null){
            listAllBookings= bookingService.listSearchedBookings(dateFrom,dateTo);
        }
        else {
            listAllBookings=bookingService.listAllBookings();
        }
        model.addAttribute("bookings",listAllBookings);
        model.addAttribute("formTitle", "Booking List");
        return "bookings";
    }

    @GetMapping("/booking/show-form")
    public String showForm(Model model){
        model.addAttribute("bookings", new Booking());
        model.addAttribute("formTitle", "Booking Entry");
        model.addAttribute("customers", customerService.listAllCustomers());
        model.addAttribute("cars", carService.listAllCars().stream().filter(car -> car.isAvailability()).collect(Collectors.toList()));
        return "addBooking";
    }
    @PostMapping("/booking/save")
    public String saveForm(Booking booking, RedirectAttributes redirectAttributes) {
        String Message = (booking.getId()!=null) ? "Entry updated Successfully" : "Entry created Successfully";
        bookingService.save(booking);
        Car car = carService.get(booking.getCar().getId());
        car.setAvailability(false);
        carService.save(car);
        redirectAttributes.addFlashAttribute("Message",Message);
        return "redirect:/bookings";
    }
    @GetMapping("/booking/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
        Booking bookings= bookingService.get(id);
        model.addAttribute("bookings", bookings);
//            model.addAttribute("addBranch","Edit Branch (ID " +id+")" );
        model.addAttribute("formTitle", "Booking Update");
        model.addAttribute("customers", customerService.listAllCustomers());
        model.addAttribute("cars", carService.listAllCars().stream().filter(car -> car.isAvailability() || car.getId().equals(bookings.getCar().getId())).collect(Collectors.toList()));
        return "addBooking";
    }
    @PostMapping("/booking/delete/{id}")
    public String deleteBranch(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Booking booking =bookingService.get(id);
        Car car = carService.get(booking.getCar().getId());
        car.setAvailability(true);
        carService.save(car);
        bookingService.delete(id);
        String Message ="Entry deleted Successfully";
        redirectAttributes.addFlashAttribute("Message",Message);
        return "redirect:/bookings";
    }

    /*@ModelAttribute("customers")
    public List<Customer> customers() {
        return customerService.listAllCustomers();
    }*/

}
