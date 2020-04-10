package com.elcom.business.manager;

import java.io.IOException;

import javax.ws.rs.core.Response.Status;

import com.elcom.business.factory.CompanyFactory;
import com.elcom.business.factory.InterviewFactory;
import com.elcom.common.Messages;
import com.elcom.data.interview.entity.Company;
import com.elcom.data.interview.entity.dto.CompanyLessDTO;
import com.elcom.model.dto.CompanyUpsertDTO;
import com.elcom.model.dto.interview.ResponseData;
import com.elcom.sharedbiz.manager.BaseManager;
import com.elcom.sharedbiz.validation.ValidationException;
import com.elcom.util.StringUtils;
import java.util.List;

public class CompanyManager extends BaseManager {

    public CompanyManager() {
    }

    public ResponseData findDetails(Long id) throws Exception {

        if (id == null) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "id"));
        }

        return this.tryCatch(() -> {

            Company result = CompanyFactory.getCompanyAggregateInstance(uok.vi).findDetails(id);

            return new ResponseData(
                    result != null ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    result != null ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result
            );
        });
    }

    /*public ResponseDataPaging findAll(String departmentName, Integer status, int page, int rowsPerPage) throws Exception {
		
		return this.tryCatch(()->{
			
			DepartmentDataPaging result = CompanyFactory.getCompanyAggregateInstance(uok.vi)
														.findAll(departmentName, status, page, rowsPerPage);
				
			return new ResponseDataPaging(
					!result.getDataRows().isEmpty() ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode()
					, !result.getDataRows().isEmpty() ? Status.OK.toString() : Status.NO_CONTENT.toString()
					, result.getTotalRows()
					, result.getDataRows()
				);
		});
	}*/
    public ResponseData insert(CompanyUpsertDTO item) throws Exception {

        InterviewFactory.getCompanyValidation().validateUpsert(item, "INSERT");

        return this.tryCatch(() -> {

            CompanyFactory.getCompanyAggregateInstance(uok.vi).insert(item);

            return new ResponseData(
                    Status.CREATED.getStatusCode(),
                    Status.CREATED.toString(),
                    null
            );
        });
    }

    public ResponseData update(CompanyUpsertDTO item) throws Exception {

        InterviewFactory.getCompanyValidation().validateUpsert(item, "UPDATE");

        return this.tryCatch(() -> {

            CompanyFactory.getCompanyAggregateInstance(uok.vi).update(item);

            return new ResponseData(
                    Status.OK.getStatusCode(),
                    Status.OK.toString(),
                    null
            );
        });
    }

    /*public static void main(String[] args) throws NumberFormatException, Exception {
		CompanyManager manager = new CompanyManager();
		
		//@SuppressWarnings("unused")
		ResponseData result = manager.findDetails(new Long(1));
		
		manager.close();
		
		System.exit(0);
	}*/
    public ResponseData loadFromLetter(Long userTo) throws Exception {
        if (userTo == null || !StringUtils.isNumberic(userTo.toString())) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "id"));
        }
        return this.tryCatch(() -> {
            List<CompanyLessDTO> result = CompanyFactory.getCompanyAggregateInstance(uok.vi).loadFromLetter(userTo);
            return new ResponseData(
                    result != null && result.size() > 0 ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    result != null && result.size() > 0 ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result
            );
        });
    }
    
    public ResponseData update(Company item) throws Exception {
        InterviewFactory.getCompanyValidation().validateUpdate(item);
        return this.tryCatch(() -> {
            boolean status = CompanyFactory.getCompanyAggregateInstance(uok.vi).update(item);
            return new ResponseData(
                    status ? Status.OK.getStatusCode() : Status.NOT_MODIFIED.getStatusCode(),
                    status ? Status.OK.toString(): Status.NOT_MODIFIED.toString(),
                    status
            );
        });
    }

    @Override
    public void close() throws IOException {

        this.uok = null;
    }
}
