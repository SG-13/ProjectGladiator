package com.lti.dto;

public class ForgotPasswordDto 
{
	private String userEmail;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	private int userOTP;
	private String newUserPassword;
	private String newUserPasswordConfirm;
	public int getUserOTP() {
		return userOTP;
	}
	public void setUserOTP(int userOTP) {
		this.userOTP = userOTP;
	}
	public String getnewUserPassword() {
		return newUserPassword;
	}
	public void setnewUserPassword(String newUserPassword) {
		this.newUserPassword = newUserPassword;
	}
	public String getNewUserPasswordConfirm() {
		return newUserPasswordConfirm;
	}
	public void setNewUserPasswordConfirm(String newUserPasswordConfirm) {
		this.newUserPasswordConfirm = newUserPasswordConfirm;
	}
	

}
