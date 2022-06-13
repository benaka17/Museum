package ch.bzz.museum.service;

import ch.bzz.museum.data.DataHandler;
import ch.bzz.museum.model.Ausstellung;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAusstellung(
            @Valid @BeanParam Ausstellung ausstellung,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("musueumID") String museumID
    ) {
        int httpstatus = 200;
        Ausstellung oldAusstellung = DataHandler.readAusstellungByUUID(museumID);
        if (oldAusstellung != null){
            oldAusstellung.setMuseumID(UUID.randomUUID().toString());
            oldAusstellung.setAnzBilder(ausstellung.getAnzBilder());
            oldAusstellung.setOrt(ausstellung.getOrt());
            oldAusstellung.setName(ausstellung.getName());

            DataHandler.updateAusstellung();
        } else {
            httpstatus = 410;
        }
        return Response
                .status(httpstatus)
                .entity("")
                .build();
    }

    /**
     * erstellt eine Ausstellung
     * @param ausstellung
     * @return Repsonse
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertAusstellung(
            @Valid @BeanParam Ausstellung ausstellung
    ) {
        ausstellung.setMuseumID(UUID.randomUUID().toString());

        DataHandler.insertAusstellung(ausstellung);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * löscht eine Ausstellung
     * @param museumID
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAusstellung(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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
