package com.elcom.model.dto;

import java.io.Serializable;

public class RatingInsertDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5113310993905485424L;
	
	private Long jobId;
	private Long userId;
	private String note;
	private String rating;
	
	public RatingInsertDTO() {
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
}
