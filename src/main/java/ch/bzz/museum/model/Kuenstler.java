package ch.bzz.museum.model;

public class Kuenstler {

    private String kuenstlerID;
    private String name;
    private String geburtsdatum;

    public String getKuenstlerID() {
        return kuenstlerID;
    }

    public void setKuenstlerID(String kuenstlerID) {
        this.kuenstlerID = kuenstlerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
}
