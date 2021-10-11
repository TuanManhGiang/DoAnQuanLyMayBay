package com.example.doanquanlymaybay.data;

import com.example.doanquanlymaybay.Flight;
import com.example.doanquanlymaybay.Plane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.ListIterator;

public class FlightData {
    private static final FlightData instance = new FlightData();
    private static final String filename="Flight.txt";
    private ObservableList<Flight> list;
    private  DateTimeFormatter formatter;

    private FlightData() {
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public ObservableList<Flight> getList() {
        return list;
    }
    public static FlightData getInstance(){
        return instance;
    }
    public boolean find(String shmb){
        for(Flight i :list){
            if(i.getSohieumaybay().equals(shmb)){
                return true;
            }
        }
        return false;
    }
    public void add(Flight flight){
        if(list.contains(flight))
          list.set(list.indexOf(flight),flight);
        else list.add(flight);
    }
    public void remove(Flight flight){
    list.remove(flight);
    }
    public void loadFlight() throws IOException {

        list = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                String sohieuchuyenbay = itemPieces[0];
                String  sohieumaybay = itemPieces[1];
                int sove =Integer.parseInt(itemPieces[2]) ;
                String diemden=itemPieces[3];
                String trangthai=itemPieces[4];
                String ngaygio= itemPieces[5];
                LocalDate date = LocalDate.parse(ngaygio,formatter);
                Flight chuyenbay = new Flight(sohieuchuyenbay,sohieumaybay,sove,diemden,trangthai,date) ;
                list.add(chuyenbay);
            }

        } finally {
            if(br != null) {
                br.close();
            }
        }
    }
    public void storeFlight() throws IOException {

        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<Flight> iter = list.iterator();
            while (iter.hasNext()) {
                Flight item = iter.next();
                bw.write(String.format("%s\t%s\t%s\t%s\t%s\t%s\t",
                        item.getSohieuchuyenbay(),
                        item.getSohieumaybay(),
                        item.getSove(),
                        item.getDiemden(),
                        item.getTrangthai(),
                        item.getNgaygio().format(formatter)));
                bw.newLine();
            }

        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }
}
