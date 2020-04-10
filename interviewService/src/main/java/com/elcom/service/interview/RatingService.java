/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.service.interview;

import com.elcom.business.manager.CommonManager;
import com.elcom.data.interview.entity.Rating;
import com.elcom.model.dto.interview.ResponseData;
import com.elcom.service.BasedService;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Administrator
 */
@Path("/v1.0/rating")
public class RatingService extends BasedService {

    public RatingService(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        super(headers, request);
    }
    
    /**
     * Insert bản ghi đánh giá ứng viên vào bảng rating
     * @param req
     * @return ResponseData chứa id bản ghi tạo ra
     * @throws Exception 
     */
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertRating(Rating req) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                return created(manager.insertRating(req));
            }
        });
    }
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllForCampany(@QueryParam("companyId") Long companyId) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                ResponseData res = manager.findAllForCampany(companyId);
                if (res.getStatus() == Response.Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }
    
}
