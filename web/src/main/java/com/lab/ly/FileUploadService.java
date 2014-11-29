package com.lab.ly;


import com.lab.ly.common.DataSet;
import com.lab.ly.common.MemoryMappedSerializableDataSet;
import com.lab.ly.formats.ds.DelimitedFileParser;
import com.lab.ly.formats.ds.InMemoryDelimitedFileParser;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

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
    @Produces("application/xml")
    @Consumes("multipart/form-data")
    public DataSet uploadFile(@MultipartForm UploadedFile form) throws UnsupportedEncodingException {
        final String result = new String(form.getData(), "UTF-8");
        return new InMemoryDelimitedFileParser().parse(result);
    }

    @GET
    @Path("file")
    @Produces("application/xml")
    public DelimitedFile getFile() {
        final String result = "hello,world\nhow are,you?";
        return new DelimitedFile(result);
    }

}
