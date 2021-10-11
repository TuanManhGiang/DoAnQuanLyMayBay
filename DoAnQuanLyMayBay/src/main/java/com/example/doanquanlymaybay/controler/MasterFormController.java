package com.example.doanquanlymaybay.controler;

import com.example.doanquanlymaybay.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;

public class MasterFormController {
    @FXML
    private BorderPane mainPane;
    @FXML
    private void handleButtonPlaneAction(ActionEvent event) throws FileNotFoundException {
        System.out.println("you click form 1 ");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPane("Plane");
        mainPane.setCenter(view);
    }
    @FXML
    private void handleButtonTicketAction(ActionEvent event) throws FileNotFoundException {
        System.out.println("you click form 3 ");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPane("Ticket");
        mainPane.setCenter(view);
    }
    @FXML
    private void handleButtonFlightAction(ActionEvent event) throws FileNotFoundException {
        System.out.println("you click form 2 ");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPane("Flight");
        mainPane.setCenter(view);
    }
}