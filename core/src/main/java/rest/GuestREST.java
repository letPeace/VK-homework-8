package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/guest")
public class GuestREST extends CommonREST {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam(ORGANIZATION_NAME_PARAMETER) String organizationName) {
        return getProductsByOrganization(organizationName);
    }

}
