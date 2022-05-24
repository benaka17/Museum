package ch.bzz.museum.service;

import ch.bzz.museum.data.DataHandler;
import ch.bzz.museum.model.Bild;
import ch.bzz.museum.model.Ausstellung;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
        List<Bild> bildList = DataHandler.getInstance().readAllBilder();
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
            Bild bild = DataHandler.getInstance().readBildByUUID(bildUUID);
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

}
