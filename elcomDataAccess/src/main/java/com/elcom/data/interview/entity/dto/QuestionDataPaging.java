package com.elcom.data.interview.entity.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.elcom.data.interview.entity.Question;

public class QuestionDataPaging implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 122103466283664407L;
	
	private List<Question> dataRows;
	private BigInteger totalRows;
	
	public QuestionDataPaging() {
		totalRows = new BigInteger("0");
		dataRows = new ArrayList<Question>();
	}
	
	public List<Question> getDataRows() {
		return dataRows;
	}
	public void setDataRows(List<Question> dataRows) {
		this.dataRows = dataRows;
	}
	public BigInteger getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(BigInteger totalRows) {
		this.totalRows = totalRows;
	}
}
