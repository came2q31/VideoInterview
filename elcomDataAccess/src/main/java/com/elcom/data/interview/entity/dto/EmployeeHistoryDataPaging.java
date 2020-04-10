package com.elcom.data.interview.entity.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class EmployeeHistoryDataPaging implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 122103466283664407L;
	
	private List<EmployeeHistory> dataRows;
	private BigInteger totalRows;
	
	public EmployeeHistoryDataPaging() {
		totalRows = new BigInteger("0");
		dataRows = new ArrayList<EmployeeHistory>();
	}
	
	public List<EmployeeHistory> getDataRows() {
		return dataRows;
	}
	public void setDataRows(List<EmployeeHistory> dataRows) {
		this.dataRows = dataRows;
	}
	public BigInteger getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(BigInteger totalRows) {
		this.totalRows = totalRows;
	}
}
