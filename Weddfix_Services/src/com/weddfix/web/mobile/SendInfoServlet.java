package com.weddfix.web.mobile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weddfix.web.controller.SendInfoAction;
import com.weddfix.web.formbean.DirectorySendInfoFormBean;
import com.weddfix.web.services.CategoryInfoService;
import com.weddfix.web.services.RegisterService;
import com.weddfix.web.util.MailMessage;
import com.weddfix.web.util.SendSMS;

public class SendInfoServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CategoryInfoService clientInfoService = new CategoryInfoService();
	LinkedHashMap<String, Object> rootMap = new LinkedHashMap<String, Object>();
	SendSMS sms = new SendSMS();
	RegisterService registerService = new RegisterService();
	SendInfoAction sendInfoAction = new SendInfoAction();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LinkedHashMap<String, Object> main = new LinkedHashMap<String, Object>();
		
        try {
        	
        	Long userId = 0L;
        	String verifyMobile = null;

			if(request.getParameter("userId") != null) {
				userId = Long.parseLong(request.getParameter("userId").toString());
			} 
			if(request.getParameter("verifyMobile") != null) {
				verifyMobile = request.getParameter("verifyMobile");
			} 
			
			String verificationCode = request.getParameter("verificationCode");

			if(verificationCode != null) {
				if(verificationCode.equals("send") && userId > 0 && verifyMobile != null) {
							String code = registerService
									.updateMobileVerificationCode(userId);

							String msg = "Your Weddfix mobile verification code is "+code;
							sms.sendSms(msg, verifyMobile);
							
							rootMap.put("status", "success");
							rootMap.put("message", "Verification code sent successfully.");
							rootMap.put("mobile", verifyMobile);
							rootMap.put("verifyMobileNumber", code);
							main.put("verification", rootMap);
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
			main.put("verification", rootMap);
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
			
			Long userId = 0L;
			Long mobile = 0L;
        	String verifyMobileNumber = null;
        	String verifyCode = null;

			if(request.getParameter("userId") != null) {
				userId = Long.parseLong(request.getParameter("userId").toString());
			} 
			if(request.getParameter("verifyMobileNumber") != null) {
				verifyMobileNumber = request.getParameter("verifyMobileNumber").toString();
			} 
			if(request.getParameter("verifyCode") != null) {
				verifyCode = request.getParameter("verifyCode").toString();
			} 
			if(request.getParameter("mobile") != null) {
				mobile = Long.parseLong(request.getParameter("mobile").toString());
			} 
			
			String save = request.getParameter("save");
			
			String verificationCode = request.getParameter("verificationCode");
			
			if(verificationCode != null) {
				if(verificationCode.equals("verify") && verifyMobileNumber.equals(verifyCode)) {
					String status = registerService
							.verifyCodeAndUpdateMobile(mobile, userId);
					if(status != null) {
						rootMap.put("status", "success");
						rootMap.put("message", "Mobile Number Verified successfully.");
						rootMap.put("mobile", mobile);
						rootMap.put("verifiedMobileNumber", true);
						main.put("verification", rootMap);
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
						main.put("verification", rootMap);
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
        			rootMap.put("message", "Invalid Verification Code.");
					main.put("verification", rootMap);
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
		            String json = gson.toJson(main);
		            
		            response.setContentType("application/json;charset=utf-8");
		            byte[] out = json.getBytes("UTF-8");
		            response.setContentLength(out.length);
		            response.getOutputStream().write(out);
					return;
				}
			}

			if(save != null) {
				if(save.equals("booking")) {
					DirectorySendInfoFormBean sendInfoFormBean = new DirectorySendInfoFormBean();
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					
					Date dob = null;
					try {
						if(request.getParameter("weddingDate") != null && !request.getParameter("weddingDate").equals("")) {
							dob = sdf.parse(request.getParameter("weddingDate"));
							sendInfoFormBean.setWeddingDate(dob);
						}
					} catch (ParseException e) {
						// e.printStackTrace();
					}
					
					sendInfoFormBean.setVendorId(Long.parseLong(request.getParameter("vendorId").toString()));
					sendInfoFormBean.setVendorCompanyName(request.getParameter("vendorCompanyName").toString());
					sendInfoFormBean.setVendorMobile(request.getParameter("vendorMobile").toString());
					sendInfoFormBean.setVendorPhone(request.getParameter("vendorPhone"));
					sendInfoFormBean.setVendorEmail(request.getParameter("vendorEmail").toString());
					sendInfoFormBean.setName(request.getParameter("name").toString());
					sendInfoFormBean.setMobile(Long.parseLong(request.getParameter("mobile").toString()));
					sendInfoFormBean.setEmail(request.getParameter("email").toString());
					sendInfoFormBean.setCreatedBy(Long.parseLong(request.getParameter("userId").toString()));
					
					if(request.getParameter("sendEmail") != null && request.getParameter("sendEmail").equals("true")) {
						sendInfoFormBean.setSendEmail(true);
					} else {
						sendInfoFormBean.setSendEmail(false);
					}
					
					if(request.getParameter("needCallBack") != null && request.getParameter("needCallBack").equals("true")) {
						sendInfoFormBean.setNeedCallBack(true);
					} else {
						sendInfoFormBean.setNeedCallBack(false);
					}
					
					Long status = clientInfoService
							.saveSendInfoDetails(sendInfoFormBean);
					
					if (status != null) {
							sendInfoAction.sendInfoToCustomer(sendInfoFormBean.getName(), sendInfoFormBean.getMobile().toString(), sendInfoFormBean.getVendorCompanyName()
										, sendInfoFormBean.getVendorMobile(), sendInfoFormBean.getVendorPhone(), sendInfoFormBean.getVendorEmail());
							sendInfoAction.sendInfoToVendor(sendInfoFormBean.getName(), sendInfoFormBean.getMobile().toString(), request.getParameter("weddingDate")
										, sendInfoFormBean.getEmail(), sendInfoFormBean.getSendEmail(), sendInfoFormBean.getNeedCallBack());
							Properties props = new Properties();
							props.put("user", sendInfoFormBean.getName());
							props.put("vendorCompanyName", sendInfoFormBean.getVendorCompanyName());
							props.put("vendorMobile", sendInfoFormBean.getVendorMobile());
							props.put("vendorPhone", sendInfoFormBean.getVendorPhone());
							props.put("vendorEmail", sendInfoFormBean.getVendorEmail());
							props.put("url", rb.getString("url"));
							MailMessage msg = new MailMessage(props, "vendordetails.vm",
									sendInfoFormBean.getEmail(), "Weddfix Vendor Details");
							msg.send();
							System.out.println("We've sent vendor details to your email address.");
						
							props = new Properties();
							props.put("name", sendInfoFormBean.getName());
							props.put("mobile", sendInfoFormBean.getMobile().toString());
							props.put("email", sendInfoFormBean.getEmail());
							if(!request.getParameter("weddingDate").equals("") && request.getParameter("weddingDate") != null) {
								props.put("weddingDate", request.getParameter("weddingDate"));
							} else {
								props.put("weddingDate", "Not Mentioned");
							}
							if(sendInfoFormBean.getSendEmail()) {
								props.put("sendEmailBack", "Yes");
							} else {
								props.put("sendEmailBack", "No");
							}
							if(sendInfoFormBean.getNeedCallBack()) {
								props.put("needCallBack", "Yes");
							} else {
								props.put("needCallBack", "No");
							}
							props.put("url", rb.getString("url"));
							msg = new MailMessage(props, "userdetails.vm",
									sendInfoFormBean.getVendorEmail(), "Weddfix User Details");
							msg.send();
							System.out.println("We've user vendor details to your email address.");
							
							rootMap.put("status", "success");
			    			rootMap.put("message", "Booked Successfully.");
							main.put("booking", rootMap);
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
							main.put("booking", rootMap);
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
					main.put("booking", rootMap);
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
		            String json = gson.toJson(main);
		            
		            response.setContentType("application/json;charset=utf-8");
		            byte[] out = json.getBytes("UTF-8");
		            response.setContentLength(out.length);
		            response.getOutputStream().write(out);
					return;
				}

		} catch (Exception e) {
			e.printStackTrace();
        	rootMap.put("status", "failure");
        	rootMap.put("message", e);
			main.put("booking", rootMap);
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
