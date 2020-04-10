package com.elcom.business.manager;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import com.elcom.business.factory.InterviewFactory;
import com.elcom.common.Messages;
import com.elcom.data.interview.entity.dto.UserDataPaging;
import com.elcom.data.user.entity.User;
import com.elcom.model.dto.ChangePasswordReq;
import com.elcom.model.dto.InterviewUserDTO;
import com.elcom.model.dto.interview.EmployeeDTO;
import com.elcom.model.dto.interview.EmployeeDataPaging;
import com.elcom.model.dto.interview.EmployeeEmailDTO;
import com.elcom.model.dto.interview.InterviewUserDataPaging;
import com.elcom.model.dto.interview.ResponseData;
import com.elcom.model.dto.interview.ResponseDataPaging;
import com.elcom.sharedbiz.factory.UserFactory;
import com.elcom.sharedbiz.manager.BaseManager;
import com.elcom.sharedbiz.validation.ValidationException;

public class UserManager extends BaseManager {

    public UserManager() {
    }

    public ResponseData findDetails(String uuid) throws Exception {

        InterviewFactory.getUserValidation().validateIdentityUser(uuid);

        return this.tryCatch(() -> {

            User result = UserFactory.getUserAgg(uok).findDetails(uuid);

            return new ResponseData(
                    result != null ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    result != null ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result
            );
        });
    }

    public ResponseDataPaging findAll(Long departmentId, String fullName, String email, int page, int rowsPerPage,
            Integer status, Integer isDeleted) throws Exception {

        return this.tryCatch(() -> {

            EmployeeDataPaging result = UserFactory.getUserAgg(uok)
                    .findAll(departmentId, fullName, email, page, rowsPerPage, status, isDeleted);

            return new ResponseDataPaging(
                    !result.getDataRows().isEmpty() ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    !result.getDataRows().isEmpty() ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result.getTotalRows(),
                    result.getDataRows()
            );
        });
    }

    public ResponseDataPaging findAllUserByCompanyId(Long companyId) throws Exception {

        if (companyId == null) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "companyId"));
        }

        return this.tryCatch(() -> {

            UserDataPaging result = UserFactory.getUserAgg(uok)
                    .findAllUserByCompanyId(companyId);

            return new ResponseDataPaging(
                    !result.getDataRows().isEmpty() ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    !result.getDataRows().isEmpty() ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result.getTotalRows(),
                    result.getDataRows()
            );
        });
    }

    public ResponseData findEmailLst() throws Exception {

        return this.tryCatch(() -> {

            List<EmployeeEmailDTO> result = UserFactory.getUserAgg(uok).findEmailLst();

            return new ResponseData(
                    !result.isEmpty() ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    !result.isEmpty() ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result
            );
        });
    }

    public ResponseData findByManagerId(Long managerId) throws Exception {

        if (managerId == null || managerId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "manager_id"));
        }

        return this.tryCatch(() -> {

            List<EmployeeDTO> result = UserFactory.getUserAgg(uok).findByManagerId(managerId);

            return new ResponseData(
                    !result.isEmpty() ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    !result.isEmpty() ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result
            );
        });
    }

    /*public static void main(String[] args) throws NumberFormatException, Exception {
		UserManager manager = new UserManager();
		
		//@SuppressWarnings("unused")
		ResponseData result = manager.findByManagerId(new Long(1));
		
		manager.close();
		
		System.exit(0);
	}*/
    public ResponseData insert(InterviewUserDTO item) throws Exception {

        InterviewFactory.getUserValidation().validateInsert(item);

        return this.tryCatch(() -> {

            Long id = UserFactory.getUserAgg(uok).insert(item);

            return new ResponseData(
                    id != null && !id.equals(0L) ? Status.CREATED.getStatusCode() : Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    id != null && !id.equals(0L) ? Status.CREATED.toString() : Status.INTERNAL_SERVER_ERROR.toString(),
                    id
            );
        });
    }

    public ResponseData update(InterviewUserDTO item) throws Exception {

        InterviewFactory.getUserValidation().validateUpdate(item);

        return this.tryCatch(() -> {

            boolean status = UserFactory.getUserAgg(uok).update(item);

            return new ResponseData(
                    status ? Status.OK.getStatusCode() : Status.NOT_MODIFIED.getStatusCode(),
                    status ? Status.OK.toString() : Status.NOT_MODIFIED.toString(),
                    null
            );
        });
    }

    /*public ResponseData delete(String uuid) throws Exception {
		
		InterviewFactory.getUserValidation().validateIdentityUser(uuid);
		
		return this.tryCatch(()->{
			
			boolean status = UserFactory.getUserAgg(uok).delete(uuid);
			
			return new ResponseData(
						status ? Status.OK.getStatusCode() : Status.BAD_REQUEST.getStatusCode()
						, status ? Status.OK.toString() : Status.BAD_REQUEST.toString()
						, null
					);
		});
	}*/
    public ResponseData resetPassword(String uuid, String newPassword) throws Exception {

        InterviewFactory.getUserValidation().validateChangePassword(uuid, newPassword);

        return this.tryCatch(() -> {

            boolean status = UserFactory.getUserAgg(uok).changePassword(uuid, newPassword, "USER");

            return new ResponseData(
                    status ? Status.OK.getStatusCode() : Status.BAD_REQUEST.getStatusCode(),
                    status ? Status.OK.toString() : Status.BAD_REQUEST.toString(),
                    null
            );
        });
    }

    public ResponseData changePassword(ChangePasswordReq req) throws Exception {

        InterviewFactory.getUserValidation().validateChangePassword(req.getUuid(), req.getNewPassword());

        return this.tryCatch(() -> {

            boolean validCurrentPassword = UserFactory.getUserAgg(uok)
                    .checkCurrentPassword(req.getUuid(), req.getCurrentPassword(), req.getChangeType());
            if (!validCurrentPassword) {
                throw new ValidationException("Current password is invalid!");
            }

            boolean status = UserFactory.getUserAgg(uok)
                    .changePassword(req.getUuid(), req.getNewPassword(), req.getChangeType());

            return new ResponseData(
                    status ? Status.OK.getStatusCode() : Status.BAD_REQUEST.getStatusCode(),
                    status ? Status.OK.toString() : Status.BAD_REQUEST.toString(),
                    null
            );
        });
    }

    public ResponseData checkMainInfoExists(String checkType, String info, Integer table) throws Exception {
        if ("mobile".equals(checkType)) {
            InterviewFactory.getUserValidation().validateMobile(info);
        } else if ("email".equals(checkType)) // EMAIL
        {
            InterviewFactory.getUserValidation().validateEmail(info);
        } else {
            throw new ValidationException("check type is invalid!");
        }

        return this.tryCatch(() -> {
            Integer id = UserFactory.getUserAgg(uok).checkMainInfoExists(checkType, info, table);
            return new ResponseData(
                    Status.OK.getStatusCode(),
                    Status.OK.toString(),
                    (id != null ? id : 0)
            );
        });
    }

    public ResponseDataPaging findUserByCompanyIdAndName(Long companyId, String name,
            int page, int rowsPerPage) throws Exception {
        if (companyId != null && !companyId.equals(0L)) {
            InterviewFactory.getUserValidation().validateCompanyId(companyId);
        }
        return this.tryCatch(() -> {
            InterviewUserDataPaging result = UserFactory.getUserAgg(uok).findUserByCompanyIdAndName(companyId, name, page, rowsPerPage);
            return new ResponseDataPaging(
                    !result.getDataRows().isEmpty() ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    !result.getDataRows().isEmpty() ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result.getTotalRows(),
                    result.getDataRows()
            );
        });
    }
    
    public ResponseData findUserDetails(Long id, Long companyId) throws Exception {
        if (id == null || id.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "id"));
        }
        if (companyId == null || companyId.equals(0L)) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "company_id"));
        }
        return this.tryCatch(() -> {
            User result = UserFactory.getUserAgg(uok) .findUserDetails(id, companyId);
            return new ResponseData(
                    result != null ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    result != null ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result
            );
        });
    }

    @Override
    public void close() throws IOException {

        this.uok = null;
    }
}
