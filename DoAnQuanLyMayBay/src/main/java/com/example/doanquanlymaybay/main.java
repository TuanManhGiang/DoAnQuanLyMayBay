package com.example.doanquanlymaybay;

import com.example.doanquanlymaybay.data.FlightData;
import com.example.doanquanlymaybay.data.PlaneData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL fileUrl = main.class.getResource("masterForm.fxml");
        Scene scene = new Scene(FXMLLoader.load(fileUrl), 320, 240);
        stage.setTitle("QUẢN LÝ MÁY BAY");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws Exception {
        try{
            PlaneData.getInstance().storePlane();
            FlightData.getInstance().storeFlight();
        } catch (IOException e){
            System.out.println("can't store plane");
        }
    }

    @Override
    public void init() throws Exception {
        try{
            PlaneData.getInstance().loadPlane();
            FlightData.getInstance().loadFlight();
        } catch (IOException e){
            System.out.println("can't load plane");
        }
    }
}