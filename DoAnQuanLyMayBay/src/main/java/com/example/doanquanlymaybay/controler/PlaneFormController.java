package com.example.doanquanlymaybay.controler;

import com.example.doanquanlymaybay.Plane;
import com.example.doanquanlymaybay.data.FlightData;
import com.example.doanquanlymaybay.data.PlaneData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class PlaneFormController implements Initializable {
    private String click;
    @FXML
    private TableView<Plane> table;
    @FXML
    private TableColumn<Plane,String> loaimaybay;
    @FXML
    private TableColumn<Plane,Integer> socho;
    @FXML
    private TableColumn<Plane,String> sohieu;
    @FXML
    private GridPane data;
    @FXML
    private Button okThem;
    @FXML
    private Button b_them;
    @FXML
    private Button b_xoa;
    @FXML
    private Button b_sua;
    @FXML
    private TextField textfieldLoaiMayBay;
    @FXML
    private TextField textfieldSoHieu;
    @FXML
    private TextField textfieldSoCho;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loaimaybay.setCellValueFactory(new PropertyValueFactory<Plane,String>("loai"));
        socho.setCellValueFactory(new PropertyValueFactory<Plane,Integer>("socho"));
        sohieu.setCellValueFactory(new PropertyValueFactory<Plane,String>("sohieu"));
        table.setItems(PlaneData.getInstance().getList());
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    @FXML public void handleClickTable(){
        Plane  plane = table.getSelectionModel().getSelectedItem();
        textfieldLoaiMayBay.setText(plane.getLoai());
        textfieldSoCho.setText(plane.getSocho()+"");
        textfieldSoHieu.setText(plane.getSohieu());
        b_sua.setVisible(true);
        b_xoa.setVisible(true);
        b_them.setVisible(true);

    }
    @FXML public void handleClickButtonThem(){
        click="them";
        textfieldSoHieu.setDisable(false);
        textfieldLoaiMayBay.setText(null);
        textfieldSoCho.setText(null);
        textfieldSoHieu.setText(null);
        data.setVisible(true);
        table.setDisable(true);
    }
    @FXML public  void handleClickButtonOk(){
        if(Pattern.matches("\\D",textfieldSoCho.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Lỗi Định Dạng Dữ Liệu   ");
            alert.setHeaderText(" số chỗ lớn hơn 20, không bao gồm kí tự chữ không có khoảng trắng và không dấu" );
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && (result.get() == ButtonType.OK)) {
                return;
            }

        }
        if(!Pattern.matches("^[a-z0-9_-]{6,12}$",textfieldLoaiMayBay.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Lỗi Định Dạng Dữ Liệu   ");
            alert.setHeaderText(" loại máy bay có độ tài từ 6 đến   12 ký tự, không có khoảng trắng và không dấu" );
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && (result.get() == ButtonType.OK)) {
                return;
            }

        }
        String loaimaybay= textfieldLoaiMayBay.getText().trim().replaceAll("\\s+","");
        if(textfieldSoHieu.getText()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Lỗi Định Dạng Dữ Liệu   ");
            alert.setHeaderText(" TRƯỜNG SỐ HIỆU KHÔNG ĐƯỢC ĐỂ TRỐNG" );
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && (result.get() == ButtonType.OK)) {
                return;
            }

        }

        String sohieu = textfieldSoHieu.getText().trim().toUpperCase().replaceAll("\\s+","");;
        String socho= textfieldSoCho.getText();
        Plane newplane= new Plane(loaimaybay,sohieu,Integer.parseInt(socho));
        if(click.equals("them")){
            if(PlaneData.getInstance().find(newplane)!=null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" Lỗi Định Dạng Dữ Liệu   ");
                alert.setHeaderText(" Số Hiệu Máy Bay Đã Tồn Tại " );
                Optional<ButtonType> result = alert.showAndWait();

                if(result.isPresent() && (result.get() == ButtonType.OK)) {
                    return;
                }

            }
        }
        PlaneData.getInstance().addList(newplane);
        textfieldSoHieu.setDisable(false);// handleClickButtonSua textfieldSoHieu.setDisable(true);
        handleClickButtonCancelThem();

    }
    @FXML public  void handleClickButtonCancelThem(){
        textfieldSoHieu.setDisable(false);// handleClickButtonSua textfieldSoHieu.setDisable(true);
        textfieldLoaiMayBay.setText(null);
        textfieldSoCho.setText(null);
        textfieldSoHieu.setText(null);
        data.setVisible(false);
        table.setDisable(false);
    }
    @FXML public void handleClickButtonXoa(){
        data.setVisible(false);
        Plane  plane = table.getSelectionModel().getSelectedItem();
        if(FlightData.getInstance().find(plane.getSohieu())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Plane");
            alert.setHeaderText("the plane is currently having a flight that cannot be deleted");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                return;
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Plane");
            alert.setHeaderText("Delete Plane: " + plane.getSohieu());
            alert.setContentText("Are you sure?  Press OK to confirm, or cancel to Back out.");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                PlaneData.getInstance().delete(plane);
            }
        }
    }
    @FXML public void handleClickButtonSua(){
        click="sua";
        data.setVisible(true);
        Plane  plane = table.getSelectionModel().getSelectedItem();
        textfieldLoaiMayBay.setText(plane.getLoai());
        textfieldSoCho.setText(plane.getSocho()+"");
        textfieldSoHieu.setText(plane.getSohieu());
        textfieldSoHieu.setDisable(true);



    }
}
