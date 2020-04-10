package com.elcom.sharedbiz.aggregate;

import java.util.List;

import com.elcom.data.UnitOfWork;
import com.elcom.data.interview.entity.Answer;
import com.elcom.data.interview.entity.Career;
import com.elcom.data.interview.entity.Job;
import com.elcom.data.interview.entity.Letter;
import com.elcom.data.interview.entity.Level;
import com.elcom.data.interview.entity.Procedures;
import com.elcom.data.interview.entity.Question;
import com.elcom.data.interview.entity.Rating;
import com.elcom.data.interview.entity.ResultCriteria;
import com.elcom.data.interview.entity.ResultTest;
import com.elcom.data.interview.entity.dto.AnswerInsertDTO;
import com.elcom.data.interview.entity.dto.JobDataPaging;
import com.elcom.data.interview.entity.dto.ProceduresInsertDTO;
import com.elcom.data.interview.entity.dto.QuestionInsertDTO;
import com.elcom.data.interview.entity.dto.QuestionJobDTO;
import com.elcom.data.user.entity.User;
import com.elcom.model.dto.ChangeFiledLetterDTO;
import com.elcom.model.dto.ChangeStatusLetterDTO;
import com.elcom.model.dto.InterviewLetterDTO;
import com.elcom.model.dto.RatingInsertDTO;
import com.elcom.model.dto.interview.InterviewLetterDataPaging;

public class CommonAggregate {

    private UnitOfWork _uok = null;

    private static List<Career> careerLst = null;

    public CommonAggregate(UnitOfWork uok) {
        this._uok = uok;
    }

    public boolean insertRating(RatingInsertDTO item) {

        return this._uok.vi.commonRepository().insertRating(item);
    }

    public void insertJob(Job item) {

        this._uok.vi.jobRepository().upsert(item);
    }

    public boolean insertQuestionJob(QuestionJobDTO item) {

        return this._uok.vi.jobRepository().insertQuestionJob(item);
    }

    public boolean insertProcedures(ProceduresInsertDTO item) {

        return this._uok.vi.proceduresRepository().upsert(item);
    }
    
    public Long insertProcedures(Procedures item) {

        return this._uok.vi.proceduresRepository().insertReturnId(item);
    }

    public List<Career> findAllCareer() {

        if (careerLst != null) {
            return careerLst;
        }

        careerLst = this._uok.vi.commonRepository().findAllCareer();

        return careerLst;
    }

    public List<Level> findAllLevel(Long careerId) {

        return this._uok.vi.commonRepository().findAllLevel(careerId);
    }

    public List<Procedures> findProceduresByJob(Long jobId) {

        return this._uok.vi.commonRepository().findProceduresByJob(jobId);
    }

    public JobDataPaging findAllJob(Long companyId, Integer type) throws Exception {
        return this._uok.vi.jobRepository().findAll(companyId, type);
    }

    public boolean checkSendLetter(Long jobId, String email) {
        return this._uok.vi.commonRepository().checkSendLetter(jobId, email);
    }

    public void insertLetter(Letter item) {
        this._uok.vi.letterRepository().upsert(item);
    }

    public Long insertQuestion(Question item) {
        return this._uok.vi.questionRepository().insertReturnId(item);
    }

    public boolean insertMultiQuestion(QuestionInsertDTO item) {
        return this._uok.vi.questionRepository().insertMultiQuestion(item);
    }

    public InterviewLetterDataPaging findLetter(Integer status, Long jobId,
            String applyName, Long interviewId, String _group, Integer procedureType,
            String procedureName, Integer type, Long companyId, String startDate, 
            String endDate, int page, int rowsPerPage) throws Exception {
        return this._uok.vi.letterRepository().findLetter(status, jobId, applyName, interviewId, 
                _group, procedureType, procedureName, type, companyId, startDate, endDate, page, rowsPerPage);
    }

    public InterviewLetterDataPaging findLetterForUser(Long userId, Integer status,
            Long jobId, Long interviewId, Integer procedureType, Long companyId,
            Integer doTest, Integer type, String startDate, String endDate, int page, 
            int rowsPerPage) throws Exception {
        return this._uok.vi.letterRepository().findLetterForUser(userId, status,
                jobId, interviewId, procedureType, companyId, doTest, type, startDate,
                endDate, page, rowsPerPage);
    }
    
    public boolean updateLetterStatus(ChangeStatusLetterDTO item) {
        return this._uok.vi.letterRepository().updateLetterStatus(item);
    }
    
    public boolean insertAnswer(AnswerInsertDTO item) {
        return this._uok.vi.commonRepository().insertAnswer(item);
    }
    
    public boolean updateQuestion(Question item) {
        return this._uok.vi.questionRepository().updateQuestion(item);
    }
    
    public boolean deleteQuestion(String idList, Long companyId) {
        return this._uok.vi.questionRepository().deleteQuestion(idList, companyId);
    }
    
    public boolean updateAnswer(Answer item) {
        return this._uok.vi.commonRepository().updateAnswer(item);
    }
    
    public boolean deleteAnswer(String idList) {
        return this._uok.vi.commonRepository().deleteAnswer(idList);
    }
    
    public boolean updateLetterUserId(Letter item) {
        return this._uok.vi.letterRepository().updateLetterUserId(item);
    }
    
    public List<User> findInterviewForUser(Long userTo) {
        return this._uok.vi.letterRepository().findInterviewForUser(userTo);
    }
    
    public List<Job> findJobForUser(Long userTo) {
        return this._uok.vi.letterRepository().findJobForUser(userTo);
    }
    
    public Long insertRating(Rating item) {
        return this._uok.vi.ratingRepository().insertReturnId(item);
    }
    
    public JobDataPaging findAllForCampany(Long companyId, Integer type) throws Exception {
        return this._uok.vi.jobRepository().findAll(companyId, type);
    }
    
    public List<Rating> findAllForCampany(Long companyId) throws Exception {
        return this._uok.vi.ratingRepository().findAllForCampany(companyId);
    }
    
    public Long insertResultCriteriaReturnId(ResultCriteria item) {
        return this._uok.vi.resultCriteriaRepository().insertResultCriteriaReturnId(item);
    }
    
    public List<ResultCriteria> findAllResultCriteria(Long companyId, Long jobId, String userId) throws Exception {
        return this._uok.vi.resultCriteriaRepository().findAllResultCriteria(companyId, jobId, userId);
    }
    
    public boolean updateLetterField(ChangeFiledLetterDTO item) {
        return this._uok.vi.letterRepository().updateLetterField(item);
    }
    
    public boolean updateLetterStatus2(Long comapanyId, Long jobId, Long userTo, Integer status) {
        return this._uok.vi.letterRepository().updateLetterStatus2(comapanyId, jobId, userTo, status);
    }
    
    public InterviewLetterDTO getDetailLetter(Long id) {
        return this._uok.vi.letterRepository().getDetailLetter(id);
    }
    
    public boolean updateLetterStatus3(Long id, Long userTo, Long jobId, Integer whereStatus, int status) {
        return this._uok.vi.letterRepository().updateLetterStatus3(id, userTo, jobId, whereStatus, status);
    }
    
    public Long insertResultTestReturnId(ResultTest item) {
        return this._uok.vi.resultTestRepository().insertResultTestReturnId(item);
    }
    
    public boolean updateLetterProcedure(Letter item) {
        return this._uok.vi.letterRepository().updateLetterProcedure(item);
    }
    
    public boolean insertCareer(Career item) {
        return this._uok.vi.commonRepository().insertCareer(item);
    }
    
    public boolean updateCareer(Career item) {
        return this._uok.vi.commonRepository().updateCareer(item);
    }
    
    public boolean deleteCareer(Long id) {
        return this._uok.vi.commonRepository().deleteCareer(id);
    }
}
