package com.sip.demo.security_role_demo.model;

import lombok.Data;

@Data
public class ChgPasswordForm {

	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
	
	
	public ChgPasswordForm() {
		super();
	}
	public ChgPasswordForm(String currentPassword, String newPassword, String confirmPassword) {
		super();
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	@Override
	public String toString() {
		return "ChgPasswordForm [currentPassword=" + currentPassword + ", newPassword=" + newPassword
				+ ", confirmPassword=" + confirmPassword + "]";
	}
	
}
