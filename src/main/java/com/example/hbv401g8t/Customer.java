package com.example.hbv401g8t;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Booking> bookings;

    public Customer(String name) {
        this.name = name;
        this.bookings = new ArrayList<>();
    }

    public void bookTrip(TripPackage tripPackage, LocalDate dateFrom, LocalDate dateTo) {
        Booking booking = new Booking(this, tripPackage, dateFrom, dateTo);
        bookings.add(booking);
    }

    public void viewBooking() {

    }
    

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public String getName() {
        return name;
    }

}
