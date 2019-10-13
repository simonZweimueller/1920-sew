package at.htl.vehicle.rest;

import at.htl.vehicle.model.Vehicle;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("vehicle")
public class VehicleEndpoint {

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Vehicle find(@PathParam("id") long id) {
        return new Vehicle("Opel " + id, "Commodere");
    }

    @GET
    public List<Vehicle> findAll() {
        List<Vehicle> all = new ArrayList<>();
        all.add(find(42));
        return all;
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        System.out.println("deleted: " + id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(Vehicle vehicle) {
        System.out.println("Vehicle = " + vehicle);
    }


}
