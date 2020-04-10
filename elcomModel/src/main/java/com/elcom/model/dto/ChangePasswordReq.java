package com.elcom.model.dto;

import java.io.Serializable;

public class ChangePasswordReq implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5113310993905485424L;
	
	private String uuid;
	private String currentPassword;
	private String newPassword;
	private String changeType;

	public ChangePasswordReq() {
	}
	
	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
