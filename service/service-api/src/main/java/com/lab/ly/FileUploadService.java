package com.lab.ly;

import com.lab.ly.model.DataSet;
import com.lab.ly.model.io.UploadedFile;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by haswell on 1/13/15.
 */

@Path("api/upload")
public interface  FileUploadService {



    @POST
    @Path("/file")
    @Consumes("multipart/form-data")
    public DataSet upload(@MultipartForm UploadedFile file);

    @GET
    @Path("{text}")
    @Produces({
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_JSON,
    })
    public String sayHello(@PathParam("text") String text);




    @GET
    @Path("/")
    @Produces({
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_JSON,
    })
    public DataSet get();


}
