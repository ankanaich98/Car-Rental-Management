package com.example.Car.Rental.scheduler;

import com.example.Car.Rental.entity.Booking;
import com.example.Car.Rental.entity.Car;
import com.example.Car.Rental.repository.CarRepository;
import com.example.Car.Rental.service.BookingService;
import com.example.Car.Rental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class Scheduler {
    private final CarService carService;
    private final BookingService bookingService;
    private final CarRepository carRepository;

    @Autowired
    public Scheduler(CarRepository carRepository, CarService carService, BookingService bookingService, CarRepository carRepository1) {
        this.carService = carService;
        this.bookingService = bookingService;
        this.carRepository = carRepository1;
    }

    @Scheduled(cron = "0 * 16 * * ?")
    public void cronJobScheduler() {
//        Date now = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
//        String currentDate = formatter.format(now);
        LocalDate date = LocalDate.from(ZonedDateTime.now());
//        List<Car> cars = carService.listAllCars();
        List<Booking> listAllForCurrentDate = bookingService.listAllForCurrentDate(date);
        for (Booking booking : listAllForCurrentDate) {
//            if (booking.getBookedFor().isBefore(date)) {
//                Car car = carService.get(booking.getCar().getId());
//                car.setAvailability(true);
//                carRepository.save(car);
//            } else {
//            }
            Car car = carService.get(booking.getCar().getId());
            car.setAvailability(true);
            carRepository.save(car);
        }
    }
//            *    *    *    *    *    *
//            -    -    -    -    -    -
//            |    |    |    |    |    |
//            |    |    |    |    |    + year [optional]
//            |    |    |    |    +----- day of week (0 - 7) (Sunday=0 or 7)
//            |    |    |    +---------- month (1 - 12)
//            |    |    +--------------- day of month (1 - 31)
//            |    +-------------------- hour (0 - 23)
//            +------------------------- min (0 - 59)
}
