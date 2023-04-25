package com.JustRent.models;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "cars")
public class Car {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "mileage")
    private int mileage;

    @Column(name = "engine_capacity")
    private double engineCapacity;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "power")
    private int power;

    @Column(name = "dimensions")
    private String dimensions;

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    @Column(name = "rental_price_hourly")
    private double rentalPriceHourly;

    @Column(name = "rental_price_daily_up_to_5")
    private double rentalPriceDailyUpTo5;

    @Column(name = "rental_price_daily_5_to_10")
    private double rentalPriceDaily5To10;

    @Column(name = "rental_price_daily_above_10")
    private double rentalPriceDailyAbove10;

    @Column(name = "UserId")
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public double getRentalPriceHourly() {
        return rentalPriceHourly;
    }

    public void setRentalPriceHourly(double rentalPriceHourly) {
        this.rentalPriceHourly = rentalPriceHourly;
    }

    public double getRentalPriceDailyUpTo5() {
        return rentalPriceDailyUpTo5;
    }

    public void setRentalPriceDailyUpTo5(double rentalPriceDailyUpTo5) {
        this.rentalPriceDailyUpTo5 = rentalPriceDailyUpTo5;
    }

    public double getRentalPriceDaily5To10() {
        return rentalPriceDaily5To10;
    }

    public void setRentalPriceDaily5To10(double rentalPriceDaily5To10) {
        this.rentalPriceDaily5To10 = rentalPriceDaily5To10;
    }

    public double getRentalPriceDailyAbove10() {
        return rentalPriceDailyAbove10;
    }

    public void setRentalPriceDailyAbove10(double rentalPriceDailyAbove10) {
        this.rentalPriceDailyAbove10 = rentalPriceDailyAbove10;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

