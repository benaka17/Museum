package ch.bzz.museum.data;

import ch.bzz.museum.model.Ausstellung;
import ch.bzz.museum.model.Bild;
import ch.bzz.museum.model.Kuenstler;
import ch.bzz.museum.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Bild> bilderList;
    private List<Ausstellung> ausstellungList;
    private List<Kuenstler> kuenstlerList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setAusstellungList(new ArrayList<>());
        readAusstellungJSON();
        setBilderList(new ArrayList<>());
        readBildJSON();
        setKuenstlerList(new ArrayList<>());
        readKünstlerJSON();
    }

    /**
     * gets the only instance of this class
     * @return instance
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }

    /**
     * liest alle Künstler
     * @return liste von allen künstlern
     */
    public List<Kuenstler> readAllKünstler() {
        return getInstance().kuenstlerList;
    }

    /**
     * liest alle Künstler nach ihrer uuid
     * @param kuenstlerUUID
     * @return the künstler (null=not found)
     */
    public Kuenstler readKünstlerByUUID(String kuenstlerUUID) {
        Kuenstler kuenslter = null;
        for (Kuenstler entry : getKuenstlerList()) {
            if (entry.getKuenstlerID().equals(kuenstlerUUID)) {
                kuenslter = entry;
            }
        }
        return kuenslter;
    }

    /**
     * liest die Künstler vom JSON-file
     */
    private void readKünstlerJSON() {
        try {
            String path = Config.getProperty("kuenstlerJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Kuenstler[] kuenstler = objectMapper.readValue(jsonData, Kuenstler[].class);
            for (Kuenstler kuenstler1 : kuenstler) {
                getKuenstlerList().add(kuenstler1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * liest alle Bilder
     * @return list of bilder
     */
    public List<Bild> readAllBilder() {
        return getInstance().bilderList;
    }

    /**
     * liest alle Bilder nach ihrer uuid
     * @param bildUUID
     * @return the bild (null=not found)
     */
    public Bild readBildByUUID(String bildUUID) {
        Bild bild = null;
        for (Bild entry : getBilderList()) {
            if (entry.getBildID().equals(bildUUID)) {
                bild = entry;
            }
        }
        return bild;
    }

    /**
     * liest alle Ausstellungen
     * @return list of Ausstellungen
     */
    public List<Ausstellung> readAllAusstellung() {

        return getInstance().ausstellungList;
    }

    /**
     * liest eine Ausstellung nach ihrer uuid
     * @param ausstellungUUID
     * @return die Ausstellung (null=not found)
     */
    public Ausstellung readAusstellungByUUID(String ausstellungUUID) {
        Ausstellung ausstellung = null;
        for (Ausstellung entry : getInstance().ausstellungList) {
            if (entry.getMuseumID().equals(ausstellungUUID)) {
                ausstellung = entry;
            }
        }
        return ausstellung;
    }

    /**
     * liest die bilder vom JSON-file
     */
    private void readBildJSON() {
        try {
            String path = Config.getProperty("bilderJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Bild[] bilder = objectMapper.readValue(jsonData, Bild[].class);
            for (Bild bild : bilder) {
                getBilderList().add(bild);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * liest die Ausstellungen vom JSON-file
     */
    private void readAusstellungJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("ausstellungJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Ausstellung[] publishers = objectMapper.readValue(jsonData, Ausstellung[].class);
            for (Ausstellung publisher : publishers) {
                getAusstellungList().add(publisher);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * gets bilderList
     *
     * @return value of bilderList
     */
    private List<Bild> getBilderList() {
        return bilderList;
    }

    /**
     * sets bilderList
     *
     * @param bilderList the value to set
     */
    private void setBilderList(List<Bild> bilderList) {
        this.bilderList = bilderList;
    }

    /**
     * gets ausstellungList
     *
     * @return value of austellungList
     */
    private List<Ausstellung> getAusstellungList() {
        return ausstellungList;
    }

    /**
     * sets ausstellungList
     *
     * @param ausstellungList the value to set
     */
    private void setAusstellungList(List<Ausstellung> ausstellungList) {
        this.ausstellungList = ausstellungList;
    }

    /**
     * gets künstlerList
     *
     * @return value of austellungList
     */
    public List<Kuenstler> getKuenstlerList() {
        return kuenstlerList;
    }

    /**
     * sets künsterList
     *
     * @param kuenstlerList the value to set
     */
    public void setKuenstlerList(List<Kuenstler> kuenstlerList) {
        this.kuenstlerList = kuenstlerList;
    }
}