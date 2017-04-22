package com.example.galan.tubes.setter_getter;

/**
 * Created by galan on 03/04/2017.
 */

public class isiPengumuman {
    private String title;
    private String pengumuman;

    public isiPengumuman(String title,String pengumuman){
        this.pengumuman=pengumuman;
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPengumuman() {
        return pengumuman;
    }

    public void setPengumuman(String pengumuman) {
        this.pengumuman = pengumuman;
    }
}
