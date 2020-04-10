package com.elcom.service.interview;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.elcom.business.manager.CommonManager;
import com.elcom.service.BasedService;

@Path("/v1.0/media")
public class MediaService extends BasedService {

    public MediaService(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        super(headers, request);
    }

    @GET
    @Path("/video/{mediaId}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response mediaProgress(@PathParam("mediaId") Long mediaId) throws Exception {
 	   
    	//return this.authorize("standard", () -> {
    		
		    try (CommonManager manager = new CommonManager()) {
			 
				return Response.ok(manager.mediaProgress(mediaId))
			              .header("content-disposition", "attachment; filename = " + mediaId + ".mp4")
			              .build();
	        }
	   	//});
    }
}
