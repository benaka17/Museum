package ch.bzz.museum.model;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;
import java.math.BigDecimal;

/**
 * Die Bild Klasse für ein einzelnes Bild mit ID, Name, dem Künstler, Datum, Art und Preis
 */

public class Bild {

    //Attribute
    @FormParam("bildID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String bildID;

    @FormParam("name")
    @NotEmpty
    @Size(min=5, max=40)
    private String name;

    @FormParam("kuenstler")
    @NotEmpty
    @Size(min=5, max=40)
    private String kuenstler;

    @FormParam("datum")
    @NotNull
    @Min(0)
    @Max(2022)
    private Integer datum; //Wrapper-Klasse benutzt, da Fehler vorkamen ohne

    @FormParam("art")
    @NotEmpty
    @Size(min=5, max=40)
    private String art; //Kategorien wie "modern"

    @FormParam("preis")
    @DecimalMax(value="999999999.95")
    @DecimalMin(value="0.05")
    private BigDecimal preis;
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
    public String getKuenstler() {
        return kuenstler;
    }

    /**
     * Setter für einen Künstler
     * @param kuenstler
     */
    public void setKuenstler(String kuenstler) {
        this.kuenstler = kuenstler;
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
    public BigDecimal getPreis() {
        return preis;
    }

    /**
     * Setter für den Preis
     * @param preis
     */
    public void setPreis(BigDecimal preis) {
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
