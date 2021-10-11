package com.example.doanquanlymaybay;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

public class FxmlLoader {
    private Pane view;
    public Pane getPane(String fileName) {
        try {
            URL fileUrl = main.class.getResource(fileName+".fxml");
            if(fileUrl== null){
                throw new java.io.FileNotFoundException("FXML cannot be found");
            }
            view = FXMLLoader.load(fileUrl);
        }
        catch (Exception e){
            System.out.println("No page "+fileName+" please check fxml loader. ");
        }
        return view;
    }
}
