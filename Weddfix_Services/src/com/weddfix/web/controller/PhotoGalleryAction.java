package com.weddfix.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.weddfix.web.formbean.PhotoGalleryFormBean;
import com.weddfix.web.services.CategoryInfoService;
import com.weddfix.web.services.CommonMasterService;

public class PhotoGalleryAction extends ActionSupport implements
		ModelDriven<PhotoGalleryFormBean>, Preparable, ServletRequestAware,
		ServletResponseAware, SessionAware, ServletContextAware {

	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	private Map<String, Object> session;

	private List<Map<String, Object>> bannerPhotosBean = new ArrayList<Map<String, Object>>();

	@SuppressWarnings("unused")
	private ServletContext context;
	private String profilePic;

	PhotoGalleryFormBean photoGalleryFormBean = new PhotoGalleryFormBean();
	CategoryInfoService clientInfoService = new CategoryInfoService();
	CommonMasterService commonMasterService = new CommonMasterService();

	public String loadBannerPhotoGallery() {
		try {
			List<PhotoGalleryFormBean> photoGalleryInfos = clientInfoService
					.loadPhotoGalleryInfo();
			Iterator<?> itr = photoGalleryInfos.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("orgId", obj[1]);
				map.put("fileName", obj[2]);
				map.put("statusName", obj[3]);

				bannerPhotosBean.add(map);
			}

			return "success";
		}

		catch (Exception e) {
			// e.printStackTrace();
			return "error";
		}
	}

	public void prepare() throws Exception {
		photoGalleryFormBean = new PhotoGalleryFormBean();
	}

	public PhotoGalleryFormBean getModel() {
		return photoGalleryFormBean;
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

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public List<Map<String, Object>> getBannerPhotosBean() {
		return bannerPhotosBean;
	}

	public void setBannerPhotosBean(List<Map<String, Object>> bannerPhotosBean) {
		this.bannerPhotosBean = bannerPhotosBean;
	}

}
