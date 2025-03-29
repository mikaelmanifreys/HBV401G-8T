package com.example.hbv401g8t;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerId;
    private String email;
    private String name;
    private TripPackage tripPackage;

    private List<Booking> bookings;

    public Customer(String name, int customerId, String email) {
        this.name = name;
        this.customerId = customerId;
        this.email = email;
        this.bookings = new ArrayList<>();
    }

    public void bookTrip(TripPackage tripPackage) {
        Booking booking = new Booking(this, tripPackage);
        bookings.add(booking);
    }

    public void viewBooking() {
        for (Booking booking : bookings) {
            System.out.println("Booking for: ");
        }
    }

    public void cancelBooking() {
        bookings.removeIf(b -> b.getTrip().equals(tripPackage));
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
