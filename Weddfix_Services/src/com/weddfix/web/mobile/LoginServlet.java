package com.weddfix.web.mobile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weddfix.web.controller.RegisterAction;
import com.weddfix.web.formbean.DirectoryUserProfileFormBean;
import com.weddfix.web.formbean.LoginFormBean;
import com.weddfix.web.formbean.MyPersonalDetailsFormBean;
import com.weddfix.web.services.CommonMasterService;
import com.weddfix.web.services.LoginService;
import com.weddfix.web.services.RegisterService;
import com.weddfix.web.util.CommonConstants;
import com.weddfix.web.util.MailMessage;

public class LoginServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final long MILLIS_IN_DAY = 60 * 60 * 24 * 1000;
	
	LoginFormBean loginFormBean = new LoginFormBean();
    LoginService loginService = new LoginService();
    RegisterService registerService = new RegisterService();
    RegisterAction registerAction = new RegisterAction();
    CommonMasterService commonMasterService = new CommonMasterService();
    LinkedHashMap<String, Object> rootMap = new LinkedHashMap<String, Object>();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LinkedHashMap<String, Object> main = new LinkedHashMap<String, Object>();
		ResourceBundle rb = ResourceBundle.getBundle("ApplicationResources");
		
        try {
        	
        	String email = request.getParameter("forgotPasswordEmailId");
        	
        	if(email != null) {
        		
        		String userExist = commonMasterService.checkUserAlreadyExist(email.toLowerCase());
        		if (userExist != null) {
        			DirectoryUserProfileFormBean userProfile = registerService
    						.loadUserProfileByEmail(email);
    				String key = registerAction.startPasswordReset(userProfile.getId());
    				Properties props = new Properties();
    				props.put("fullName", userProfile.getFullName());
    				props.put("email", userProfile.getEmail());
    				props.put("url", rb.getString("url") + "/reset?key=" + key);
    				MailMessage msg = new MailMessage(props, "pwreset.vm",
    						userProfile.getEmail(), "Password reset request");
    				msg.send();
    				
    				rootMap.put("status", "success");
    				rootMap.put("message", "We've sent a password reset link to your email address.");
    				main.put("forgotPassword", rootMap);
    				Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	            String json = gson.toJson(main);
    	            
    	            response.setContentType("application/json;charset=utf-8");
    	            byte[] out = json.getBytes("UTF-8");
    	            response.setContentLength(out.length);
    	            response.getOutputStream().write(out);
    				return;
        		} else {
        			rootMap.put("status", "failure");
        			rootMap.put("message", "User does not exist.");
        			main.put("forgotPassword", rootMap);
    				Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	            String json = gson.toJson(main);
    	            
    	            response.setContentType("application/json;charset=utf-8");
    	            byte[] out = json.getBytes("UTF-8");
    	            response.setContentLength(out.length);
    	            response.getOutputStream().write(out);
    				return;
        		}
        		
        	} else {
        		rootMap.put("status", "failure");
        		rootMap.put("message", "Missing Parameters.");
        		main.put("forgotPassword", rootMap);
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
			main.put("forgotPassword", rootMap);
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
		ResourceBundle rb = ResourceBundle.getBundle("ApplicationResources");
		
        try {
        	
        	String username = request.getParameter("username");
            String password = request.getParameter("password");
            
           /* Enumeration<String> paramEnumeration= request.getParameterNames();
            StringBuffer buffer = new StringBuffer();
            while(paramEnumeration.hasMoreElements())
            {
                String paramName= paramEnumeration.nextElement();
                String paramValue =request.getParameter(paramName);
                buffer.append(paramName + ":" + paramValue);
                buffer.append("<br/>");
            }*/
            
            if (username == null || username.trim().length() == 0) {
            	rootMap.put("status", "failure");
            	rootMap.put("message", "No Username provided.");
            	main.put("login", rootMap);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
	            String json = gson.toJson(main);
	            
	            response.setContentType("application/json;charset=utf-8");
	            byte[] out = json.getBytes("UTF-8");
	            response.setContentLength(out.length);
	            response.getOutputStream().write(out);
				return;
            }
            
            if (password == null || password.trim().length() == 0) {
            	rootMap.put("status", "failure");
            	rootMap.put("message", "No Password provided.");
            	main.put("login", rootMap);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
	            String json = gson.toJson(main);
	            
	            response.setContentType("application/json;charset=utf-8");
	            byte[] out = json.getBytes("UTF-8");
	            response.setContentLength(out.length);
	            response.getOutputStream().write(out);
				return;
            }
            
            try {
			loginFormBean = loginService.checkLogin(
					username, password);
			
			if(loginFormBean.getStatus().equals(CommonConstants.INACTIVE_STR)) {
				rootMap.put("status", "failure");
				rootMap.put("message", "Your account is currently inactive. Please contact your administrator.");
    			main.put("login", rootMap);
    			Gson gson = new GsonBuilder().setPrettyPrinting().create();
	            String json = gson.toJson(main);
	            
	            response.setContentType("application/json;charset=utf-8");
	            byte[] out = json.getBytes("UTF-8");
	            response.setContentLength(out.length);
	            response.getOutputStream().write(out);
				return;
			} else {
//				return "success";
			}
            } catch (Exception e) {
//    			e.printStackTrace();
            	rootMap.put("status", "failure");
            	rootMap.put("message", "The email or password you entered wrong.");
            	main.put("login", rootMap);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
	            String json = gson.toJson(main);
	            
	            response.setContentType("application/json;charset=utf-8");
	            byte[] out = json.getBytes("UTF-8");
	            response.setContentLength(out.length);
	            response.getOutputStream().write(out);
				return;
    		}
            
            rootMap.put("status", "success");
        	main.put("login", rootMap);
			
            main.put("baseUrl", rb.getString("url"));
            
          //add CommonDetails
            LinkedHashMap<String, Object> commonDetailsMap = new LinkedHashMap<String, Object>();
            
            commonDetailsMap.put("userId", loginFormBean.getId());
			commonDetailsMap.put("email", loginFormBean.getEmail());
			commonDetailsMap.put("mobile", loginFormBean.getMobile());
			commonDetailsMap.put("role", loginFormBean.getRole());
			commonDetailsMap.put("status", loginFormBean.getStatus());
			commonDetailsMap.put("passwordHash", loginFormBean.getPassword());
			commonDetailsMap.put("userName", loginFormBean.getFullName());
			commonDetailsMap.put("accountType", loginFormBean.getAccountType());
			commonDetailsMap.put("verifyMobileNumber", loginFormBean.getVerifyMobileNumber());
			commonDetailsMap.put("verifyEmailId", loginFormBean.getVerifyEmailId());
			if(loginFormBean.getVerifiedMobileNumber().equals(true)) {
            	commonDetailsMap.put("verifiedMobileNumber", loginFormBean.getVerifiedMobileNumber());
			} else {
				commonDetailsMap.put("verifiedMobileNumber", false);
			}
			if(loginFormBean.getVerifiedEmailId().equals(true)) {
            	commonDetailsMap.put("verifiedEmailId", loginFormBean.getVerifiedEmailId());
			} else {
				commonDetailsMap.put("verifiedEmailId", false);
			}
			if(loginFormBean.getMyPlanId() != null) {
				commonDetailsMap.put("myPlanId", loginFormBean.getMyPlanId());
			} else {
				commonDetailsMap.put("myPlanId", "");
			}
			
			main.put("loginDetails", commonDetailsMap);

            List<MyPersonalDetailsFormBean> myPersonalDetails = registerService
					.loadMyDashBoardDetails(loginFormBean.getId());
            
            //add MyPersonalDetails
            LinkedHashMap<String, Object> myPersonalDetailsMap = new LinkedHashMap<String, Object>();
            
            Iterator<?> itr = myPersonalDetails.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				myPersonalDetailsMap.put("id",
						Long.parseLong(obj[0].toString()));
				myPersonalDetailsMap.put("fullName", obj[1].toString());
				if (obj[2] != null) {
					myPersonalDetailsMap.put("description", obj[2].toString());
				} else {
					myPersonalDetailsMap.put("description", "");
				}
				if (obj[3] != null) {
					myPersonalDetailsMap.put("weddingDateStr",
							obj[3].toString());
				} else {
					myPersonalDetailsMap.put("weddingDateStr", "");
				}
				if (obj[4] != null) {
					myPersonalDetailsMap.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+obj[4].toString());
				} else {
					myPersonalDetailsMap.put("fileName", "");
				}
				myPersonalDetailsMap.put("planName", obj[5].toString());
				if (obj[5].toString().equalsIgnoreCase("FREE")) {
					DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
					Date cDate = null;
					try {
						cDate = dateFormat.parse(obj[6].toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Date createdDate = DateUtils.addMonths(cDate, 3);
					// it's still active if the created date + 11 days is
					// greater than the current time
					int daysIntoTrial = Math
							.round(((createdDate.getTime() - System
									.currentTimeMillis()) / MILLIS_IN_DAY));

					int reminingDays = daysIntoTrial - 1;
					myPersonalDetailsMap.put("validityDaysLeft", reminingDays);
				} else {
					Date createdDate = DateUtils.addMonths(new Date(),
							Integer.valueOf(obj[7].toString()));
					// it's still active if the created date + 11 days is
					// greater than the current time
					int daysIntoTrial = Math
							.round(((createdDate.getTime() - System
									.currentTimeMillis()) / MILLIS_IN_DAY));

					int reminingDays = daysIntoTrial;
					myPersonalDetailsMap.put("validityDaysLeft", reminingDays);
				}
				if (obj[8] != null) {
					myPersonalDetailsMap.put("myListingTotalCount",
							Long.parseLong(obj[8].toString()));
				} else {
					myPersonalDetailsMap.put("myListingTotalCount", 0);
				}
				if (obj[9] != null) {
					myPersonalDetailsMap.put("myShortlistedTotalCount",
							Long.parseLong(obj[9].toString()));
				} else {
					myPersonalDetailsMap.put("myShortlistedTotalCount", 0);
				}
				if (obj[10] != null) {
					if (obj[10].toString().startsWith("-")) {
						myPersonalDetailsMap.put("weddingDaysLeft", "");
					} else {
						myPersonalDetailsMap.put("weddingDaysLeft", obj[10]
								.toString().split("\\.")[0]);
					}
				} else {
					myPersonalDetailsMap.put("weddingDaysLeft", "");
				}
				if (obj[11] != null) {
					myPersonalDetailsMap.put("myBookingTotalCount",
							Long.parseLong(obj[11].toString()));
				} else {
					myPersonalDetailsMap.put("myBookingTotalCount", 0);
				}
			}
			
			main.put("myHomeDetails", myPersonalDetailsMap);
            
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
        	main.put("login", rootMap);
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
