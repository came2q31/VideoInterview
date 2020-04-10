package com.elcom.data.interview.entity.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.elcom.data.interview.entity.Company;

public class DepartmentDataPaging implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6837619735829551358L;
	
	private List<Company> dataRows;
	private BigInteger totalRows;
	
	public DepartmentDataPaging() {
		totalRows = new BigInteger("0");
		dataRows = new ArrayList<Company>();
	}
	
	public List<Company> getDataRows() {
		return dataRows;
	}
	public void setDataRows(List<Company> dataRows) {
		this.dataRows = dataRows;
	}
	public BigInteger getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(BigInteger totalRows) {
		this.totalRows = totalRows;
	}
}
