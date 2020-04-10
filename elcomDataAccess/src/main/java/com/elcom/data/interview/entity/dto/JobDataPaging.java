package com.elcom.data.interview.entity.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.elcom.data.interview.entity.Job;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobDataPaging implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 122103466283664407L;
	
	private List<Job> dataRows;
	private BigInteger totalRows;
	
	public JobDataPaging() {
		totalRows = new BigInteger("0");
		dataRows = new ArrayList<Job>();
	}
	
	public List<Job> getDataRows() {
		return dataRows;
	}
	public void setDataRows(List<Job> dataRows) {
		this.dataRows = dataRows;
	}
	public BigInteger getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(BigInteger totalRows) {
		this.totalRows = totalRows;
	}
}
