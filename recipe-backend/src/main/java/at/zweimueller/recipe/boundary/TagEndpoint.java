package at.zweimueller.recipe.boundary;

import at.zweimueller.recipe.control.TagRepository;
import at.zweimueller.recipe.entity.Dish;
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

@Path("tag")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class TagEndpoint {

    @Inject
    TagRepository tagRepository;

    @GET
    //@RolesAllowed({"admin", "user"})
    public Response getAll() {
        List<Tag> tagList = tagRepository.findAll().list();
        for (Tag tag: tagList) {
            Hibernate.initialize(tag.getDishTags());
        }
        return Response.ok(tagList).build();
    }

    @POST
    @Path("/{tagname}")
    //@RolesAllowed({"admin"})
    public Response create(@PathParam("tagname") String tagname) {
        Tag tag = new Tag(tagname);
        tagRepository.persist(tag);
        return Response.ok(tag).build();
    }

    @DELETE
    @Path("/{tagname}")
    //@RolesAllowed({"admin"})
    public Response delete(@PathParam("tagname") String tagname) {
        Tag tag = tagRepository.findByName(tagname);
        if (tag.getDishTags().isEmpty()) {
            tagRepository.delete(tag);
        }
        return Response.ok(tag).build();
    }
}
