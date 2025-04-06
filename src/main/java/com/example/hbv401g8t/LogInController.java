package com.example.hbv401g8t;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {
    public TextField fxNotandanafn;
    public PasswordField fxLykilord;
    public Button fxSkraInnButton;

    public static void main(String[] args) {

    }

    public void SkraInn(ActionEvent actionEvent) {
        if (fxNotandanafn.getText().equals("admin") && fxLykilord.getText().equals("admin")) {
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
}
