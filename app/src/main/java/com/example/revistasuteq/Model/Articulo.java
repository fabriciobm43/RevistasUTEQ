package com.example.revistasuteq.Model;

public class Articulo {
    private String section;
    private String title;
    private String date;
    private String sectionid;
    private String url;
    private String descripcion;
    private String doi;
    private String keywords;
    private String authors;

    public Articulo(String section, String title, String date, String sectionid, String url, String descripcion, String doi, String keywords, String authors) {
        this.section = section;
        this.title = title;
        this.date = date;
        this.sectionid = sectionid;
        this.url=url;
        this.descripcion=descripcion;
        this.doi=doi;
        this.keywords=keywords;
        this.authors=authors;

    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSectionid() {
        return sectionid;
    }

    public void setSectionid(String sectionid) {
        this.sectionid = sectionid;
    }
}
