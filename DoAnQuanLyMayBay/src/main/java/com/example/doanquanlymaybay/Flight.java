package com.example.doanquanlymaybay;

import java.time.LocalDate;
import java.util.Objects;

public class Flight {
    private String sohieuchuyenbay;
    private String sohieumaybay;
    private int sove;
    private String diemden;
    private String trangthai;
    private LocalDate ngaygio;

    public Flight() {
    }

    public Flight(String sohieuchuyenbay, String sohieumaybay, int sove, String diemden, String trangthai, LocalDate ngaygio) {
        this.sohieuchuyenbay = sohieuchuyenbay;
        this.sohieumaybay = sohieumaybay;
        this.sove = sove;
        this.diemden = diemden;
        this.trangthai = trangthai;
        this.ngaygio = ngaygio;
    }

    public Flight(Plane plane, String sohieuchuyenbay, String diemden, String trangthai, LocalDate ngaygio) {
        this.sohieuchuyenbay =sohieuchuyenbay;
        this.sohieumaybay = plane.getSohieu();
        this.sove = plane.getSocho();
        this.diemden = diemden;
        this.trangthai = trangthai;
        this.ngaygio = ngaygio;
    }

    public String getSohieuchuyenbay() {
        return sohieuchuyenbay;
    }

    public String getSohieumaybay() {
        return sohieumaybay;
    }
    public int getSove() {
        return sove;
    }

    public void setSove(int sove) {
        this.sove = sove;
    }

    public String getDiemden() {
        return diemden;
    }
    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public LocalDate getNgaygio() {
        return ngaygio;
    }

    public void setNgaygio(LocalDate ngaygio) {
        this.ngaygio = ngaygio;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Flight) {
            Flight another = (Flight) o;
            if (this.sohieuchuyenbay.equals(another.getSohieuchuyenbay()))
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sohieuchuyenbay)+88;
    }
}
