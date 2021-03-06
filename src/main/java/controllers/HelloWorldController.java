package controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import io.dropwizard.jersey.sessions.Session;
//import jdk.nashorn.internal.objects.annotations.Getter;

// For a Java class to be eligible to receive ANY requests
// it must be annotated with at least @Path
@Path("")
public class HelloWorldController {

    // You can specify additional @Path steps; they will be relative
    // to the @Path defined at the class level
    @GET
    @Path("/netid")
    public String netid() {
        return "hc935";
    }
}