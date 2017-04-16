package com.weddfix.web.mobile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weddfix.web.formbean.DirectoryUserProfileFormBean;
import com.weddfix.web.formbean.MyPersonalDetailsFormBean;
import com.weddfix.web.services.CommonMasterService;
import com.weddfix.web.services.RegisterService;
import com.weddfix.web.util.CommonConstants;

public class MyProfileServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	RegisterService registerService = new RegisterService();
	CommonMasterService commonMasterService = new CommonMasterService();
	LinkedHashMap<String, Object> rootMap = new LinkedHashMap<String, Object>();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LinkedHashMap<String, Object> main = new LinkedHashMap<String, Object>();
		ResourceBundle rb = ResourceBundle.getBundle("ApplicationResources");
		
        try {
        	
        	Long userId = 0L;
        	String password = null;
        	
			if(request.getParameter("userId") != null) {
				userId = Long.parseLong(request.getParameter("userId").toString());
			} 
			
			if(request.getParameter("password") != null) {
				password = request.getParameter("password").toString();
			}
			
			String update = request.getParameter("update");
			
			if(update != null) {
				if(update.equals("changePassword") && userId > 0 && password != null) {
			
					String pwdHash = CommonConstants.generateEncryptedPwd(password);
					commonMasterService.resetPassword(userId, pwdHash);
					
					rootMap.put("status", "success");
					rootMap.put("message", "Password Changed Successfully.");
					main.put("changePassword", rootMap);
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
		            String json = gson.toJson(main);
		            
		            response.setContentType("application/json;charset=utf-8");
		            byte[] out = json.getBytes("UTF-8");
		            response.setContentLength(out.length);
		            response.getOutputStream().write(out);
					return;
				}
			}
			
			if(userId > 0) {
				MyPersonalDetailsFormBean myPersonalDetails = registerService
						.loadPersonalDetails(userId);
				
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		    
				map.put("id", myPersonalDetails.getId());
				map.put("fullName",
						myPersonalDetails.getFullName());
				map.put("email", myPersonalDetails.getEmail());
				map.put("passwordHash",
						myPersonalDetails.getPasswordHash());
				map.put("mobile", myPersonalDetails.getMobile());
				map.put("countryId",
						myPersonalDetails.getCountryId());
				map.put("stateId", myPersonalDetails.getStateId());
				map.put("cityId", myPersonalDetails.getCityId());
				map.put("pincode", myPersonalDetails.getPincode());
				if (myPersonalDetails.getDescription() != null) {
					map.put("description", myPersonalDetails.getDescription());
				} else {
					map.put("description", "");
				}
				if (myPersonalDetails.getWeddingDateStr() != null) {
					map.put("weddingDateStr",
							myPersonalDetails.getWeddingDateStr());
				} else {
					map.put("weddingDateStr", "");
				}
				if (myPersonalDetails.getProfilePictureId() != null) {
					map.put("profilePictureId",
							myPersonalDetails.getWeddingDateStr());
				} else {
					map.put("profilePictureId", "");
				}
				if (myPersonalDetails.getWebsiteUrl() != null) {
					map.put("websiteUrl",
							myPersonalDetails.getWebsiteUrl());
				} else {
					map.put("websiteUrl", "");
				}
				if (myPersonalDetails.getFacebookUrl() != null) {
					map.put("facebookUrl",
							myPersonalDetails.getFacebookUrl());
				} else {
					map.put("facebookUrl", "");
				}
				if (myPersonalDetails.getTwitterUrl() != null) {
					map.put("twitterUrl",
							myPersonalDetails.getTwitterUrl());
				} else {
					map.put("twitterUrl", "");
				}
				if (myPersonalDetails.getLinkedinUrl() != null) {
					map.put("linkedinUrl",
							myPersonalDetails.getLinkedinUrl());
				} else {
					map.put("linkedinUrl", "");
				}
				if (myPersonalDetails.getPinterestUrl() != null) {
					map.put("pinterestUrl",
							myPersonalDetails.getPinterestUrl());
				} else {
					map.put("pinterestUrl", "");
				}
				if (myPersonalDetails.getInstagramUrl() != null) {
					map.put("instagramUrl",
							myPersonalDetails.getInstagramUrl());
				} else {
					map.put("instagramUrl", "");
				}
				map.put("country", myPersonalDetails.getCountry());
				map.put("state", myPersonalDetails.getState());
				map.put("city", myPersonalDetails.getCity());
				if (myPersonalDetails.getFileName() != null) {
					map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+myPersonalDetails.getFileName());
				} else {
					map.put("fileName", "");
				}
				
				main.put("myProfile", map);
				
				Map<Object, Object> countryMap = commonMasterService.loadCountry();
	            
	            LinkedList<LinkedHashMap<String, Object>> countryMapList = new LinkedList<LinkedHashMap<String, Object>>();
	            Iterator<?> itrCountryMap = countryMap.entrySet().iterator();
	            while (itrCountryMap.hasNext()) {
	                @SuppressWarnings({ "rawtypes" })
					Map.Entry pair = (Map.Entry)itrCountryMap.next();
					LinkedHashMap<String, Object> countrysMap = new LinkedHashMap<String, Object>();
					countrysMap.put("id", pair.getKey());
					countrysMap.put("countryName", pair.getValue());
					countryMapList.add(countrysMap);
					itrCountryMap.remove(); // avoids a ConcurrentModificationException
	            }
	            
	            main.put("country", countryMapList);
				
	            Gson gson = new GsonBuilder().setPrettyPrinting().create();
	            String json = gson.toJson(main);
	            
	            response.setContentType("application/json;charset=utf-8");
	            byte[] out = json.getBytes("UTF-8");
	            response.setContentLength(out.length);
	            response.getOutputStream().write(out);
	            return;
			} else {
				rootMap.put("status", "failure");
				rootMap.put("message", "Missing Parameters.");
				main.put("myProfile", rootMap);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
	            String json = gson.toJson(main);
	            
	            response.setContentType("application/json;charset=utf-8");
	            byte[] out = json.getBytes("UTF-8");
	            response.setContentLength(out.length);
	            response.getOutputStream().write(out);
	            return;
			}
			
        } catch (Exception e) {
        	rootMap.put("status", "failure");
        	rootMap.put("message", e);
			main.put("myProfile", rootMap);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(main);
            
            response.setContentType("application/json;charset=utf-8");
            byte[] out = json.getBytes("UTF-8");
            response.setContentLength(out.length);
            response.getOutputStream().write(out);
			return;
        }
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LinkedHashMap<String, Object> main = new LinkedHashMap<String, Object>();

		try {
		
        String update = request.getParameter("update");
        
		if(update != null) {
		if(update.equals("myProfile")) {
			DirectoryUserProfileFormBean userProfile = new DirectoryUserProfileFormBean();
			
			DirectoryUserProfileFormBean oldUser = registerService
					.loadUserProfileDetails(Long.parseLong(request.getParameter("id").toString()));
							
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dob = null;
			try {
				if(request.getParameter("weddingDate") != null && !request.getParameter("weddingDate").equals("")) {
					dob = sdf.parse(request.getParameter("weddingDate"));
					userProfile.setWeddingDate(dob);
				}
			} catch (ParseException e) {
				// e.printStackTrace();
			}

				userProfile.setId(Long.parseLong(request.getParameter("id").toString()));
				userProfile.setFullName(request.getParameter("fullName"));
				userProfile.setMobile(Long.parseLong(request.getParameter("mobile").toString()));
				userProfile.setCityId(Long.parseLong(request.getParameter("cityId").toString()));
				userProfile.setStateId(Long.parseLong(request.getParameter("stateId").toString()));
				userProfile.setCountryId(Long.parseLong(request.getParameter("countryId").toString()));
				userProfile.setPincode(Long.parseLong(request.getParameter("pincode").toString()));
				userProfile.setDescription(request.getParameter("description"));
				userProfile.setUpdatedBy(Long.parseLong(request.getParameter("id")));
				
				Long status = registerService
						.saveRegisterDetails(userProfile);
				
				if (status != null) {
					String code = "";
					Boolean verifyedMobileNumber = true;
					if(!userProfile.getMobile().toString().equals(oldUser.getMobile().toString())) {
						code = registerService
								.updateMobileVerificationCodeDetails(userProfile.getId());
						verifyedMobileNumber = false;
					}						
					
					rootMap.put("status", "success");
					rootMap.put("message", "Profile Updated Successfully.");
					rootMap.put("mobile", userProfile.getMobile());
					rootMap.put("verifyMobileNumber", code);
					rootMap.put("verifyedMobileNumber", verifyedMobileNumber);
					main.put("myProfile", rootMap);
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
		            String json = gson.toJson(main);
		            
		            response.setContentType("application/json;charset=utf-8");
		            byte[] out = json.getBytes("UTF-8");
		            response.setContentLength(out.length);
		            response.getOutputStream().write(out);
					return;
				} else {
					rootMap.put("status", "failure");
					rootMap.put("message", "Something went wrong. Please try again later.");
					main.put("myProfile", rootMap);
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
		            String json = gson.toJson(main);
		            
		            response.setContentType("application/json;charset=utf-8");
		            byte[] out = json.getBytes("UTF-8");
		            response.setContentLength(out.length);
		            response.getOutputStream().write(out);
					return;
				}
		}
		}
		}
		catch (Exception e) {
	    	rootMap.put("status", "failure");
			rootMap.put("message", e);
			main.put("myProfile", rootMap);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String json = gson.toJson(main);
	        
	        response.setContentType("application/json;charset=utf-8");
	        byte[] out = json.getBytes("UTF-8");
	        response.setContentLength(out.length);
	        response.getOutputStream().write(out);
			return;
	    }
	    }

}
