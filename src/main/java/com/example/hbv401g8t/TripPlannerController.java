package com.example.hbv401g8t;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TripPlannerController {
    public Button fxOnBookings;
    public Button fxStadfestingartakki;
    public Label fxStadfestingartexti;
    public Button fxSkraInn;
    public Button fxDateSubmit;
    public Button fxTilBakaButton;
    public TextField fxNotandanafn;
    public Button fxSkraInnButton;
    public PasswordField fxLykilord;
    public TextField fxLeitaBrottfarastadur;
    public TextField fxLeitaKomustadur;
    public TextField fxLeitaFlugnumer;
    public TextField fxLeitaHotelNafn;
    public TextField fxLeitaHotelStadsetning;
    public TextField fxLeitaHotelID;
    public TextField fxLeitaHeitiFerdar;
    public TextField fxleitaStadsetningFerdar;
    public TextField fxLeitaIdFerdar;
    private List<TripPackage> tripPackages;
    private List<Booking> bookings;
    private TripPlanner tripPlanner;
    private LocalDate fraDate;
    private LocalDate tilDate;

    @FXML
    private ListView<String> fxFlights;
    @FXML
    private ListView<String> fxHotels;
    @FXML
    private ListView<String> fxDayTours;
    @FXML
    private DatePicker fxFraDate;
    @FXML
    private DatePicker fxTilDate;
    @FXML
    private Label fxDateLabel;

    @FXML
    public void initialize() {

    }


    public TripPlannerController() {

    }


    @FXML
    private void stadfestaValHandler() {
        String flight = fxFlights.getSelectionModel().getSelectedItem();
        String hotel = fxHotels.getSelectionModel().getSelectedItem();
        String tour = fxDayTours.getSelectionModel().getSelectedItem();

        System.out.println("Confirmed: " + flight + ", " + hotel + ", " + tour);
    }

    public TripPlannerController(TripPlanner tripPlanner) {
        this.tripPlanner = tripPlanner;
    }


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

    public void onDateSubmit() {
        this.tilDate = fxTilDate.getValue();
        this.fraDate = fxFraDate.getValue();

        if (fraDate == null || tilDate == null) {
            fxDateLabel.setText("Vinsamlegast veldu dagsetningu!");
        } else {
            System.out.println("Ferð frá: " + fraDate + " til: " + tilDate);
        }
    }

    public List<TripPackage> getAvailableTripPackages() {
        return tripPlanner.getTripPackages();
    }

    public void StadfestaVal(ActionEvent actionEvent) {
        String flight = fxFlights.getSelectionModel().getSelectedItem();
        String hotel = fxHotels.getSelectionModel().getSelectedItem();
        String tour = fxDayTours.getSelectionModel().getSelectedItem();

        if (flight == null || hotel == null || tour == null || fraDate == null || tilDate == null) {
            fxStadfestingartexti.setText("Vinsamlegast veldu úr öllum flokkum.");
        } else {
            fxStadfestingartexti.setText("Valið: " + flight + ", " + hotel + ", " + tour + " frá: " + fraDate + " til: " + tilDate);
        }
    }

    public void SkraInn(ActionEvent actionEvent) {
        if (fxNotandanafn.getText().equals("admin") && fxLykilord.getText().equals("admin")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hbv401g8t/Administrator.fxml"));
                Stage stage = (Stage) fxSkraInnButton.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hbv401g8t/Bookings.fxml"));
                Stage stage = (Stage) fxSkraInnButton.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadTripPackages() {
        fxFlights.getItems().clear();
        fxHotels.getItems().clear();
        fxDayTours.getItems().clear();
        List<TripPackage> packages = TripPlanner.getInstance().getTripPackages();
        for (TripPackage pkg : packages) {
            if (!pkg.getFlights().isEmpty()) {
                Flights f = pkg.getFlights().get(0);
                fxFlights.getItems().add(f.getFlightPlaces());
            }
            if (!pkg.getHotel().isEmpty()) {
                Hotels h = pkg.getHotel().get(0);
                fxHotels.getItems().add(h.getHotelName());
            }
            if (!pkg.getDayTours().isEmpty()) {
                DayTours d = pkg.getDayTours().get(0);
                fxDayTours.getItems().add(d.getTourName());
            }
        }
    }

    public void tilBaka(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hbv401g8t/Bookings.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) fxTilBakaButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
