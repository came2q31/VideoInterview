package com.elcom.business.validation;

import com.elcom.common.Messages;
import com.elcom.data.interview.entity.Answer;
import com.elcom.data.interview.entity.Career;
import com.elcom.data.interview.entity.Job;
import com.elcom.data.interview.entity.Letter;
import com.elcom.data.interview.entity.Procedures;
import com.elcom.data.interview.entity.Question;
import com.elcom.data.interview.entity.QuestionJob;
import com.elcom.data.interview.entity.Rating;
import com.elcom.data.interview.entity.ResultCriteria;
import com.elcom.data.interview.entity.ResultTest;
import com.elcom.data.interview.entity.dto.AnswerInsertDTO;
import com.elcom.data.interview.entity.dto.ProceduresInsertDTO;
import com.elcom.data.interview.entity.dto.QuestionInsertDTO;
import com.elcom.data.interview.entity.dto.QuestionJobDTO;
import com.elcom.model.dto.ChangeFiledLetterDTO;
import com.elcom.model.dto.ChangeStatusLetterDTO;
import com.elcom.model.dto.MailContentDTO;
import com.elcom.model.dto.RatingInsertDTO;
import com.elcom.sharedbiz.validation.AbstractValidation;
import com.elcom.sharedbiz.validation.ValidationException;
import com.elcom.util.StringUtils;
import com.sun.jersey.core.header.FormDataContentDisposition;
import java.io.InputStream;
import java.util.Date;

public class CommonValidation extends AbstractValidation {

    public void validateInsertRating(RatingInsertDTO item) throws ValidationException {

        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }

        if (item.getJobId() == null || item.getJobId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "jobId"));
        }

        if (item.getUserId() == null || item.getUserId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "userId"));
        }

        if (StringUtils.isNullOrEmpty(item.getNote())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "note"));
        }

        if (StringUtils.isNullOrEmpty(item.getRating())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "rating"));
        } else if (item.getRating().length() > 45) {
            getMessageDes().add(Messages.getString("validation.field.length", "rating", 45));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateInsertJob(Job item) throws ValidationException {

        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }

        if (item.getCompanyId() == null || item.getCompanyId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "companyId"));
        }

        /*if ( item.getCareerId() == null || item.getCareerId().equals(0L) )
			getMessageDes().add(Messages.getString("validation.field.madatory", "careerId"));*/
        if (StringUtils.isNullOrEmpty(item.getName())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "name"));
        }

        if (StringUtils.isNullOrEmpty(item.getGroup())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "group"));
        }

        if (StringUtils.isNullOrEmpty(item.getOffer())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "offer"));
        }

        if (StringUtils.isNullOrEmpty(item.getType())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "type"));
        }

        if (item.getNumber() == null || item.getNumber() == 0) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "number"));
        }

        if (StringUtils.isNullOrEmpty(item.getContent())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "content"));
        }

        if (item.getEndDate() == null) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "endDate"));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateInsertQuestionJob(QuestionJobDTO item) throws ValidationException {

        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }

        for (QuestionJob obj : item.getData()) {

            if (obj.getJobId() == null || obj.getJobId().equals(0L)) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "jobId"));
            }

            if (obj.getProcedureId() == null || obj.getProcedureId().equals(0L)) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "procedureId"));
            }

            if (obj.getQuestionId() == null || obj.getQuestionId().equals(0L)) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "questionId"));
            }

            if (obj.getPoint() == null || obj.getPoint() == 0) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "point"));
            }
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateInsertProcedures(ProceduresInsertDTO item) throws ValidationException {

        if (item == null || item.getData().isEmpty()) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }

        for (Procedures obj : item.getData()) {

            if (obj.getUserInterview() == null || obj.getUserInterview().equals(0L)) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "userInterview"));
            }

            if (obj.getJobId() == null || obj.getJobId().equals(0L)) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "jobId"));
            }

//            if (obj.getStt() == null || obj.getStt() == 0) {
//                getMessageDes().add(Messages.getString("validation.field.madatory", "stt"));
//            }
//            
//            if (obj.getTotal() == null || obj.getTotal() == 0) {
//                getMessageDes().add(Messages.getString("validation.field.madatory", "total"));
//            }
//            if (obj.getTimeTest() == null || obj.getTimeTest().equals(0L)) {
//                getMessageDes().add(Messages.getString("validation.field.madatory", "timeTest"));
//            }

            if (StringUtils.isNullOrEmpty(obj.getName())) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "name"));
            }

            if (obj.getType() == null || obj.getType() == 0) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "type"));
            }
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateSendEmail(MailContentDTO item) throws ValidationException {

        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }

        if (StringUtils.isNullOrEmpty(item.getEmailTo())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "emailTo"));
        }

        if (StringUtils.isNullOrEmpty(item.getEmailTitle())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "emailTitle"));
        }

        if (StringUtils.isNullOrEmpty(item.getEmailContent())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "emailContent"));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateUploadFile(InputStream uploadedInputStream, FormDataContentDisposition fileDetail) throws ValidationException {

        if (uploadedInputStream == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "uploadedInputStream"));
            throw new ValidationException(this.buildValidationMessage());
        }

        if (fileDetail == null || StringUtils.isNullOrEmpty(fileDetail.getName())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "fileDetail"));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateInsertLetter(Letter item) throws ValidationException {
        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }
        if (item.getJobId() == null || item.getJobId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "jobId"));
        }
        if (item.getAppointmentTimeTs() == null || item.getAppointmentTimeTs().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "appointmentTimeTs"));
        } else if (new Date(item.getAppointmentTimeTs() * 1000L).getTime() < System.currentTimeMillis()) {
            getMessageDes().add(Messages.getString("validation.field.date", "appointmentTimeTs"));
        }

        if (!StringUtils.validateEmail(item.getEmail())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "email"));
        }
        //if (StringUtils.isNullOrEmpty(item.getLinkCv())) {
        //    getMessageDes().add(Messages.getString("validation.field.madatory", "linkCv"));
        //}

        if (item.getUserTo() == null) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "userTo"));
        }
        if (item.getProcedureId() == null || item.getProcedureId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "procedureId"));
        }
        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateInsertQuestion(Question item) throws ValidationException {
        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }
        if (item.getCareerId() == null || item.getCareerId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "careerId"));
        }
        if (item.getCompanyId() == null || item.getCompanyId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "companyId"));
        }
        if (StringUtils.isNullOrEmpty(item.getQuestion())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "question"));
        }
        if (item.getLevelId() == null || item.getLevelId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "levelId"));
        }
        if (item.getQuestionType() == null || item.getQuestionType() == 0) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "questionType"));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateInsertMultiQuestion(QuestionInsertDTO item) throws ValidationException {
        if (item == null || item.getData() == null || item.getData().isEmpty()) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }
        for (Question obj : item.getData()) {
            if (obj.getCareerId() == null || obj.getCareerId().equals(0L)) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "careerId"));
            }
            if (obj.getCompanyId() == null || obj.getCompanyId().equals(0L)) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "companyId"));
            }
            if (StringUtils.isNullOrEmpty(obj.getQuestion())) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "question"));
            }
            if (obj.getLevelId() == null || obj.getLevelId().equals(0L)) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "levelId"));
            }
            if (obj.getQuestionType() == null || obj.getQuestionType() == 0) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "questionType"));
            }
        }
        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateUpdateLetterStatus(ChangeStatusLetterDTO item) throws ValidationException {
        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }
        if (item.getId() == null || item.getId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "id"));
        }
        if (StringUtils.isNullOrEmpty(item.getField())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "field"));
        } else if (!item.getField().toLowerCase().equals("status") && !item.getField().equals("user_view")
                && !item.getField().equals("interview_view")) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "field"));
        }
        if (item.getValue() == null) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "value"));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateInsertAnswer(AnswerInsertDTO item) throws ValidationException {
        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }
        for (Answer obj : item.getData()) {
            if (obj.getQuestionId() == null || obj.getQuestionId().equals(0L)) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "questionId"));
            }
            if (StringUtils.isNullOrEmpty(obj.getAnswer())) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "answer"));
            } else if (obj.getAnswer().length() > 255) {
                getMessageDes().add(Messages.getString("validation.field.length", "answer", 255));
            }
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateUpdateQuestion(Question item) throws ValidationException {
        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }
        if (item.getId() == null || item.getId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "id"));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateUpdateAnswer(Answer item) throws ValidationException {
        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }
        if (item.getId() == null || item.getId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "id"));
            throw new ValidationException(this.buildValidationMessage());
        }

        if (item.getQuestionId() == null || item.getQuestionId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "questionId"));
        }
//        if (StringUtils.isNullOrEmpty(item.getAnswer())) {
//            getMessageDes().add(Messages.getString("validation.field.madatory", "answer"));
//        } else if (item.getAnswer().length() > 255) {
//            getMessageDes().add(Messages.getString("validation.field.length", "answer", 255));
//        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateInsertRating(Rating item) throws ValidationException {
        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }

        if (item.getJobId() == null || item.getJobId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "jobId"));
        }

        if (item.getUserId() == null || item.getUserId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "userId"));
        }

        if (StringUtils.isNullOrEmpty(item.getRating())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "rating"));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateInsertResultCriteria(ResultCriteria item) throws ValidationException {
        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }

        if (item.getCompanyId() == null || item.getCompanyId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "companyId"));
        }

        if (item.getJobId() == null || item.getJobId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "jobId"));
        }

        if (item.getUserId() == null || item.getUserId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "userId"));
        }

        if (StringUtils.isNullOrEmpty(item.getChuyenmon())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "chuyenmon"));
        } else if (item.getChuyenmon().length() > 45) {
            getMessageDes().add(Messages.getString("validation.field.length", "chuyenmon", 45));
        }

        if (StringUtils.isNullOrEmpty(item.getThaidocongviec())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "thaidocongviec"));
        } else if (item.getThaidocongviec().length() > 45) {
            getMessageDes().add(Messages.getString("validation.field.length", "thaidocongviec", 45));
        }

        if (StringUtils.isNullOrEmpty(item.getNgoaingu())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "ngoaingu"));
        } else if (item.getNgoaingu().length() > 45) {
            getMessageDes().add(Messages.getString("validation.field.length", "ngoaingu", 45));
        }

        if (StringUtils.isNullOrEmpty(item.getGiaotiep())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "giaotiep"));
        } else if (item.getGiaotiep().length() > 45) {
            getMessageDes().add(Messages.getString("validation.field.length", "giaotiep", 45));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateUpdateLetterField(ChangeFiledLetterDTO item) throws ValidationException {
        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }
        if (item.getId() == null || item.getId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "id"));
        }
        if (StringUtils.isNullOrEmpty(item.getField())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "field"));
        } else if (!item.getField().toLowerCase().equals("job_id") && !item.getField().equals("user_to")
                && !item.getField().equals("company_id")) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "field"));
        }
        if (item.getValue() == null) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "value"));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateInsertResultTest(ResultTest item) throws ValidationException {
        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }

        if (item.getJobId() == null || item.getJobId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "jobId"));
        }

        if (item.getUserId() == null || item.getUserId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "userId"));
        }

        if (item.getQuestionId() == null || item.getQuestionId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "questionId"));
        }

        if (item.getAnswerId() == null || item.getAnswerId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "answerId"));
        }
        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateInsertProcedures(Procedures item) throws ValidationException {

        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }

        if (item.getUserInterview() == null || item.getUserInterview().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "userInterview"));
        }

        if (item.getJobId() == null || item.getJobId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "jobId"));
        }

        if (item.getStt() == null || item.getStt() == 0) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "stt"));
        }

        if (item.getTotal() == null || item.getTotal() == 0) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "total"));
        }

        if (StringUtils.isNullOrEmpty(item.getName())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "name"));
        }

        if (item.getType() == null || item.getType() == 0) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "type"));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }
}
