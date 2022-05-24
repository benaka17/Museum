package ch.bzz.museum.model;

/**
 * Die Bild Klasse für ein einzelnes Bild mit ID, Name, dem Künstler, Datum, Art und Preis
 */

public class Bild {

    //Attribute
    private String bildID;
    private String name;
    private String künstler;
    private Integer datum; //Wrapper-Klasse benutzt, da Fehler vorkamen ohne
    private String art; //Kategorien wie "modern"
    private double preis;
    /*
    private Ausstellung ausstellung;
     */

    /**
     * Getter für eine BildID
     * @return bildID
     */
    public String getBildID() {
        return bildID;
    }

    /**
     * Setter für eine BildID
     * @param bildID
     */
    public void setBildID(String bildID) {
        this.bildID = bildID;
    }

    /**
     * Getter für den Namen eines Bildes
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter für den Namen eines Bildes
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter für den Künstler
     * @return künstler
     */
    public String getKünstler() {
        return künstler;
    }

    /**
     * Setter für einen Künstler
     * @param künstler
     */
    public void setKünstler(String künstler) {
        this.künstler = künstler;
    }

    /**
     * Getter für das Datum, also das Jahr
     * @return datum
     */
    public int getDatum() {
        return datum;
    }

    /**
     * Setter für das Datum, also das Jahr
     * @param datum
     */
    public void setDatum(int datum) {
        this.datum = datum;
    }

    /**
     * Getter für die Art des Bildes, also Epoche etc
     * @return art
     */
    public String getArt() {
        return art;
    }

    /**
     * Setter für die Art eines Bildes
     * @param art
     */
    public void setArt(String art) {
        this.art = art;
    }

    /**
     * Holt den Preis eines Bildes
     * @return preis
     */
    public double getPreis() {
        return preis;
    }

    /**
     * Setter für den Preis
     * @param preis
     */
    public void setPreis(double preis) {
        this.preis = preis;
    }

    /*
    public Ausstellung getAusstellung() {
        return ausstellung;
    }

    public void setAusstellung(Ausstellung ausstellung) {
        this.ausstellung = ausstellung;
    }
     */

}
