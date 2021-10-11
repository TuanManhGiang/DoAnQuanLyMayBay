package com.example.doanquanlymaybay;

public class Plane {
    private String loai;
    private String sohieu;
    private int socho;

    public Plane(String loai, String sohieu, int socho) {
        this.loai = loai;
        this.sohieu = sohieu;
        this.socho = socho;
    }

    public Plane() {
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getSohieu() {
        return sohieu;
    }

    public void setSohieu(String sohieu) {
        this.sohieu = sohieu;
    }

    public int getSocho() {
        return socho;
    }

    public void setSocho(int socho) {
        this.socho = socho;
    }

    @Override
    public int hashCode() {
        return this.sohieu.hashCode()+31;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Plane) {
            Plane another = (Plane) obj;
            if (this.sohieu.equals(another.getSohieu()))
                return true;

        }
        return false;
    }

}
