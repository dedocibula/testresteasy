package resteasy.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import resteasy.entities.Person;

/**
 *
 * @author Andrej Galád <agalad@redhat.com>
 */
@Path("/content_type")
public class ContentTypeService {
    
    @GET
    @Path("/html")
    public Person getHtmlResponse() {
        
        Person pepa = new Person();
        pepa.setName("pepa");
        return pepa;
    }
}
