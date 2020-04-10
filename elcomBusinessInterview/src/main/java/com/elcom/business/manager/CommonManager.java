package com.elcom.business.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.StreamingOutput;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import com.elcom.business.factory.InterviewFactory;
import com.elcom.common.Messages;
import com.elcom.data.interview.entity.Answer;
import com.elcom.data.interview.entity.Career;
import com.elcom.data.interview.entity.Job;
import com.elcom.data.interview.entity.Letter;
import com.elcom.data.interview.entity.Level;
import com.elcom.data.interview.entity.Procedures;
import com.elcom.data.interview.entity.Rating;
import com.elcom.data.interview.entity.ResultCriteria;
import com.elcom.data.interview.entity.ResultTest;
import com.elcom.data.interview.entity.dto.AnswerInsertDTO;
import com.elcom.data.interview.entity.dto.JobDataPaging;
import com.elcom.data.interview.entity.dto.ProceduresInsertDTO;
import com.elcom.data.interview.entity.dto.QuestionJobDTO;
import com.elcom.model.constant.InterviewConstant;
import com.elcom.model.dto.DeleteFileDTO;
import com.elcom.model.dto.MailContentDTO;
import com.elcom.model.dto.RatingInsertDTO;
import com.elcom.model.dto.interview.ResponseData;
import com.elcom.model.dto.interview.ResponseDataPaging;
import com.elcom.sharedbiz.mail.SendMail;
import com.elcom.sharedbiz.mail.ToMail;
import com.elcom.sharedbiz.manager.BaseManager;
import com.elcom.sharedbiz.validation.ValidationException;
import com.elcom.util.DateUtil;
import com.elcom.util.StringUtils;
import com.google.gson.Gson;
import com.sun.jersey.core.header.FormDataContentDisposition;
import java.util.Date;

public class CommonManager extends BaseManager {

    private static final Logger logger = Logger.getLogger(CommonManager.class.getName());

    public CommonManager() {
    }

    public ResponseData findAllCareer() throws Exception {

        return this.tryCatch(() -> {

            List<Career> lst = InterviewFactory.getCommonAggregateInstance(uok).findAllCareer();

            return new ResponseData(
                    lst != null && lst.size() > 0 ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    lst != null && lst.size() > 0 ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    lst
            );
        });
    }

    public ResponseData findAllLevel(Long careerId) throws Exception {

        if (careerId == null || careerId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "careerId"));
        }

        return this.tryCatch(() -> {

            List<Level> lst = InterviewFactory.getCommonAggregateInstance(uok).findAllLevel(careerId);

            return new ResponseData(
                    lst != null && lst.size() > 0 ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    lst != null && lst.size() > 0 ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    lst
            );
        });
    }

    public ResponseData insertRating(RatingInsertDTO item) throws Exception {

        InterviewFactory.getCommonValidation().validateInsertRating(item);

        return this.tryCatch(() -> {

            boolean status = InterviewFactory.getCommonAggregateInstance(uok).insertRating(item);

            return new ResponseData(
                    status ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    status ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    null
            );
        });
    }

    public ResponseData insertJob(Job item) throws Exception {

        InterviewFactory.getCommonValidation().validateInsertJob(item);

        return this.tryCatch(() -> {

            InterviewFactory.getCommonAggregateInstance(uok).insertJob(item);
            boolean created = item.getId() != null && !item.getId().equals(0L);

            return new ResponseData(
                    created ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    created ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    item.getId()
            );
        });
    }

    public ResponseData insertQuestionJob(QuestionJobDTO item) throws Exception {

        InterviewFactory.getCommonValidation().validateInsertQuestionJob(item);

        return this.tryCatch(() -> {

            boolean statusInsert = InterviewFactory.getCommonAggregateInstance(uok).insertQuestionJob(item);

            return new ResponseData(
                    statusInsert ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    statusInsert ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    statusInsert
            );
        });
    }

    public ResponseData insertProcedures(ProceduresInsertDTO item) throws Exception {

        InterviewFactory.getCommonValidation().validateInsertProcedures(item);

        return this.tryCatch(() -> {

            boolean status = InterviewFactory.getCommonAggregateInstance(uok).insertProcedures(item);

            return new ResponseData(
                    status ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    status ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    status
            );
        });
    }

    public ResponseDataPaging findAllJob(Long companyId, Integer type) throws Exception {
        if (companyId == null || companyId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "companyId"));
        }
        return this.tryCatch(() -> {
            JobDataPaging result = InterviewFactory.getCommonAggregateInstance(uok).findAllJob(companyId, type);
            return new ResponseDataPaging(
                    !result.getDataRows().isEmpty() ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    !result.getDataRows().isEmpty() ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result.getTotalRows(),
                    result.getDataRows()
            );
        });
    }

    public ResponseData sendMail(MailContentDTO item) throws Exception {

        InterviewFactory.getCommonValidation().validateSendEmail(item);
        System.out.println("json: " + new Gson().toJson(item));
        return this.tryCatch(() -> {
            SendMail mailUtil = new SendMail();
            ToMail toMail = new ToMail(item.getFromName(), item.getEmailTo());
            List<ToMail> listToMail = new ArrayList<>();
            listToMail.add(toMail);
            boolean status = mailUtil.sendMail(InterviewConstant.MAIL_HOST,
                    InterviewConstant.MAIL_PORT, InterviewConstant.MAIL_SEND_ACC,
                    InterviewConstant.MAIL_SEND_PW, item.getEmailTitle(),
                    item.getEmailContent(), listToMail);

            return new ResponseData(
                    status ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    status ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    status
            );
        });
    }

    public ResponseData uploadFile(InputStream uploadedInputStream, FormDataContentDisposition fileDetail) throws Exception {
        InterviewFactory.getCommonValidation().validateUploadFile(uploadedInputStream, fileDetail);
        return this.tryCatch(() -> {
            String ddmmyyyy = DateUtil.today("ddMMyyyy");
            System.out.println("InterviewConstant.ROOT_DIR: " + InterviewConstant.ROOT_DIR + ", file: " + fileDetail.getFileName());
            createUploadFolder(ddmmyyyy, fileDetail);

            //Write file
            String uploadedFileLocation = InterviewConstant.ROOT_DIR + File.separator + "upload" + File.separator + ddmmyyyy + File.separator
                    + (isImage(fileDetail.getFileName()) ? "images" : "file") + File.separator + fileDetail.getFileName();
            File file = new File(uploadedFileLocation);
            long now = System.currentTimeMillis();
            boolean isExistFile = file.exists();
            if (isExistFile) {
                System.out.println("File upload " + uploadedFileLocation + " da ton tai ==> Tao file moi");
                uploadedFileLocation = InterviewConstant.ROOT_DIR + File.separator + "upload" + File.separator + ddmmyyyy + File.separator
                        + (isImage(fileDetail.getFileName()) ? "images" : "file") + File.separator + now + "_" + fileDetail.getFileName();
            }
            // save it
            boolean uploadOk = writeToFile(uploadedInputStream, uploadedFileLocation);
            //Image url return
            String returnImageUrl = InterviewConstant.ROOT_URL + "upload/" + ddmmyyyy
                    + (isImage(fileDetail.getFileName()) ? "/images/" : "/file/") + fileDetail.getFileName();
            if (isExistFile) {
                returnImageUrl = InterviewConstant.ROOT_URL + "upload/" + ddmmyyyy
                        + (isImage(fileDetail.getFileName()) ? "/images/" : "/file/") + now + "_" + fileDetail.getFileName();
            }
            String output = "File uploaded to : " + uploadedFileLocation;
            
            return new ResponseData(
                    uploadOk ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    uploadOk ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    uploadOk ? returnImageUrl : null
            );
        });
    }

    private void createUploadFolder(String ddmmyyyy, FormDataContentDisposition fileDetail) {
        //Create upload dir if not exist
        File uploadDir = new File(InterviewConstant.ROOT_DIR + File.separator + "upload");
        if (!uploadDir.exists()) {
            System.out.println("Tao folder upload: " + uploadDir.mkdir());
        }

        //Create dir if not exist
        File ddmmyyyyDir = new File(InterviewConstant.ROOT_DIR + File.separator + "upload" + File.separator + ddmmyyyy);
        if (!ddmmyyyyDir.exists()) {
            System.out.println("Tao folder " + ddmmyyyy + ": " + ddmmyyyyDir.mkdir());
        }

        //Create dir if not exist
        File imgDir = new File(InterviewConstant.ROOT_DIR + File.separator + "upload" + File.separator + ddmmyyyy + File.separator + "images");
        if (!imgDir.exists()) {
            System.out.println("Tao folder images: " + imgDir.mkdir());
        }

        File fileDir = new File(InterviewConstant.ROOT_DIR + File.separator + "upload" + File.separator + ddmmyyyy + File.separator + "file");
        if (!fileDir.exists()) {
            System.out.println("Tao folder file: " + fileDir.mkdir());
        }
    }

    // save uploaded file to new location
    private boolean writeToFile(InputStream uploadedInputStream,
            String uploadedFileLocation) {
        try {
            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private boolean isImage(String fileName) {
        if (fileName == null || fileName.equals("")) {
            return false;
        }
        if (fileName.indexOf("/") > 0) {
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        }
        fileName = fileName.toLowerCase();
        return fileName.contains(".png") || fileName.contains(".jpg") || fileName.contains(".bmp")
                || fileName.contains(".gif") || fileName.contains(".jpeg");
    }

    public ResponseData findProceduresByJob(Long jobId) throws Exception {
//        if (jobId == null || jobId.equals(0L)) {
//            throw new ValidationException(Messages.getString("validation.field.madatory", "jobId"));
//        }
        return this.tryCatch(() -> {
            List<Procedures> lst = InterviewFactory.getCommonAggregateInstance(uok).findProceduresByJob(jobId);
            return new ResponseData(
                    lst != null && lst.size() > 0 ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    lst != null && lst.size() > 0 ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    lst
            );
        });
    }

    public ResponseData checkSendLetter(Long jobId, String email) throws Exception {
        if (jobId == null || jobId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "jobId"));
        }
        if (StringUtils.isNullOrEmpty(email)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "email"));
        }
        if (!StringUtils.validateEmail(email)) {
            throw new ValidationException(Messages.getString("validation.field.type", "email"));
        }

        return this.tryCatch(() -> {
            boolean check = InterviewFactory.getCommonAggregateInstance(uok).checkSendLetter(jobId, email);
            return new ResponseData(Status.OK.getStatusCode(), Status.OK.toString(), check);
        });
    }

    public ResponseData insertLetter(Letter item) throws Exception {
        InterviewFactory.getCommonValidation().validateInsertLetter(item);
        return this.tryCatch(() -> {
            if (item.getCreatedAt() == null) {
                item.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            }
            System.out.println("getAppointmentTimeTs: " + item.getAppointmentTimeTs());
            item.setAppointmentTime(new Timestamp(new Date(item.getAppointmentTimeTs() * 1000L).getTime()));
            InterviewFactory.getCommonAggregateInstance(uok).insertLetter(item);
            boolean created = item.getId() != null && !item.getId().equals(0L);
            return new ResponseData(
                    created ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    created ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    created ? item : null
            );
        });
    }

    public StreamingOutput mediaProgress(Long mediaId) throws Exception {

        if (mediaId == null || mediaId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "mediaId"));
        }

        return this.tryCatch(() -> {

            StreamingOutput result = InterviewFactory
                    .getMediaAggregate(uok.vi)
                    .mediaProgress(mediaId);

            if (result == null) {
                logger.error("CommonManager.mediaProgress(): Error when generate media file");
                throw new ValidationException(HttpStatus.SC_INTERNAL_SERVER_ERROR, "CommonManager.mediaProgress(): Error when generate media file");
            }

            return result;
        });
    }

    public ResponseData insertAnswer(AnswerInsertDTO item) throws Exception {
        InterviewFactory.getCommonValidation().validateInsertAnswer(item);
        return this.tryCatch(() -> {
            boolean statusInsert = InterviewFactory.getCommonAggregateInstance(uok).insertAnswer(item);
            return new ResponseData(
                    statusInsert ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    statusInsert ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    statusInsert
            );
        });
    }

    public ResponseData updateAnswer(Answer item) throws Exception {
        InterviewFactory.getCommonValidation().validateUpdateAnswer(item);
        return this.tryCatch(() -> {
            boolean statusInsert = InterviewFactory.getCommonAggregateInstance(uok).updateAnswer(item);
            return new ResponseData(
                    statusInsert ? Status.OK.getStatusCode() : Status.NOT_MODIFIED.getStatusCode(),
                    statusInsert ? Status.OK.toString() : Status.NOT_MODIFIED.toString(),
                    statusInsert
            );
        });
    }

    public ResponseData deleteAnswer(String idList) throws Exception {
        if (StringUtils.isNullOrEmpty(idList)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "idList"));
        }
        return this.tryCatch(() -> {
            boolean status = InterviewFactory.getCommonAggregateInstance(uok).deleteAnswer(idList);
            return new ResponseData(
                    status ? Status.OK.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    status ? Status.OK.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    status
            );
        });
    }

    public ResponseData insertRating(Rating item) throws Exception {
        InterviewFactory.getCommonValidation().validateInsertRating(item);
        return this.tryCatch(() -> {
            InterviewFactory.getCommonAggregateInstance(uok).insertRating(item);
            boolean created = item.getId() != null && !item.getId().equals(0L);
            return new ResponseData(
                    created ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    created ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    item.getId()
            );
        });
    }

    public ResponseData findAllForCampany(Long companyId) throws Exception {
        if (companyId == null || companyId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "companyId"));
        }
        return this.tryCatch(() -> {
            List<Rating> result = InterviewFactory.getCommonAggregateInstance(uok).findAllForCampany(companyId);
            return new ResponseData(
                    result != null && !result.isEmpty() ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    result != null && !result.isEmpty() ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result
            );
        });
    }

    public ResponseData insertResultCriteriaReturnId(ResultCriteria item) throws Exception {
        InterviewFactory.getCommonValidation().validateInsertResultCriteria(item);
        return this.tryCatch(() -> {
            InterviewFactory.getCommonAggregateInstance(uok).insertResultCriteriaReturnId(item);
            boolean created = item.getId() != null && !item.getId().equals(0L);
            return new ResponseData(
                    created ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    created ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    item.getId()
            );
        });
    }

    public ResponseData findAllResultCriteria(Long companyId, Long jobId, String userId) throws Exception {
        return this.tryCatch(() -> {
            List<ResultCriteria> result = InterviewFactory.getCommonAggregateInstance(uok).findAllResultCriteria(companyId, jobId, userId);
            return new ResponseData(
                    result != null && !result.isEmpty() ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    result != null && !result.isEmpty() ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result
            );
        });
    }

    public ResponseData insertResultTestReturnId(ResultTest item) throws Exception {
        InterviewFactory.getCommonValidation().validateInsertResultTest(item);
        return this.tryCatch(() -> {
            InterviewFactory.getCommonAggregateInstance(uok).insertResultTestReturnId(item);
            boolean created = item.getId() != null && !item.getId().equals(0L);
            return new ResponseData(
                    created ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    created ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    item.getId()
            );
        });
    }

    public ResponseData insertCareer(Career item) throws Exception {
        if (StringUtils.isNullOrEmpty(item.getName())) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "name"));
        }
        return this.tryCatch(() -> {
            boolean status = InterviewFactory.getCommonAggregateInstance(uok).insertCareer(item);
            return new ResponseData(
                    status ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    status ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    status
            );
        });
    }

    public ResponseData updateCareer(Career item) throws Exception {
        if (StringUtils.isNullOrEmpty(item.getName())) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "name"));
        }
        return this.tryCatch(() -> {
            boolean status = InterviewFactory.getCommonAggregateInstance(uok).updateCareer(item);
            return new ResponseData(
                    status ? Status.OK.getStatusCode() : Status.NOT_MODIFIED.getStatusCode(),
                    status ? Status.OK.toString() : Status.NOT_MODIFIED.toString(),
                    status
            );
        });
    }

    public ResponseData deleteCareer(Long id) throws Exception {
        if (id == null || id.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "id"));
        }
        return this.tryCatch(() -> {
            boolean status = InterviewFactory.getCommonAggregateInstance(uok).deleteCareer(id);
            return new ResponseData(
                    status ? Status.OK.getStatusCode() : Status.NOT_FOUND.getStatusCode(),
                    status ? Status.OK.toString() : Status.NOT_FOUND.toString(),
                    status
            );
        });
    }
    
    public ResponseData insertProcedures(Procedures item) throws Exception {

        InterviewFactory.getCommonValidation().validateInsertProcedures(item);

        return this.tryCatch(() -> {

            Long id = InterviewFactory.getCommonAggregateInstance(uok).insertProcedures(item);

            return new ResponseData(
                    id != null && !id.equals(0L) ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    id != null && !id.equals(0L) ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    id
            );
        });
    }
    
    public ResponseData deleteFile(DeleteFileDTO file) throws Exception {
        if (file == null) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "input"));
        }
        if(StringUtils.isNullOrEmpty(file.getLinkFile())){
            throw new ValidationException(Messages.getString("validation.field.madatory", "linkFile"));
        }
        return this.tryCatch(() -> {
            String linkFolder = file.getLinkFile().replace(InterviewConstant.ROOT_URL, "");
            if(!file.getLinkFile().contains(InterviewConstant.ROOT_URL) && file.getLinkFile().contains("/upload")){
                linkFolder = file.getLinkFile().substring(file.getLinkFile().indexOf("/upload/"));
            }
            String uploadedFileLocation = InterviewConstant.ROOT_DIR + linkFolder.replace("/", File.separator);
            System.out.println("ROOT_URL:" + InterviewConstant.ROOT_URL + " | linkFolder: " + linkFolder + 
                    " | file : " + uploadedFileLocation);
            File uploadedFile = new File(uploadedFileLocation);
            boolean deleted = false;
            if(uploadedFile.exists()){
                deleted = uploadedFile.delete();
            }

            return new ResponseData(
                    deleted ? Status.OK.getStatusCode() : Status.NOT_FOUND.getStatusCode(),
                    deleted ? Status.OK.toString() : Status.NOT_FOUND.toString(),
                    deleted
            );
        });
    }
    
    @Override
    public void close() throws IOException {

        this.uok = null;
    }
}
