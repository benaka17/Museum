package ch.bzz.museum.data;

import ch.bzz.museum.model.Ausstellung;
import ch.bzz.museum.model.Bild;
import ch.bzz.museum.model.Kuenstler;
import ch.bzz.museum.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * liest und schreibt die Daten in den JSON-files
 */
public final class DataHandler {
    private static List<Bild> bilderList;
    private static List<Ausstellung> ausstellungList;
    private static List<Kuenstler> kuenstlerList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
    }

    public static void initLists() {
        DataHandler.setBilderList(null);
        DataHandler.setAusstellungList(null);
    }

    /**
     * liest alle Künstler
     * @return liste von allen künstlern
     */
    public static List<Kuenstler> readAllKuenstler() {
        return getKuenstlerList();
    }

    /**
     * liest alle Künstler nach ihrer uuid
     * @param kuenstlerUUID
     * @return the künstler (null=not found)
     */
    public static Kuenstler readKuenstlerByUUID(String kuenstlerUUID) {
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
    public static void readKuenstlerJSON() {
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
    public static List<Bild> readAllBilder() {
        return getBilderList();
    }

    /**
     * liest alle Bilder nach ihrer uuid
     * @param bildUUID
     * @return the bild (null=not found)
     */
    public static Bild readBildByUUID(String bildUUID) {
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
    public static List<Ausstellung> readAllAusstellung() {
        return getAusstellungList();
    }

    /**
     * liest eine Ausstellung nach ihrer uuid
     * @param ausstellungUUID
     * @return die Ausstellung (null=not found)
     */
    public static Ausstellung readAusstellungByUUID(String ausstellungUUID) {
        Ausstellung ausstellung = null;
        for (Ausstellung entry : ausstellungList) {
            if (entry.getMuseumID().equals(ausstellungUUID)) {
                ausstellung = entry;
            }
        }
        return ausstellung;
    }

    /**
     * liest die bilder vom JSON-file
     */
    public static void readBildJSON() {
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
    public static void readAusstellungJSON() {
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
     * holt bilderList
     *
     * @return value of bilderList
     */
    public static List<Bild> getBilderList() {
        if (bilderList == null) {
            setBilderList(new ArrayList<>());
            readBildJSON();
        }
        return bilderList;
    }

    /**
     * setzt bilderList
     *
     * @param bilderList the value to set
     */
    public static void setBilderList(List<Bild> bilderList) {
        DataHandler.bilderList = bilderList;
    }

    /**
     * holt ausstellungList
     *
     * @return value of austellungList
     */
    private static List<Ausstellung> getAusstellungList() {
        if (ausstellungList == null) {
            setAusstellungList(new ArrayList<>());
            readAusstellungJSON();
        }

        return ausstellungList;
    }

    /**
     * setzt ausstellungList
     *
     * @param ausstellungList the value to set
     */
    public static void setAusstellungList(List<Ausstellung> ausstellungList) {
        DataHandler.ausstellungList = ausstellungList;
    }

    /**
     * holt künstlerList
     *
     * @return value of austellungList
     */
    public static List<Kuenstler> getKuenstlerList() {
        if (kuenstlerList == null){
            setKuenstlerList(new ArrayList<>());
            readKuenstlerJSON();
        }
        return kuenstlerList;
    }

    /**
     * setzt künsterList
     *
     * @param kuenstlerList the value to set
     */
    public static void setKuenstlerList(List<Kuenstler> kuenstlerList) {
        DataHandler.kuenstlerList = kuenstlerList;
    }

    /**
     * schreibt die bilderList zum JSON-file
     */
    private static void writeBildJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("bilderJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getBilderList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertBild(Bild bild) {
        getBilderList().add(bild);
        writeBildJSON();
    }
    /**
     * löscht ein bild identifiziert durh die uuid
     * @param bildUUID  the key
     * @return  success=true/false
            */
    public static boolean deleteBild(String bildUUID) {
        Bild bild = readBildByUUID(bildUUID);
        if (bild != null) {
            getBilderList().remove(bild);
            writeBildJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * updated die bilderList
     */
    public static void updateBild() {
        writeBildJSON();
    }

    //TODO TRENNUNG

    public static void insertAusstellung(Ausstellung ausstellung) {
        getAusstellungList().add(ausstellung);
        writeAusstellungJSON();
    }

    private static void writeAusstellungJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("ausstellungJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getBilderList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateAusstellung() {
        writeAusstellungJSON();
    }

    public static boolean deleteAusstellung(String museumID) {
        Ausstellung ausstellung = readAusstellungByUUID(museumID);
        if (ausstellung != null) {
            getAusstellungList().remove(ausstellung);
            writeAusstellungJSON();
            return true;
        } else {
            return false;
        }
    }

    //TODO TRENNUNG

    public static void insertKuenstler(Kuenstler kuenstler) {
        getKuenstlerList().add(kuenstler);
        writeKuenstlerJSON();
    }

    private static void writeKuenstlerJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("kuenstlerJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getBilderList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateKuenstler() {
        writeKuenstlerJSON();
    }

    public static boolean deleteKuenstler(String kuenstlerID) {
        Kuenstler kuenstler = readKuenstlerByUUID(kuenstlerID);
        if (kuenstler != null) {
            getKuenstlerList().remove(kuenstler);
            writeKuenstlerJSON();
            return true;
        } else {
            return false;
        }
    }
}