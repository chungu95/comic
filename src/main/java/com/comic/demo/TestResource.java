package com.comic.demo;

import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
@Tag(name = "Test")
@ApplicationScoped
@RequiredArgsConstructor
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class TestResource {

    @POST
    @Path("/health-check")
    @RolesAllowed({"ADMIN"})
    public String healthCheck() {
        return "ok";
    }
}
