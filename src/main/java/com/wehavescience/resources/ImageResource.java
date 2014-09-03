package com.wehavescience.resources;

import com.wehavescience.domain.Image;
import com.wehavescience.repositories.Repository;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.noContent;
import static javax.ws.rs.core.Response.ok;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Path("/images")
public class ImageResource {
    @Inject
    private Repository<Image> repository;

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") String id){
        Image image = repository.findById(new ObjectId(id));

        if(image == null){
            return noContent()
                    .build();
        }

        return ok(image.image())
                .type(image.contentType())
                .build();
    }
}
