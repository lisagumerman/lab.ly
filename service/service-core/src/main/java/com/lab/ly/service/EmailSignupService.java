package com.lab.ly.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by haswell on 2/7/15.
 */

@Path("api/signup")
@Produces({
        MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON,
})
@Consumes({
        MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON,
})
public interface EmailSignupService {

        @GET
        @Path("/{email}")
        public String save(@PathParam("email") String emailAddress);
}
