/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.service.interview;

import com.elcom.business.manager.CommonManager;
import com.elcom.data.interview.entity.ResultCriteria;
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
@Path("/v1.0/result_criteria")
public class ResultCriteriaService extends BasedService {

    public ResultCriteriaService(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        super(headers, request);
    }
    
    /**
     * Insert to table result_criteria a record
     * @param req
     * @return ResponseData contains record id
     * @throws Exception 
     */
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertResultCriteriaReturnId(ResultCriteria req) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                return created(manager.insertResultCriteriaReturnId(req));
            }
        });
    }
    
    /**
     * Load ResultCriteria
     * @param companyId
     * @param jobId
     * @param userId
     * @return
     * @throws Exception 
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllResultCriteria(@QueryParam("companyId") Long companyId, 
            @QueryParam("jobId") Long jobId, @QueryParam("userId") String userId) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                ResponseData res = manager.findAllResultCriteria(companyId, jobId, userId);
                if (res.getStatus() == Response.Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }
}
