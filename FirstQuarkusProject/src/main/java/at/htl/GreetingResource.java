package at.htl;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService service;

    @Inject
    SantaClausService clausService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(@PathParam("name") String name) {
        return service.greeting(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Path("gift")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Gift> allGifts() {
        return clausService.findAllGifts();
    }
}