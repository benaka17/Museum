package ch.bzz.museum.model;

/**
 * Die Künstler Klasse um Künstler zu erstellen; ID, Name und Geburtsdatum vorhanden
 */

public class Kuenstler {

    //Attribute
    private String kuenstlerID;
    private String name;
    private String geburtsdatum;

    /**
     * Holt die ID eines Kuenstlers, als UUID
     * @return kuenstlerID
     */
    public String getKuenstlerID() {
        return kuenstlerID;
    }

    /**
     * Setzt die ID eines Kuenstlers
     * @param kuenstlerID
     */
    public void setKuenstlerID(String kuenstlerID) {
        this.kuenstlerID = kuenstlerID;
    }

    /**
     * Holt den Namen des Kuenstlers
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter für den Namen eines Kuenstlers
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Holt das Geburtsdatum eines Kuenstlers
     * @return geburtsdatum
     */
    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    /**
     * Setter für das Geburtsdatum des Kuenstlers
     * @param geburtsdatum
     */
    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
}
