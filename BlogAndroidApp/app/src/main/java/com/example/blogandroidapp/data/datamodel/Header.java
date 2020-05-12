package com.example.blogandroidapp.data.datamodel;

public class Header {
    private String id;
    private String title;

    public Header(String id, String title){
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }
}
