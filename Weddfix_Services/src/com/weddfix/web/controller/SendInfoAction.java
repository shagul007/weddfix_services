package com.weddfix.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.weddfix.web.formbean.DirectorySendInfoFormBean;
import com.weddfix.web.services.CategoryInfoService;
import com.weddfix.web.util.MailMessage;
import com.weddfix.web.util.SendSMS;

public class SendInfoAction extends ActionSupport implements
		ModelDriven<DirectorySendInfoFormBean>, Preparable, ServletRequestAware,
		ServletResponseAware, SessionAware {

	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	private Map<String, Object> session;
	private Long reqBookingId;
	private String reqEmail;
	private String reqMobile;
	private String reqFullName;
	private String reqCompanyName;
	DirectorySendInfoFormBean sendInfoFormBean = new DirectorySendInfoFormBean();
	CategoryInfoService clientInfoService = new CategoryInfoService();
	SendSMS sms = new SendSMS();

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public String saveSendInfoDetails() {
		HttpSession session = request.getSession(true);
		try {
			Date dob = null;
			try {
				if(sendInfoFormBean.getWdStr() != null && !sendInfoFormBean.getWdStr().equals("")) {
					dob = sdf.parse(sendInfoFormBean.getWdStr());
					sendInfoFormBean.setWeddingDate(dob);
				}
			} catch (ParseException e) {
				// e.printStackTrace();
			}
			
			sendInfoFormBean.setCreatedBy((Long) session.getAttribute("userId"));
			
			if(sendInfoFormBean.getSendEmail() != null) {
				sendInfoFormBean.setSendEmail(true);
			} else {
				sendInfoFormBean.setSendEmail(false);
			}
			
			if(sendInfoFormBean.getNeedCallBack() != null) {
				sendInfoFormBean.setNeedCallBack(true);
			} else {
				sendInfoFormBean.setNeedCallBack(false);
			}
			
			Long status = clientInfoService
					.saveSendInfoDetails(sendInfoFormBean);
			if (status != null) {
				sendInfoToCustomer(sendInfoFormBean.getName(), sendInfoFormBean.getMobile().toString(), sendInfoFormBean.getVendorCompanyName()
						, sendInfoFormBean.getVendorMobile(), sendInfoFormBean.getVendorPhone(), sendInfoFormBean.getVendorEmail());
				sendInfoToVendor(sendInfoFormBean.getName(), sendInfoFormBean.getMobile().toString(), sendInfoFormBean.getWdStr()
						, sendInfoFormBean.getEmail(), sendInfoFormBean.getSendEmail(), sendInfoFormBean.getNeedCallBack());
				sendEmailToCustomer(sendInfoFormBean.getName(), sendInfoFormBean.getEmail(), sendInfoFormBean.getVendorCompanyName()
						, sendInfoFormBean.getVendorMobile(), sendInfoFormBean.getVendorPhone(), sendInfoFormBean.getVendorEmail());
				sendEmailToVendor(sendInfoFormBean.getName(), sendInfoFormBean.getMobile().toString(), sendInfoFormBean.getEmail(), sendInfoFormBean.getWdStr()
						, sendInfoFormBean.getVendorEmail(), sendInfoFormBean.getSendEmail(), sendInfoFormBean.getNeedCallBack());
				session.setAttribute("successMessage",
						"Send Info Inserted Successfully...");
				session.setAttribute("hrefParamSuccess", "login");
				System.out.println("Send Info Inserted Successfully...");
				return "success";
			} else {
				session.setAttribute("errorMessage",
						"Something went wrong. Please try again later.");
				session.setAttribute("hrefParamError", "home");
				return "error";
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage",
					"Something went wrong. Please try again later.");
			session.setAttribute("hrefParamError", "home");
			return "error";
		}
	}
	
	public String requestFeedBack() {
		HttpSession session = request.getSession(true);
		try {
			
			sendInfoFormBean.setUpdatedBy((Long) session.getAttribute("userId"));
			sendInfoFormBean.setId(reqBookingId);
			sendInfoFormBean.setFeedbackRequested(true);
			Long status = clientInfoService
					.saveSendInfoDetails(sendInfoFormBean);
			if (status != null) {
				sendFeedBackToCustomer(reqFullName, reqMobile, reqEmail, reqCompanyName, reqBookingId);
				sendFeedBackEmailToCustomer(reqFullName, reqMobile, reqEmail, reqCompanyName, reqBookingId);
				session.setAttribute("successMessage",
						"Send Info Inserted Successfully...");
				session.setAttribute("hrefParamSuccess", "login");
				System.out.println("Send Info Inserted Successfully...");
				return "success";
			} else {
				session.setAttribute("errorMessage",
						"Something went wrong. Please try again later.");
				session.setAttribute("hrefParamError", "home");
				return "error";
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage",
					"Something went wrong. Please try again later.");
			session.setAttribute("hrefParamError", "home");
			return "error";
		}
	}

	public String sendInfoToCustomer(String user, String mobile, String vendorCompanyName, String vendorMobile, String vendorPhone, String vendorEmail) {
		String weddixUrl = getText("url");
		try {
			String msg = "Dear "+user+", contact details of "+vendorCompanyName+": \r\n Phone: "+vendorMobile+"";
			if(!vendorPhone.equals("") && vendorPhone != null) {
				msg += "/"+vendorPhone;
			}
				msg += "\r\n Email: "+vendorEmail+"\r\n Find more vendors visit: "+weddixUrl;
			sms.sendSms(msg, mobile);
			System.out.println("Vendor info sent successfully...");
			return "success";
		}

		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String sendInfoToVendor(String name, String mobile, String weddingDate, String email, Boolean sendEmail, Boolean needCallBack) {
		String weddixUrl = getText("url");
		try {
			String msg = "Dear vendor, contact details of customer : \r\n Name: "+name+"\r\n Phone: "+mobile+"\r\n Email: "+email+"";
			if(!weddingDate.equals("") && weddingDate != null) {
				msg += "\r\n Wedding Date: "+weddingDate;
			}
			if(sendEmail) {
				msg += "\r\n Send Email Back: Yes";
			} else {
				msg += "\r\n Send Email Back: No";
			}
			if(needCallBack) {
				msg += "\r\n Need Call Back: Yes";
			} else {
				msg += "\r\n Need Call Back: No";
			}
				msg += "\r\n For more info visit: "+weddixUrl;
			sms.sendSms(msg, mobile);
			System.out.println("Customer info sent successfully...");
			return "success";
		}

		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String sendEmailToCustomer(String user, String email, String vendorCompanyName, String vendorMobile, String vendorPhone, String vendorEmail) {
		HttpSession session = request.getSession(true);
		try {
			Properties props = new Properties();
			props.put("user", user);
			props.put("vendorCompanyName", vendorCompanyName);
			props.put("vendorMobile", vendorMobile);
			props.put("vendorPhone", vendorPhone);
			props.put("vendorEmail", vendorEmail);
			props.put("url", getText("url"));
			MailMessage msg = new MailMessage(props, "vendordetails.vm",
					email, "Weddfix Vendor Details");
			msg.send();
			session.setAttribute("successMessage", "We've sent vendor details to your email address.");
			session.setAttribute("hrefParamSuccess", "home");
			System.out.println("We've sent vendor details to your email address.");
			return "success";
		}

		catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage",
					"Something went wrong. Please try again later.");
			session.setAttribute("hrefParamError", "vendor_deatails");
			return "error";
		}
	}
	
	public String sendEmailToVendor(String name, String mobile, String email, String weddingDate, String vendorEmail, Boolean sendEmail, Boolean needCallBack) {
		HttpSession session = request.getSession(true);
		try {
			Properties props = new Properties();
			props.put("name", name);
			props.put("mobile", mobile);
			props.put("email", email);
			if(!weddingDate.equals("") && weddingDate != null) {
				props.put("weddingDate", weddingDate);
			} else {
				props.put("weddingDate", "Not Mentioned");
			}
			if(sendEmail) {
				props.put("sendEmailBack", "Yes");
			} else {
				props.put("sendEmailBack", "No");
			}
			if(needCallBack) {
				props.put("needCallBack", "Yes");
			} else {
				props.put("needCallBack", "No");
			}
			props.put("url", getText("url"));
			MailMessage msg = new MailMessage(props, "userdetails.vm",
					vendorEmail, "Weddfix User Details");
			msg.send();
			session.setAttribute("successMessage", "We've sent user details to your email address.");
			session.setAttribute("hrefParamSuccess", "home");
			System.out.println("We've user vendor details to your email address.");
			return "success";
		}

		catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage",
					"Something went wrong. Please try again later.");
			session.setAttribute("hrefParamError", "vendor_deatails");
			return "error";
		}
	}
	
	public String sendFeedBackToCustomer(String user, String mobile, String email, String companyName, Long reqBookingId) {
		String weddixUrl = getText("url");
		try {
			String msg = "Dear "+user+", submit your feedback";
				msg += "\r\n Click here: "+getText("feedbackUrl")+"?request="+reqBookingId;
				msg += "\r\n Feedback request by: "+companyName+"\r\n Find more details visit: "+weddixUrl;
			sms.sendSms(msg, mobile);
			System.out.println("Vendor feedback info sent successfully...");
			return "success";
		}

		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String sendFeedBackEmailToCustomer(String user, String mobile, String email, String companyName, Long reqBookingId) {
		HttpSession session = request.getSession(true);
		try {
			Properties props = new Properties();
			props.put("user", user);
			props.put("vendorCompanyName", companyName);
			props.put("feedbackUrl", getText("feedbackUrl")+"?request="+reqBookingId);
			props.put("url", getText("url"));
			MailMessage msg = new MailMessage(props, "feedbackrequest.vm",
					email, "Weddfix Feedback Request");
			msg.send();
			session.setAttribute("successMessage", "We've sent vendor details to your email address.");
			session.setAttribute("hrefParamSuccess", "home");
			System.out.println("We've sent feedback details to user email address.");
			return "success";
		}

		catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage",
					"Something went wrong. Please try again later.");
			session.setAttribute("hrefParamError", "vendor_deatails");
			return "error";
		}
	}
	
	public void prepare() throws Exception {
		sendInfoFormBean = new DirectorySendInfoFormBean();
	}

	public DirectorySendInfoFormBean getModel() {
		return sendInfoFormBean;
	}

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Long getReqBookingId() {
		return reqBookingId;
	}

	public void setReqBookingId(Long reqBookingId) {
		this.reqBookingId = reqBookingId;
	}

	public String getReqEmail() {
		return reqEmail;
	}

	public void setReqEmail(String reqEmail) {
		this.reqEmail = reqEmail;
	}

	public String getReqMobile() {
		return reqMobile;
	}

	public void setReqMobile(String reqMobile) {
		this.reqMobile = reqMobile;
	}

	public String getReqFullName() {
		return reqFullName;
	}

	public void setReqFullName(String reqFullName) {
		this.reqFullName = reqFullName;
	}

	public String getReqCompanyName() {
		return reqCompanyName;
	}

	public void setReqCompanyName(String reqCompanyName) {
		this.reqCompanyName = reqCompanyName;
	}


}
