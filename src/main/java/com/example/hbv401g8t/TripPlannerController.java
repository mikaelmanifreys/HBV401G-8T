package com.example.hbv401g8t;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TripPlannerController {
    public Button fxStadfestingartakki;
    public Label fxStadfestingartexti;
    public Button fxDateSubmit;
    public TextField fxLeitaBrottfarastadur;
    public TextField fxLeitaKomustadur;
    public TextField fxLeitaFlugnumer;
    public TextField fxLeitaHotelNafn;
    public TextField fxLeitaHotelStadsetning;
    public TextField fxLeitaHotelID;
    public TextField fxLeitaHeitiFerdar;
    public TextField fxleitaStadsetningFerdar;
    public TextField fxLeitaIdFerdar;
    public Button fxLeitaIFlugumButton;
    public Button fxLeitaIHotelumButton;
    public Button fxLeitaIDagsferdumButton;
    public Label fxBrottfarastadur;
    public Label fxAfangastadur;
    public Label fxDagsetning;
    public Label fxHamarkFarthega;
    public Label fxBrottfarartimi;
    public Label fxKomutimi;
    public Label fxHamarksverd;
    public Label fxHotelNafn;
    public Label fxHotelStadsetning;
    public Label fxHotelKomudagur;
    public Label fxHotelBrottfarardagur;
    public Label fxHotelLausHerbergi;
    public Label fxHotelID;
    public Label fxHeitiDagsferd;
    public Label fxDagsferdID;
    public Label fxDagsferdDagsetning;
    public Label fxDagsferdStadsetning;
    public Label fxFlugnumer;
    public Label fxInnskradurNotandi;
    public Button fxTilBakaButton;
    public Button fxBokanirButton;
    private TripPlanner tripPlanner;
    private LocalDate fraDate;
    private LocalDate tilDate;
    private Customer loggedInCustomer;

    @FXML
    private ListView<Flights> fxFlights;
    @FXML
    private ListView<Hotels> fxHotels;
    @FXML
    private ListView<DayTours> fxDayTours;
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


    public void onDateSubmit() {
        this.tilDate = fxTilDate.getValue();
        this.fraDate = fxFraDate.getValue();

        if (fraDate == null || tilDate == null) {
            fxDateLabel.setText("Vinsamlegast veldu dagsetningu!");
        } else {
            System.out.println("Ferð frá: " + fraDate + " til: " + tilDate);
        }
    }

    public void StadfestaVal(ActionEvent actionEvent) {
        Flights flight = fxFlights.getSelectionModel().getSelectedItem();
        Hotels hotel = fxHotels.getSelectionModel().getSelectedItem();
        DayTours tour = fxDayTours.getSelectionModel().getSelectedItem();

        if (flight == null || hotel == null || tour == null || fraDate == null || tilDate == null) {
            fxStadfestingartexti.setText("Vinsamlegast veldu úr öllum flokkum.");
        } else {
            TripPackage newPackage = new TripPackage(flight.getDestination(), 0);
            newPackage.addFlight(fxFlights.getSelectionModel().getSelectedItem());
            newPackage.addHotel(fxHotels.getSelectionModel().getSelectedItem());
            newPackage.addDayTour(fxDayTours.getSelectionModel().getSelectedItem());
            Booking newBooking = new Booking(loggedInCustomer, newPackage, fraDate, tilDate);
            TripPlanner.getInstance().addBooking(newBooking);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hbv401g8t/Bookings.fxml"));
                Parent root = loader.load();
                BookingsController controller = loader.getController();
                controller.setLoggedInCustomer(loggedInCustomer);
                controller.updateBookingList();
                Stage stage = (Stage) fxStadfestingartakki.getScene().getWindow();
                stage.setTitle("Bookings");
                stage.setScene(new Scene(root));
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
            fxFlights.getItems().addAll(pkg.getFlights());
            fxHotels.getItems().addAll(pkg.getHotel());
            fxDayTours.getItems().addAll(pkg.getDayTours());
        }
    }


    public void leitaIFlugum(ActionEvent actionEvent) {
        String leitBrottfararstadur = fxLeitaBrottfarastadur.getText().toLowerCase();
        String leitKomustadur = fxLeitaKomustadur.getText().toLowerCase();
        String leitFlugnumer = fxLeitaFlugnumer.getText().toLowerCase();

        fxFlights.getItems().clear();

        List<TripPackage> packages = TripPlanner.getInstance().getTripPackages();
        for (TripPackage pkg : packages) {
            for (Flights f : pkg.getFlights()) {
                boolean passar = true;

                if (!leitBrottfararstadur.isBlank() &&
                        (f.getDeparturePlace() == null || !f.getDeparturePlace().toLowerCase().contains(leitBrottfararstadur))) {
                    passar = false;
                }

                if (!leitKomustadur.isBlank() &&
                        (f.getDestination() == null || !f.getDestination().toLowerCase().contains(leitKomustadur))) {
                    passar = false;
                }

                if (!leitFlugnumer.isBlank() &&
                        (f.getFlightId() == null || !f.getFlightId().toLowerCase().contains(leitFlugnumer))) {
                    passar = false;
                }

                if (passar) {
                    fxFlights.getItems().add(f);
                }
            }
        }
    }

    public void leitaIHotelum(ActionEvent actionEvent) {
        String leitHotelNafn = fxLeitaHotelNafn.getText().toLowerCase();
        String leitHotelStadsetning = fxLeitaHotelStadsetning.getText().toLowerCase();
        Integer leitHotelID = null;
        String idText = fxLeitaHotelID.getText();
        if (!idText.isBlank()) {
            leitHotelID = Integer.parseInt(idText);
        }

        fxHotels.getItems().clear();

        List<TripPackage> packages = TripPlanner.getInstance().getTripPackages();
        for (TripPackage pkg : packages) {
            for (Hotels h : pkg.getHotel()) {
                boolean passar = leitHotelNafn.isBlank() ||
                        (h.getHotelName() != null && h.getHotelName().toLowerCase().contains(leitHotelNafn));

                if (!leitHotelStadsetning.isBlank() &&
                        (h.getHotelLocation() == null || !h.getHotelLocation().toLowerCase().contains(leitHotelStadsetning))) {
                    passar = false;
                }

                if (leitHotelID != null && h.getHotelId() != leitHotelID) {
                    passar = false;
                }

                if (passar) {
                    fxHotels.getItems().add(h);
                }
            }
        }
    }

    public void leitaIDagsferdum(ActionEvent actionEvent) {
        String leitDagsferdNafn = fxLeitaHeitiFerdar.getText().toLowerCase();
        String leitDagsferdStadsetning = fxleitaStadsetningFerdar.getText().toLowerCase();
        Integer leitDagsferdID = null;
        String idText = fxLeitaIdFerdar.getText();
        if (!idText.isBlank()) {
            leitDagsferdID = Integer.parseInt(idText);
        }

        fxDayTours.getItems().clear();

        List<TripPackage> packages = TripPlanner.getInstance().getTripPackages();
        for (TripPackage pkg : packages) {
            for (DayTours d : pkg.getDayTours()) {
                boolean passar = leitDagsferdNafn.isBlank() ||
                        (d.getTourName() != null && d.getTourName().toLowerCase().contains(leitDagsferdNafn));

                if (!leitDagsferdStadsetning.isBlank() &&
                        (d.getTourLocation() == null || !d.getTourLocation().toLowerCase().contains(leitDagsferdStadsetning))) {
                    passar = false;
                }

                if (leitDagsferdID != null && d.getTourId() != leitDagsferdID) {
                    passar = false;
                }

                if (passar) {
                    fxDayTours.getItems().add(d);
                }
            }
        }
    }

    public void flugValid(MouseEvent mouseEvent) {
        Flights f = fxFlights.getSelectionModel().getSelectedItem();
        if (f != null) {
            fxBrottfarastadur.setText("Brottfararstaður:\n" + f.getDeparturePlace());
            fxAfangastadur.setText("Áfangastaður:\n" + f.getDestination());
            fxFlugnumer.setText("Flugnúmer:\n" + f.getFlightId());
            fxDagsetning.setText("Dagsetning:\n" + f.getDate().toString());
            fxBrottfarartimi.setText("Brottfarartími:\n" + f.getDepartureTime());
            fxKomutimi.setText("Komutími:\n" + f.getArrivalTime());
            fxHamarkFarthega.setText("Farþegafjöldi:\n" + f.getFjoldiFarthega());
            fxHamarksverd.setText("Hámarksverð:\n" + f.getPrice());
        }
    }

    public void hotelValid(MouseEvent mouseEvent) {
        Hotels h = fxHotels.getSelectionModel().getSelectedItem();
        if (h != null) {
            fxHotelNafn.setText("Nafn hótels:\n" + h.getHotelName());
            fxHotelStadsetning.setText("Staðsetning hótels:\n" + h.getHotelLocation());
            fxHotelKomudagur.setText("Komudagur:\n" + h.getDateFrom());
            fxHotelBrottfarardagur.setText("Brottfarardagur:\n" + h.getDateTo());
            fxHotelID.setText("Hótel ID:\n" + h.getHotelId());
            fxHotelLausHerbergi.setText("Fjöldi lausra herbergja:\n" + h.getAvailableRooms());
        }
    }

    public void dayTourValid(MouseEvent mouseEvent) {
        DayTours d = fxDayTours.getSelectionModel().getSelectedItem();
        if (d != null) {
            fxHeitiDagsferd.setText("Heiti dagsferðar:\n" + d.getTourName());
            fxDagsferdStadsetning.setText("Staðsetning dagsferðar:\n" + d.getTourLocation());
            fxDagsferdDagsetning.setText("Dagsetning dagsferðar:\n" + d.getTourDate());
            fxDagsferdID.setText("ID dagsferðar:\n" + d.getTourId());
        }
    }

    public void setLoggedInCustomer(Customer customer) {
        this.loggedInCustomer = customer;
        fxInnskradurNotandi.setText("Innskráður Notandi: " + customer.getName());
    }

    public void tilBaka(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hbv401g8t/LogIn.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) fxTilBakaButton.getScene().getWindow();
            stage.setTitle("Log In");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void opnaBokanir(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hbv401g8t/Bookings.fxml"));
            Parent root = loader.load();
            BookingsController controller = loader.getController();
            controller.setLoggedInCustomer(loggedInCustomer);
            controller.updateBookingList();
            Stage stage = (Stage) fxBokanirButton.getScene().getWindow();
            stage.setTitle("Bookings");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
