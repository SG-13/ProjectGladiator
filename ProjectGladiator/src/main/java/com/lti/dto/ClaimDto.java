package com.lti.dto;

import java.time.LocalDate;

public class ClaimDto {
  
  
  private int policyId;
  private LocalDate dateOfIncident;
  private LocalDate claimDate;
  private String reason;
  private String elaborateReason;
  private int userId;
  private String status;
  private double claimAmount;
  
public int getPolicyId() {
	return policyId;
}
public void setPolicyId(int policyId) {
	this.policyId = policyId;
}
public LocalDate getDateOfIncident() {
	return dateOfIncident;
}
public void setDateOfIncident(LocalDate dateOfIncident) {
	this.dateOfIncident = dateOfIncident;
}
public LocalDate getClaimDate() {
	return claimDate;
}
public void setClaimDate(LocalDate claimDate) {
	this.claimDate = claimDate;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public String getElaborateReason() {
	return elaborateReason;
}
public void setElaborateReason(String elaborateReason) {
	this.elaborateReason = elaborateReason;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

public double getClaimAmount() {
	return claimAmount;
}
public void setClaimAmount(double claimAmount) {
	this.claimAmount = claimAmount;
}




}
