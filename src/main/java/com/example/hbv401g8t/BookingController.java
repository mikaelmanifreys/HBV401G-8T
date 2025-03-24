package com.example.hbv401g8t;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BookingController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
