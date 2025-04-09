package com.example.hbv401g8t;

import com.example.hbv401g8t.hotel.DatabaseConnection;
import com.example.hbv401g8t.hotel.Hotel;
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
import java.util.Objects;

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
    public Label fxDagsferdVerd;
    public Label fxHotelVerd;
    public Label fxVerdLabel;
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
            // reduced availabillity
            DatabaseConnection.updateAvailableRooms(hotel.getHotelId(), hotel.getAvailableRooms() - 1);

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


        // Load mock data
        for (Flights mockData : MockData.getMockFlights()) {
            fxFlights.getItems().add(mockData);
        }
        for (Hotels mockData : MockData.getMockHotels()) {
            fxHotels.getItems().add(mockData);
        }
        for (DayTours mockData : MockData.getMockDayTours()) {
            fxDayTours.getItems().add(mockData);
        }

        List<Hotel> dbHotels = DatabaseConnection.getAllHotelsFromDB();
        for (Hotel h : dbHotels) {
            fxHotels.getItems().add(new Hotels(h.getName(), h.getRegion(), h.getId(), h.getPrice(), h.getAvailableRooms()));
        }
    }


    public void leitaIFlugum() {
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
        for (Flights mock : MockData.getMockFlights()) {
            boolean passar = true;

            if (!leitBrottfararstadur.isBlank() &&
                    (mock.getDeparturePlace() == null || !mock.getDeparturePlace().toLowerCase().contains(leitBrottfararstadur))) {
                passar = false;
            }

            if (!leitKomustadur.isBlank() &&
                    (mock.getDestination() == null || !mock.getDestination().toLowerCase().contains(leitKomustadur))) {
                passar = false;
            }

            if (!leitFlugnumer.isBlank() &&
                    (mock.getFlightId() == null || !mock.getFlightId().toLowerCase().contains(leitFlugnumer))) {
                passar = false;
            }

            if (passar) {
                fxFlights.getItems().add(mock);
            }
        }
    }

    public void leitaIHotelum() {
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
        for (Hotels mock : MockData.getMockHotels()) {
            boolean passar = leitHotelNafn.isBlank() ||
                    (mock.getHotelName() != null && mock.getHotelName().toLowerCase().contains(leitHotelNafn));

            if (!leitHotelStadsetning.isBlank() &&
                    (mock.getHotelLocation() == null || !mock.getHotelLocation().toLowerCase().contains(leitHotelStadsetning))) {
                passar = false;
            }

            if (leitHotelID != null && mock.getHotelId() != leitHotelID) {
                passar = false;
            }

            if (passar) {
                fxHotels.getItems().add(mock);
            }
        }
        List<Hotel> dbHotels = DatabaseConnection.getAllHotelsFromDB();
        for (Hotel h : dbHotels) {
            boolean passar = leitHotelNafn.isBlank() ||
                    (h.getName() != null && h.getName().toLowerCase().contains(leitHotelNafn));

            if (!leitHotelStadsetning.isBlank() &&
                    (h.getRegion() == null || !h.getRegion().toLowerCase().contains(leitHotelStadsetning))) {
                passar = false;
            }

            if (leitHotelID != null && h.getId() != leitHotelID) {
                passar = false;
            }

            if (passar) {
                fxHotels.getItems().add(new Hotels(h.getName(), h.getRegion(), h.getId(), h.getPrice(), h.getAvailableRooms()));
            }
        }
    }

    public void leitaIDagsferdum() {
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
        for (DayTours mock : MockData.getMockDayTours()) {
            boolean passar = leitDagsferdNafn.isBlank() ||
                    (mock.getTourName() != null && mock.getTourName().toLowerCase().contains(leitDagsferdNafn));

            if (!leitDagsferdStadsetning.isBlank() &&
                    (mock.getTourLocation() == null || !mock.getTourLocation().toLowerCase().contains(leitDagsferdStadsetning))) {
                passar = false;
            }

            if (leitDagsferdID != null && mock.getTourId() != leitDagsferdID) {
                passar = false;
            }

            if (passar) {
                fxDayTours.getItems().add(mock);
            }
        }
    }

    public void uppfaeraPakkaverd() {
        int flightPrice = 0;
        int hotelPrice = 0;
        int dayTourPrice = 0;

        Flights f = fxFlights.getSelectionModel().getSelectedItem();
        Hotels h = fxHotels.getSelectionModel().getSelectedItem();
        DayTours d = fxDayTours.getSelectionModel().getSelectedItem();

        if (f != null) flightPrice = f.getPrice();
        if (h != null && fraDate != null && tilDate != null) {
            long nights = tilDate.toEpochDay() - fraDate.toEpochDay();
            if (nights <= 0) nights = 1;
            hotelPrice = h.getPrice() * (int) nights;
        }
        if (d != null) dayTourPrice = d.getPrice();

        int total = flightPrice + hotelPrice + dayTourPrice;

        fxVerdLabel.setText("Verð fyrir pakka: " + total + " kr.");
    }

    public void flugValid(MouseEvent mouseEvent) {
        Flights f = fxFlights.getSelectionModel().getSelectedItem();
        if (f != null) {
            String hotelLocation = f.getDestination();
            if (!Objects.equals(fxLeitaKomustadur.getText(), hotelLocation) && !Objects.equals(fxLeitaHotelStadsetning.getText(), hotelLocation)) {
                fxHotels.getSelectionModel().clearSelection();
                fxDayTours.getSelectionModel().clearSelection();
                fxLeitaHotelStadsetning.setText(hotelLocation);
                fxleitaStadsetningFerdar.setText(hotelLocation);
                leitaIHotelum();
                leitaIDagsferdum();
            } else {
                fxBrottfarastadur.setText("Brottfararstaður: " + f.getDeparturePlace());
                fxAfangastadur.setText("Áfangastaður: " + f.getDestination());
                fxFlugnumer.setText("Flugnúmer: " + f.getFlightId());
                fxDagsetning.setText("Dagsetning: " + f.getDate().toString());
                fxBrottfarartimi.setText("Brottfarartími: " + f.getDepartureTime());
                fxKomutimi.setText("Komutími: " + f.getArrivalTime());
                fxHamarksverd.setText("Verð: " + f.getPrice());
                fxHamarkFarthega.setText("Laus sæti: " + f.getAvailableSeats());
                uppfaeraPakkaverd();
            }
        }


    }

    public void hotelValid(MouseEvent mouseEvent) {
        Hotels h = fxHotels.getSelectionModel().getSelectedItem();
        Hotel hdb = DatabaseConnection.getHotelFromDB(h.getHotelId());
        if (h != null) {
            String flightDestination = h.getHotelLocation();
            if (!Objects.equals(fxLeitaKomustadur.getText(), flightDestination) && !Objects.equals(fxLeitaHotelStadsetning.getText(), flightDestination)) {
                fxFlights.getSelectionModel().clearSelection();
                fxDayTours.getSelectionModel().clearSelection();
                fxLeitaKomustadur.setText(flightDestination);
                fxleitaStadsetningFerdar.setText(flightDestination);
                leitaIFlugum();
                leitaIDagsferdum();
            } else {
                fxHotelNafn.setText("Nafn hótels: " + h.getHotelName());
                fxHotelStadsetning.setText("Staðsetning hótels: " + h.getHotelLocation());
                if (fraDate != null) {
                    fxHotelKomudagur.setText("Komudagur: " + fraDate);
                }
                if (tilDate != null) {
                    fxHotelBrottfarardagur.setText("Brottfarardagur: " + tilDate);
                }
                fxHotelVerd.setText("Verð á nótt: " + h.getPrice());
                fxHotelID.setText("Hótel ID: " + h.getHotelId());
                if (hdb != null) {
                    fxHotelLausHerbergi.setText(" Laus herbergi: " + hdb.getAvailableRooms());
                }
                uppfaeraPakkaverd();
            }
        }
    }

    public void dayTourValid(MouseEvent mouseEvent) {
        DayTours d = fxDayTours.getSelectionModel().getSelectedItem();
        if (d != null) {
            if (!Objects.equals(fxLeitaKomustadur.getText(), d.getTourLocation()) && !Objects.equals(fxLeitaHotelStadsetning.getText(), d.getTourLocation())) {
                fxFlights.getSelectionModel().clearSelection();
                fxHotels.getSelectionModel().clearSelection();
                fxLeitaKomustadur.setText(d.getTourLocation());
                fxLeitaHotelStadsetning.setText(d.getTourLocation());
                leitaIFlugum();
                leitaIHotelum();
            } else {
                fxHeitiDagsferd.setText("Heiti dagsferðar: " + d.getTourName());
                fxDagsferdStadsetning.setText("Staðsetning dagsferðar: " + d.getTourLocation());
                fxDagsferdDagsetning.setText("Dagsetning dagsferðar: " + d.getTourDate());
                fxDagsferdID.setText("ID dagsferðar: " + d.getTourId());
                fxDagsferdVerd.setText("Verð: " + d.getPrice());
                uppfaeraPakkaverd();
            }
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

    public void onReset(){
        //leitargluggar
        fxLeitaHotelStadsetning.clear();
        fxleitaStadsetningFerdar.clear();
        fxLeitaBrottfarastadur.clear();
        fxLeitaKomustadur.clear();
        fxLeitaFlugnumer.clear();
        fxLeitaHotelNafn.clear();
        fxLeitaHotelID.clear();
        fxLeitaHeitiFerdar.clear();
        fxLeitaIdFerdar.clear();

        //label
        fxBrottfarastadur.setText("");
        fxAfangastadur.setText("");
        fxDagsetning.setText("");
        fxFlugnumer.setText("");
        fxHamarkFarthega.setText("");
        fxBrottfarartimi.setText("");
        fxKomutimi.setText("");
        fxHamarksverd.setText("");
        fxHotelNafn.setText("");
        fxHotelStadsetning.setText("");
        fxHotelKomudagur.setText("");
        fxHotelBrottfarardagur.setText("");
        fxHotelLausHerbergi.setText("");
        fxHotelID.setText("");
        fxHotelVerd.setText("");
        fxHeitiDagsferd.setText("");
        fxDagsferdID.setText("");
        fxDagsferdDagsetning.setText("");
        fxDagsferdStadsetning.setText("");
        fxDagsferdVerd.setText("");

        fxVerdLabel.setText("");

        leitaIHotelum();
        leitaIFlugum();
        leitaIDagsferdum();
    }

}
