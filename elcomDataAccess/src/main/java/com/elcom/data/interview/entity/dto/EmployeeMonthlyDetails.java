package com.elcom.data.interview.entity.dto;

import java.io.Serializable;

public class EmployeeMonthlyDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String date;
	private String contentShow;
	private String weekendFlag;
	private String startDay;
	private String endDay;
	private String startTime;
	private String endTime;
	private String reasonShortName;
	
	public EmployeeMonthlyDetails() {
	}

	public String getReasonShortName() {
		return reasonShortName;
	}

	public void setReasonShortName(String reasonShortName) {
		this.reasonShortName = reasonShortName;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContentShow() {
		return contentShow;
	}

	public void setContentShow(String contentShow) {
		this.contentShow = contentShow;
	}

	public String getWeekendFlag() {
		return weekendFlag;
	}

	public void setWeekendFlag(String weekendFlag) {
		this.weekendFlag = weekendFlag;
	}
}