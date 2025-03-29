package com.example.hbv401g8t;

import java.util.ArrayList;
import java.util.List;

public class TripPlanner {
    private String passengerName;
    private int numberOfPassengers;
    private int tripLength;
    private List<TripPackage> tripPackages;

    public TripPlanner(){
        tripPackages = new ArrayList<>();
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
        if (packageId >= 0 && packageId < tripPackages.size()) {
            TripPackage pkg = tripPackages.get(packageId);
            System.out.println("Destination: " + pkg.getDestination());
            System.out.println("Price: " + pkg.getPrice());
            for (Flights f : pkg.getFlights()) {
                System.out.println(f.getFlightDetails());
            }
        }
    }

    public void createTripPackage(String destination, double price) {
        tripPackages.add(new TripPackage(destination, price));
    }

    public List<TripPackage> getTripPackages() {
        return tripPackages;
    }

}
