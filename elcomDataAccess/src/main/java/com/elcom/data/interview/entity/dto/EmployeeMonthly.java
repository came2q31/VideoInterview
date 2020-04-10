package com.elcom.data.interview.entity.dto;

import java.io.Serializable;
import java.util.List;

public class EmployeeMonthly implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String employeeCode;
	private String fullName;
	private float nghiPhep;
	private float nghiKhongLuong;
	private float nghiCheDo;
	private float nghiHuongLuong;
	List<EmployeeMonthlyDetails> dateLst;
	
	public EmployeeMonthly() {
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public float getNghiPhep() {
		return nghiPhep;
	}

	public void setNghiPhep(float nghiPhep) {
		this.nghiPhep = nghiPhep;
	}

	public float getNghiKhongLuong() {
		return nghiKhongLuong;
	}

	public void setNghiKhongLuong(float nghiKhongLuong) {
		this.nghiKhongLuong = nghiKhongLuong;
	}

	public float getNghiCheDo() {
		return nghiCheDo;
	}

	public void setNghiCheDo(float nghiCheDo) {
		this.nghiCheDo = nghiCheDo;
	}

	public float getNghiHuongLuong() {
		return nghiHuongLuong;
	}

	public void setNghiHuongLuong(float nghiHuongLuong) {
		this.nghiHuongLuong = nghiHuongLuong;
	}

	public List<EmployeeMonthlyDetails> getDateLst() {
		return dateLst;
	}

	public void setDateLst(List<EmployeeMonthlyDetails> dateLst) {
		this.dateLst = dateLst;
	}
}