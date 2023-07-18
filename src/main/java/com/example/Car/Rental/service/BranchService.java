package com.example.Car.Rental.service;

import com.example.Car.Rental.entity.Branch;
import com.example.Car.Rental.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {
    private final BranchRepository branchRepository;
    @Autowired
    public BranchService(BranchRepository repository) {
        this.branchRepository = repository;
    }
    public List<Branch> listAllBranches(){
        return (List<Branch>) branchRepository.findAll();
    }
    public void save(Branch branch){
        branchRepository.save(branch);
    }
}
