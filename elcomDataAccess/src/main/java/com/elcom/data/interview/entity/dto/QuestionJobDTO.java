package com.elcom.data.interview.entity.dto;

import java.io.Serializable;
import java.util.List;

import com.elcom.data.interview.entity.QuestionJob;

public class QuestionJobDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<QuestionJob> data;
	
	public QuestionJobDTO() {
	}

	public List<QuestionJob> getData() {
		return data;
	}

	public void setData(List<QuestionJob> data) {
		this.data = data;
	}
}