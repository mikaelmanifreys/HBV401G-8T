package com.example.hbv401g8t;

import java.time.LocalDate;

public class Hotels {
    private int hotelId;
    private String name;
    private String location;
    private int availableRooms;
    private LocalDate dateFrom;
    private LocalDate dateTo;


    public Hotels(String name, String location, LocalDate dateFrom, LocalDate dateTo, int hotelId, int availableRooms) {
        this.hotelId = hotelId;
        this.name = name;
        this.location = location;
        this.availableRooms = availableRooms;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public boolean checkAvailability() {
        return availableRooms > 0;
    }

    public String getHotelDetails() {
        return "Hotel " + name + " ID: " + hotelId + "Date From:" + dateFrom + "To:" + dateTo + " Location: " + location + " Rooms available? " + checkAvailability();
    }

    public String getHotelName() {
        return name;
    }

    public String getHotelLocation() {
        return location;
    }

    public int getHotelId() {
        return hotelId;
    }

}
