package ch.bzz.museum.service;

import ch.bzz.museum.data.DataHandler;
import ch.bzz.museum.model.Kuenstler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
        List<Kuenstler> kuenstlerList = DataHandler.getInstance().readAllKünstler();
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
            Kuenstler kuenstler = DataHandler.getInstance().readKünstlerByUUID(kuenstlerUUID);
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

}
