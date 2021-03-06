package com.example.doanquanlymaybay.controler;

import com.example.doanquanlymaybay.Flight;
import com.example.doanquanlymaybay.Plane;
import com.example.doanquanlymaybay.data.FlightData;
import com.example.doanquanlymaybay.data.PlaneData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightFormController implements Initializable{
    @FXML
    private TableView<Flight> table;
    @FXML
    private TableColumn<Flight,String> sohieuchuyenbay;
    @FXML
    private TableColumn<Flight,Integer> sove;
    @FXML
    private TableColumn<Flight,String> sohieumaybay;
    @FXML
    private  TableColumn<Flight,String> diemden;
    @FXML
    private  TableColumn<Flight,String> trangthai;
    @FXML
    private  TableColumn<Flight,LocalDate> ngaygio;
    @FXML
    private Button b_hieuchinh;
    @FXML
    private Button b_huychuyen;
    @FXML
    private Button b_danhsachve;
    @FXML
    private ChoiceBox<String> sohieumaybaychoice;
    @FXML
    private TextField sohieuchuyenbayTextfield;
    @FXML
    private TextField soveTextfield;
    @FXML
    private TextField diemdenTextfield;
    @FXML
    private DatePicker ngaygioDatePick;
    @FXML
    private Button b_ok;
    @FXML
    private Button b_cancel;
    private String click;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sohieuchuyenbay.setCellValueFactory(new PropertyValueFactory<Flight,String>("sohieuchuyenbay"));
        sohieumaybay.setCellValueFactory(new PropertyValueFactory<Flight,String>("sohieumaybay"));
        sove.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("sove"));
        diemden.setCellValueFactory(new PropertyValueFactory<Flight,String>("diemden"));
        trangthai.setCellValueFactory(new PropertyValueFactory<Flight,String>("trangthai"));
        ngaygio.setCellValueFactory(new PropertyValueFactory<Flight,LocalDate>("ngaygio"));
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Flight>() {
            @Override
            public void changed(ObservableValue<? extends Flight> observableValue, Flight flight, Flight t1) {
                DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
            }
        });
        table.setItems(FlightData.getInstance().getList());
        table.getSelectionModel().selectFirst();
        handleClickTable();
        Iterator<Plane> iterator = PlaneData.getInstance().getList().listIterator();
        ArrayList<String> sohieu= new ArrayList<>();
        while(iterator.hasNext()){
            sohieu.add(iterator.next().getSohieu());
        }
        sohieumaybaychoice.getItems().addAll(sohieu);
        ngaygioDatePick.setConverter(new StringConverter<LocalDate>() {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString == null || dateString.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(dateString, dateTimeFormatter);
            }
        });
    }
    @FXML public void handleClickTable(){// DIEN THONG TIN MAY BAY DANG SELECTED
    Flight flight= table.getSelectionModel().getSelectedItem();
    ngaygioDatePick.setValue(flight.getNgaygio());
    sohieuchuyenbayTextfield.setText(flight.getSohieuchuyenbay());
    diemdenTextfield.setText(flight.getDiemden());
    soveTextfield.setText(flight.getSove()+"");
    b_danhsachve.setDisable(false);
    b_hieuchinh.setDisable(false);
    b_huychuyen.setDisable(false);


    }
    @FXML public void handleClickThem(){
        click="them";
        table.setDisable(true);
        b_danhsachve.setDisable(true);
        b_hieuchinh.setDisable(true);
        b_huychuyen.setDisable(true);
        sohieumaybaychoice.setDisable(false);
        sohieuchuyenbayTextfield.setDisable(false);
        soveTextfield.setDisable(false);
        diemdenTextfield.setDisable(false);
        ngaygioDatePick.setDisable(false);
        ngaygioDatePick.setValue(null);
        sohieuchuyenbayTextfield.setText(null);
        soveTextfield.setText(null);
        diemdenTextfield.setText(null);
        b_ok.setDisable(false);
        b_cancel.setDisable(false);

    }
    @FXML public  void handleClickOK(){
        if(click.equals("them")){
        if(!Pattern.matches("^[a-z0-9_-]{6,12}$",sohieuchuyenbayTextfield.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" L???i ?????nh D???ng D??? Li???u   ");
            alert.setHeaderText(" s??? hi???u chuy???n bay t??? 6-12 k?? t???, kh??ng bao g???m k?? t??? ?????c bi???t v?? kh??ng d???u" );
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && (result.get() == ButtonType.OK)) {
                return;
            }

        }
        if(!Pattern.matches("^[0-9_-]{1,3}$",soveTextfield.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" L???i ?????nh D???ng D??? Li???u   ");
            alert.setHeaderText(" s??? v?? t??? 1-3 k?? t???, kh??ng bao g???m k?? t??? ch??? kh??ng c?? kho???ng tr???ng v?? kh??ng d???u " );
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && (result.get() == ButtonType.OK)) {
                return;
            }

        }
        if(!Pattern.matches("^[a-z_-]{6,20}$",diemdenTextfield.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" L???i ?????nh D???ng D??? Li???u   ");
            alert.setHeaderText(" ??i???m ?????n c?? ????? d??i t??? 6-20 k?? t??? kh??ng bao g???m k?? t??? s??? kh??ng kho???ng tr???ng " );
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && (result.get() == ButtonType.OK)) {
                return;
            }

        }
        if(ngaygioDatePick.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" L???i ?????nh D???ng D??? Li???u   ");
            alert.setHeaderText(" NG??Y GI??? KH??NG H???P L??? " );
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && (result.get() == ButtonType.OK)) {
                return;
            }

        }


            if(sohieumaybaychoice.getValue()==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" L???i ?????nh D???ng D??? Li???u   ");
                alert.setHeaderText(" S??? HI???U M??Y BAY KH??NG ???????C ????? TR???NG " );
                Optional<ButtonType> result = alert.showAndWait();

                if(result.isPresent() && (result.get() == ButtonType.OK)) {
                    return;
                }
            }
            String sohieuchuyenbay=sohieuchuyenbayTextfield.getText().trim().toUpperCase().replaceAll("\\s+","");;
            String sohieumaybay= sohieumaybaychoice.getValue().toUpperCase().replaceAll("\\s+","");;
            int soveMax=PlaneData.getInstance().find(sohieumaybay).getSocho();
            int sove=PlaneData.getInstance().find(sohieumaybay).getSocho();
            if(sove>soveMax) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" L???i ?????nh D???ng D??? Li???u   ");
                alert.setHeaderText(" S??? V?? KH??NG ???????C L???N H??N S??? CH??? C???A M??Y BAY S??? HI??U " + sohieumaybay + " (" + soveMax + ")");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && (result.get() == ButtonType.OK)) {
                    return;
                }
            }
            String diemden= diemdenTextfield.getText().trim().toUpperCase().replaceAll("\\s+","");;
            LocalDate ngaygio= ngaygioDatePick.getValue();
            Flight newFlight= new Flight(sohieuchuyenbay,sohieumaybay,sove,diemden,"CONVE",ngaygio);
            FlightData.getInstance().add(newFlight);
            table.getSelectionModel().select(newFlight);


        }
        if(click.equals("hieuchinh")){
            Flight flight = table.getSelectionModel().getSelectedItem();
            flight.setNgaygio(ngaygioDatePick.getValue());
            System.out.println(ngaygioDatePick.getValue());
            FlightData.getInstance().add(flight);
        }
        handleClickCancel();
    }
    public void handleClickCancel(){
        handleClickTable();
        sohieumaybaychoice.setDisable(true);
        sohieuchuyenbayTextfield.setDisable(true);
        soveTextfield.setDisable(true);
        diemdenTextfield.setDisable(true);
        ngaygioDatePick.setDisable(true);
        b_ok.setDisable(true);
        b_cancel.setDisable(true);
        table.setDisable(false);


    }
    public void handleClickHieuChinh(){
        click="hieuchinh";
        ngaygioDatePick.setDisable(false);
        b_ok.setDisable(false);
        b_cancel.setDisable(false);
    }
    public  void handleClickXoa(){
        Flight flight = table.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Plane");
        alert.setHeaderText("H???y chuy???n bay: " +flight.getSohieuchuyenbay() );
        alert.setContentText("B???n ch???c ch???n mu???n h???y chuy???n bay ?  ???n ok ????? x??c nh???n , cancel ????? tr??? l???i.");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && (result.get() == ButtonType.OK)) {
            FlightData.getInstance().remove(flight);
        }

    }
}