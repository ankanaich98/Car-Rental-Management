package com.example.Car.Rental.controllers;

import com.example.Car.Rental.entities.User;
import com.example.Car.Rental.services.BranchService;
import com.example.Car.Rental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final BranchService branchService;

    @Autowired
    public UserController(UserService userService, BranchService branchService) {
        this.userService = userService;
        this.branchService = branchService;
    }
    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listAllUsers = userService.listAllUsers();
        model.addAttribute("users",listAllUsers);
        model.addAttribute("formTitle", "User List");
        return "users";
    }
    @GetMapping("/user/show-form")
    public String showForm(Model model){
        model.addAttribute("users", new User());
        model.addAttribute("formTitle", "User Entry");
        model.addAttribute("branches", branchService.listAllBranches());
        return "addUser";
    }
    @PostMapping("/user/save")
    public String saveForm(User user, RedirectAttributes redirectAttributes) {
        String Message = (user.getId()!=null) ? "Entry updated Successfully" : "Entry created Successfully";
        userService.save(user);
        redirectAttributes.addFlashAttribute("Message",Message);
        return "redirect:/users";
    }
    @GetMapping("/user/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
        User users= userService.get(id);
        model.addAttribute("users", users);
        model.addAttribute("formTitle", "User Update");
        model.addAttribute("branches", branchService.listAllBranches());
        return "addUser";
    }
    @PostMapping("/user/delete/{id}")
    public String deleteBranch(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        userService.delete(id);
        String Message ="Entry deleted Successfully";
        redirectAttributes.addFlashAttribute("Message",Message);
        return "redirect:/users";
    }
}
