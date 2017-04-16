package com.weddfix.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.weddfix.web.formbean.DirectoryCategoryReviewFormBean;
import com.weddfix.web.services.CategoryInfoService;

public class ReviewAction extends ActionSupport implements
		ModelDriven<DirectoryCategoryReviewFormBean>, Preparable, ServletRequestAware,
		ServletResponseAware, SessionAware {

	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	private Map<String, Object> session;
	DirectoryCategoryReviewFormBean categoryReviewFormBean = new DirectoryCategoryReviewFormBean();
	CategoryInfoService clientInfoService = new CategoryInfoService();

	public String saveReviewDetails() {
		HttpSession session = request.getSession(true);
		try {
			if(session.getAttribute("userId") != null) {
				categoryReviewFormBean.setCreatedBy((Long) session.getAttribute("userId"));
			} else {
				categoryReviewFormBean.setCreatedBy(1L);
			}
			
			categoryReviewFormBean.setSendInfoId(Long.parseLong(session.getAttribute("sendInfoId").toString()));
			Long status = clientInfoService
					.saveReviewDetails(categoryReviewFormBean);
			if (status != null) {
				session.setAttribute("successMessage",
						"Review Inserted Successfully...");
				session.setAttribute("hrefParamSuccess", "login");
				System.out.println("Review Inserted Successfully...");
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


	
	public void prepare() throws Exception {
		categoryReviewFormBean = new DirectoryCategoryReviewFormBean();
	}

	public DirectoryCategoryReviewFormBean getModel() {
		return categoryReviewFormBean;
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


}
