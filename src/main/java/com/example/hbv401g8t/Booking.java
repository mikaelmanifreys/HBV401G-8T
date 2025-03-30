package com.example.hbv401g8t;

public class Booking {
    private int bookingId;
    private Customer customer;
    private TripPackage trip;
    private String status;

    public Booking(Customer customer, TripPackage trip) {
        this.customer = customer;
        this.trip = trip;
    }


    public boolean processPayment() {
        return true;
    }

    public TripPackage getTrip() {
        return trip;
    }
}
