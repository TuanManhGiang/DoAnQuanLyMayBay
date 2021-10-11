package com.example.doanquanlymaybay.data;

import com.example.doanquanlymaybay.Plane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.ListIterator;

public class PlaneData {
    private static final PlaneData instance = new PlaneData();
    private static final String filename = "Plane.txt";
    private ObservableList<Plane> list;


    public ObservableList<Plane> getList() {
        return list;
    }
    public Plane find(Plane plane){
        ListIterator<Plane> iterator= list.listIterator();
        while (iterator.hasNext()){
            Plane listplane= iterator.next();
            if(listplane.getSohieu().equals(plane.getSohieu())){
                return listplane;
            }
        }
        return null;
    }
    public  Plane find(String sohieu){
        ListIterator<Plane> iterator= list.listIterator();
        while (iterator.hasNext()){
            Plane listplane= iterator.next();
            if(listplane.getSohieu().equals(sohieu)){
                return listplane;
            }
        }
         return null;
    }
    public void addList(Plane plane){
        if(list.contains(plane)){
        list.set(list.indexOf(plane),plane);
        }
        else
        list.add(plane);
    }
    public void delete(Plane plane){
        list.remove(plane);
    }
    private PlaneData() {

    }
    public static PlaneData getInstance(){
        return instance;
    }
    public void loadPlane() throws IOException {

        list = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                String loaimaybay = itemPieces[0];
                String  sohieu = itemPieces[1];
                int socho =Integer.parseInt(itemPieces[2]) ;
                Plane mayBay = new Plane(loaimaybay,sohieu,socho) ;
                list.add(mayBay);
            }

        } finally {
            if(br != null) {
                br.close();
            }
        }
    }

    public void storePlane() throws IOException {

        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<Plane> iter = list.iterator();
            while (iter.hasNext()) {
                Plane item = iter.next();
                bw.write(String.format("%s\t%s\t%s\t",
                        item.getLoai(),
                        item.getSohieu(),
                        item.getSocho()));
                bw.newLine();
            }

        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }
}
