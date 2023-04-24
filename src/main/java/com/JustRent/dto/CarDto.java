package com.JustRent.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CarDto {

    @NotNull
    @NotEmpty
    private String brand;

    @NotNull
    @NotEmpty
    private String model;

    @NotNull
    @NotEmpty
    private int year;

    @NotNull
    @NotEmpty
    private String registrationNumber;

    @NotNull
    @NotEmpty
    private int mileage;

    @NotNull
    @NotEmpty
    private double engineCapacity;

    @NotNull
    @NotEmpty
    private String fuelType;

    @NotNull
    @NotEmpty
    private int power;

    @NotNull
    @NotEmpty
    private String dimensions;

    @NotNull
    @NotEmpty
    private int numberOfSeats;

    @NotNull
    @NotEmpty
    private double rentalPriceHourly;

    @NotNull
    @NotEmpty
    private double rentalPriceDailyUpTo5;

    @NotNull
    @NotEmpty
    private double rentalPriceDaily5To10;

    @NotNull
    @NotEmpty
    private double rentalPriceDailyAbove10;

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

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
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
}
