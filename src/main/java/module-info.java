module com.example.hellofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.hellofx to javafx.fxml;
    exports com.example.hellofx;
}