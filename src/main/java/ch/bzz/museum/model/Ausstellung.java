package ch.bzz.museum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Die Ausstellungs-Klasse mit ID, Anzahl, Ort und Name (die BilderList wird ignoriert)
 */

public class Ausstellung {

    //Attribute
    private String museumID;
    private int anzBilder;
    private String ort;
    private String name;
    @JsonIgnore
    private List<Bild> bilderList;

    /**
     * Holt den Namen der Ausstellung
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter für den Namen der Ausstellung
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter für die ID des Museums, in UUIDv4
     * @return museumID
     */
    public String getMuseumID() {
        return museumID;
    }

    /**
     * Setter für die ID des Museums
     * @param museumID
     */
    public void setMuseumID(String museumID) {
        this.museumID = museumID;
    }

    /**
     * Holt die Anzahl an Bildern in dem Museum
     * @return anzBilder
     */
    public int getAnzBilder() {
        return anzBilder;
    }

    /**
     * Setter der Anzahl an Bildern im Museum
     * @param anzBilder
     */
    public void setAnzBilder(int anzBilder) {
        this.anzBilder = anzBilder;
    }

    /**
     * Holt den Ort des Museums, als Stadt bezeichnet
     * @return ort
     */
    public String getOrt() {
        return ort;
    }

    /**
     * Setter für den Ort des Museums
     * @param ort
     */
    public void setOrt(String ort) {
        this.ort = ort;
    }

    /**
     * Die Liste aller Bilder, welche im JSON ignoriert werden
     * @return bilderList
     */
    public List<Bild> getBilderList() {
        return bilderList;
    }

    /**
     * Setter für die Liste aller Bilder, wird im JSON ignoriert zur Zeit
     * @param bilderList
     */
    public void setBilderList(List<Bild> bilderList) {
        this.bilderList = bilderList;
    }
}
