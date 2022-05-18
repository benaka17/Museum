package ch.bzz.museum.data;

import ch.bzz.museum.model.Ausstellung;
import ch.bzz.museum.model.Bild;
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

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setAusstellungList(new ArrayList<>());
        readAusstellungJSON();
        setBilderList(new ArrayList<>());
        readBildJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all bilder
     * @return list of bilder
     */
    public List<Bild> readAllBilder() {
        return getInstance().bilderList;
    }

    /**
     * reads a bild by its uuid
     * @param bildUUID
     * @return the bild (null=not found)
     */
    public Bild readBildByUUID(String bildUUID) {
        Bild bild = null;
        for (Bild entry : getInstance().bilderList) {
            if (entry.getBildID().equals(bildUUID)) {
                bild = entry;
            }
        }
        return bild;
    }

    /**
     * reads all Ausstellungen
     * @return list of Ausstellungen
     */
    public List<Ausstellung> readAllPublishers() {

        return getInstance().ausstellungList;
    }

    /**
     * reads a ausstellung by its uuid
     * @param ausstellungUUID
     * @return the Ausstellung (null=not found)
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
     * reads the bilder from the JSON-file
     */
    private void readBildJSON() {
        try {
            String path = Config.getProperty("bookJSON");
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
     * reads the Ausstellungen from the JSON-file
     */
    private void readAusstellungJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("publisherJSON")
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


}