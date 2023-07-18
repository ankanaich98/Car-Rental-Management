package com.example.Car.Rental;

import com.example.Car.Rental.entity.Branch;
import com.example.Car.Rental.repository.BranchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarRentalApplicationTests {
	@Autowired
	private BranchRepository branchRepository;

	@Test
	void contextLoads() {
	}
	@Test
	public void addNewBranch(){
	Branch branch = new Branch();
	branch.setAddress("New Street");
	branch.setName("London");
	branchRepository.save(branch);
	}

}
