package com.elcom.business.aggregate;

import com.elcom.data.interview.UnitOfWorkInterview;
import com.elcom.data.interview.entity.dto.QuestionDataPaging;

public class QuestionAggregate {

    private UnitOfWorkInterview _uokInterview = null;

    public QuestionAggregate(UnitOfWorkInterview _uokEp) {
        this._uokInterview = _uokEp;
    }

    public QuestionDataPaging findAll(Long companyId, Long careerId, Long levelId,
            Integer questionType, Long careerIdPackage, int page, int rowsPerPage) throws Exception {
        return this._uokInterview.questionRepository().findAll(companyId, careerId,
                levelId, questionType, careerIdPackage, page, rowsPerPage);
    }

    public QuestionDataPaging findAllByQuestionIdLst(String questionIdLst, Long careerId, Integer type) throws Exception {
        return this._uokInterview.questionRepository().findAllByQuestionIdLst2(questionIdLst, careerId, type);
    }
    
    public QuestionDataPaging findQuestionForUser(Long companyId, Long careerId, 
            Long procedureId, int page, int rowsPerPage) throws Exception {
        return this._uokInterview.questionRepository().findQuestionForUser(companyId, 
                careerId, procedureId, page, rowsPerPage);
    }
    
    public String listByJob(Long jobId) throws Exception {
        return this._uokInterview.questionRepository().listByJob(jobId);
    }
}
