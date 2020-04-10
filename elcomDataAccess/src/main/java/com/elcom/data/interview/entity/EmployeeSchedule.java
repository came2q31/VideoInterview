package com.elcom.data.interview.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name = "employee_schedule")
public class EmployeeSchedule implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "EMPLOYEE_ID")
	private Long employeeId;
	
	@Column(name = "EMPLOYEE_STATUS_ID")
	private Long employeeStatusId;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "START_DAY")
	private Date startDay;
	
	@Column(name = "END_DAY")
	private Date endDay;
	
	@Column(name = "START_TIME")
	private String startTime;
	
	@Column(name = "END_TIME")
	private String endTime;
	
	@Column(name = "RECIPIENTS")
	private String recipients;
	
	/*@Column(name = "employee_confirm")
	private Long employeeConfirm;
	
	@Column(name = "confirm_status")
	private Integer confirmStatus;*/
	
	@Column(name = "TOTAL_LEAVE")
	private BigDecimal totalLeave;
	
	@Column(name = "STATUS_CODE")
	private String statusCode;
	
	@Column(name = "CREATED_AT", updatable = false)
	private Long createdAt;
	
	/*@Column(name = "UPDATED_AT")
	private Long updatedAt;*/
	
	@Transient
	private String employeeName;

	@Transient
	private String statusName;
	
	@Transient
	private String statusShortName;
	
	@Transient
	private String employeeStatusName;
	
	public EmployeeSchedule() {
	}

	public String getEmployeeStatusName() {
		return employeeStatusName;
	}

	public void setEmployeeStatusName(String employeeStatusName) {
		this.employeeStatusName = employeeStatusName;
	}

	public String getStatusShortName() {
		return statusShortName;
	}

	public void setStatusShortName(String statusShortName) {
		this.statusShortName = statusShortName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getEmployeeStatusId() {
		return employeeStatusId;
	}

	public void setEmployeeStatusId(Long employeeStatusId) {
		this.employeeStatusId = employeeStatusId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
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

	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public BigDecimal getTotalLeave() {
		return totalLeave;
	}

	public void setTotalLeave(BigDecimal totalLeave) {
		this.totalLeave = totalLeave;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}