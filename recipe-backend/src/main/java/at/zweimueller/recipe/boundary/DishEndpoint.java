package at.zweimueller.recipe.boundary;

import at.zweimueller.recipe.control.DishRepository;
import at.zweimueller.recipe.control.TagRepository;
import at.zweimueller.recipe.entity.Dish;
import at.zweimueller.recipe.entity.DishTag;
import at.zweimueller.recipe.entity.Tag;
import org.hibernate.Hibernate;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("dish")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class DishEndpoint {

    @Inject
    DishRepository dishRepository;
    @Inject
    TagRepository tagRepository;

    @GET
    //@RolesAllowed({"admin", "user"})
    public Response getAll() {
        List<Dish> dishList = dishRepository.findAll().list();
        for (Dish dish: dishList) {
            Hibernate.initialize(dish.getDishTags());
            Hibernate.initialize(dish.getIngredients());
        }
        return Response.ok(dishList).build();
    }

    @POST
    //@RolesAllowed({"admin"})
    public Response create(Dish dish) {
        dishRepository.persist(dish);
        dish.getIngredients().forEach(ingredient -> ingredient.setDish(dish));
        return Response.ok(dish).build();
    }

    @GET
    @Path("/{dishname}/{tagname}")
    //@RolesAllowed({"admin"})
    public Response tagADish(@PathParam("dishname") String dishname, @PathParam("tagname") String tagname) {
        Dish dish = dishRepository.findByName(dishname);
        Tag tag = tagRepository.findByName(tagname);
        if(dish == null && tag == null) {
            return Response.status(404).build();
        }
        DishTag dishTag = new DishTag(dish, tag);
        return Response.ok(dishTag).build();
    }

    @GET
    @Path("/{tagname}")
    //@RolesAllowed({"user", "admin"})
    public Response findDishByTag(@PathParam("tagname") String tagname) {
        List<Dish> dishList = tagRepository.findDishesByTag(tagname);
        return Response.ok(dishList).build();
    }


}
