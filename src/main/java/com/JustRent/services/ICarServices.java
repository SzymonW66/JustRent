package com.JustRent.services;

import com.JustRent.dto.CarDto;
import com.JustRent.models.Car;

import java.util.List;

public interface ICarServices {
    Car addNewCarToRent (CarDto carDto);
    Car findCarByName (String brand, String model);
    List<CarDto> findAllCars();

}
