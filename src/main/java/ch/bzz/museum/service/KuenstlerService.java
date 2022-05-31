package ch.bzz.museum.service;

import ch.bzz.museum.data.DataHandler;
import ch.bzz.museum.model.Ausstellung;
import ch.bzz.museum.model.Kuenstler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("kuenstler")
public class KuenstlerService {

    /**
     * liest eine liste von allen Künstlern
     * @return Künstler as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listKuenstler(){
        List<Kuenstler> kuenstlerList = DataHandler.readAllKuenstler();
        return Response
                .status(200)
                .entity(kuenstlerList)
                .build();
    }

    /**
     * liest eine liste von einem einzigen Künstler
     * @return künstler as JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readKuenstler(
            @QueryParam("uuid") String kuenstlerUUID
    ) {
        if (kuenstlerUUID.isEmpty()){ //falls uuid leer ist, exception werfen
            new IllegalArgumentException("Error. Illegal argument.");
            return Response.status(400).entity(null).build();
        } else { //sonst die response wiedergeben
            Kuenstler kuenstler = DataHandler.readKuenstlerByUUID(kuenstlerUUID);
            if (kuenstler != null){
                return Response
                        .status(200)
                        .entity(kuenstler)
                        .build();
            } else {
                return Response.status(404).entity(kuenstler).build();
            }
        }
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateKuenstler(
            @FormParam("kuenstlerID") String kuenstlerID,
            @FormParam("name") String name,
            @FormParam("geburtsdatum") String geburtsdatum
    ) {
        int httpstatus = 200;
        Kuenstler kuenstler = DataHandler.readKuenstlerByUUID(kuenstlerID);
        if (kuenstler != null){
            kuenstler.setKuenstlerID(UUID.randomUUID().toString());
            kuenstler.setName(name);
            kuenstler.setGeburtsdatum(geburtsdatum);
            kuenstler.setName(name);

            DataHandler.updateKuenstler();
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
    public Response insertKuenstler(
            @FormParam("name") String name,
            @FormParam("geburtsdatum") String geburtsdatum

    ) {
        Kuenstler kuenstler = new Kuenstler();
        kuenstler.setKuenstlerID(UUID.randomUUID().toString());
        kuenstler.setName(name);
        kuenstler.setGeburtsdatum(geburtsdatum);

        DataHandler.insertKuenstler(kuenstler);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteKuenstler(
            @QueryParam("uuid") String kuenstlerID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteKuenstler(kuenstlerID)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }


}
