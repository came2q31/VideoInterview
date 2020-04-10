package com.elcom.business.manager;

import java.io.IOException;

import javax.ws.rs.core.Response.Status;

import com.elcom.business.factory.InterviewFactory;
import com.elcom.common.Messages;
import com.elcom.data.interview.entity.Question;
import com.elcom.data.interview.entity.dto.QuestionDataPaging;
import com.elcom.data.interview.entity.dto.QuestionInsertDTO;
import com.elcom.model.dto.interview.ResponseData;
import com.elcom.model.dto.interview.ResponseDataPaging;
import com.elcom.sharedbiz.manager.BaseManager;
import com.elcom.sharedbiz.validation.ValidationException;
import com.elcom.util.StringUtils;

public class QuestionManager extends BaseManager {

    public QuestionManager() {
    }

    public ResponseDataPaging findAll(Long companyId, Long careerId, Long levelId,
            Integer questionType, Long careerIdPackage, int page, int rowsPerPage)
            throws Exception {
        if (companyId == null || companyId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "companyId"));
        }
        return this.tryCatch(() -> {
            QuestionDataPaging result = InterviewFactory.getQuestionAggregateInstance(uok.vi)
                    .findAll(companyId, careerId, levelId, questionType, careerIdPackage, page, rowsPerPage);
            return new ResponseDataPaging(
                    !result.getDataRows().isEmpty() ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    !result.getDataRows().isEmpty() ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result.getTotalRows(),
                    result.getDataRows()
            );
        });
    }

    public ResponseDataPaging findAllByQuestionIdLst(String questionIdLst, Long careerId, Integer type) throws Exception {
        return this.tryCatch(() -> {
            QuestionDataPaging result = InterviewFactory.getQuestionAggregateInstance(uok.vi)
                    .findAllByQuestionIdLst(questionIdLst, careerId, type);
            return new ResponseDataPaging(
                    !result.getDataRows().isEmpty() ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    !result.getDataRows().isEmpty() ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result.getTotalRows(),
                    result.getDataRows()
            );
        });
    }

    public ResponseData insertQuestion(Question item) throws Exception {
        InterviewFactory.getCommonValidation().validateInsertQuestion(item);
        return this.tryCatch(() -> {
            Long id = InterviewFactory.getCommonAggregateInstance(uok).insertQuestion(item);
            boolean status = (id != null && !id.equals(0L));
            return new ResponseData(
                    status ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    status ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    status ? id : 0
            );
        });
    }

    public ResponseData insertMultiQuestion(QuestionInsertDTO item) throws Exception {
        InterviewFactory.getCommonValidation().validateInsertMultiQuestion(item);
        return this.tryCatch(() -> {
            boolean status = InterviewFactory.getCommonAggregateInstance(uok).insertMultiQuestion(item);
            return new ResponseData(
                    status ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    status ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    status
            );
        });
    }

    public ResponseDataPaging findQuestionForUser(Long companyId, Long jobId, Long procedureId,
            int page, int rowsPerPage)
            throws Exception {
        if (companyId == null || companyId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "companyId"));
        }
        if (jobId == null || jobId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "jobId"));
        }
        if (procedureId == null || procedureId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "procedureId"));
        }
        return this.tryCatch(() -> {
            QuestionDataPaging result = InterviewFactory.getQuestionAggregateInstance(uok.vi)
                    .findQuestionForUser(companyId, jobId, procedureId, page, rowsPerPage);
            return new ResponseDataPaging(
                    !result.getDataRows().isEmpty() ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    !result.getDataRows().isEmpty() ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result.getTotalRows(),
                    result.getDataRows()
            );
        });
    }

    public ResponseData updateQuestion(Question item) throws Exception {
        InterviewFactory.getCommonValidation().validateUpdateQuestion(item);
        return this.tryCatch(() -> {
            boolean status = InterviewFactory.getCommonAggregateInstance(uok).updateQuestion(item);
            return new ResponseData(
                    status ? Status.OK.getStatusCode() : Status.NOT_MODIFIED.getStatusCode(),
                    status ? Status.OK.toString() : Status.NOT_MODIFIED.toString(),
                    status
            );
        });
    }

    public ResponseData deleteQuestion(String idList, Long companyId) throws Exception {
        if (StringUtils.isNullOrEmpty(idList)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "id_list"));
        }
        if (companyId == null) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "company_id"));
        }
        return this.tryCatch(() -> {
            boolean status = InterviewFactory.getCommonAggregateInstance(uok).deleteQuestion(idList, companyId);
            return new ResponseData(
                    status ? Status.OK.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    status ? Status.OK.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    status
            );
        });
    }

    public ResponseData listByJob(Long jobId) throws Exception {
        if (jobId == null || jobId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "jobId"));
        }
        return this.tryCatch(() -> {
            String result = InterviewFactory.getQuestionAggregateInstance(uok.vi).listByJob(jobId);
            return new ResponseData(
                    !StringUtils.isNullOrEmpty(result) ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    !StringUtils.isNullOrEmpty(result) ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result
            );
        });
    }

    @Override
    public void close() throws IOException {

        this.uok = null;
    }
}
