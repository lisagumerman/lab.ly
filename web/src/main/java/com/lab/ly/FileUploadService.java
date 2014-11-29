package com.lab.ly;


import com.lab.ly.common.DataSet;
import com.lab.ly.common.MemoryMappedSerializableDataSet;
import com.lab.ly.formats.ds.DelimitedFileParser;
import com.lab.ly.formats.ds.InMemoryDelimitedFileParser;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * User: haswell
 * Date: 11/23/14
 * Time: 3:01 PM
 */
@Path("upload")
@Singleton
public class FileUploadService {


    DataSet dataset;

    @GET
    @Path("hello/{name}")
    @Produces("text/plain")
    public String sayHello(@PathParam("name") String name) {
        return "Hello" + name;

    }

    @GET
    @Path("dataset")
    @Produces(MediaType.APPLICATION_XML)
    public DataSet getDataset() {
        System.out.println(dataset);
        return dataset;
    }


    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    public String uploadFile(@MultipartForm UploadedFile form) throws UnsupportedEncodingException {
        final String result = new String(form.getData(), "UTF-8");
        dataset = new InMemoryDelimitedFileParser().parse(result);
        return "Uploaded :)";
    }

    @GET
    @Path("file")
    @Produces("application/xml")
    public DelimitedFile getFile() {
        final String result = "hello,world\nhow are,you?";
        return new DelimitedFile(result);
    }

}
