package com.example.Car.Rental.controller;

import com.example.Car.Rental.entity.Customer;
import com.example.Car.Rental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/customers")
    private String showCustomerList(Model model){
        List<Customer> listAllCustomers = customerService.listAllCustomers();
        model.addAttribute("customers",listAllCustomers);
        model.addAttribute("formTitle","Customer List");
        return "customers";
    }
    @GetMapping("/customer/show-form")
    private String showForm(Model model){
        model.addAttribute("customers",new Customer());
        model.addAttribute("formTitle","Customer Entry");
        return "addCustomer";
    }
    @PostMapping("/customer/save")
    private String saveForm(Customer customer,RedirectAttributes redirectAttributes){
        String Message = (customer.getId()!=null) ? "Entry updated Successfully" : "Entry created Successfully";
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("Message",Message);
        return "redirect:/customers";
    }

    @GetMapping("/customer/edit/{id}")
    private String showEditForm(@PathVariable("id") Long id,Model model){
        Customer customers = customerService.get(id);
        model.addAttribute("customers",customers);
        model.addAttribute("formTitle", "Customer Update");
        return "addCustomer";
    }
    @PostMapping("/customer/delete/{id}")
    private String deleteCustomer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
      customerService.delete(id);
        String Message ="Entry deleted Successfully";
        redirectAttributes.addFlashAttribute("Message",Message);
      return "redirect:/customers";
    }


}
