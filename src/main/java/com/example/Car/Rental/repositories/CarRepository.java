package com.example.Car.Rental.repositories;
import com.example.Car.Rental.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CarRepository  extends JpaRepository<Car,Long> {
}
