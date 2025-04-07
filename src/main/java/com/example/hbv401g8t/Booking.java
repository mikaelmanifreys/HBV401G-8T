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
        int flightPrice = trip.getFlights().get(0).getPrice();
        int hotelPrice = trip.getHotel().get(0).getPrice();
        int dayTourPrice = trip.getDayTours().get(0).getPrice();
        long days = dateTo.toEpochDay() - dateFrom.toEpochDay();
        int totalHotelCost = hotelPrice * (int) days;
        int totalCost = flightPrice + totalHotelCost + dayTourPrice;

        return "Bókun til: " + trip.getDestination() + " Verð: " + totalCost + " Frá: " + dateFrom + " Til: " + dateTo;
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
