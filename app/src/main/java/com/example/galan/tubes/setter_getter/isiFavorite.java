package com.example.galan.tubes.setter_getter;

/**
 * Created by galan on 08/04/2017.
 */

public class isiFavorite {
    String namaMateri;
    String date;
    String idmateri;

    public isiFavorite(String namaMateri, String date, String idmateri){
        this.namaMateri=namaMateri;
        this.date=date;
        this.idmateri=idmateri;
    }

    public String getIdmateri() {
        return idmateri;
    }

    public void setIdmateri(String idmateri) {
        this.idmateri = idmateri;
    }

    public String getNamaMateri() {
        return namaMateri;
    }

    public void setNamaMateri(String namaMateri) {
        this.namaMateri = namaMateri;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
