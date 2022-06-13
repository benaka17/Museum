package ch.bzz.museum.service;

import ch.bzz.museum.data.DataHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * test service zum initialisieren
 */
@Path("test")
public class TestService {

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {

        return Response
                .status(200)
                .entity("Test erfolgreich")
                .build();
    }

    /**
     * restores the json-files
     * @return Response
     */
    @GET
    @Path("restore")
    @Produces(MediaType.TEXT_PLAIN)
    public Response restore() {
        try {
            java.nio.file.Path path = Paths.get(Config.getProperty("bildJSON"));
            String filename = path.getFileName().toString();
            String folder = path.getParent().toString();

            byte[] bildJSON = Files.readAllBytes(Paths.get(folder, "backup", filename));
            FileOutputStream fileOutputStream = new FileOutputStream(Config.getProperty("bildJSON"));
            fileOutputStream.write(bildJSON);

            path = Paths.get(Config.getProperty("ausstellungJSON"));
            filename = path.getFileName().toString();
            folder = path.getParent().toString();

            byte[] ausstellungJSON = Files.readAllBytes(Paths.get(folder, "backup", filename));
            fileOutputStream = new FileOutputStream(Config.getProperty("ausstellungJSON"));
            fileOutputStream.write(ausstellungJSON);

            path = Paths.get(Config.getProperty("kuenstlerJSON"));
            filename = path.getFileName().toString();
            folder = path.getParent().toString();

            byte[] kuenstlerJSON = Files.readAllBytes(Paths.get(folder, "backup", filename));
            fileOutputStream = new FileOutputStream(Config.getProperty("kuenstlerJSON"));
            fileOutputStream.write(kuenstlerJSON);

        } catch (IOException e) {
            e.printStackTrace();
        }

        DataHandler.initLists();
        return Response
                .status(200)
                .entity("Erfolgreich")
                .build();
    }
}


