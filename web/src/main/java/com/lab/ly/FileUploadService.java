package com.lab.ly;


import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import sun.org.mozilla.javascript.annotations.JSGetter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * User: haswell
 * Date: 11/23/14
 * Time: 3:01 PM
 */
@Path("upload")
public class FileUploadService {

    @GET
    @Path("hello/{name}")
    @Produces("text/plain")
    public String sayHello(@PathParam("name") String name) {
        return "Hello" + name;

    }



    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    public String uploadFile(@MultipartForm UploadedFile form) throws UnsupportedEncodingException {
        throw new RuntimeException(new DelimitedFile(new String(form.getData(), "UTF-8")).contents.toString());
    }

}
