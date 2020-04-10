package com.elcom.service.interview;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.elcom.business.manager.CommonManager;
import com.elcom.business.manager.LetterManager;
import com.elcom.data.interview.entity.Career;
import com.elcom.data.interview.entity.Job;
import com.elcom.data.interview.entity.Letter;
import com.elcom.data.interview.entity.Procedures;
import com.elcom.data.interview.entity.dto.CheckSendLetterDTO;
import com.elcom.data.interview.entity.dto.ProceduresInsertDTO;
import com.elcom.data.interview.entity.dto.QuestionJobDTO;
import com.elcom.model.dto.ChangeFiledLetterDTO;
import com.elcom.model.dto.ChangeStatusLetter2DTO;
import com.elcom.model.dto.ChangeStatusLetterDTO;
import com.elcom.model.dto.RatingInsertDTO;
import com.elcom.model.dto.interview.ResponseData;
import com.elcom.model.dto.interview.ResponseDataPaging;
import com.elcom.service.BasedService;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PATCH;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

@Path("/v1.0/common")
public class CommonService extends BasedService {

    public CommonService(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        super(headers, request);
    }

    /**
     * Get list lĩnh vực ngành ngh�?
     *
     * @return ResponseData
     * @author anhdv
     */
    @GET
    @Path("/career")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllCareer() throws Exception {

        System.out.println("careercareercareercareercareercareercareercareercareer");

        try (CommonManager manager = new CommonManager()) {
            ResponseData res = manager.findAllCareer();
            if(res.getStatus() == Status.OK.getStatusCode() || res.getStatus() == Status.CREATED.getStatusCode()){
                return ok(res);
            } else {
                return noContent(res);
            }
        }
    }

    /**
     * Get list công việc
     *
     * @param companyId
     * @return ResponseData
     * @author anhdv
     */
    @GET
    @Path("/job")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllJob(@QueryParam("companyId") Long companyId, @DefaultValue("1") @QueryParam("type") Integer type) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                ResponseDataPaging res = manager.findAllJob(companyId, type);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    /**
     * Get list cấp bậc
     *
     * @param careerId
     * @return ResponseData
     * @author anhdv
     */
    @GET
    @Path("/level/{careerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllLevel(@PathParam("careerId") Long careerId) throws Exception {

        return this.authorize("standard", () -> {

            try (CommonManager manager = new CommonManager()) {
                ResponseData res = manager.findAllLevel(careerId);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    /**
     * Tạo rating
     *
     * @param RatingInsertDTO
     * @return ResponseData
     * @author anhdv
     */
    @POST
    @Path("/rating")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertRating(RatingInsertDTO req) throws Exception {

        return this.authorize("standard", () -> {

            try (CommonManager manager = new CommonManager()) {

                return created(manager.insertRating(req));
            }
        });
    }

    /**
     * Tạo công việc
     *
     * @param Job
     * @return ResponseData
     * @author anhdv
     */
    @POST
    @Path("/job")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertJob(Job req) throws Exception {

        return this.authorize("standard", () -> {

            try (CommonManager manager = new CommonManager()) {

                return created(manager.insertJob(req));
            }
        });
    }

    /**
     * Tạo công việc - câu h�?i
     *
     * @param QuestionJobDTO
     * @return ResponseData
     * @author anhdv
     */
    @POST
    @Path("/question-job")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertQuestionJob(QuestionJobDTO req) throws Exception {

        return this.authorize("standard", () -> {

            try (CommonManager manager = new CommonManager()) {

                return created(manager.insertQuestionJob(req));
            }
        });
    }

    /**
     * Tạo quy trình ph�?ng vấn
     *
     * @param Procedures
     * @return ResponseData
     * @author anhdv
     */
    @POST
    @Path("/procedures")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertProcedures(ProceduresInsertDTO req) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                ResponseData res = manager.insertProcedures(req);
                if (res.getStatus() == Status.OK.getStatusCode() || res.getStatus() == Status.CREATED.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    /**
     * Load procedure by jobid
     *
     * @param jobId
     * @return
     * @throws Exception
     */
    @GET
    @Path("/procedures/{jobId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findProceduresByJob(@PathParam("jobId") Long jobId) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                ResponseData res = manager.findProceduresByJob(jobId);
                if (res.getStatus() == Status.OK.getStatusCode() || res.getStatus() == Status.CREATED.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    /**
     * Kiem tra xem jobId da gui email cho ung vien email chua
     *
     * @param req
     * @return
     * @throws Exception
     */
    @POST
    @Path("/checkSendLetter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkSendLetter(CheckSendLetterDTO req) throws Exception {
        System.out.println("jobId: " + req.getJobId() + ", email: " + req.getEmail());
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                ResponseData res = manager.checkSendLetter(req.getJobId(), req.getEmail());
                if (res.getStatus() == Status.OK.getStatusCode() || res.getStatus() == Status.CREATED.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    /**
     * Them letter
     *
     * @param req
     * @return
     * @throws Exception
     */
    @POST
    @Path("/letter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertLetter(Letter req) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                return created(manager.insertLetter(req));
            }
        });
    }

    /**
     * Load letter for Nha tuyen dung
     *
     * @param status
     * @param jobId
     * @param applyName
     * @param interviewId
     * @param _group
     * @param procedureType
     * @param procedureName
     * @param type
     * @param companyId
     * @param startDate
     * @param endDate
     * @param page
     * @param rowsPerPage
     * @return
     * @throws Exception
     */
    @GET
    @Path("/letter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findLetter(@QueryParam("status") Integer status, @QueryParam("job_id") Long jobId,
            @QueryParam("apply_name") String applyName, @QueryParam("interview_id") Long interviewId,
            @QueryParam("_group") String _group, @QueryParam("procedure_type") Integer procedureType,
            @QueryParam("procedure_name") String procedureName, @QueryParam("type") Integer type,
            @QueryParam("company_id") Long companyId, @QueryParam("start_date") String startDate,
            @QueryParam("end_date") String endDate, @QueryParam("page") int page,
            @QueryParam("rows_per_page") int rowsPerPage) throws Exception {
        return this.authorize("standard", () -> {
            try (LetterManager manager = new LetterManager()) {
                ResponseDataPaging res = manager.findLetter(status, jobId, applyName,
                        interviewId, _group, procedureType, procedureName, type,
                        companyId, startDate, endDate, page, rowsPerPage);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    /**
     * Load letter for ung vien
     *
     * @param userId
     * @param status
     * @param jobId
     * @param interviewId
     * @param procedureType
     * @param companyId
     * @param doTest
     * @param type
     * @param startDate
     * @param endDate
     * @param page
     * @param rowsPerPage
     * @return
     * @throws Exception
     */
    @GET
    @Path("/letter-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findLetterForUser(@QueryParam("user_id") Long userId, @QueryParam("status") Integer status,
            @QueryParam("job_id") Long jobId, @QueryParam("interview_id") Long interviewId,
            @QueryParam("procedure_type") Integer procedureType, @QueryParam("company_id") Long companyId,
            @QueryParam("do_test") Integer doTest, @QueryParam("type") Integer type,
            @QueryParam("start_date") String startDate, @QueryParam("end_date") String endDate,
            @QueryParam("page") int page, @QueryParam("rows_per_page") int rowsPerPage) throws Exception {
        return this.authorize("standard", () -> {
            try (LetterManager manager = new LetterManager()) {
                ResponseDataPaging res = manager.findLetterForUser(userId, status,
                        jobId, interviewId, procedureType, companyId, doTest, type,
                        startDate, endDate, page, rowsPerPage);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    /**
     * Update Letter status, interview_view, userview_view... status
     *
     * @param req
     * @return
     * @throws Exception
     */
    @PATCH
    @Path("/letter-status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLetterStatus(ChangeStatusLetterDTO req) throws Exception {
        return this.authorize("standard", () -> {
            try (LetterManager manager = new LetterManager()) {
                ResponseData res = manager.updateLetterStatus(req);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return notModified(res);
                }
            }
        });
    }

    /**
     * Update letter user id khi user duoc moi thu dang ky tai khoan
     *
     * @param Letter (user_to, email)
     * @return ResponseData
     * @author anhdv
     */
    @PATCH
    @Path("/letter-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLetterUserId(Letter req) throws Exception {
//        return this.authorize("standard", () -> {
        try (LetterManager manager = new LetterManager()) {
            ResponseData res = manager.updateLetterUserId(req);
            if (res.getStatus() == Status.OK.getStatusCode()) {
                return ok(res);
            } else {
                return notModified(res);
            }
        }
//        });
    }

    /**
     * Load danh sach nguoi phong van ung vien
     *
     * @param userTo
     * @return
     * @throws Exception
     */
    @GET
    @Path("/letter-interview")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findInterviewForUser(@QueryParam("user_to") Long userTo) throws Exception {
        return this.authorize("standard", () -> {
            try (LetterManager manager = new LetterManager()) {
                ResponseData res = manager.findInterviewForUser(userTo);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    @GET
    @Path("/letter-job")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findJobForUser(@QueryParam("user_to") Long userTo) throws Exception {
        return this.authorize("standard", () -> {
            try (LetterManager manager = new LetterManager()) {
                ResponseData res = manager.findJobForUser(userTo);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    /**
     * Update Letter jobId, Comapany_Id, userId... other value
     *
     * @param req
     * @return
     * @throws Exception
     */
    @PATCH
    @Path("/letter-field")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLetterField(ChangeFiledLetterDTO req) throws Exception {
        return this.authorize("standard", () -> {
            try (LetterManager manager = new LetterManager()) {
                ResponseData res = manager.updateLetterField(req);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return notModified(res);
                }
            }
        });
    }

    @PATCH
    @Path("/letter-status2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLetterStatus2(ChangeStatusLetter2DTO item) throws Exception {
        return this.authorize("standard", () -> {
            try (LetterManager manager = new LetterManager()) {
                ResponseData res = manager.updateLetterStatus2(item.getCompanyId(), item.getJobId(), item.getUserTo(), item.getStatus());
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return notModified(res);
                }
            }
        });
    }

    /**
     * Chi tiet thong tin ung vien
     *
     * @param id
     * @return ResponseData chua ban ghi ung vien
     * @throws Exception
     */
    @GET
    @Path("/letter-detail")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetailLetter(@QueryParam("id") Long id) throws Exception {
        return this.authorize("standard", () -> {
            try (LetterManager manager = new LetterManager()) {
                ResponseData res = manager.getDetailLetter(id);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    /**
     * Update letter status by id, user_to, job_id, where_status
     *
     * @param item
     * @return ResponseData contains update result
     * @throws Exception
     */
    @PATCH
    @Path("/letter-status3")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLetterStatus3(Letter item) throws Exception {
        return this.authorize("standard", () -> {
            try (LetterManager manager = new LetterManager()) {
                ResponseData res = manager.updateLetterStatus3(item.getId(), item.getUserTo(),
                        item.getJobId(), item.getWhereStatus(), item.getStatus());
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return notModified(res);
                }
            }
        });
    }

    @PATCH
    @Path("/letter-procedure")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLetterProcedure(Letter item) throws Exception {
        return this.authorize("standard", () -> {
            try (LetterManager manager = new LetterManager()) {
                ResponseData res = manager.updateLetterProcedure(item);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return notModified(res);
                }
            }
        });
    }

    @POST
    @Path("/career")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCareer(Career req) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                return created(manager.insertCareer(req));
            }
        });
    }

    @PUT
    @Path("/career")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCareer(Career req) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                return created(manager.updateCareer(req));
            }
        });
    }

    @DELETE
    @Path("/career/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCareer(@PathParam("id") Long id) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                return created(manager.deleteCareer(id));
            }
        });
    }
    
    @POST
    @Path("/procedureOne")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertProcedure(Procedures req) throws Exception {
        return this.authorize("standard", () -> {
            try (CommonManager manager = new CommonManager()) {
                ResponseData res = manager.insertProcedures(req);
                if (res.getStatus() == Status.OK.getStatusCode() || res.getStatus() == Status.CREATED.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }
}
