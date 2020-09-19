package com.example.revistasuteq.Model;

public class Editorial {
    private String issue_id;
    private String volume;
    private String number;
    private String year;
    private String title;
    private String cover;

    public Editorial(String issue_id, String volume, String number, String year, String title, String cover) {
        this.issue_id = issue_id;
        this.volume = volume;
        this.number = number;
        this.year = year;
        this.title = title;
        this.cover = cover;
    }

    public String getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(String issue_id) {
        this.issue_id = issue_id;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
