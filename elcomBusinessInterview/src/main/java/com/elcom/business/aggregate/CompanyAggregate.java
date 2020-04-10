package com.elcom.business.aggregate;

import com.elcom.data.interview.UnitOfWorkInterview;
import com.elcom.data.interview.entity.Company;
import com.elcom.data.interview.entity.dto.CompanyLessDTO;
import com.elcom.model.dto.CompanyUpsertDTO;
import java.util.List;

public class CompanyAggregate {

    private UnitOfWorkInterview _uokInterview = null;

    public CompanyAggregate(UnitOfWorkInterview _uokEp) {
        this._uokInterview = _uokEp;
    }

    /*public DepartmentDataPaging findAll(String departmentName, Integer status, int page, int rowsPerPage) throws Exception {
		
		return this._uokEp.companyRepository().findAll(departmentName, status, page, rowsPerPage);
	}*/
    public Company findDetails(Long id) throws Exception {

        return this._uokInterview.companyRepository().findDetails(id);
    }

    public void insert(CompanyUpsertDTO item) {

        this._uokInterview.companyRepository().insert(item);
    }

    public void update(CompanyUpsertDTO item) {

        this._uokInterview.companyRepository().update(item);
    }

    public List<CompanyLessDTO> loadFromLetter(Long userTo) throws Exception {
        return this._uokInterview.companyRepository().loadFromLetter(userTo);
    }
    
    public boolean update(Company item) {
        return this._uokInterview.companyRepository().update(item);
    }
}
