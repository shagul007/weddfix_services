package com.weddfix.web.mobile;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
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
import com.weddfix.web.util.MailMessage;
import com.weddfix.web.util.SendSMS;

public class VendorBookingsServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CategoryInfoService categoryInfoService = new CategoryInfoService();
	SendSMS sms = new SendSMS();
	SendInfoAction sendInfoAction = new SendInfoAction();
	LinkedHashMap<String, Object> rootMap = new LinkedHashMap<String, Object>();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LinkedHashMap<String, Object> main = new LinkedHashMap<String, Object>();
		ResourceBundle rb = ResourceBundle.getBundle("ApplicationResources");
		
        try {
        	
        	Long userId = 0L;

			if(request.getParameter("userId") != null) {
				userId = Long.parseLong(request.getParameter("userId").toString());
			} 
			
			if(userId > 0) {
				List<DirectorySendInfoFormBean> bookingInfoList = categoryInfoService
						.loadVendorBookingDetails(userId);
				
			LinkedList<LinkedHashMap<String, Object>> bookingsList = new LinkedList<LinkedHashMap<String, Object>>();
		    
		    Iterator<?> itr = bookingInfoList.iterator();
			
		    while (itr.hasNext()) {

					Object[] obj = (Object[]) itr.next();
					LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
					map.put("id", obj[0]);
					map.put("fullName", obj[1]);
					map.put("email", obj[2]);
					map.put("mobile", obj[3]);
					map.put("cityName", obj[4]);
					map.put("stateName", obj[5]);
					map.put("countryName", obj[6]);
					map.put("pincode", obj[7]);
					map.put("categoryId", obj[8]);
					map.put("categoryName", obj[9].toString().substring(0, obj[9].toString().length()-1));
					map.put("companyName", obj[10]);
					map.put("price", obj[11]);
					if(obj[12] != null) {
						map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+obj[12]);
					} else {
						map.put("fileName", "");
					}
					map.put("bookingId", obj[13]);
					map.put("bookingStatus", obj[14]);
					map.put("bookingDate", obj[15]);
					if(obj[16] != null) {
					if(obj[16].equals(true)) {
						map.put("feedbackRequested", obj[16]);
					} else {
						map.put("feedbackRequested", false);
					}} else {
						map.put("feedbackRequested", false);
					}
					if(obj[17] != null) {
						map.put("feedbackStatus", obj[17]);
					} else {
						map.put("feedbackStatus", "");
					}
					
					bookingsList.add(map);
				}
				
				main.put("bookings", bookingsList);
				
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
				main.put("bookings", rootMap);
				
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
			main.put("bookings", rootMap);
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
			Long bookingId = 0L;

			if(request.getParameter("userId") != null) {
				userId = Long.parseLong(request.getParameter("userId").toString());
			} 
			
			if(request.getParameter("bookingId") != null) {
				bookingId = Long.parseLong(request.getParameter("bookingId").toString());
			} 
			
			String save = request.getParameter("save");

			if(save != null) {
				if(save.equals("requestFeedBack") && userId > 0 && bookingId > 0) {
					DirectorySendInfoFormBean sendInfoFormBean = new DirectorySendInfoFormBean();
					sendInfoFormBean.setUpdatedBy(userId);
					sendInfoFormBean.setId(bookingId);
					sendInfoFormBean.setFeedbackRequested(true);
					Long status = categoryInfoService
							.saveSendInfoDetails(sendInfoFormBean);
					
					if(status != null) {
						sendInfoAction.sendFeedBackToCustomer(request.getParameter("fullName").toString(), 
								request.getParameter("mobile").toString(), request.getParameter("email").toString()
								, request.getParameter("companyName").toString(), bookingId);
						Properties props = new Properties();
						props.put("user", request.getParameter("fullName").toString());
						props.put("vendorCompanyName", request.getParameter("companyName").toString());
						props.put("feedbackUrl", rb.getString("feedbackUrl")+"?request="+bookingId);
						props.put("url", rb.getString("url"));
						MailMessage msg = new MailMessage(props, "feedbackrequest.vm",
								request.getParameter("email").toString(), "Weddfix Feedback Request");
						msg.send();
						System.out.println("We've sent feedback details to user email address.");
						System.out.println("Feedback Requested Successfully.");
						rootMap.put("status", "success");
						rootMap.put("message", "Feedback Requested Successfully.");
						main.put("feedback", rootMap);
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
						main.put("feedback", rootMap);
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
					main.put("feedback", rootMap);
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
			main.put("feedback", rootMap);
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
