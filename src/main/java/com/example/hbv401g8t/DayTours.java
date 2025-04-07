package com.example.hbv401g8t;


import java.time.LocalDate;


public class DayTours {
    private int tourId;
    private String name;
    private String location;
    private LocalDate date;
    private int price;

    public DayTours(String name, String location, int tourId, LocalDate date, int price) {
        this.tourId = tourId;
        this.name = name;
        this.location = location;
        this.date = date;
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getTourId() {
        return tourId;
    }

    public String getTourName() {
        return name;
    }

    public String getTourLocation() {
        return location;
    }

    public LocalDate getTourDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

}
