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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BranchController {
    private final BranchService branchService;
    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }
    @GetMapping("/branches")
    public String showBranchList(Model model){
        List<Branch> listAllBranches = branchService.listAllBranches();
        model.addAttribute("branches",listAllBranches);
        model.addAttribute("formTitle", "Branch List");
        return "branches";
    }
    @GetMapping("/branch/show-form")
    public String showForm(Model model){
        model.addAttribute("branches", new Branch());
        model.addAttribute("formTitle", "Branch Entry");
        return "addBranch";
    }

    @PostMapping("/branch/save")
    public String saveForm(Branch branch, RedirectAttributes redirectAttributes) {
        String Message = (branch.getId()!=null) ? "Entry updated Successfully" : "Entry created Successfully";
        branchService.save(branch);
        redirectAttributes.addFlashAttribute("Message",Message);
        return "redirect:/branches";
    }
    @GetMapping("/branch/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
            Branch branches= branchService.get(id);
            model.addAttribute("branches", branches);
//            model.addAttribute("addBranch","Edit Branch (ID " +id+")" );
            return "addBranch";
    }
    @PostMapping("/branch/delete/{id}")
    public String deleteBranch(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        branchService.delete(id);
        String Message ="Entry deleted Successfully";
        redirectAttributes.addFlashAttribute("Message",Message);
        return "redirect:/branches";
    }


}
