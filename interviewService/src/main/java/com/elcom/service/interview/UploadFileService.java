/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.service.interview;

import com.elcom.business.manager.CommonManager;
import com.elcom.model.dto.DeleteFileDTO;
import com.elcom.model.dto.interview.ResponseData;
import com.elcom.service.BasedService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("/v1.0/file")
public class UploadFileService extends BasedService {

    public UploadFileService(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        super(headers, request);
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                return created(manager.uploadFile(uploadedInputStream, fileDetail));
            }
        });
    }
    
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFile(DeleteFileDTO file) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                return ok(manager.deleteFile(file));
            }
        });
    }
}
