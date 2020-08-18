package com.lti.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.model.ClaimDetails;
import com.lti.model.TravelDetails;
import com.lti.model.TravelInsuranceDetails;
import com.lti.model.TravelInsurancePlan;
import com.lti.model.UserDetails;
import com.lti.model.VehicleDetails;
import com.lti.model.VehicleInsuranceDetails;
import com.lti.model.VehicleInsurancePlan;

@Repository
public class RepositoryImpl implements ProjectRepository {

	@PersistenceContext
	EntityManager em;
    
	@Transactional
	public int addNewUser(UserDetails user) {
		System.out.println(user);
		em.persist(user); 
		   int userId=user.getUserId();
		   System.out.println("User added"); 
		   return userId; 
	}

	public int isValidUser(int userId, String password) {
		UserDetails user=em.find(UserDetails.class, userId);
		
		if(user!=null && user.getUserPassword().equals(password))
			return userId;
		
		return 0;
	}

	@Transactional
	public int buyVehicleInsurance(int userId, String regno, int planId, VehicleInsuranceDetails vid) {
		UserDetails user = em.find(UserDetails.class, userId);
		VehicleDetails vd = em.find(VehicleDetails.class, regno);
		VehicleInsurancePlan vip = em.find(VehicleInsurancePlan.class, planId);
		List<VehicleInsuranceDetails> vidlist = new ArrayList<VehicleInsuranceDetails>();
		vidlist.add(vid);
		
		//System.out.println(vip);
		
		user.setVechileinsurancedetails(vidlist);
		vd.setVehicleinsurancedetails(vid);
		vip.setVehicleinsurancedetails(vid);
		
		vid.setUser(user);
		vid.setVehicledetails(vd);
		vid.setVehicleinsuranceplan(vip);
		
		
		em.persist(user); 
		   int policyId=vid.getInsurancePolicyId(); 
		   System.out.println("Vehicle Insurance added"); 
		   return policyId; 
		
	}
	
	@Transactional
	public int buyTravelInsurance(int userId, int travelId, int planId, TravelInsuranceDetails tid) {
		UserDetails user = em.find(UserDetails.class, userId);
		TravelDetails td = em.find(TravelDetails.class, travelId);
		TravelInsurancePlan tip = em.find(TravelInsurancePlan.class, planId);
		List<TravelInsuranceDetails> tidlist = new ArrayList<TravelInsuranceDetails>();
		tidlist.add(tid);
		
		user.setTravelinsurancedetails(tidlist);
		td.setTravelinsurancedetails(tid);
		tip.setTravelinsurancedetails(tid);
		
		tid.setUser(user);
		tid.setTraveldetails(td);
		tid.setTravelinsuranceplan(tip);
		
		em.persist(user); 
		   int policyId=tid.getInsurancePolicyId(); 
		   System.out.println("Travel Insurance added"); 
		   return policyId; 
	}

	@Transactional
	public int renewInsurance(int policyId, int duration) {
		VehicleInsuranceDetails vid=em.find(VehicleInsuranceDetails.class, policyId);
		
		LocalDate date=vid.getInsuranceDuration();
		vid.setInsuranceDuration(date.plusYears(duration));
		em.merge(vid);
		
		return policyId;
	}

	@Transactional
	public String addVehicleDetails(VehicleDetails vehicle) {
		em.persist(vehicle); 
		   String Rgno = vehicle.getRegistrationNumber(); 
		   System.out.println("Vehicle details Added"); 
		   return Rgno;
	}
	
	@Transactional
	public int addTravelDetails(TravelDetails travel) {
		em.persist(travel); 
		   int travelid = travel.getTravelId(); 
		   System.out.println("Travel details Added"); 
		   return travelid;
	}
	

	public List<VehicleInsurancePlan> showVehicleInsurancePlan() {
		  String sql="select vip from VehicleInsurancePlan vip";
	        Query qry=em.createQuery(sql);
	        
	        List<VehicleInsurancePlan> vehicleinsuranceplans=qry.getResultList();
	        return vehicleinsuranceplans;
		
	}

	public List<TravelInsurancePlan> showTravelInsurancePlan() {
		String sql="select tip from TravelInsurancePlan tip";
        Query qry=em.createQuery(sql);
        
        List<TravelInsurancePlan> travelinsuranceplans=qry.getResultList();
        return travelinsuranceplans;
	}

	@Transactional
	public int addVehicleClaimdetails(int policyId, ClaimDetails claimdetails) {
		
		VehicleInsuranceDetails vid =em.find(VehicleInsuranceDetails.class, policyId);
		claimdetails.setVehicleinsurancedetails(vid);
		List<ClaimDetails> cd = new ArrayList<ClaimDetails>();
		cd.add(claimdetails);
		vid.setClaimdetails(cd);
		
		em.persist(claimdetails); 
		   int claimId=claimdetails.getClaimId(); 
		   System.out.println("Vehicle Claim Registered"); 
		   return claimId; 
	}

	@Transactional
	public int addTravelClaimdetails(int policyId, ClaimDetails claimdetails) {
		TravelInsuranceDetails tid =em.find(TravelInsuranceDetails.class, policyId);
		
		claimdetails.setTravelinsurancedetails(tid);
		List<ClaimDetails> cd = new ArrayList<ClaimDetails>();
		cd.add(claimdetails);
		tid.setClaimdetails(cd);
		
		em.persist(claimdetails); 
		   int claimId=claimdetails.getClaimId(); 
		   System.out.println("Travel Claim Registered"); 
		   return claimId;
	}
	
	public boolean checkVehiclePolicyId(int policyId) {
		VehicleInsuranceDetails vid=em.find(VehicleInsuranceDetails.class, policyId);
		if(vid!=null)
			return true;
		return false;
	}
	
	public boolean checkTravelPolicyId(int policyId) {
		TravelInsuranceDetails tid=em.find(TravelInsuranceDetails.class, policyId);
		if(tid!=null)
			return true;
		return false;
	}

	public ClaimDetails showClaimDetails(int claimId) {
		ClaimDetails cd=em.find(ClaimDetails.class, claimId);
        if(cd!=null)
        	return cd;
        return null;
	}

	public List<ClaimDetails> showAllClaimDetails() {
		String sql="select cd from ClaimDetails cd";
        Query qry=em.createQuery(sql);
        
        List<ClaimDetails> claimdetails=qry.getResultList();
        return claimdetails;
	}

	public List<TravelInsuranceDetails> showTravelInsuranceDetails() {
		String sql="select tid from TravelInsuranceDetails tid";
        Query qry=em.createQuery(sql);
        
        List<TravelInsuranceDetails> tid=qry.getResultList();
        return tid;
	}

	public List<VehicleInsuranceDetails> showVehicleInsuranceDetails() {
		String sql="select vid from VehicleInsuranceDetails vid";
        Query qry=em.createQuery(sql);
        
        List<VehicleInsuranceDetails> vid=qry.getResultList();
        return vid;
	}

	public List<VehicleInsuranceDetails> showUserVehicleInsurance(int userId) {
		String sql="select vid from VehicleInsuranceDetails vid where vid.user.userId=:uid";
        Query qry=em.createQuery(sql);
        qry.setParameter("uid", userId);
        
        List<VehicleInsuranceDetails> vid=qry.getResultList();
        return vid;
	}

	public List<TravelInsuranceDetails> showUserTravelInsurance(int userId) {
		String sql="select tid from TravelInsuranceDetails tid where tid.user.userId=:uid";
        Query qry=em.createQuery(sql);
        qry.setParameter("uid", userId);
        
        List<TravelInsuranceDetails> tid=qry.getResultList();
        return tid;
	}

	@Transactional
	public void updateVehicleInsuranceStatus(int policyId, String status) {
		
		VehicleInsuranceDetails vid = em.find(VehicleInsuranceDetails.class, policyId);
		vid.setInsuranceStatus(status);
		
	}
	
	@Transactional
	public void updateTravelInsuranceStatus(int policyId, String status) {
		TravelInsuranceDetails tid = em.find(TravelInsuranceDetails.class, policyId);
		tid.setInsuranceStatus(status);

	}

	@Transactional
	public void updateClaimStatus(int claimId, String status) {
		ClaimDetails cd = em.find(ClaimDetails.class, claimId);
		cd.setStatus(status);
	}
	
	@Transactional
	public int addVehicleInsurancePlan(VehicleInsurancePlan vip)
	{
		em.persist(vip); 
		   int Id=vip.getInsurancePlanId();
		   System.out.println("Vehicle insurance plan added"); 
		   return Id; 	
	}	
	
	@Transactional
	public int addTravelInsurancePlan(TravelInsurancePlan tip)
	{
		em.persist(tip); 
		   int Id=tip.getInsurancePlanId();
		   System.out.println("Travel insurance plan added"); 
		   return Id; 	
	}	
	

}
