/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.service.interview;

import com.elcom.business.manager.CommonManager;
import com.elcom.data.interview.entity.Answer;
import com.elcom.data.interview.entity.dto.AnswerInsertDTO;
import com.elcom.service.BasedService;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Path("/v1.0/answer")
public class AnswerService extends BasedService {

    public AnswerService(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        super(headers, request);
    }
    
    /**
     * Tạo câu hoi
     *
     * @param AnswerInsertDTO
     * @return ResponseData
     * @author anhdv
     */
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertAnswer(AnswerInsertDTO req) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                return created(manager.insertAnswer(req));
            }
        });
    }
    
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAnswer(Answer req) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                return created(manager.updateAnswer(req));
            }
        });
    }
    
    @DELETE
    @Path("/{idList}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAnswer(@PathParam("idList") String idList) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                return created(manager.deleteAnswer(idList));
            }
        });
    }
}
