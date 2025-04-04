package com.example.hbv401g8t;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class AdministratorController {
    public Button fxTilBakaTakki;
    public Button fxBaetaVidTakki;
    public DatePicker fxHotelDagsetningTil;
    public DatePicker fxHotelDagsetningFra;
    public TextField fxHotelStadsetning;
    public TextField fxHotelNafn;
    public TextField fxFlugBrottfararstadur;
    public TextField fxFlugKomustadur;
    public DatePicker fxFlugDagsetning;
    public DatePicker fxDayTourDagsetning;
    public TextField fxDayTourNafn;
    public TextField fxFlugnumer;
    public TextField fxFlugfelag;
    public ComboBox fxFlugBrottfaratimiKlst;
    public ComboBox fxFlugBrottfaratimiMin;
    public ComboBox fxFlugKomutimiKlst;
    public ComboBox fxFlugKomutimiMin;
    public TextField fxHotelId;
    public TextField fxFjoldiLausraHerbergja;
    public TextField fxDayTourId;
    public TextField fxDayTourStadsetning;

    @FXML
    public void initialize() {
        ObservableList<Integer> hours = FXCollections.observableArrayList();
        ObservableList<Integer> minutes = FXCollections.observableArrayList(00, 10, 20, 30, 40, 50);
        for (int i = 0; i <= 23; i++) {
            hours.add(i);
        }
        fxFlugBrottfaratimiKlst.setItems(hours);
        fxFlugBrottfaratimiMin.setItems(minutes);
        fxFlugKomutimiKlst.setItems(hours);
        fxFlugKomutimiMin.setItems(minutes);
    }

    public static void main(String[] args) {

    }

    public void tilBaka(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hbv401g8t/Trip-Planner.fxml"));
            Parent root = loader.load();
            BookingController controller = loader.getController();
            controller.loadTripPackages();
            Stage stage = (Stage) fxTilBakaTakki.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void baetaVid(ActionEvent actionEvent) {
        String flugFra = fxFlugBrottfararstadur.getText();
        String flugTil = fxFlugKomustadur.getText();
        LocalDate flugDagsetning = fxFlugDagsetning.getValue();
        int flugBrottfararTimiKlst = (int) fxFlugBrottfaratimiKlst.getValue();
        int flugBrottfararTimiMin = (int) fxFlugBrottfaratimiMin.getValue();
        int flugKomutimiKlst = (int) fxFlugKomutimiKlst.getValue();
        int flugKomutimiMin = (int) fxFlugKomutimiMin.getValue();
        String flugnumer = fxFlugnumer.getText();
        String flugfelag = fxFlugfelag.getText();
        Flights flug = new Flights(flugnumer, flugfelag, flugTil, flugFra, flugDagsetning, flugBrottfararTimiKlst, flugBrottfararTimiMin, flugKomutimiKlst, flugKomutimiMin);
        String hotelNafn = fxHotelNafn.getText();
        String hotelStadsetning = fxHotelStadsetning.getText();
        LocalDate hotelDagsetningFra = fxHotelDagsetningFra.getValue();
        LocalDate hotelDagsetningTil = fxHotelDagsetningTil.getValue();
        int hotelId = Integer.parseInt(fxHotelId.getText());
        int lausHerbergi = Integer.parseInt(fxFjoldiLausraHerbergja.getText());
        Hotels hotel = new Hotels(hotelNafn, hotelStadsetning, hotelDagsetningFra, hotelDagsetningTil, hotelId, lausHerbergi);
        String dayTourNafn = fxDayTourNafn.getText();
        int dayTourId = Integer.parseInt(fxDayTourId.getText());
        String dayTourStadsetning = fxDayTourStadsetning.getText();
        LocalDate dayTourDate = fxDayTourDagsetning.getValue();
        DayTours dayTour = new DayTours(dayTourNafn, dayTourStadsetning, dayTourId, dayTourDate);
        TripPackage pkg = new TripPackage(flugTil, 999);
        pkg.addFlight(flug);
        pkg.addHotel(hotel);
        pkg.addDayTour(dayTour);
        TripPlanner.getInstance().addTripPackage(pkg);
        tilBaka(actionEvent);

    }
}
