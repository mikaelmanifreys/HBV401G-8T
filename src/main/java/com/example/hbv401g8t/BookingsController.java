package com.example.hbv401g8t;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingsController {
    public Button fxAfboka;
    public ListView<Booking> fxBokanir;
    public Button fxTilBakaButton;
    public TripPlanner tripPlanner;
    public Label fxInnskradurNotandi;
    public Label fxBokunUpplysingarLabel;
    private Customer loggedInCustomer;

    @FXML
    public void initialize() {
        tripPlanner = TripPlanner.getInstance();
    }

    public void updateBookingList() {
        fxBokanir.getItems().clear();
        for (Booking booking : TripPlanner.getInstance().getBookings()) {
            if (booking.getCustomer().getName().equals(loggedInCustomer.getName())) {
                fxBokanir.getItems().add(booking);
            }
        }
    }


    public void afboka(ActionEvent actionEvent) {
        int selectedIndex = fxBokanir.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Booking selectedBooking = fxBokanir.getItems().get(selectedIndex);
            TripPlanner.getInstance().getBookings().remove(selectedBooking);
            Customer customer = selectedBooking.getCustomer();
            if (customer != null) {
                customer.getBookings().remove(selectedBooking);
            }
            fxBokanir.getItems().remove(selectedIndex);
        }
        fxBokunUpplysingarLabel.setText("");

    }

    public void tilBaka(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hbv401g8t/Trip-Planner.fxml"));
            Parent root = loader.load();
            TripPlannerController controller = loader.getController();
            controller.setLoggedInCustomer(loggedInCustomer);
            controller.loadTripPackages();
            Stage stage = (Stage) fxTilBakaButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Trip Planner");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLoggedInCustomer(Customer customer) {
        if (customer == null) {
            System.out.println("Viðvörun: customer er null í setLoggedInCustomer");
            return;
        }
        this.loggedInCustomer = customer;
        fxInnskradurNotandi.setText("Innskráður Notandi: " + loggedInCustomer.getName());
        updateBookingList();
    }


    public void bokunValin(MouseEvent mouseEvent) {
        Booking valinBokun = fxBokanir.getSelectionModel().getSelectedItem();
        TripPackage trip = valinBokun.getTrip();
        Flights flug = trip.getFlights().get(0);
        Hotels hotel = trip.getHotel().get(0);
        DayTours dayTour = trip.getDayTours().get(0);
        fxBokunUpplysingarLabel.setText("Flug frá: " + flug.getDeparturePlace() + " til: " + flug.getDestination() + " þann: " + flug.getDate() + " klukkan: " + flug.getDepartureTime() + "-" + flug.getArrivalTime() + ".\nGisting á: " + hotel.getHotelName() + " frá: " + valinBokun.getDateFrom() + " til: " + valinBokun.getDateTo() + ".\nDagsferð: " + dayTour.getTourName() + " þann: " + dayTour.getTourDate() + ". Staðsetning: " + dayTour.getTourLocation());

    }
}

