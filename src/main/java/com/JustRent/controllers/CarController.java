package com.JustRent.controllers;

import com.JustRent.dto.CarDto;
import com.JustRent.models.Car;
import com.JustRent.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/add-car")
    private String addNewCar () {
        return "addNewCarToRent";
    }

    @PostMapping("/add-car")
    public String addCar(@ModelAttribute("car") CarDto carDto) {
        carService.addNewCarToRent(carDto);
        return "redirect:/homepage";
    }


}
