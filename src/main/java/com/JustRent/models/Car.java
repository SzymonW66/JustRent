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

    @Column(name = "dimension")
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

    public Car(Long id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
    }

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


}

