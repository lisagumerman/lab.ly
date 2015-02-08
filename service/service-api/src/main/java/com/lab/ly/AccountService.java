package com.lab.ly;

import com.lab.ly.model.Account;
import com.lab.ly.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by haswell on 2/2/15.
 */
@Path("api/account")
@Produces({
        MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON,
})
@Consumes({
        MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON,
})
public interface AccountService {

    @POST
    @Path("{id}/user/")
    User addUser(@PathParam("id") Long accountId, User user);


    @POST
    @Path("/")
    Long save(Account account);


}
