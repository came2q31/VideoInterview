/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.service.interview;

import com.elcom.business.manager.InterviewManager;
import com.elcom.data.interview.entity.Rating;
import com.elcom.data.interview.entity.ResultCriteria;
import com.elcom.data.interview.entity.ResultTest;
import com.elcom.service.BasedService;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Path("/v1.0/interview")
public class InterviewService extends BasedService {

    public InterviewService(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        super(headers, request);
    }
    
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(Rating req) throws Exception {
        return this.authorize("standard", () -> {
        try (InterviewManager manager = new InterviewManager()) {
            return created(manager.insert());
        }
        });
    }
    
    @POST
    @Path("/result_test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(ResultTest req) throws Exception {
        return this.authorize("standard", () -> {
        try (InterviewManager manager = new InterviewManager()) {
            return created(manager.insert());
        }
        });
    }
    
    @POST
    @Path("/result_criteria")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(ResultCriteria req) throws Exception {
        return this.authorize("standard", () -> {
        try (InterviewManager manager = new InterviewManager()) {
            return created(manager.insert());
        }
        });
    }
}
