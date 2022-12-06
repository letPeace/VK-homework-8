package rest;

import enums.Folder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Path("/info")
public class StaticREST extends CommonREST {

    public static final String STATIC_INFO_HTML = Folder.STATIC_INFO.value+".html";

    @GET
    @Produces(MediaType.TEXT_HTML)
    public FileInputStream get() throws FileNotFoundException {
        File file = new File(getClass().getResource(STATIC_INFO_HTML).getFile());
        return new FileInputStream(file);
    }

}
