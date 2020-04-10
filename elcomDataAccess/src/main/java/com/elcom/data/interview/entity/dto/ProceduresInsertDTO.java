package com.elcom.data.interview.entity.dto;

import java.io.Serializable;
import java.util.List;

import com.elcom.data.interview.entity.Procedures;

public class ProceduresInsertDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Procedures> data;
	
	public ProceduresInsertDTO() {
	}

	public List<Procedures> getData() {
		return data;
	}

	public void setData(List<Procedures> data) {
		this.data = data;
	}
}