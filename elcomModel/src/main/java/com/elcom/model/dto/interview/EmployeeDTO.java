package com.elcom.model.dto.interview;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EmployeeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1080653699770493911L;
	
	private Long id;
	private String uuid;
	private String email;
	private String fullName;
	private String cardNumber;
	private String extNumber;
	private String address;
	private String avatar;
	private Integer status;
	private BigDecimal leaveDay;
	private Date leaveTimeApply;
	private String description;
	private Integer isDeleted;
	private Integer type;
	private Long updatedAt;
	private String departmentName;
	
	public EmployeeDTO() {
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getLeaveDay() {
		return leaveDay;
	}

	public void setLeaveDay(BigDecimal leaveDay) {
		this.leaveDay = leaveDay;
	}

	public Date getLeaveTimeApply() {
		return leaveTimeApply;
	}

	public void setLeaveTimeApply(Date leaveTimeApply) {
		this.leaveTimeApply = leaveTimeApply;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getExtNumber() {
		return extNumber;
	}
	public void setExtNumber(String extNumber) {
		this.extNumber = extNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
