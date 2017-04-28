package com.example.galan.tubes.setter_getter;

/**
 * Created by pandu on 25/03/17.
 */

public class isiMateri {
    private String title;
    private String description;
    private String idmateri;
    private String link;
    private String iddosen;

    public isiMateri(String title, String description, String idmateri, String link, String iddosen) {
        this.description = description;
        this.title = title;
        this.idmateri=idmateri;
        this.link=link;
        this.iddosen=iddosen;
    }

    public String getIddosen() {
        return iddosen;
    }

    public void setIddosen(String iddosen) {
        this.iddosen = iddosen;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIdmateri() {
        return idmateri;
    }

    public void setIdmateri(String idmateri) {
        this.idmateri = idmateri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
