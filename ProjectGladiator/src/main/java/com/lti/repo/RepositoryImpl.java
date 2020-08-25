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

import com.lti.dto.AllVehiclePolicies;
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
		//System.out.println(user.getUserPassword());
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
		vip.setVehicleinsurancedetails(vidlist);
		
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
		tip.setTravelinsurancedetails(tidlist);
		
		tid.setUser(user);
		tid.setTraveldetails(td);
		tid.setTravelinsuranceplan(tip);
		
		em.persist(user); 
		   int policyId=tid.getInsurancePolicyId(); 
		   System.out.println("Travel Insurance added"); 
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
	public void updateClaimStatus(int claimId, double claimAmount, String status) {
		ClaimDetails cd = em.find(ClaimDetails.class, claimId);
		cd.setStatus(status);
		cd.setClaimAmount(claimAmount);
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

	@Override
	@Transactional
	public boolean checkVehicleExist(String registrationNumber) {
		return  (Long) em.createQuery("select count(vd.registrationNumber) from VehicleDetails vd where vd.registrationNumber=:rn")
				.setParameter("rn",registrationNumber)
				.getSingleResult()==1 ? true : false;
	}
	
	
	@Override
	public UserDetails findById(int userId) 
	{
		return em.find(UserDetails.class, userId);
	}
	
	@Override
	public List<UserDetails> findAll() 
	{
		return em.createNamedQuery("fetch-all").getResultList();
	}
	
	@Override
	public boolean findByEmail(String email) 
	{
		return (long)em
				.createQuery("select count(userDetails.userId) from UserDetails userDetails where userDetails.userEmail=:em ")
				.setParameter("em", email)
				.getSingleResult() == 1? true : false;
		
	}
	
	@Override	
	public int findByIdAndPassword(int userId, String password) {
		System.out.println(userId);
		System.out.println(password);
		
		int a=(int)em.createQuery("select u.userId from UserDetails u where u.userId=:id and u.userPassword=:pass")
				.setParameter("id", userId)
				.setParameter("pass", password)
				.getSingleResult();
		System.out.println(a);
		return a;
	}
	
	
	@Transactional
	public boolean checkPolicyId(int policyId) {
		VehicleInsuranceDetails vid=em.find(VehicleInsuranceDetails.class, policyId);
		TravelInsuranceDetails tid=em.find(TravelInsuranceDetails.class, policyId);
		if(vid!=null||tid!=null)
			return true;
		return false;
	}


	
	
	public List<ClaimDetails> getAllClaim(int userId) {
		/*
		 * List<ClaimDetails> obj=new ArrayList<ClaimDetails>(); //List<ClaimDetails>
		 * allClaim = new ArrayList<ClaimDetails>();
		 * 
		 * UserDetails userObj = em.find(UserDetails.class,userId);
		 * System.out.println("Email is "+userObj.getUserEmail());
		 * 
		 * List<TravelInsuranceDetails> list = userObj.getTravelinsurancedetails();
		 * System.out.println(list.size());
		 * 
		 * for(TravelInsuranceDetails policyNum : list) { String hql =
		 * "select I from TravelInsuranceDetails I where I.insurancePolicyId=:number";
		 * Query query = em.createQuery(hql);
		 * query.setParameter("number",policyNum.getInsurancePolicyId());
		 * List<TravelInsuranceDetails> allInsurance = query.getResultList();
		 * 
		 * for(TravelInsuranceDetails ins:allInsurance) { obj = ins.getClaimdetails();
		 * //allClaim.add(obj); }
		 * 
		 * }
		 * 
		 * List<VehicleInsuranceDetails> list1 = userObj.getVechileinsurancedetails();
		 * System.out.println(list1.size());
		 * 
		 * for(VehicleInsuranceDetails policyNum:list1) { String hql =
		 * "select V from VehicleInsuranceDetails V where V.insurancePolicyId=:number";
		 * Query query = em.createQuery(hql);
		 * query.setParameter("number",policyNum.getInsurancePolicyId());
		 * List<VehicleInsuranceDetails> allInsurance = query.getResultList();
		 * 
		 * for(VehicleInsuranceDetails ins:allInsurance) { obj = ins.getClaimdetails();
		 * //allClaim.add(obj); }
		 * 
		 * } return obj;
		 */
		String hql = "select cd from ClaimDetails cd where cd.travelinsurancedetails.insurancePolicyId IN (select td from TravelInsuranceDetails td where td.user.userId=:u)";
		Query query = em.createQuery(hql);
		query.setParameter("u",userId);
		List<ClaimDetails> obj=query.getResultList();
		System.out.println(obj);
		
		String hql1 = "select cd1 from ClaimDetails cd1 where cd1.vehicleinsurancedetails.insurancePolicyId IN (select vd from VehicleInsuranceDetails vd where vd.user.userId=:n)";
		Query query2 = em.createQuery(hql1);
		query2.setParameter("n", userId);
		List<ClaimDetails> obj1 = query2.getResultList();
		
		for(ClaimDetails cd : obj) {
			obj1.add(cd);
		}
		
		return obj1;
		
	}
	
	
	@Override
	@Transactional
	public String findUserByPolicyId(int insurancePolicyId) {

		VehicleInsuranceDetails vid= em.find(VehicleInsuranceDetails.class, insurancePolicyId);
		// System.out.println(vid.getUser()); 
		UserDetails user=vid.getUser(); 
		/* System.out.println(user.getUserName()); */
		return user.getUserName();
	}

	@Override
	@Transactional
	public String findInsuranceByPolicyId(int insurancePolicyId) {
		
		VehicleInsuranceDetails vid= em.find(VehicleInsuranceDetails.class, insurancePolicyId);
		/* System.out.println(vid.getUser()); */
		VehicleInsurancePlan plan=vid.getVehicleinsuranceplan(); 
		/* System.out.println(user.getUserName()); */
		return plan.getInsurancePlan();
	
	}
	
	@Override
	@Transactional
	public VehicleInsuranceDetails renewInsurance(int insurancePolicyId, int insuranceDuration) {
		VehicleInsuranceDetails vid = em.find(VehicleInsuranceDetails.class, insurancePolicyId);

		LocalDate date = vid.getInsuranceDuration().minusDays(1);
		vid.setInsuranceDuration(date.plusYears(insuranceDuration));
		em.merge(vid);

		return vid;
	}
	
	
	@Override
	public UserDetails findUserById(int userId) {
		UserDetails user = em.find(UserDetails.class, userId);
		return user;
	}
	
	@Transactional
	public List<Object> getAllVehiclePolicies() {
		String hql1 = "select vid.insurancePolicyId, vid.insuranceDuration, vid.insurancePremium, vid.insuranceStatus, vid.user.userId , vid.vehicledetails.registrationNumber, vid.vehicleinsuranceplan.insurancePlanId from VehicleInsuranceDetails vid";
		Query query1 = em.createQuery(hql1);
		return query1.getResultList();

	}
	
	@Transactional
	public List<Object> getAllTravelPolicies() {
		String hql1 = "select tid.insurancePolicyId, tid.insuranceDuration, tid.insurancePremium, tid.insuranceStatus, tid.user.userId , tid.traveldetails.travelId, tid.travelinsuranceplan.insurancePlanId from TravelInsuranceDetails tid";
		Query query1 = em.createQuery(hql1);
		return query1.getResultList();

	}
	
	@Override
	public UserDetails findUserByVehiclePolicyId(int insurancePolicyId)
	{
		VehicleInsuranceDetails vid= em.find(VehicleInsuranceDetails.class, insurancePolicyId);
		// System.out.println(vid.getUser()); 
		UserDetails user=vid.getUser(); 
		return  user;
	}
	
	@Override
	@Transactional
	public void forgotPassword(String userEmail, String newPassword)
	{
		String sql="select ud.userId from UserDetails ud where ud.userEmail=:email";
		Query qry=em.createQuery(sql);
        qry.setParameter("email", userEmail);
        int id=(int) qry.getSingleResult();
        
        UserDetails ud=em.find(UserDetails.class, id);
        System.out.println(id);
        System.out.println(ud.toString());
        ud.setUserPassword(newPassword);
        
        em.merge(ud);
        
	}


}
