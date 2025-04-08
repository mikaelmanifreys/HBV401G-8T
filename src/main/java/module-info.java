module com.example.hbv401g8t {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hbv401g8t to javafx.fxml;
    exports com.example.hbv401g8t;
}
