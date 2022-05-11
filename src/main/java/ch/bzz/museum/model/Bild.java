package ch.bzz.museum.model;

import java.util.Date;

public class Bild {

    private String bildID;
    private String name;
    private String künstler;
    private Date datum;
    private String art; //Kategorien wie "modern"
    private float preis;

    public String getBildID() {
        return bildID;
    }

    public void setBildID(String bildID) {
        this.bildID = bildID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKünstler() {
        return künstler;
    }

    public void setKünstler(String künstler) {
        this.künstler = künstler;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }

}
