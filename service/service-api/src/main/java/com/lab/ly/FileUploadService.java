package com.lab.ly;

import com.lab.ly.model.DataSet;
import com.lab.ly.model.io.UploadedFile;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by haswell on 1/13/15.
 */

@Path("api/upload")
public interface  FileUploadService {



    @POST
    @Path("/file")
    @Consumes("multipart/form-data")
    public DataSet upload(@MultipartForm UploadedFile file);


}
