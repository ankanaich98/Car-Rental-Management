package com.example.Car.Rental.controller;

import com.example.Car.Rental.entity.Branch;
import com.example.Car.Rental.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class BranchController {
    private final BranchService branchService;
    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }
    @GetMapping("/branches")
    public String showUserList(Model model){
        List<Branch> listAllBranches = branchService.listAllBranches();
        model.addAttribute("branches",listAllBranches);
        return "branches";
    }
    @GetMapping("/branch/show-form")
    public String showForm(Model model){
        model.addAttribute("branches", new Branch());
        return "addBranch";
    }

    @PostMapping("/branch/save")
    public String saveForm(Branch branch) {
        branchService.save(branch);
        return "redirect:/branches";
    }
    @GetMapping("/branch/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
            Branch branches= branchService.get(id);
            model.addAttribute("branches", branches);
//            model.addAttribute("addBranch","Edit Branch (ID " +id+")" );
            return "addBranch";
    }
    @GetMapping("/branch/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        branchService.delete(id);
        return "redirect:/branches";
    }


}
