package com.example.hbv401g8t;

import java.util.ArrayList;
import java.util.List;

public class TripPackage {
    private List<Flights> flights;
    private List<Hotels> hotels;
    private List<DayTours> dayTours;

    private String destination;
    private double price;

    public ArrayList<Package> packages = new ArrayList<>();

    public TripPackage(String destination, double price) {
        this.destination = destination;
        this.price = price;
        this.flights = flights;
        this.hotels = hotels;
        this.dayTours = dayTours;
    }

    public void addFlight(Flights flight) {
        flights.add(flight);
    }

    public void addHotel(Hotels hotel) {
        hotels.add(hotel);
    }

    public void addDayTour(DayTours dayTour) {
        dayTours.add(dayTour);
    }

    public List<Flights> getFlights() {
        return flights;
    }

    public double getPrice() {
        return price;
    }

    public String getDestination() {
        return destination;
    }

    public List<Hotels> getHotel() {
        return hotels;
    }

    public List<DayTours> getDayTours() {
        return dayTours;
    }
}
