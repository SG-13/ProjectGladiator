package com.lti.utility;


import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.lti.dto.BuyTravelInsuranceDto;
import com.lti.dto.BuyVehicleInsuranceDto;
import com.lti.model.UserDetails;
import com.lti.model.VehicleInsuranceDetails;

public class EmailUtility {

	// private String sentBy = "Lti.insurance@outlook.com";
	private String sentBy = "sharmautkarsh815@outlook.com";

	public void sendRegisterEmail(MailSender mailSender, UserDetails userDetails, int id) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sentBy);
		message.setTo(userDetails.getUserEmail());
		message.setSubject("Registration Successfull");
		message.setText("Dear " + userDetails.getUserName() + "!" + "\n\n" + "You are successfully registered." + "\n"
				+ "This is your user Id " + id + ". Use it for the future refrences." + "\n\n" + "Have a good day."
				+ "\n" + "LTI Insurance");
		mailSender.send(message);
	}

	public void BuyVehicleInsuraceEmail(MailSender mailSender, BuyVehicleInsuranceDto dto, UserDetails user, int id) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sentBy);
		message.setTo(user.getUserEmail());
		message.setSubject("Application Recieved");
		message.setText("Dear " + user.getUserName() + "," + "\n\n" + "You have successfully Applied insurance for "
				+ dto.getRegistrationNumber() + "\n" + "Your policy ID for this application is " + id
				+ ". Use it for the future refrences." + "\n\n" + "Have a good day." + "\n" + "LTI Insurance");
		mailSender.send(message);
	}

	public void BuyTravelInsuraceEmail(MailSender mailSender, BuyTravelInsuranceDto dto, UserDetails user, int id) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sentBy);
		message.setTo(user.getUserEmail());
		message.setSubject("Application Recieved");
		message.setText("Dear " + user.getUserName() + "," + "\n\n"
				+ "You have successfully Applied insurance for your travel. " + "\n"
				+ "Your policy ID for this application is " + id + ". Use it for the future refrences." + "\n\n"
				+ "Have a good day." + "\n" + "LTI Insurance");
		mailSender.send(message);
	}

	public void sendRenewEmail(MailSender mailSender, VehicleInsuranceDetails vehicle, UserDetails userDetails) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(sentBy);
		message.setTo(userDetails.getUserEmail());
		message.setText("Dear " + userDetails.getUserName() + "!" + "\n\n" + "Your insurance policy"
				+ vehicle.getInsurancePolicyId() + " ." + "is successfully renewed" + "\n" + "This is your user Id "
				+ userDetails.getUserId() + " policy  is being extended till " + vehicle.getInsuranceDuration() + "."
				+ "\n\n" + "Have a good day." + "\n" + "LTI Insurance");
		mailSender.send(message);

	}

	public void sendClaimRegisterEmail(MailSender mailSender, String email, int id) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(sentBy);
		message.setTo(email);
		System.out.println(email);
		message.setSubject("Registration Successfull");
		message.setText("Dear User!" + "\n\n" + "You have successfully Registered your Claim." + "\n"
				+ "This is your user Id " + id + ". Use it for the future refrences." + "\n\n" + "Have a good day."
				+ "\n" + "LTI Insurance");
		mailSender.send(message);
	}

	public void sendOtpEmail(MailSender mailSender, int otp, String userEmail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sentBy);
		message.setTo(userEmail);
		message.setSubject("Verify Email");
		message.setText("Dear User!" + "\n\n" + "You have successfully verified your registered email." + "\n"
				+ "This is your otp " + otp + ". Use it to reset your password." + "\n\n" + "Have a good day."
				+ "\n" + "LTI Insurance");
		mailSender.send(message);

	}

	public void resetPasswordEmail(MailSender mailSender, String userEmail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sentBy);
		message.setTo(userEmail);
		message.setSubject(" Password updated");
		message.setText("Dear User!" + "\n\n" + "Your password has been successfully resetted." + "\n\n" + "Have a good day."
				+ "\n" + "LTI Insurance");
		mailSender.send(message);
	}

}
