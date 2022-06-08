package ch.bzz.museum.service;

import ch.bzz.museum.data.DataHandler;
import ch.bzz.museum.model.Kuenstler;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * Service für den Künstler
 */

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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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

    /**
     * updated einen einzigen Künstler
     * @param kuenstler
     * @param kuenstlerID
     * @return
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateKuenstler(
            @Valid @BeanParam Kuenstler kuenstler,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("kuenstlerID") String kuenstlerID
    ) {
        int httpstatus = 200;
        Kuenstler oldKuenstler = DataHandler.readKuenstlerByUUID(kuenstler.getKuenstlerID());
        if (oldKuenstler != null){
            oldKuenstler.setKuenstlerID(UUID.randomUUID().toString());
            oldKuenstler.setName(kuenstler.getName());
            oldKuenstler.setGeburtsdatum(kuenstler.getGeburtsdatum());
            oldKuenstler.setName(kuenstler.getName());

            DataHandler.updateKuenstler();
        } else {
            httpstatus = 410;
        }
        return Response
                .status(httpstatus)
                .entity("")
                .build();
    }

    /**
     * erstellt einen Künstler
     * @param kuenstler
     * @return
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertKuenstler(
            @Valid @BeanParam Kuenstler kuenstler
    ) {
        kuenstler.setKuenstlerID(UUID.randomUUID().toString());

        DataHandler.insertKuenstler(kuenstler);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * löscht einen Künstler
     * @param kuenstlerID
     * @return
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteKuenstler(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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
