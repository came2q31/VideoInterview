/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.business.manager;

import com.elcom.business.factory.InterviewFactory;
import com.elcom.common.Messages;
import com.elcom.data.interview.entity.Job;
import com.elcom.data.interview.entity.Letter;
import com.elcom.data.user.entity.User;
import com.elcom.model.dto.ChangeFiledLetterDTO;
import com.elcom.model.dto.ChangeStatusLetterDTO;
import com.elcom.model.dto.InterviewLetterDTO;
import com.elcom.model.dto.interview.InterviewLetterDataPaging;
import com.elcom.model.dto.interview.ResponseData;
import com.elcom.model.dto.interview.ResponseDataPaging;
import com.elcom.sharedbiz.factory.UserFactory;
import com.elcom.sharedbiz.manager.BaseManager;
import com.elcom.sharedbiz.validation.ValidationException;
import com.elcom.util.StringUtils;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class LetterManager extends BaseManager {

    private static final Logger logger = Logger.getLogger(CommonManager.class.getName());

    public LetterManager() {

    }

    public ResponseDataPaging findLetter(Integer status, Long jobId, String applyName, Long interviewId,
            String _group, Integer procedureType, String procedureName, Integer type, Long companyId,
            String startDate, String endDate, int page, int rowsPerPage) throws Exception {
        return this.tryCatch(() -> {
            InterviewLetterDataPaging result = UserFactory.getCommonAggregate(uok).findLetter(status,
                    jobId, applyName, interviewId, _group, procedureType, procedureName, type, companyId, 
                    startDate, endDate, page, rowsPerPage);
            return new ResponseDataPaging(
                    !result.getDataRows().isEmpty() ? Response.Status.OK.getStatusCode() : Response.Status.NO_CONTENT.getStatusCode(),
                    !result.getDataRows().isEmpty() ? Response.Status.OK.toString() : Response.Status.NO_CONTENT.toString(),
                    result.getTotalRows(),
                    result.getDataRows()
            );
        });
    }
    
    public ResponseDataPaging findLetterForUser(Long userId, Integer status, Long jobId, 
            Long interviewId,Integer procedureType, Long companyId, Integer doTest, Integer type,
            String startDate, String endDate, int page, int rowsPerPage) throws Exception {
        if (userId == null || userId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "user_id"));
        }
        return this.tryCatch(() -> {
            InterviewLetterDataPaging result = UserFactory.getCommonAggregate(uok).findLetterForUser(
                    userId, status, jobId, interviewId, procedureType, companyId, 
                    doTest, type, startDate, endDate, page, rowsPerPage);
            return new ResponseDataPaging(
                    !result.getDataRows().isEmpty() ? Response.Status.OK.getStatusCode() : Response.Status.NO_CONTENT.getStatusCode(),
                    !result.getDataRows().isEmpty() ? Response.Status.OK.toString() : Response.Status.NO_CONTENT.toString(),
                    result.getTotalRows(),
                    result.getDataRows()
            );
        });
    }
    
    public ResponseData updateLetterStatus(ChangeStatusLetterDTO item) throws Exception {
        InterviewFactory.getCommonValidation().validateUpdateLetterStatus(item);
        return this.tryCatch(() -> {
            boolean status = UserFactory.getCommonAggregate(uok).updateLetterStatus(item);
            return new ResponseData(
                    status ? Response.Status.OK.getStatusCode() : Response.Status.NOT_MODIFIED.getStatusCode(),
                    status ? Response.Status.OK.toString() : Response.Status.NOT_MODIFIED.toString(),
                    status
            );
        });
    }
    
    public ResponseData updateLetterUserId(Letter item) throws Exception {
        if (item == null) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "input"));
        }
        if (!StringUtils.validateEmail(item.getEmail())) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "email"));
        }
        if(item.getUserTo() == null){
            throw new ValidationException(Messages.getString("validation.field.madatory", "user_to"));
        } else if(item.getUserTo() != null && !StringUtils.isNumberic(item.getUserTo().toString())){
            throw new ValidationException(Messages.getString("validation.field.invalidFormat", "user_to"));
        }
        return this.tryCatch(() -> {
            boolean status = UserFactory.getCommonAggregate(uok).updateLetterUserId(item);
            return new ResponseData(
                    status ? Response.Status.OK.getStatusCode() : Response.Status.NOT_MODIFIED.getStatusCode(),
                    status ? Response.Status.OK.toString() : Response.Status.NOT_MODIFIED.toString(),
                    null
            );
        });
    }
    
    public ResponseData findInterviewForUser(Long userTo) throws Exception {
        if (userTo == null || userTo.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "user_to"));
        }
        return this.tryCatch(() -> {
            List<User> list = UserFactory.getCommonAggregate(uok).findInterviewForUser(userTo);
            return new ResponseData(
                    list != null && list.size() > 0 ? Response.Status.OK.getStatusCode() : Response.Status.NO_CONTENT.getStatusCode(),
                    list != null && list.size() > 0 ? Response.Status.OK.toString() : Response.Status.NO_CONTENT.toString(),
                    list
            );
        });
    }
    
    public ResponseData findJobForUser(Long userTo) throws Exception {
        if (userTo == null || userTo.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "user_to"));
        }
        return this.tryCatch(() -> {
            List<Job> list = UserFactory.getCommonAggregate(uok).findJobForUser(userTo);
            return new ResponseData(
                    list != null && list.size() > 0 ? Response.Status.OK.getStatusCode() : Response.Status.NO_CONTENT.getStatusCode(),
                    list != null && list.size() > 0 ? Response.Status.OK.toString() : Response.Status.NO_CONTENT.toString(),
                    list
            );
        });
    }
    
    public ResponseData updateLetterField(ChangeFiledLetterDTO item) throws Exception {
        InterviewFactory.getCommonValidation().validateUpdateLetterField(item);
        return this.tryCatch(() -> {
            boolean status = UserFactory.getCommonAggregate(uok).updateLetterField(item);
            return new ResponseData(
                    status ? Response.Status.OK.getStatusCode() : Response.Status.NOT_MODIFIED.getStatusCode(),
                    status ? Response.Status.OK.toString() : Response.Status.NOT_MODIFIED.toString(),
                    status
            );
        });
    }
    
    public ResponseData updateLetterStatus2(Long comapanyId, Long jobId, Long userTo, Integer status) throws Exception {
        if (comapanyId == null || comapanyId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "company_id"));
        }
        if (jobId == null || jobId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "job_id"));
        }
        if (userTo == null || userTo.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "user_to"));
        }
        if (status == null) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "status"));
        }
        return this.tryCatch(() -> {
            boolean update = UserFactory.getCommonAggregate(uok).updateLetterStatus2(comapanyId, jobId, userTo, status);
            return new ResponseData(
                    update ? Response.Status.OK.getStatusCode() : Response.Status.NOT_MODIFIED.getStatusCode(),
                    update ? Response.Status.OK.toString() : Response.Status.NOT_MODIFIED.toString(),
                    update
            );
        });
    }
    
    public ResponseData getDetailLetter(Long id) throws Exception {
        if (id == null || id.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "id"));
        }
        return this.tryCatch(() -> {
            InterviewLetterDTO detail = UserFactory.getCommonAggregate(uok).getDetailLetter(id);
            return new ResponseData(
                    detail != null ? Response.Status.OK.getStatusCode() : Response.Status.NO_CONTENT.getStatusCode(),
                    detail != null ? Response.Status.OK.toString() : Response.Status.NO_CONTENT.toString(),
                    detail
            );
        });
    }
    
    public ResponseData updateLetterStatus3(Long id, Long userTo, Long jobId, Integer whereStatus,
            int status) throws Exception {
        if (id == null || id.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "id"));
        }
        if (jobId == null || jobId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "jobId"));
        }
        if (userTo == null || userTo.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "userTo"));
        }
        return this.tryCatch(() -> {
            boolean update = UserFactory.getCommonAggregate(uok).updateLetterStatus3(id, userTo, jobId, whereStatus, status);
            return new ResponseData(
                    update ? Response.Status.OK.getStatusCode() : Response.Status.NOT_MODIFIED.getStatusCode(),
                    update ? Response.Status.OK.toString() : Response.Status.NOT_MODIFIED.toString(),
                    update
            );
        });
    }
    
    public ResponseData updateLetterProcedure(Letter item) throws Exception {
        if (item == null) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "input"));
        }
        if (item.getId() == null || item.getId().equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "id"));
        }
        if (item.getProcedureId() == null || item.getProcedureId().equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "procedureId"));
        }
        
        return this.tryCatch(() -> {
            boolean update = UserFactory.getCommonAggregate(uok).updateLetterProcedure(item);
            return new ResponseData(
                    update ? Response.Status.OK.getStatusCode() : Response.Status.NOT_MODIFIED.getStatusCode(),
                    update ? Response.Status.OK.toString() : Response.Status.NOT_MODIFIED.toString(),
                    update
            );
        });
    }
}
