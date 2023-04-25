package com.JustRent.services;

import com.JustRent.dto.CarDto;
import com.JustRent.dto.UserDto;
import com.JustRent.models.Car;
import com.JustRent.models.User;
import com.JustRent.repositories.CarRepository;
import com.JustRent.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class CarService implements ICarServices {
    private final CarRepository carRepository;

    @Override
    public Car addNewCarToRent(CarDto carDto, Long loggedInUserId) {
       Car car = new Car();
       car.setBrand(carDto.getBrand());
       car.setModel(carDto.getModel());
       car.setYear(carDto.getYear());
       car.setRegistrationNumber(carDto.getRegistrationNumber());
       car.setMileage(carDto.getMileage());
       car.setEngineCapacity(carDto.getEngineCapacity());
       car.setFuelType(carDto.getFuelType());
       car.setPower(carDto.getPower());
       car.setDimensions(carDto.getDimensions());
       car.setNumberOfSeats(carDto.getNumberOfSeats());
       car.setRentalPriceHourly(carDto.getRentalPriceHourly());
       car.setRentalPriceDailyUpTo5(carDto.getRentalPriceDailyUpTo5());
       car.setRentalPriceDailyAbove10(carDto.getRentalPriceDaily5To10());
       car.setRentalPriceDailyAbove10(carDto.getRentalPriceDailyAbove10());
       car.setUserId(loggedInUserId);
       return carRepository.save(car);
    }

    @Override
    public Car findCarByName(String brand, String model) {
        return null;
    }

    @Override
    public List<CarDto> findAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map((car) -> mapToCarDto(car))
                .collect(Collectors.toList());
    }

    private CarDto mapToCarDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setBrand(car.getBrand());
        carDto.setModel(car.getModel());
        carDto.setYear(car.getYear());
        carDto.setRegistrationNumber(car.getRegistrationNumber());
        carDto.setMileage(car.getMileage());
        carDto.setEngineCapacity(car.getEngineCapacity());
        carDto.setFuelType(car.getFuelType());
        carDto.setPower(car.getPower());
        carDto.setNumberOfSeats(car.getNumberOfSeats());
        carDto.setDimensions(car.getDimensions());
        carDto.setRentalPriceHourly(car.getRentalPriceHourly());
        carDto.setRentalPriceDailyUpTo5(car.getRentalPriceDailyUpTo5());
        carDto.setRentalPriceDaily5To10(car.getRentalPriceDaily5To10());
        carDto.setRentalPriceDailyAbove10(car.getRentalPriceDailyAbove10());
        carDto.setUserId(car.getUserId());
        return carDto;
    }
}
