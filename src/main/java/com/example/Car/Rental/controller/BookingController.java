package com.example.Car.Rental.controller;

import com.example.Car.Rental.entity.Booking;
import com.example.Car.Rental.entity.Branch;
import com.example.Car.Rental.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookingController {
    private final BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @GetMapping("/bookings")
    public String showBookingList(Model model){
        List<Booking> listAllBookings = bookingService.listAllBookings();
        model.addAttribute("bookings",listAllBookings);
        model.addAttribute("formTitle", "Booking List");
        return "bookings";
    }
    @GetMapping("/booking/show-form")
    public String showForm(Model model){
        model.addAttribute("bookings", new Booking());
        model.addAttribute("formTitle", "Booking Entry");
        return "addBooking";
    }
    @PostMapping("/booking/save")
    public String saveForm(Booking booking, RedirectAttributes redirectAttributes) {
        String Message = (booking.getId()!=null) ? "Entry updated Successfully" : "Entry created Successfully";
        bookingService.save(booking);
        redirectAttributes.addFlashAttribute("Message",Message);
        return "redirect:/bookings";
    }
    @GetMapping("/booking/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
        Booking bookings= bookingService.get(id);
        model.addAttribute("bookings", bookings);
//            model.addAttribute("addBranch","Edit Branch (ID " +id+")" );
        return "addBooking";
    }
    @PostMapping("/booking/delete/{id}")
    public String deleteBranch(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        bookingService.delete(id);
        String Message ="Entry deleted Successfully";
        redirectAttributes.addFlashAttribute("Message",Message);
        return "redirect:/bookings";
    }

}
