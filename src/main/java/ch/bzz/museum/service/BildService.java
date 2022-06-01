package ch.bzz.museum.service;

import ch.bzz.museum.data.DataHandler;
import ch.bzz.museum.model.Bild;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Path("bild")
public class BildService {

    /**
     * liest eine liste von allen Bildern
     * @return bild as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listBilder(){
        List<Bild> bildList = DataHandler.readAllBilder();
        return Response
                .status(200)
                .entity(bildList)
                .build();
    }

    /**
     * liest eine liste von einem einzigen Bild
     * @return bild as JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readBild(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String bildUUID
    ) {
        if (bildUUID.isEmpty()){ //falls uuid leer ist, exception werfen
            new IllegalArgumentException("Error. Illegal argument.");
            return Response.status(400).entity(null).build();
        } else { //sonst die response wiedergeben
            Bild bild = DataHandler.readBildByUUID(bildUUID);
            if (bild != null){
                return Response
                        .status(200)
                        .entity(bild)
                        .build();
            } else {
                return Response.status(404).entity(bild).build();
            }
        }
    }

    /**
     * updated ein Bild
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBild(
            @Valid @BeanParam Bild bild,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String bildUUID
    ) {
        int httpstatus = 200;
        Bild oldBild = DataHandler.readBildByUUID(bild.getBildID());
        if (oldBild != null){
            oldBild.setBildID(UUID.randomUUID().toString());
            oldBild.setName(bild.getName());
            oldBild.setKuenstler(bild.getKuenstler());
            oldBild.setDatum(bild.getDatum());
            oldBild.setArt(bild.getArt());
            oldBild.setPreis(bild.getPreis());

            DataHandler.updateBild();
        } else {
            httpstatus = 410;
        }
        return Response
                .status(httpstatus)
                .entity("")
                .build();
    }

    /**
     * erstellt ein neues Bild
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertBild(
            @Valid @BeanParam Bild bild
    ) {
        bild.setBildID(UUID.randomUUID().toString());

        DataHandler.insertBild(bild);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * löscht ein Bild durch die uuid
     * @param bildUUID
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteBild(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String bildUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteBild(bildUUID)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }


}
