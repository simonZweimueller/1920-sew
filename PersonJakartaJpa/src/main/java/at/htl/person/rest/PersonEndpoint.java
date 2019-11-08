package at.htl.person.rest;

import at.htl.person.model.Person;

import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("person")
public class PersonEndpoint {

    @PersistenceContext
    EntityManager em;

    /**
     *
     * @return
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> findFirtst() {
        //return em.find(Person.class, 1L);
        return em.createNamedQuery("Person.findAll", Person.class).getResultList();
    }

    /**
     * creating a new person in database
     * @param person
     * @param uriInfo
     * @return
     */

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Person person, @Context UriInfo uriInfo) {
        Person p = em.merge(person);
        Long id = p.getId();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/" + id).build();
        return Response.created(uri).entity(p).build();
    }

    /**
     * FormParam ... html -> submit
     * QueryParam ... http://myurl:8080/person/api/person?name=Susi
     * PathParam ...  http://myurl:8080/person/api/person/4
     * @return
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonById(@PathParam("id") long id) {
        return em.find(Person.class, id);
    }

    @GET
    @Path("name")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonByName(@QueryParam("name") String name) {
        try {
            TypedQuery<Person> query = em.createNamedQuery("Person.findByName", Person.class)
                    .setParameter("NAME", name);
            Person p = query.getSingleResult();
            return p;
        } catch (NoResultException e) {
            throw new WebApplicationException(Response.Status.BAD_GATEWAY);
        }
    }

    @POST
    @Path("jsondemo")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getJsonObject(JsonObject json) {
        String text = json.getString("city");
        return Response.ok(text).build();
    }
}
