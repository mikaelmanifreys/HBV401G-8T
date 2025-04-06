package com.example.hbv401g8t;

import java.util.ArrayList;
import java.util.List;

public class TripPlanner {
    private static TripPlanner instance = null;
    private String passengerName;
    private int numberOfPassengers;
    private int tripLength;
    private List<TripPackage> tripPackages;
    private List<Booking> bookings = new ArrayList<>();

    public TripPlanner() {
        tripPackages = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public static TripPlanner getInstance() {
        if (instance == null) {
            instance = new TripPlanner();
        }
        return instance;
    }

    public void addFlightToPackage(int packageId, Flights flight) {
        if (packageId >= 0 && packageId < tripPackages.size()) {
            tripPackages.get(packageId).addFlight(flight);
        }
    }

    public void addHotelToPackage(int packageId, Hotels hotel) {
        if (packageId >= 0 && packageId < tripPackages.size()) {
            tripPackages.get(packageId).addHotel(hotel);
        }
    }

    public void addDayTourToPackage(int packageId, DayTours dayTour) {
        if (packageId >= 0 && packageId < tripPackages.size()) {
            tripPackages.get(packageId).addDayTour(dayTour);
        }
    }


    public void viewTripDetails(int packageId) {

    }

    public void createTripPackage(String destination, double price) {
        tripPackages.add(new TripPackage(destination, price));
    }

    public List<TripPackage> getTripPackages() {
        return tripPackages;
    }

    public void addTripPackage(TripPackage pkg) {
        tripPackages.add(pkg);
    }
}
