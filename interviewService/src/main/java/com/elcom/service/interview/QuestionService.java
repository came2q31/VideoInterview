package com.elcom.service.interview;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.elcom.business.manager.QuestionManager;
import com.elcom.data.interview.entity.Question;
import com.elcom.data.interview.entity.dto.QuestionInsertDTO;
import com.elcom.model.dto.interview.ResponseData;
import com.elcom.model.dto.interview.ResponseDataPaging;
import com.elcom.service.BasedService;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

@Path("/v1.0/question")
public class QuestionService extends BasedService {

    public QuestionService(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        super(headers, request);
    }

    /**
     * Find all question
     *
     * @param careerId, levelId, questionType, page, rows_per_page
     * @return ResponseData
     * @author anhdv
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@QueryParam("companyId") Long companyId, @QueryParam("careerId") Long careerId, 
            @QueryParam("levelId") Long levelId, @QueryParam("questionType") Integer questionType, 
            @QueryParam("careerIdPackage") Long careerIdPackage, @QueryParam("page") int page, 
            @QueryParam("rows_per_page") int rowsPerPage) throws Exception {
        return this.authorize("standard", () -> {
            try (QuestionManager manager = new QuestionManager()) {
                ResponseDataPaging res = manager.findAll(companyId, careerId, levelId, 
                        questionType, careerIdPackage, page, rowsPerPage);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }

    /**
     * Find all question by id list
     *
     * @param careerId, levelId, questionType, page, rows_per_page
     * @return ResponseData
     * @author anhdv
     */
    @GET
    @Path("/byIdLst")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllByQuestionIdLst(@QueryParam("questionIdLst") String questionIdLst, 
            @QueryParam("careerId") Long careerId, @QueryParam("type") Integer type) throws Exception {
        return this.authorize("standard", () -> {
            try (QuestionManager manager = new QuestionManager()) {
                ResponseDataPaging res = manager.findAllByQuestionIdLst(questionIdLst, careerId, type);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }
    
    /**
     * Thêm câu hoi tra ve id cau hoi
     * @param req
     * @return ResponseData chua id cau hoi
     * @throws Exception 
     */
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertQuestion(Question req) throws Exception {
        return this.authorize("standard", () -> {
            try (QuestionManager manager = new QuestionManager()) {
                return created(manager.insertQuestion(req));
            }
        });
    }
    
    /**
     * Them cau hoi them mang
     * @param req
     * @return ResponseData chua ket qua boolean
     * @throws Exception 
     */
    @POST
    @Path("/multi")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertMultiQuestion(QuestionInsertDTO req) throws Exception {
        return this.authorize("standard", () -> {
            try (QuestionManager manager = new QuestionManager()) {
                return created(manager.insertMultiQuestion(req));
            }
        });
    }
    
    /**
     * List cau hoi phan trang
     * @param companyId
     * @param jobId
     * @param procedureId
     * @param page
     * @param rowsPerPage
     * @return Response chua list cau hoi phan trang
     * @throws Exception 
     */
    @GET
    @Path("/forUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findQuestionForUser(@QueryParam("company_id") Long companyId, 
            @QueryParam("job_id") Long jobId, @QueryParam("procedure_id") Long procedureId, 
            @QueryParam("page") int page, @QueryParam("rows_per_page") int rowsPerPage) throws Exception {
        return this.authorize("standard", () -> {
            try (QuestionManager manager = new QuestionManager()) {
                ResponseDataPaging res = manager.findQuestionForUser(companyId, jobId, 
                        procedureId, page, rowsPerPage);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }
    
    /**
     * Update cau hoi
     * @param req
     * @return
     * @throws Exception 
     */
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateQuestion(Question req) throws Exception {
        return this.authorize("standard", () -> {
            try (QuestionManager manager = new QuestionManager()) {
                return created(manager.updateQuestion(req));
            }
        });
    }
    
    /**
     * Xoa cau hoi
     * @param idList
     * @param companyId
     * @return
     * @throws Exception 
     */
    @DELETE
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteQuestion(@QueryParam("id_list") String idList, @QueryParam("company_id") Long companyId) throws Exception {
        return this.authorize("standard", () -> {
            try (QuestionManager manager = new QuestionManager()) {
                return created(manager.deleteQuestion(idList, companyId));
            }
        });
    }
    
    @GET
    @Path("/lstByJob")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listByJob(@QueryParam("jobId") Long jobId) throws Exception {
        return this.authorize("standard", () -> {
            try (QuestionManager manager = new QuestionManager()) {
                ResponseData res = manager.listByJob(jobId);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }
}
