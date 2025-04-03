package com.example.hbv401g8t;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministratorController {
    public Button fxTilBakaTakki;
    public Button fxBaetaVidTakki;
    public DatePicker fxHotelDagsetningTil;
    public DatePicker fxHotelDagsetningFra;
    public TextField fxHotelStadsetning;
    public TextField fxHotelNafn;
    public TextField fxFlugBrottfararstadur;
    public TextField fxFlugKomustaður;
    public DatePicker fxFlugDagsetning;
    public DatePicker fxDayTourDagsetning;

    @FXML
    public void initialize() {

    }

    public static void main(String[] args) {

    }

    public void tilBaka(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hbv401g8t/Trip-Planner.fxml"));
            Stage stage = (Stage) fxTilBakaTakki.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void baetaVid(ActionEvent actionEvent) {
        
    }
}
