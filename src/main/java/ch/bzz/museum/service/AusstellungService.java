package ch.bzz.museum.service;

import ch.bzz.museum.data.DataHandler;
import ch.bzz.museum.model.Ausstellung;
import ch.bzz.museum.model.Bild;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("ausstellung")
public class AusstellungService {

    /**
     * liest eine liste von allen Ausstellungen
     * @return ausstellung als JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAusstellung(){
        List<Ausstellung> ausstellungList = DataHandler.readAllAusstellung();
        return Response
                .status(200)
                .entity(ausstellungList)
                .build();
    }

    /**
     * liest eine liste von einer einzigen Ausstellung
     * @return ausstellung as JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAusstellung(
            @QueryParam("uuid") String bildUUID
    ) {
        if (bildUUID.isEmpty()){ //falls uuid leer ist, exception werfen
            new IllegalArgumentException("Error. Illegal argument.");
            return Response.status(400).entity(null).build();
        } else { //sonst response wiedergeben
            Ausstellung ausstellung = DataHandler.readAusstellungByUUID(bildUUID);
            if (ausstellung != null){
                return Response
                        .status(200)
                        .entity(ausstellung)
                        .build();
            } else {
                return Response.status(404).entity(ausstellung).build();
            }
        }
    }

    /**
     * updated eine Ausstellung
     * @param museumID
     * @param anzBilder
     * @param ort
     * @param name
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAusstellung(
            @FormParam("musueumID") String museumID,
            @FormParam("anzBiler") int anzBilder,
            @FormParam("ort") String ort,
            @FormParam("name") String name
    ) {
        int httpstatus = 200;
        Ausstellung ausstellung = DataHandler.readAusstellungByUUID(museumID);
        if (ausstellung != null){
            ausstellung.setMuseumID(UUID.randomUUID().toString());
            ausstellung.setAnzBilder(anzBilder);
            ausstellung.setOrt(ort);
            ausstellung.setName(name);

            DataHandler.updateAusstellung();
        } else {
            httpstatus = 410;
        }
        return Response
                .status(httpstatus)
                .entity("")
                .build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertAusstellung(
            @FormParam("anzBiler") int anzBilder,
            @FormParam("ort") String ort,
            @FormParam("name") String name

    ) {
        Ausstellung ausstellung = new Ausstellung();
        ausstellung.setMuseumID(UUID.randomUUID().toString());
        ausstellung.setName(name);
        ausstellung.setOrt(ort);
        ausstellung.setName(name);

        DataHandler.insertAusstellung(ausstellung);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAusstellung(
            @QueryParam("uuid") String museumID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteAusstellung(museumID)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }



}
