package com.example.hbv401g8t;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {
    public TextField fxNotandanafn;
    public PasswordField fxLykilord;
    public Button fxSkraInnButton;
    public Label fxLoginInfo;

    public void login(String username, String password) {
        username = fxNotandanafn.getText();
        password = fxLykilord.getText();;

        if (username.equals("admin") && password.equals("admin")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hbv401g8t/Administrator.fxml"));
                Stage stage = (Stage) fxSkraInnButton.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                stage.setTitle("Administrator");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Customer customer = new Customer(fxNotandanafn.getText());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hbv401g8t/Trip-Planner.fxml"));
                Parent root = loader.load();
                TripPlannerController controller = loader.getController();
                controller.setLoggedInCustomer(customer);
                controller.loadTripPackages();
                Stage stage = (Stage) fxSkraInnButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Trip Planner");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void SkraInn(ActionEvent actionEvent) {
        String username = fxNotandanafn.getText();
        String password = fxLykilord.getText();

        if (!isValidInput(username) || !isValidInput(password)) {
            fxLoginInfo.setText("Username and password must be at least 3 characters long and cannot be empty.");
        } else {
            login(username, password);
        }
    }

    private Boolean isValidInput(String input) {
        return input != null && input.trim().length() >= 3;
    }
}


