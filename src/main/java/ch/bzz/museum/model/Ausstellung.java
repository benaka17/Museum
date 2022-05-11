package ch.bzz.museum.model;

import java.util.Date;
import java.util.List;

public class Ausstellung {

    private String museumID;
    private int anzBilder;
    private String ort;
    private List<Bild> bilderList;

    public String getMuseumID() {
        return museumID;
    }

    public void setMuseumID(String museumID) {
        this.museumID = museumID;
    }

    public int getAnzBilder() {
        return anzBilder;
    }

    public void setAnzBilder(int anzBilder) {
        this.anzBilder = anzBilder;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public List<Bild> getBilderList() {
        return bilderList;
    }

    public void setBilderList(List<Bild> bilderList) {
        this.bilderList = bilderList;
    }
}
