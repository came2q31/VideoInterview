package com.elcom.data.interview.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the LEVEL database table.
 * 
 */
@Entity
@Table(name = "level")
public class Level implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CAREER_ID")
	private Long careerId;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CREATED_AT", updatable = false)
	private Timestamp createdAt;
	
	public Level() {
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

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Long getCareerId() {
		return careerId;
	}

	public void setCareerId(Long careerId) {
		this.careerId = careerId;
	}
}