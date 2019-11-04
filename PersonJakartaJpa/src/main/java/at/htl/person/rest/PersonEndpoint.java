package at.htl.person.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("person")
public class PersonEndpoint {

    @GET
    public String hello() {
        return "hello";
    }
}
