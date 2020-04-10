package com.elcom.service.interview;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.elcom.business.manager.UserManager;
import com.elcom.model.dto.ChangePasswordReq;
import com.elcom.model.dto.InterviewUserDTO;
import com.elcom.model.dto.interview.ResponseData;
import com.elcom.model.dto.interview.ResponseDataPaging;
import com.elcom.service.BasedService;

@Path("/v1.0/user")
public class UserService extends BasedService {

    public UserService(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        super(headers, request);
    }

    /**
     * Create user
     *
     * @param InterviewUserDTO
     * @return ResponseData
     * @author anhdv
     */
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(InterviewUserDTO req) throws Exception {

        //return this.authorize("standard", () -> {
        try (UserManager manager = new UserManager()) {

            return created(manager.insert(req));
        }
        //});
    }

    /**
     * Update user
     *
     * @param InterviewUserDTO
     * @return ResponseData
     * @author anhdv
     */
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(InterviewUserDTO req) throws Exception {

        return this.authorize("standard", () -> {

            try (UserManager manager = new UserManager()) {

                ResponseData res = manager.update(req);

                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return notModified(res);
                }
            }
        });
    }

    /**
     * Delete user
     *
     * @param uuid
     * @return ResponseData
     * @author anhdv
     *//*
	@DELETE
	@Path("/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("uuid") String uuid) throws Exception {
		
		return this.authorize("standard", () -> {
			
			try (UserManager manager = new UserManager()) {
				
				ResponseData res = manager.delete(uuid);
				
				if( res.getStatus() == Status.OK.getStatusCode() )
					return ok(res);
				else
					return badRequest(res);
			}
		});
	}*/

    /**
     * Find all employees
     *
     * @param department_id, full_name, email, page, rows_per_page, status,
     * is_deleted
     * @return ResponseData
     * @author anhdv
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@QueryParam("department_id") Long departmentId, @QueryParam("full_name") String fullName,
            @QueryParam("email") String email, @QueryParam("page") int page, @QueryParam("rows_per_page") int rowsPerPage,
            @QueryParam("status") Integer status, @QueryParam("is_deleted") Integer isDeleted) throws Exception {

        return this.authorize("standard", () -> {

            try (UserManager manager = new UserManager()) {

                ResponseDataPaging res = manager.findAll(departmentId, fullName, email, page, rowsPerPage, status, isDeleted);

                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    /**
     * Find details user
     *
     * @param uuid
     * @return ResponseData
     * @author anhdv
     */
    @GET
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findDetails(@PathParam("uuid") String uuid) throws Exception {

        return this.authorize("standard", () -> {

            try (UserManager manager = new UserManager()) {

                ResponseData res = manager.findDetails(uuid);

                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    /**
     * Find all user by companyId
     *
     * @param companyId
     * @return ResponseData
     * @author anhdv
     */
    @GET
    @Path("/company/{companyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllUserByCompanyId(@PathParam("companyId") Long companyId) throws Exception {

        return this.authorize("standard", () -> {

            try (UserManager manager = new UserManager()) {

                ResponseDataPaging res = manager.findAllUserByCompanyId(companyId);

                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    /**
     * Find all employees by manager_id
     *
     * @param manager_id
     * @return ResponseData
     * @author anhdv
     */
    @GET
    @Path("/by-manager")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByManagerId(@QueryParam("manager_id") Long managerId) throws Exception {

        return this.authorize("standard", () -> {

            try (UserManager manager = new UserManager()) {

                ResponseData res = manager.findByManagerId(managerId);

                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    /**
     * Find list email
     *
     * @return ResponseData
     * @author anhdv
     */
    @GET
    @Path("/email-list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findEmailLst() throws Exception {

        return this.authorize("standard", () -> {

            try (UserManager manager = new UserManager()) {

                ResponseData res = manager.findEmailLst();

                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    /**
     * User change password
     *
     * @param ChangePasswordReq
     * @return ResponseData
     * @author anhdv
     */
    @PATCH
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(ChangePasswordReq req) throws Exception {

        return this.authorize("standard", () -> {

            try (UserManager manager = new UserManager()) {

                ResponseData res = manager.changePassword(req);

                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return badRequest(res);
                }
            }
        });
    }

    /**
     * Admin reset password user (123456 default)
     *
     * @param uuid
     * @return ResponseData
     * @author anhdv
     */
    @PATCH
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resetPassword(@PathParam("uuid") String uuid) throws Exception {

        return this.authorize("standard", () -> {

            try (UserManager manager = new UserManager()) {

                ResponseData res = manager.resetPassword(uuid, "123456");

                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return badRequest(res);
                }
            }
        });
    }

    /**
     * Check email/mobile exists
     * table default : uninon 2 bảng user và company table : 1 - chỉ lấy ở user table : 2 - chỉ lấy ở company
     * @param checkType : email or mobile
     * @param info
     * @param email
     * @return ResponseData
     * @author anhdv
     */
    @GET
    @Path("/check-info")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkEmailExists(@QueryParam("type") String checkType, @QueryParam("info") String info,
            @QueryParam("table") Integer table) throws Exception {
        try (UserManager manager = new UserManager()) {
            return ok(manager.checkMainInfoExists(checkType, info, table));
        }
    }
    
    @GET
    @Path("/detail")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findUserDetails(@QueryParam("id") Long id, @QueryParam("company_id") Long companyId) throws Exception {
        return this.authorize("standard", () -> {
            try (UserManager manager = new UserManager()) {
                ResponseData res = manager.findUserDetails(id, companyId);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }
}
