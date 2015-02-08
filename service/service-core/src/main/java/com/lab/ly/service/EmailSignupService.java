package com.lab.ly.service;

import com.lab.ly.model.internal.EmailSignup;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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

        @GET
        @Path("/list/{password}")
        public List<EmailSignup> getSignups(@PathParam("password") String password);


}
