package com.comic.story_type;

import com.comic.core.constants.Roles;
import com.comic.core.query.PageQueryBuilder;
import com.comic.core.query.PageQueryParams;
import com.comic.core.query.Pageable;
import com.comic.story_type.domain.StoryTypeCreateRQ;
import com.comic.story_type.domain.StoryTypeRS;
import com.comic.story_type.domain.StoryTypeUpdateRQ;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/story-type")
@Tag(name = "Story type")
@ApplicationScoped
@RequiredArgsConstructor
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoryTypeResource {
    private final StoryTypeService storyTypeService;

    @POST
    @RolesAllowed({Roles.ADMIN})
    @Operation(summary = "Create", description = "Create Story type")
    public StoryTypeRS create(@Valid StoryTypeCreateRQ storyTypeCreateRQ) {
        return storyTypeService.create(storyTypeCreateRQ);
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({Roles.ADMIN})
    @Operation(summary = "Update", description = "Update Story type")
    public StoryTypeRS update(@PathParam("id") Long id, @Valid StoryTypeUpdateRQ storyTypeUpdateRQ) {
        return storyTypeService.update(id, storyTypeUpdateRQ);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({Roles.ADMIN})
    @Operation(summary = "Delete", description = "Delete Story type")
    public void delete(@PathParam("id") Long id) {
        storyTypeService.delete(id);
    }
    
    @GET
    @PermitAll
    @Path("/search")
    @Operation(summary = "Search", description = "Search Story type")
    public Pageable<StoryTypeRS> search(@BeanParam PageQueryParams pageQueryParams) {
        return storyTypeService.search(PageQueryBuilder.of(pageQueryParams));
    }
}
