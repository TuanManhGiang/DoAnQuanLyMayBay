module com.example.doanquanlymaybay {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.doanquanlymaybay to javafx.fxml;
    exports com.example.doanquanlymaybay;
    exports com.example.doanquanlymaybay.data;
    opens com.example.doanquanlymaybay.data to javafx.fxml;
    exports com.example.doanquanlymaybay.controler;
    opens com.example.doanquanlymaybay.controler to javafx.fxml;
}