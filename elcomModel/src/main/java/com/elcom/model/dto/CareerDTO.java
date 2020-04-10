package com.elcom.model.dto;

import java.io.Serializable;

public class CareerDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5113310993905485424L;
	
	private Long id;
	private String name;
	
	public CareerDTO() {
	}
	
	public CareerDTO(Long id, String name) {
		this.id = id;
		this.name = name;
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
}
