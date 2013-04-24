/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resteasy.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Andrej Galád <agalad@redhat.com>
 */
@Path("/query_string")
public class QueryStringService {

    @GET
    @Path("weird")
    public String getQueryString(@QueryParam("foo") String incoming) {
        return incoming;
    }
}
