package com.example.Car.Rental.repository;
import com.example.Car.Rental.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CarRepository  extends JpaRepository<Car,Long> {
}
