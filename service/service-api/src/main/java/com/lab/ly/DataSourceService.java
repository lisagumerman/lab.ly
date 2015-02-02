package com.lab.ly;

import com.lab.ly.model.DataSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by haswell on 1/31/15.
 */
@Path("api/ds")
@Produces({
        MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON,
})
public interface DataSourceService {

    @GET
    @Path("/{id}/{name}")
    DataSet get(@PathParam("id") String dsName, @PathParam("name") String dataSetName);
}
