package ch.bzz.museum.service;

import ch.bzz.museum.data.DataHandler;
import ch.bzz.museum.model.Ausstellung;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
        List<Ausstellung> ausstellungList = DataHandler.getInstance().readAllAusstellung();
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
            Ausstellung ausstellung = DataHandler.getInstance().readAusstellungByUUID(bildUUID);
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

}
