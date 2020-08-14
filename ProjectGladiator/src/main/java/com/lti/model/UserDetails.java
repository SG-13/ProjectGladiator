package com.lti.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class UserDetails {

	@Id
	@SequenceGenerator(name = "seq_userDe", initialValue = 1101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_userDe")
	private int userId;	
	@Column
	private String userName;
	@Column
	private String userEmail;
	@Column
	private LocalDate userDob;
	@Column
	private String userMobileNumber;
	@Column
	private String userAddress;
	@Column
	private String userPassword;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<VehicleInsuranceDetails> vechileinsurancedetails;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<TravelInsuranceDetails> travelinsurancedetails;

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public LocalDate getUserDob() {
		return userDob;
	}

	public void setUserDob(LocalDate userDob) {
		this.userDob = userDob;
	}

	public String getUserMobileNumber() {
		return userMobileNumber;
	}

	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<VehicleInsuranceDetails> getVechileinsurancedetails() {
		return vechileinsurancedetails;
	}

	public void setVechileinsurancedetails(List<VehicleInsuranceDetails> vechileinsurancedetails) {
		this.vechileinsurancedetails = vechileinsurancedetails;
	}

	public List<TravelInsuranceDetails> getTravelinsurancedetails() {
		return travelinsurancedetails;
	}

	public void setTravelinsurancedetails(List<TravelInsuranceDetails> travelinsurancedetails) {
		this.travelinsurancedetails = travelinsurancedetails;
	}

	
	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userDob="
				+ userDob + ", userMobileNumber=" + userMobileNumber + ", userAddress=" + userAddress
				+ ", userPassword=" + userPassword + "]";
	}
	
	
	
}
