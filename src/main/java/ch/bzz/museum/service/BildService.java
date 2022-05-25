package ch.bzz.museum.service;

import ch.bzz.museum.data.DataHandler;
import ch.bzz.museum.model.Bild;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
     * @param bildID
     * @param name
     * @param künstler
     * @param datum
     * @param art
     * @param preis
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBild(
            @FormParam("bildID") String bildID,
            @FormParam("name") String name,
            @FormParam("künstler") String künstler,
            @FormParam("datum") int datum,
            @FormParam("art") String art,
            @FormParam("preis") double preis
    ) {
        int httpstatus = 200;
        Bild bild = DataHandler.readBildByUUID(bildID);
        if (bild != null){
            bild.setBildID(UUID.randomUUID().toString());
            bild.setName(name);
            bild.setKünstler(künstler);
            bild.setDatum(datum);
            bild.setArt(art);
            bild.setPreis(preis);

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
     * @param name
     * @param künstler
     * @param datum
     * @param art
     * @param preis
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertBild(
            @FormParam("name") String name,
            @FormParam("künstler") String künstler,
            @FormParam("datum") int datum,
            @FormParam("art") String art,
            @FormParam("preis") double preis

    ) {
        Bild bild = new Bild();
        bild.setBildID(UUID.randomUUID().toString());
        bild.setName(name);
        bild.setKünstler(künstler);
        bild.setDatum(datum);
        bild.setArt(art);
        bild.setPreis(preis);

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
