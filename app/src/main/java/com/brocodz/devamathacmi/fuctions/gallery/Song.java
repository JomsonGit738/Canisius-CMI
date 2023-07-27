package com.brocodz.devamathacmi.fuctions.gallery;

public class Song {
    private String sname,surl;

    public Song() {
    }

    public Song(String sname, String surl) {
        this.sname = sname;
        this.surl = surl;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }
}
