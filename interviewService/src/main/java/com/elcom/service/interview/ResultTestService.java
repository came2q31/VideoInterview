/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.service.interview;

import com.elcom.business.manager.CommonManager;
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
 * @author Administrator
 */
@Path("/v1.0/result_test")
public class ResultTestService extends BasedService {

    public ResultTestService(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        super(headers, request);
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertResultCriteriaReturnId(ResultTest req) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                return created(manager.insertResultTestReturnId(req));
            }
        });
    }
}
