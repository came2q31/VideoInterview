package com.elcom.data.interview.entity.dto;

import java.io.Serializable;

public class EmployeeHistory implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String cardNumber;
	private String email;
	private String departmentName;
	private String date;
	private String weekendFlag;
	private String reason;
	private String checkTime;
	private String checkInAt;
	private String checkOutAt;
	private long comeViolate;
	private long backViolate;
	
	public EmployeeHistory() {
	}

	public long getComeViolate() {
		return comeViolate;
	}

	public void setComeViolate(long comeViolate) {
		this.comeViolate = comeViolate;
	}

	public long getBackViolate() {
		return backViolate;
	}

	public void setBackViolate(long backViolate) {
		this.backViolate = backViolate;
	}

	public String getCheckInAt() {
		return checkInAt;
	}

	public void setCheckInAt(String checkInAt) {
		this.checkInAt = checkInAt;
	}

	public String getCheckOutAt() {
		return checkOutAt;
	}

	public void setCheckOutAt(String checkOutAt) {
		this.checkOutAt = checkOutAt;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeekendFlag() {
		return weekendFlag;
	}

	public void setWeekendFlag(String weekendFlag) {
		this.weekendFlag = weekendFlag;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}