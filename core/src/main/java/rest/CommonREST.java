package rest;

import database.ProductDAO;
import entities.Product;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class CommonREST {

    protected static final String NAME_PARAMETER = "name";
    protected static final String ORGANIZATION_NAME_PARAMETER = "organization_name";
    protected static final String QUANTITY_PARAMETER = "quantity";

    protected Response getProductsByOrganization(String organizationName){
        try {
            ProductDAO productDAO = new ProductDAO();
            List<Product> records = organizationName == null ? productDAO.getAll() : productDAO.getAll(organizationName);
            if(records.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).build();
            return Response.ok(records).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_GATEWAY).build();
        }
    }

}
