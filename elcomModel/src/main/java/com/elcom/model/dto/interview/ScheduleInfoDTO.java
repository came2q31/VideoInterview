package com.elcom.model.dto.interview;

import java.io.Serializable;
import java.math.BigDecimal;

public class ScheduleInfoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7448741760634606050L;
	
	private String employeeName;
	private String employeeEmail;
	private BigDecimal leaveDays; // Số ngày phép còn lại
	private String departmentName;
	private String managerName; // Tên quản lý
	
	public ScheduleInfoDTO() {
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public BigDecimal getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(BigDecimal leaveDays) {
		this.leaveDays = leaveDays;
	}
}
