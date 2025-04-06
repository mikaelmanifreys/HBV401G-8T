package com.example.hbv401g8t;

import java.time.LocalDate;

public class Booking {
    private int bookingId;
    private Customer customer;
    private TripPackage trip;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Booking(Customer customer, TripPackage trip, LocalDate dateFrom, LocalDate dateTo) {
        this.customer = customer;
        this.trip = trip;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "Bókun til: " + trip.getDestination() + " Verð: " + trip.getPrice() + " Frá: " + dateFrom + " Til: " + dateTo;
    }

    public boolean processPayment() {
        return true;
    }

    public TripPackage getTrip() {
        return trip;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}
