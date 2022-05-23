package ch.bzz.museum.model;

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

    //Getter und Setter für alles:
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

    public int getDatum() {
        return datum;
    }

    public void setDatum(int datum) {
        this.datum = datum;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public double getPreis() {
        return preis;
    }

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
