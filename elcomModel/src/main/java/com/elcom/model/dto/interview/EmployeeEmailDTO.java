package com.elcom.model.dto.interview;

import java.io.Serializable;

public class EmployeeEmailDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3899286802336154005L;
	
	private Long id;
	private String email;
	
	public EmployeeEmailDTO() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
