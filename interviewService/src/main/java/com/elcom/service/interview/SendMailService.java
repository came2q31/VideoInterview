/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.service.interview;

import com.elcom.business.manager.CommonManager;
import com.elcom.model.dto.MailContentDTO;
import com.elcom.service.BasedService;
import com.google.gson.Gson;
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
@Path("/v1.0/mail")
public class SendMailService extends BasedService {

    public SendMailService(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        super(headers, request);
    }

    @POST
    @Path("/sendMail")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertRating(MailContentDTO req) throws Exception {
        System.out.println("json in service: " + new Gson().toJson(req));
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                return created(manager.sendMail(req));
            }
        });
    }
}
