package com.example.hbv401g8t;

import javafx.event.ActionEvent;
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

    public static void main(String[] args) {

    }

    public void afboka(ActionEvent actionEvent) {
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
