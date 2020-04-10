package com.elcom.data.interview.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "UUID")
	private String uuid;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "OFFICE_PHONE")
	private String office_phone;
	
	@Column(name = "HOTLINE")
	private String hotline;
	
	@Column(name = "WEBSITE")
	private String website;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "PACKAGE")
	private Integer _package;
	
	@Column(name = "CREATED_AT", updatable = false)
	private Timestamp createdAt;
	
	@Column(name = "CAREER_ID")
	private Long career_id;
	
	@Column(name = "PACKAGE_START")
	private Date package_start;
	
	@Column(name = "PACKAGE_END")
	private Date package_end;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "SALT_VALUE")
	private String saltValue;

	@Column(name = "LOGO")
	private String logo;
	
	public Company() {
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOffice_phone() {
		return office_phone;
	}

	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer get_package() {
		return _package;
	}

	public void set_package(Integer _package) {
		this._package = _package;
	}

	public Long getCareer_id() {
		return career_id;
	}

	public void setCareer_id(Long career_id) {
		this.career_id = career_id;
	}

	public Date getPackage_start() {
		return package_start;
	}

	public void setPackage_start(Date package_start) {
		this.package_start = package_start;
	}

	public Date getPackage_end() {
		return package_end;
	}

	public void setPackage_end(Date package_end) {
		this.package_end = package_end;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSaltValue() {
		return saltValue;
	}

	public void setSaltValue(String saltValue) {
		this.saltValue = saltValue;
	}
}