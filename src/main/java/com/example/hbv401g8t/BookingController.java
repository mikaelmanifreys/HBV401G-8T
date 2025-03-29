package com.example.hbv401g8t;

import java.util.List;

public class BookingController {
    private List<TripPackage> tripPackages;
    private List<Booking> bookings;
    private TripPlanner tripPlanner;

    public BookingController(TripPlanner tripPlanner) {
        this.tripPlanner = tripPlanner;
    }

    //public boolean cancelBooking(int bookingId) {}



    public void bookTrip(Customer customer, int packageId) {
        TripPackage tripPackage = tripPlanner.getTripPackages().get(packageId);
        customer.bookTrip(tripPackage);
    }

    public void addFlightToPackage(int packageId, Flights flight) {
        tripPlanner.addFlightToPackage(packageId, flight);
    }

    public void addHotelToPackage(int packageId, Hotels hotel) {
        tripPlanner.addHotelToPackage(packageId, hotel);
    }

    public void addDayTourToPackage(int packageId, DayTours dayTour) {
        tripPlanner.addDayTourToPackage(packageId, dayTour);
    }

    public List<TripPackage> getAvailableTripPackages() {
        return tripPlanner.getTripPackages();
    }
}
