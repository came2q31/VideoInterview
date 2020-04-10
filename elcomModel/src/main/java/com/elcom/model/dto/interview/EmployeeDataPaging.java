package com.elcom.model.dto.interview;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDataPaging implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2453742056720538711L;
	
	private List<EmployeeDTO> dataRows;
	private BigInteger totalRows;
	
	public EmployeeDataPaging() {
		totalRows = new BigInteger("0");
		dataRows = new ArrayList<EmployeeDTO>();
	}
	
	public List<EmployeeDTO> getDataRows() {
		return dataRows;
	}
	public void setDataRows(List<EmployeeDTO> dataRows) {
		this.dataRows = dataRows;
	}
	public BigInteger getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(BigInteger totalRows) {
		this.totalRows = totalRows;
	}
}
