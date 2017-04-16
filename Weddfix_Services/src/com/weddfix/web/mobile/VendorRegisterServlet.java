package com.weddfix.web.mobile;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weddfix.web.formbean.DirectoryVendorProfileFormBean;
import com.weddfix.web.services.CommonMasterService;
import com.weddfix.web.services.RegisterService;
import com.weddfix.web.util.CommonConstants;
import com.weddfix.web.util.MailMessage;

public class VendorRegisterServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	RegisterService registerService = new RegisterService();
	CommonMasterService commonMasterService = new CommonMasterService();
	LinkedHashMap<String, Object> rootMap = new LinkedHashMap<String, Object>();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LinkedHashMap<String, Object> main = new LinkedHashMap<String, Object>();
		
		try {
			
			Long countryId = 0L;
			Long stateId = 0L;

			if(request.getParameter("country_Id") != null) {
				countryId = Long.parseLong(request.getParameter("country_Id").toString());
			}
			
			if(request.getParameter("state_Id") != null) {
				stateId = Long.parseLong(request.getParameter("state_Id").toString());
			}
			
			if(countryId > 0) {
				Map<Object, Object> stateMap = commonMasterService.loadState(countryId);
				
				LinkedList<LinkedHashMap<String, Object>> stateMapList = new LinkedList<LinkedHashMap<String, Object>>();
	            Iterator<?> itrStateMap = stateMap.entrySet().iterator();
	            while (itrStateMap.hasNext()) {
	                @SuppressWarnings({ "rawtypes" })
					Map.Entry pair = (Map.Entry)itrStateMap.next();
					LinkedHashMap<String, Object> statesMap = new LinkedHashMap<String, Object>();
					statesMap.put("id", pair.getKey());
					statesMap.put("stateName", pair.getValue());
					stateMapList.add(statesMap);
					itrStateMap.remove(); // avoids a ConcurrentModificationException
	            }
	            
				main.put("state", stateMapList);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
	            String json = gson.toJson(main);
	            
	            response.setContentType("application/json;charset=utf-8");
	            byte[] out = json.getBytes("UTF-8");
	            response.setContentLength(out.length);
	            response.getOutputStream().write(out);
				return;
			}
		
			if(stateId > 0) {
				Map<Object, Object> cityMap = commonMasterService.loadCity(stateId);
				
				LinkedList<LinkedHashMap<String, Object>> cityMapList = new LinkedList<LinkedHashMap<String, Object>>();
	            Iterator<?> itrCityMap = cityMap.entrySet().iterator();
	            while (itrCityMap.hasNext()) {
	                @SuppressWarnings({ "rawtypes" })
					Map.Entry pair = (Map.Entry)itrCityMap.next();
					LinkedHashMap<String, Object> citysMap = new LinkedHashMap<String, Object>();
					citysMap.put("id", pair.getKey());
					citysMap.put("cityName", pair.getValue());
					cityMapList.add(citysMap);
					itrCityMap.remove(); // avoids a ConcurrentModificationException
	            }
	            
				main.put("city", cityMapList);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
	            String json = gson.toJson(main);
	            
	            response.setContentType("application/json;charset=utf-8");
	            byte[] out = json.getBytes("UTF-8");
	            response.setContentLength(out.length);
	            response.getOutputStream().write(out);
				return;
			}
			
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
        }
        catch (Exception e) {
        	rootMap.put("status", "failure");
			rootMap.put("message", e);
			main.put("register", rootMap);
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
		
        String save = request.getParameter("save");
        
		if(save != null) {
		if(save.equals("register")) {
			DirectoryVendorProfileFormBean userProfile = new DirectoryVendorProfileFormBean();
			RegisterService registerService = new RegisterService();
			String pwdHash = null;
			
			userProfile.setFullName(request.getParameter("vendorName").toString());
			userProfile.setWebsiteUrl(request.getParameter("websiteUrl"));
			userProfile.setEmail(request.getParameter("email"));
			userProfile.setPassword(request.getParameter("password"));
			userProfile.setMobile(Long.parseLong(request.getParameter("mobile").toString()));
			userProfile.setCityId(Long.parseLong(request.getParameter("cityId").toString()));
			userProfile.setStateId(Long.parseLong(request.getParameter("stateId").toString()));
			userProfile.setCountryId(Long.parseLong(request.getParameter("countryId").toString()));
			userProfile.setPincode(Long.parseLong(request.getParameter("pincode").toString()));
			
			try {
				if (userProfile.getPassword() != null) {
					pwdHash = CommonConstants
							.generateEncryptedPwd(userProfile
									.getPassword());
				}
				if (pwdHash != null) {
					userProfile.setPassword(pwdHash);
					userProfile.setPasswordHash(pwdHash);
				}

				String userExist = commonMasterService.checkVendorAlreadyExist(userProfile.getEmail().toLowerCase());
				if (userExist != null) {
					rootMap.put("status", "failure");
					rootMap.put("message", userProfile.getEmail()+" User Alerady Exist.");
					main.put("register", rootMap);
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
		            String json = gson.toJson(main);
		            
		            response.setContentType("application/json;charset=utf-8");
		            byte[] out = json.getBytes("UTF-8");
		            response.setContentLength(out.length);
		            response.getOutputStream().write(out);
					return;
				} else {
					
				Long userId = registerService
						.saveVendorRegisterDetails(userProfile);
				if (userId != null) {
					rootMap.put("status", "success");
					rootMap.put("message", "Registered Successfully.");
					rootMap.put("userId", userId);
					main.put("register", rootMap);
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
		            String json = gson.toJson(main);
		            
		            Properties props = new Properties();
			        props.put("fullName", userProfile.getFullName());
		            
		            MailMessage msg = new MailMessage(props, "welcome.vm", userProfile.getEmail(), 
		            		"Welcome to Weddfix");

					msg.send();
		            
		            response.setContentType("application/json;charset=utf-8");
		            byte[] out = json.getBytes("UTF-8");
		            response.setContentLength(out.length);
		            response.getOutputStream().write(out);
					return;
				} else {
					rootMap.put("status", "failure");
					rootMap.put("message", "Something went wrong. Please try again later.");
					main.put("register", rootMap);
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
		            String json = gson.toJson(main);
		            
		            response.setContentType("application/json;charset=utf-8");
		            byte[] out = json.getBytes("UTF-8");
		            response.setContentLength(out.length);
		            response.getOutputStream().write(out);
					return;
				}
				}
			} catch (Exception e) {
					rootMap.put("status", "failure");
					rootMap.put("message", e);
					main.put("register", rootMap);
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
			        String json = gson.toJson(main);
			        
			        response.setContentType("application/json;charset=utf-8");
			        byte[] out = json.getBytes("UTF-8");
			        response.setContentLength(out.length);
			        response.getOutputStream().write(out);
					return;
		    }
				
		}
		
		} else {
			rootMap.put("status", "failure");
			rootMap.put("message", "Missing Parameters.");
			main.put("register", rootMap);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String json = gson.toJson(main);
	        
	        response.setContentType("application/json;charset=utf-8");
	        byte[] out = json.getBytes("UTF-8");
	        response.setContentLength(out.length);
	        response.getOutputStream().write(out);
			return;
		}
		
	 }
    catch (Exception e) {
    	rootMap.put("status", "failure");
		rootMap.put("message", e);
		main.put("register", rootMap);
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
