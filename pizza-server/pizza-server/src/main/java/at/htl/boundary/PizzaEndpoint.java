package at.htl.boundary;

import at.htl.entity.Order;
import at.htl.entity.Pizza;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Path("/pizza")
public class PizzaEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPizzas() {
        List<Pizza> pizzas = new LinkedList<>();

        pizzas.add(new Pizza(
                1, "Salami", "L", 9.90,
                "https://ichkocheheute.de/wp-content/uploads/2017/08/P1120510.jpg"));
        pizzas.add(new Pizza(
                2, "Hawaii", "L", 8.90,
                        "https://media.kaufland.com/images/PPIM/AP_Content_1010/std.lang.all/86/17/Asset_3618617.jpg"));
        pizzas.add(new Pizza(
                3,"Capricciosa", "L", 9.50,
                "https://cdn.tasteatlas.com/images/dishes/5789ac96790b4e27b6e8ca102f917b2c.jpg?w=600&h=450%27"));

        return Response.ok(pizzas).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void postOrder(Order order) {
        System.out.println(order.toString());
    }
}
