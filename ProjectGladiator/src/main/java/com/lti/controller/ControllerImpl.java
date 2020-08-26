package com.lti.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AllVehiclePolicies;
import com.lti.dto.BuyTravelInsuranceDto;
import com.lti.dto.BuyVehicleInsuranceDto;
import com.lti.dto.CheckDto;
import com.lti.dto.ClaimDto;
import com.lti.dto.DashDto;
import com.lti.dto.ForgotPasswordDto;
import com.lti.dto.LoginDto;
import com.lti.dto.NewClaimStatusDto;
import com.lti.dto.NewInsuranceStatusDto;
import com.lti.dto.PolicyIdObject;
import com.lti.dto.RenewObject;
import com.lti.dto.SendId;
import com.lti.dto.SendVehicleId;
import com.lti.dto.ShowDto;
import com.lti.dto.UserDto;
import com.lti.exception.ServiceException;
import com.lti.model.ClaimDetails;
import com.lti.model.TravelDetails;
import com.lti.model.TravelInsuranceDetails;
import com.lti.model.TravelInsurancePlan;
import com.lti.model.UserDetails;
import com.lti.model.VehicleDetails;
import com.lti.model.VehicleInsuranceDetails;
import com.lti.model.VehicleInsurancePlan;
import com.lti.service.ProjectService;
import com.lti.status.ClaimStatus;
import com.lti.status.ClaimStatus.ClaimStatusType;
import com.lti.status.EmailStatus;
import com.lti.status.LoginStatus;
import com.lti.status.Status;
import com.lti.status.Status.StatusType;
import com.lti.utility.EmailUtility;

@RestController
@CrossOrigin
public class ControllerImpl {

	@Autowired
	ProjectService service;

	@Autowired
	private MailSender mailSender;

	@PostMapping("/buyVehicleInsurance")
	public SendId buyVehicleInsurance(@RequestBody BuyVehicleInsuranceDto buyvehicleinsurancedto) {
		SendId sendId = new SendId();
		VehicleInsuranceDetails vid = new VehicleInsuranceDetails();
		vid.setInsurancePremium(buyvehicleinsurancedto.getInsurancePremium());
		LocalDate date = LocalDate.now().minusDays(1);
		vid.setInsuranceDuration(date.plusYears(buyvehicleinsurancedto.getDuration()));
		vid.setInsuranceStatus("Pending");
		UserDetails user = service.findUserById(buyvehicleinsurancedto.getUserId());
		sendId.setId(service.buyVehicleInsurance(buyvehicleinsurancedto.getUserId(),
				buyvehicleinsurancedto.getRegistrationNumber(), buyvehicleinsurancedto.getInsurancePlanId(), vid));
		EmailUtility eutility = new EmailUtility();
		eutility.BuyVehicleInsuraceEmail(mailSender, buyvehicleinsurancedto, user, sendId.getId());
		return sendId;
	}

	@PostMapping("/buyTravelInsurance")
	public SendId buyTravelInsurance(@RequestBody BuyTravelInsuranceDto buytravelinsurancedto) {
		SendId sendId = new SendId();
		TravelInsuranceDetails tid = new TravelInsuranceDetails();
		tid.setInsurancePremium(buytravelinsurancedto.getInsurancePremium());
		tid.setInsuranceDuration(buytravelinsurancedto.getInsuranceDuration());
		tid.setInsuranceStatus("Pending");
		UserDetails user = service.findUserById(buytravelinsurancedto.getUserId());
		sendId.setId(service.buyTravelInsurance(buytravelinsurancedto.getUserId(), buytravelinsurancedto.getTravelId(),
				buytravelinsurancedto.getInsurancePlanId(), tid));
		EmailUtility eutility = new EmailUtility();
		eutility.BuyTravelInsuraceEmail(mailSender, buytravelinsurancedto, user, sendId.getId());
		return sendId;
	}

	@PostMapping("/addVehicleDetails")
	public Status addVehicleDetails(@RequestBody VehicleDetails vehicle) {
		Status status = new Status();
		try {
			if (service.checkIfVehicleExist(vehicle.getRegistrationNumber())) {
				status.setMessage("Vehicle is not registered");
				status.setStatus(StatusType.SUCCESS);
			}
		} catch (ServiceException e) {
			status.setMessage(e.getMessage());
			status.setStatus(StatusType.FAILURE);
		}
		return status;
	}

	@PostMapping("/addTravelDetails")
	public SendId addTravelDetails(@RequestBody TravelDetails travel) {
		SendId sendId = new SendId();
		sendId.setId(service.addTravelDetails(travel));
		return sendId;
	}

	@GetMapping("/showVehicleInsurancePlan")
	public List<VehicleInsurancePlan> showVehicleInsurancePlan() {
		return service.showVehicleInsurancePlan();
	}

	@GetMapping("/showTravelInsurancePlan")
	public List<TravelInsurancePlan> showTravelInsurancePlan() {
		return service.showTravelInsurancePlan();
	}

	@GetMapping("/showAllClaimDetails")
	public List<ClaimDetails> showAllClaimDetails() {
		return service.showAllClaimDetails();
	}

	@GetMapping("/showTravelInsuranceDetails")
	public List<TravelInsuranceDetails> showTravelInsuranceDetails() {
		return service.showTravelInsuranceDetails();
	}

	@GetMapping("/showVehicleInsuranceDetails")
	public List<VehicleInsuranceDetails> showVehicleInsuranceDetails() {
		return service.showVehicleInsuranceDetails();
	}

	@PostMapping("/updateVehicleInsuranceStatus")
	public void updateVehicleInsuranceStatus(@RequestBody NewInsuranceStatusDto dto) {
		service.updateVehicleInsuranceStatus(dto.getInsurancePolicyId(), dto.getStatus());

	}

	@PostMapping("/updateTravelInsuranceStatus")
	public void updateTravelInsuranceStatus(@RequestBody NewInsuranceStatusDto dto) {
		service.updateTravelInsuranceStatus(dto.getInsurancePolicyId(), dto.getStatus());
	}

	@PostMapping("/updateClaimStatus")
	public void updateClaimStatus(@RequestBody NewClaimStatusDto dto) {
		service.updateClaimStatus(dto.getClaimId(), dto.getClaimAmount(), dto.getStatus());

	}

	@PostMapping("/register")
	public Status register(@RequestBody UserDto userDto) {
		try {
			UserDetails userDetails = new UserDetails();
			BeanUtils.copyProperties(userDto, userDetails);
			int id = service.register(userDetails);
			Status status = new Status();
			status.setUserId(id);
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration successfull");
			EmailUtility util = new EmailUtility();
			util.sendRegisterEmail(mailSender, userDetails, id);
			return status;
		} catch (ServiceException e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());

			return status;
		}

	}

	@PostMapping("/login")
	public LoginStatus login(@RequestBody LoginDto loginDto) {
		try {
			UserDetails userDetails = service.login(loginDto.getUserId(), loginDto.getUserPassword());
			LoginStatus loginStatus = new LoginStatus();

			loginStatus.setStatus(StatusType.SUCCESS);
			loginStatus.setUserId(userDetails.getUserId());
			loginStatus.setUserName(userDetails.getUserName());
			loginStatus.setMessage("You are successfully logged in!");
			return loginStatus;

		} catch (ServiceException e) {
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}

	@PostMapping(path = "/checkPolicyIdForUserId")
	public ClaimStatus checkPolicyIdForUserId(@RequestBody ClaimDto claimdto) {
		ClaimStatus status = new ClaimStatus();
		List<VehicleInsuranceDetails> vid = service.showUserVehicleInsurance(claimdto.getUserId());

		for (VehicleInsuranceDetails details : vid) {
			if (details.getInsurancePolicyId() == claimdto.getPolicyId()) {
				long d = service.checkVehicleClaimOnBasisOfStatus(claimdto.getPolicyId());
				if (d >= 1) {
					status.setStatus(ClaimStatusType.Failure);
					status.setMessage("Claim Already registered");
					return status;
				}
				status.setStatus(ClaimStatusType.Success);
				status.setMessage("OK");
				return status;
			}
		}
		List<TravelInsuranceDetails> tid = service.showUserTravelInsurance(claimdto.getUserId());

		for (TravelInsuranceDetails trvdetails : tid) {
			if (trvdetails.getInsurancePolicyId() == claimdto.getPolicyId()) {
				long t = service.checkTravelClaimOnBasisOfStatus(claimdto.getPolicyId());
				if (t >= 1) {
					status.setStatus(ClaimStatusType.Failure);
					status.setMessage("Claim Already registered");
					return status;
				}
				status.setStatus(ClaimStatusType.Success);
				status.setMessage("OK");
				return status;
			}
		}
		status.setStatus(ClaimStatusType.Failure);
		status.setMessage("This Policy does not exist for this User");
		return status;
	}

	@PostMapping("/addClaimForUser")
	public ClaimStatus addClaimForUser(@RequestBody ClaimDto claimDto) {

		ClaimDetails claimDetails = new ClaimDetails();
		claimDetails.setDateOfIncident(claimDto.getDateOfIncident());
		claimDetails.setClaimDate(claimDto.getClaimDate());
		claimDetails.setReason(claimDto.getReason());
		claimDetails.setElaborateReason(claimDto.getElaborateReason());
		claimDetails.setStatus("Pending");
		if (service.addClaim(claimDto.getPolicyId(), claimDetails) != 0) {
			ClaimStatus status = new ClaimStatus();
			status.setStatus(ClaimStatusType.Success);
			status.setMessage("OK");
			status.setClaimId(claimDetails.getClaimId());

			UserDetails user = service.findUserById(claimDto.getUserId());
			EmailUtility util = new EmailUtility();
			util.sendClaimRegisterEmail(mailSender, user.getUserEmail(), claimDetails.getClaimId());
			return status;

		} else {
			ClaimStatus status = new ClaimStatus();
			status.setStatus(ClaimStatusType.Failure);
			status.setMessage("Fail");
			return status;
		}

	}

	@PostMapping("/listAllClaimDetailsForUser")
	public List<ClaimDetails> listAllClaimDetailsForUser(@RequestBody ShowDto showdto) {
		System.out.println(showdto.getUserId());
		System.out.println(service.getAllClaim(showdto.getUserId()));
		return service.getAllClaim(showdto.getUserId());
	}

	@PostMapping("/findUserByPolicyId")
	public RenewObject findUserByPolicyId(@RequestBody PolicyIdObject pio) {
		RenewObject renew = new RenewObject();
		renew.setUserName(service.findUserByPolicyId(pio.getInsurancePolicyId()));
		renew.setPolicyType(service.findInsuranceByPolicyId(pio.getInsurancePolicyId()));
		return renew;

	}

	@PostMapping("/renewInsurance")
	public LocalDate renewInsurance(@RequestBody PolicyIdObject pio) {
		VehicleInsuranceDetails vehicle = service.renewInsurance(pio.getInsurancePolicyId(),
				pio.getInsuranceDuration());
		UserDetails user = service.findUserByVehiclePolicyId(vehicle.getInsurancePolicyId());

		EmailUtility email = new EmailUtility();
		email.sendRenewEmail(mailSender, vehicle, user);
		
		return vehicle.getInsuranceDuration();
	}

	@GetMapping("/getAllVehiclePolicies")
	public List<Object> getAllVehiclePolicies() {
		return service.getAllVehiclePolicies();
	}

	@GetMapping("/getAllTravelPolicies")
	public List<Object> getAllTravelPolicies() {
		return service.getAllTravelPolicies();
	}

	@PostMapping("/checkVehiclePolicyId")
	public boolean checkVehiclePolicyId(@RequestBody PolicyIdObject pio) {
		return service.checkVehiclePolicyId(pio.getInsurancePolicyId());
	}
	
	@PostMapping("/checkIfVehiclePolicyRenewable")
	public boolean checkIfVehiclePolicyRenewable(@RequestBody PolicyIdObject pio) {
		 if(service.checkIfRenewable(pio.getInsurancePolicyId()))
			 return true;
		 return false;
	}

	@PostMapping("/getAllInsurance")
	public List<Object> getAllinsurance(@RequestBody DashDto dashDto) {
		List<Object> list = new ArrayList<>();
		List<VehicleInsuranceDetails> vl = service.showUserVehicleInsurance(dashDto.getUserId());
		List<TravelInsuranceDetails> tl = service.showUserTravelInsurance(dashDto.getUserId());
		list.addAll(tl);
		list.addAll(vl);
		return list;
	}

	@PostMapping("/verifyEmail")
	public EmailStatus verifyEmail(@RequestBody ForgotPasswordDto forgotPasswordDto) {
		int otp = 0;
		try {
			otp = service.findByEmailforOTP(forgotPasswordDto.getUserEmail());
			EmailStatus emailStatus = new EmailStatus();
			EmailUtility emailUtility = new EmailUtility();
			emailUtility.sendOtpEmail(mailSender, otp, forgotPasswordDto.getUserEmail());
			emailStatus.setUserOTP(otp);
			emailStatus.setStatus(StatusType.SUCCESS);
			return emailStatus;
		} catch (ServiceException e) {
			EmailStatus status = new EmailStatus();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}

	}

	@PostMapping("/forgotPassword")
	public Status forgotPassword(@RequestBody ForgotPasswordDto forgotPassworddto) {

		try {
			service.forgotPassword(forgotPassworddto.getUserEmail(), forgotPassworddto.getnewUserPassword());
			EmailStatus emailStatus = new EmailStatus();
			EmailUtility emailUtility = new EmailUtility();
			emailUtility.resetPasswordEmail(mailSender, forgotPassworddto.getUserEmail());
			emailStatus.setStatus(StatusType.SUCCESS);
			return emailStatus;
		} catch (ServiceException e) {
			EmailStatus status = new EmailStatus();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@PostMapping("/getAllVehiclepoliciesByUserId")
	public List<Object> getAllVehiclePoliciesByUserId(@RequestBody DashDto dashDto) {
		return service.getAllVehiclePoliciesByUserId(dashDto.getUserId());
	}

	@PostMapping("/getTravelpoliciesByUserId")
	public List<Object> getAllTravelPoliciesByUserId(@RequestBody DashDto dashDto) {
		return service.getAllTravelPoliciesByUserId(dashDto.getUserId());
	}

	@PostMapping("/findPolicyIdByUserId")
	public Status findPolicyIdByUserId(@RequestBody PolicyIdObject pio) {
		Status st = new Status();
		boolean result = service.findPolicyIdByUserId(pio.getInsurancePolicyId(), pio.getUserId());
		if (result == true) {
			st.setStatus(StatusType.SUCCESS);
		} else {
			st.setStatus(StatusType.FAILURE);
		}
		return st;
	}

}
