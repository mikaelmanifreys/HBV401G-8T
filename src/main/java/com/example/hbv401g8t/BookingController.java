package com.example.hbv401g8t;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class BookingController {
    private List<TripPackage> tripPackages;
    private List<Booking> bookings;
    private TripPlanner tripPlanner;

    @FXML
    private ListView<String> fxFlights;
    @FXML
    private ListView<String> fxHotels;
    @FXML
    private ListView<String> fxDayTours;

    @FXML
    public void initialize() {
        fxFlights.getItems().addAll("Reykjavík → Akureyri", "Reykjavík → Egilsstaðir");
        fxHotels.getItems().addAll("Hótel Akureyri", "Hótel Austurland");
        fxDayTours.getItems().addAll("Hvalaskoðun", "Gönguferð í fjöllum");
    }


    public BookingController() {

    }


    @FXML
    private void stadfestaValHandler() {
        String flight = fxFlights.getSelectionModel().getSelectedItem();
        String hotel = fxHotels.getSelectionModel().getSelectedItem();
        String tour = fxDayTours.getSelectionModel().getSelectedItem();

        System.out.println("Confirmed: " + flight + ", " + hotel + ", " + tour);
    }

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
