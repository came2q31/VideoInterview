package com.elcom.data.interview.entity.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.elcom.data.interview.entity.EmployeeSchedule;

public class EmployeeScheduleDataPaging implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 122103466283664407L;
	
	private List<EmployeeSchedule> dataRows;
	private BigInteger totalRows;
	
	public EmployeeScheduleDataPaging() {
		totalRows = new BigInteger("0");
		dataRows = new ArrayList<EmployeeSchedule>();
	}
	
	public List<EmployeeSchedule> getDataRows() {
		return dataRows;
	}
	public void setDataRows(List<EmployeeSchedule> dataRows) {
		this.dataRows = dataRows;
	}
	public BigInteger getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(BigInteger totalRows) {
		this.totalRows = totalRows;
	}
}
