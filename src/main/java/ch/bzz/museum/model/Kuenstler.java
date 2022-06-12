package ch.bzz.museum.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * Die Künstler Klasse um Künstler zu erstellen; ID, Name und Geburtsdatum vorhanden
 */

public class Kuenstler {

    //Attribute
    @FormParam("kuenstlerID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String kuenstlerID;

    @FormParam("name")
    @NotEmpty
    @Size(min=5, max=40)
    private String name;

    @FormParam("geburtsdatum")
    @NotEmpty
    @Size(min=10, max=10)
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
