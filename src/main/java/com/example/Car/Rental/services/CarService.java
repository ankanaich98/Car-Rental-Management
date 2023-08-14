package com.example.Car.Rental.service;

import com.example.Car.Rental.entity.Branch;
import com.example.Car.Rental.entity.Car;
import com.example.Car.Rental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public List<Car> listAllCars() {
        return (List<Car>) carRepository.findAll();
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public Car get(Long id) {
        Optional<Car> optionalUser = carRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        else {
            return null;
        }
    }
    public void delete(Long id){
        carRepository.deleteById(id);
    }


}
