package rest;

import database.ProductDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/manager")
public class ManagerREST extends CommonREST {

    @Path("/delete")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(@FormParam(NAME_PARAMETER) String name) {
        if(name == null){
            return Response.serverError().build();
        }
        try {
            ProductDAO productDAO = new ProductDAO();
            Integer deleteResult = productDAO.delete(name);
            if(deleteResult == 0) return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_GATEWAY).build();
        }
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(@FormParam(NAME_PARAMETER) String name,
                         @FormParam(ORGANIZATION_NAME_PARAMETER) String organizationName,
                         @FormParam(QUANTITY_PARAMETER) Integer quantity) {
        if(name == null || organizationName == null || quantity == null){
            return Response.serverError().build();
        }
        try {
            ProductDAO productDAO = new ProductDAO();
            Integer saveResult = productDAO.save(name, organizationName, quantity);
            if(saveResult == 0) return Response.status(Response.Status.BAD_GATEWAY).build();
            return Response.ok(saveResult).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_GATEWAY).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam(ORGANIZATION_NAME_PARAMETER) String organizationName) {
        return getProductsByOrganization(organizationName);
    }

}
