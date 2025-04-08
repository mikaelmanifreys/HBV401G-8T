package com.example.hbv401g8t.hotel;

import java.util.Date;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private String region;
    private Booking booking = new Booking(0, 0, new Date(), new Date(), "");
    private Amenities amenities = new Amenities(true, true, false, true, false, false, true, false, false, true, true, false);
    private int availableRooms;
    private int ratings;
    private int price;


    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getRegion() {
        return this.region;
    }

    public int getAvailableRooms() {
        return this.availableRooms;
    }

    public int getRatings() {
        return this.ratings;
    }

    public Amenities getAmenities() {
        return this.amenities;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public int getPrice() {
        return this.price;
    }

    public void setAmenities(Amenities amenities) {
        this.amenities = amenities;
    }


    public void getHotel() {

    }

    public boolean addBooking(Booking booking) {
        this.booking = booking;
        return true;
    }

    public Booking getBooking() {
        return this.booking;
    }

    public void setBookings() {

    }

    public int updateAvailableRooms() {
        return this.availableRooms;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
