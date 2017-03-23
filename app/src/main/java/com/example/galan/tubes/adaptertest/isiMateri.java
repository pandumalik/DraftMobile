package com.example.galan.tubes.adaptertest;

/**
 * Created by galan on 23/03/2017.
 */

public class isiMateri {
    private String title;
    private String description;

    public isiMateri(String description, String title) {
        this.description = description;
        this.title = title;
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
