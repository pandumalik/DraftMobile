package com.example.galan.tubes.setter_getter;

/**
 * Created by galan on 15/04/2017.
 */

public class isiContributor {

    private String name;
    private String contact;
    private String photos;
    private String cover;


    public isiContributor(String name, String contact){
        this.name=name;
        this.contact=contact;
        this.photos=photos;
        this.cover=cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
