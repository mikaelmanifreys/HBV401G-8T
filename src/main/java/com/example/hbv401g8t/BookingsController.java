package com.example.hbv401g8t;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingsController {
    public Button fxAfboka;
    public ListView fxBokanir;
    public Button fxTilBakaButton;
    public TripPlanner tripPlanner;

    @FXML
    public void initialize(){
        tripPlanner = new TripPlanner();
        tripPlanner.addTripPackage(new TripPackage("Reykjavik", 1000));
        tripPlanner.addTripPackage(new TripPackage("London", 2000));
        fxBokanir.getItems().clear();
        for (TripPackage tripPackage : tripPlanner.getTripPackages()) {
            fxBokanir.getItems().add("Trip destination: " + tripPackage.getDestination());
        }

    }

    public void afboka(ActionEvent actionEvent) {
        int selectedIndex = fxBokanir.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tripPlanner.getTripPackages().remove(selectedIndex);
            fxBokanir.getItems().remove(selectedIndex);
        }
    }

    public void tilBaka(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hbv401g8t/Trip-Planner.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) fxTilBakaButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
