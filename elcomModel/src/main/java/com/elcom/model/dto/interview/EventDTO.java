package com.elcom.model.dto.interview;

import java.io.Serializable;
import java.sql.Timestamp;

public class EventDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8443586499207296675L;
	
	private String employeeCode;
	private Timestamp evenTimes;
	
	public EventDTO() {
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public Timestamp getEvenTimes() {
		return evenTimes;
	}

	public void setEvenTimes(Timestamp evenTimes) {
		this.evenTimes = evenTimes;
	}
}
