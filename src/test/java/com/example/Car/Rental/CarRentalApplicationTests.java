package com.example.Car.Rental;

import com.example.Car.Rental.entity.Branch;
import com.example.Car.Rental.entity.Car;
import com.example.Car.Rental.repository.BranchRepository;
import com.example.Car.Rental.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarRentalApplicationTests {
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private CarRepository carRepository;

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
	@Test
	public void addNewCar(){
		Car car = new Car();
		car.setMake("Nissan");
		car.setModel("GTR");
		car.setRate(5000L);
		carRepository.save(car);
	}

}
