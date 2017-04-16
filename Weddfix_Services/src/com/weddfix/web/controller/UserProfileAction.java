package com.weddfix.web.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.classic.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.weddfix.web.formbean.DirectoryAccountDetailsFormBean;
import com.weddfix.web.formbean.DirectoryCategoryInfoFormBean;
import com.weddfix.web.formbean.DirectoryUpgradePlanFormBean;
import com.weddfix.web.formbean.MyPersonalDetailsFormBean;
import com.weddfix.web.services.CategoryInfoService;
import com.weddfix.web.services.CommonMasterService;
import com.weddfix.web.services.RegisterService;
import com.weddfix.web.util.HibernateUtil;

public class UserProfileAction extends ActionSupport implements Preparable,
		ServletRequestAware, ServletResponseAware, SessionAware {

	private static final long serialVersionUID = 1L;
	private static final long MILLIS_IN_DAY = 60 * 60 * 24 * 1000;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	private Map<String, Object> session;
	private Map<Object, Object> stateMap;
	private Map<Object, Object> countryMap;
	private Map<Object, Object> orgMap;
	private Map<Object, Object> roleMap;
	private Map<String, Object> categoryInfoBean = new HashMap<String, Object>();
	private Map<String, Object> myPersonalDetailsBean = new HashMap<String, Object>();
	private Map<String, Object> partnerPreferenceDetailsBean = new HashMap<String, Object>();
	private List<Map<String, Object>> profilePicturesInfoBean = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> similarPartnerPreferenceInfoBean = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> upgradePlanInfoBean = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> whoViewedMyProfileInfoBean = new ArrayList<Map<String, Object>>();
	private Map<Object, Object> cityMap;
	private Long deactivateProfileDays;
	private String deleteProfileReason;
	private Long country_Id;
	private Long state_Id;
	private Long religionId;

	DirectoryAccountDetailsFormBean accountDetailsFormBean = new DirectoryAccountDetailsFormBean();
	RegisterService registerService = new RegisterService();
	CommonMasterService commonMasterService = new CommonMasterService();
	CategoryInfoService categoryInfoService = new CategoryInfoService();

	public String deleteProfile() {
		HttpSession session = request.getSession(true);
			try {

				accountDetailsFormBean.setDeleteProfileReason(getDeleteProfileReason());
				
				accountDetailsFormBean.setUpdatedBy(Long.parseLong(session
							.getAttribute("userId").toString()));

				Long status = registerService
						.deleteProfile(accountDetailsFormBean, (Long) session
								.getAttribute("userId"));
				if (status != null) {
						session.setAttribute("successMessage",
								"Updated Successfully...");
						session.setAttribute("hrefParamSuccess", "user_home");
						System.out.println("Updated Successfully...");
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
	
	public String loadMasters() {

		countryMap = commonMasterService.loadCountry();
		System.out.println("****" + countryMap);

		return "success";
	}

	public String loadCountryMaster() {
		countryMap = commonMasterService.loadCountry();
		System.out.println("****" + countryMap);

		return "success";
	}

	public String loadStateMaster() {
		stateMap = commonMasterService.loadState();
		System.out.println("****" + stateMap);

		return "success";
	}

	public String loadMyDashBoardDetails() {
		HttpSession session = request.getSession();
		try {

			List<MyPersonalDetailsFormBean> myPersonalDetails = registerService
					.loadMyDashBoardDetails((Long) session
							.getAttribute("userId"));

			Iterator<?> itr = myPersonalDetails.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				myPersonalDetailsBean.put("id",
						Long.parseLong(obj[0].toString()));
				myPersonalDetailsBean.put("fullName", obj[1].toString());
				if (obj[2] != null) {
					myPersonalDetailsBean.put("description", obj[2].toString());
				} else {
					myPersonalDetailsBean.put("description", "");
				}
				if (obj[3] != null) {
					myPersonalDetailsBean.put("weddingDateStr",
							obj[3].toString());
				} else {
					myPersonalDetailsBean.put("weddingDateStr", "");
				}
				if (obj[4] != null) {
					myPersonalDetailsBean.put("fileName", obj[4].toString());
				} else {
					myPersonalDetailsBean.put("fileName", null);
				}
				myPersonalDetailsBean.put("planName", obj[5].toString());
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
					myPersonalDetailsBean.put("validity", reminingDays);
				} else {
					Date createdDate = DateUtils.addMonths(new Date(),
							Integer.valueOf(obj[7].toString()));
					// it's still active if the created date + 11 days is
					// greater than the current time
					int daysIntoTrial = Math
							.round(((createdDate.getTime() - System
									.currentTimeMillis()) / MILLIS_IN_DAY));

					int reminingDays = daysIntoTrial;
					myPersonalDetailsBean.put("validity", reminingDays);
				}
				if (obj[8] != null) {
					myPersonalDetailsBean.put("myListingTotalCount",
							Long.parseLong(obj[8].toString()));
				} else {
					myPersonalDetailsBean.put("myListingTotalCount", 0);
				}
				if (obj[9] != null) {
					myPersonalDetailsBean.put("myShortlistedTotalCount",
							Long.parseLong(obj[9].toString()));
				} else {
					myPersonalDetailsBean.put("myShortlistedTotalCount", 0);
				}
				if (obj[10] != null) {
					if (obj[10].toString().startsWith("-")) {
						myPersonalDetailsBean.put("weddingDaysLeft", null);
					} else {
						myPersonalDetailsBean.put("weddingDaysLeft", obj[10]
								.toString().split("\\.")[0]);
					}
				} else {
					myPersonalDetailsBean.put("weddingDaysLeft", null);
				}
				if (obj[11] != null) {
					myPersonalDetailsBean.put("myBookingTotalCount",
							Long.parseLong(obj[11].toString()));
				} else {
					myPersonalDetailsBean.put("myBookingTotalCount", 0);
				}
			}

			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String loadVendorDashBoardDetails() {
		HttpSession session = request.getSession();
		try {

			List<MyPersonalDetailsFormBean> myPersonalDetails = registerService
					.loadVendorDashBoardDetails((Long) session
							.getAttribute("userId"));

			Iterator<?> itr = myPersonalDetails.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				myPersonalDetailsBean.put("id",
						Long.parseLong(obj[0].toString()));
				myPersonalDetailsBean.put("fullName", obj[1].toString());
				if (obj[2] != null) {
					myPersonalDetailsBean.put("description", obj[2].toString());
				} else {
					myPersonalDetailsBean.put("description", "");
				}
				if (obj[3] != null) {
					myPersonalDetailsBean.put("fileName", obj[3].toString());
				} else {
					myPersonalDetailsBean.put("fileName", null);
				}
				myPersonalDetailsBean.put("planName", obj[4].toString());
				if (obj[4].toString().equalsIgnoreCase("FREE")) {
					DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
					Date cDate = null;
					try {
						cDate = dateFormat.parse(obj[5].toString());
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
					myPersonalDetailsBean.put("validity", reminingDays);
				} else {
					Date createdDate = DateUtils.addMonths(new Date(),
							Integer.valueOf(obj[6].toString()));
					// it's still active if the created date + 11 days is
					// greater than the current time
					int daysIntoTrial = Math
							.round(((createdDate.getTime() - System
									.currentTimeMillis()) / MILLIS_IN_DAY));

					int reminingDays = daysIntoTrial;
					myPersonalDetailsBean.put("validity", reminingDays);
				}
				if (obj[7] != null) {
					myPersonalDetailsBean.put("myListingTotalCount",
							Long.parseLong(obj[7].toString()));
				} else {
					myPersonalDetailsBean.put("myListingTotalCount", 0);
				}
				if (obj[8] != null) {
					myPersonalDetailsBean.put("bookingTotalCount",
							Long.parseLong(obj[8].toString()));
				} else {
					myPersonalDetailsBean.put("bookingTotalCount", 0);
				}
			}

			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String loadMyPersonalDetails() {
		HttpSession session = request.getSession();
		try {

			MyPersonalDetailsFormBean myPersonalDetails = registerService
					.loadPersonalDetails((Long) session.getAttribute("userId"));

			session.setAttribute("countryId", myPersonalDetails.getCountryId());
			session.setAttribute("stateId", myPersonalDetails.getStateId());
			session.setAttribute("cityId", myPersonalDetails.getCityId());
			session.setAttribute("password", myPersonalDetails.getPassword());
			myPersonalDetailsBean.put("id", myPersonalDetails.getId());
			myPersonalDetailsBean.put("fullName",
					myPersonalDetails.getFullName());
			myPersonalDetailsBean.put("email", myPersonalDetails.getEmail());
			myPersonalDetailsBean.put("password",
					myPersonalDetails.getPassword());
			myPersonalDetailsBean.put("passwordHash",
					myPersonalDetails.getPasswordHash());
			myPersonalDetailsBean.put("mobile", myPersonalDetails.getMobile());
			myPersonalDetailsBean.put("countryId",
					myPersonalDetails.getCountryId());
			myPersonalDetailsBean
					.put("stateId", myPersonalDetails.getStateId());
			myPersonalDetailsBean.put("cityId", myPersonalDetails.getCityId());
			myPersonalDetailsBean
					.put("pincode", myPersonalDetails.getPincode());
			myPersonalDetailsBean.put("description",
					myPersonalDetails.getDescription());
			myPersonalDetailsBean.put("profilePictureId",
					myPersonalDetails.getProfilePictureId());
			myPersonalDetailsBean.put("weddingDateStr",
					myPersonalDetails.getWeddingDateStr());
			myPersonalDetailsBean.put("websiteUrl",
					myPersonalDetails.getWebsiteUrl());
			myPersonalDetailsBean.put("facebookUrl",
					myPersonalDetails.getFacebookUrl());
			myPersonalDetailsBean.put("twitterUrl",
					myPersonalDetails.getTwitterUrl());
			myPersonalDetailsBean.put("linkedinUrl",
					myPersonalDetails.getLinkedinUrl());
			myPersonalDetailsBean.put("pinterestUrl",
					myPersonalDetails.getPinterestUrl());
			myPersonalDetailsBean.put("instagramUrl",
					myPersonalDetails.getInstagramUrl());
			myPersonalDetailsBean
					.put("country", myPersonalDetails.getCountry());
			myPersonalDetailsBean.put("state", myPersonalDetails.getState());
			myPersonalDetailsBean.put("city", myPersonalDetails.getCity());
			myPersonalDetailsBean.put("fileName",
					myPersonalDetails.getFileName());

			countryMap = commonMasterService.loadCountry();
			System.out.println("****" + countryMap);

			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String loadVendorPersonalDetails() {
		HttpSession session = request.getSession();
		try {

			MyPersonalDetailsFormBean myPersonalDetails = registerService
					.loadVendorPersonalDetails((Long) session.getAttribute("userId"));

			session.setAttribute("countryId", myPersonalDetails.getCountryId());
			session.setAttribute("stateId", myPersonalDetails.getStateId());
			session.setAttribute("cityId", myPersonalDetails.getCityId());
			session.setAttribute("password", myPersonalDetails.getPassword());
			myPersonalDetailsBean.put("id", myPersonalDetails.getId());
			myPersonalDetailsBean.put("fullName",
					myPersonalDetails.getFullName());
			myPersonalDetailsBean.put("email", myPersonalDetails.getEmail());
			myPersonalDetailsBean.put("password",
					myPersonalDetails.getPassword());
			myPersonalDetailsBean.put("passwordHash",
					myPersonalDetails.getPasswordHash());
			myPersonalDetailsBean.put("mobile", myPersonalDetails.getMobile());
			myPersonalDetailsBean.put("countryId",
					myPersonalDetails.getCountryId());
			myPersonalDetailsBean
					.put("stateId", myPersonalDetails.getStateId());
			myPersonalDetailsBean.put("cityId", myPersonalDetails.getCityId());
			myPersonalDetailsBean
					.put("pincode", myPersonalDetails.getPincode());
			myPersonalDetailsBean.put("description",
					myPersonalDetails.getDescription());
			myPersonalDetailsBean.put("profilePictureId",
					myPersonalDetails.getProfilePictureId());
			myPersonalDetailsBean.put("weddingDateStr",
					myPersonalDetails.getWeddingDateStr());
			myPersonalDetailsBean.put("websiteUrl",
					myPersonalDetails.getWebsiteUrl());
			myPersonalDetailsBean.put("facebookUrl",
					myPersonalDetails.getFacebookUrl());
			myPersonalDetailsBean.put("twitterUrl",
					myPersonalDetails.getTwitterUrl());
			myPersonalDetailsBean.put("linkedinUrl",
					myPersonalDetails.getLinkedinUrl());
			myPersonalDetailsBean.put("pinterestUrl",
					myPersonalDetails.getPinterestUrl());
			myPersonalDetailsBean.put("instagramUrl",
					myPersonalDetails.getInstagramUrl());
			myPersonalDetailsBean
					.put("country", myPersonalDetails.getCountry());
			myPersonalDetailsBean.put("state", myPersonalDetails.getState());
			myPersonalDetailsBean.put("city", myPersonalDetails.getCity());
			myPersonalDetailsBean.put("fileName",
					myPersonalDetails.getFileName());

			countryMap = commonMasterService.loadCountry();
			System.out.println("****" + countryMap);

			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String loadUpgradePlanDetails() {
		HttpSession session = request.getSession(true);
		try {

			List<DirectoryUpgradePlanFormBean> upgradePlanInfoList = registerService
					.loadUpgradePlanDetails();

			Iterator<?> itr = upgradePlanInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("planName", obj[0]);
				map.put("amount", obj[1]);
				if (Long.parseLong(obj[2].toString()) > 1
						&& Long.parseLong(obj[2].toString()) < 11) {
					map.put("validity", obj[2] + " Months");
				} else if (Long.parseLong(obj[2].toString()) > 11
						&& Long.parseLong(obj[2].toString()) < 23) {
					int months = Integer.valueOf(obj[2].toString());
					int years = months / 12; // 1
					int remainingMonths = months % 12; // 6
					if (remainingMonths == 0) {
						map.put("validity", years + " Year");
					} else if (remainingMonths > 0 && remainingMonths < 2) {
						map.put("validity", years + " Year" + remainingMonths
								+ " Month");
					} else {
						map.put("validity", years + " Year" + remainingMonths
								+ " Months");
					}
				} else if (Long.parseLong(obj[2].toString()) > 23) {
					int months = Integer.valueOf(obj[2].toString());
					int years = months / 12; // 1
					int remainingMonths = months % 12; // 6
					if (remainingMonths == 0) {
						map.put("validity", years + " Years");
					} else if (remainingMonths > 0 && remainingMonths < 2) {
						map.put("validity", years + " Years" + remainingMonths
								+ " Month");
					} else {
						map.put("validity", years + " Years" + remainingMonths
								+ " Months");
					}
				} else {
					map.put("validity", obj[2] + " Month");
				}
				map.put("getSMSAlert", Boolean.valueOf(obj[3].toString()));
				map.put("id", obj[4]);

				upgradePlanInfoBean.add(map);
			}
			
			DirectoryCategoryInfoFormBean categoryInfo = categoryInfoService
					.loadEditVenueDetails((Long) session.getAttribute("editCategoryId"));
			
			categoryInfoBean.put("id",
					categoryInfo.getId());
			categoryInfoBean.put("companyName", categoryInfo.getCompanyName());
			categoryInfoBean.put("price", categoryInfo.getPrice());
			categoryInfoBean.put("fileName", categoryInfo.getFileName());

			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String loadFeedBackInfoDetails() {
		HttpSession session = request.getSession(true);
		Session conn = HibernateUtil.getSessionFactory().openSession();
		try {

			List<?> sendInfolist = conn.getNamedQuery("getFeddbackInfoById")
						.setLong("id", Long.parseLong(session.getAttribute("sendInfoId").toString())).list();
			Iterator<?> itr = sendInfolist.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				session.setAttribute("bookingId",  Long.parseLong(obj[0].toString()));
				session.setAttribute("userId",  Long.parseLong(obj[1].toString()));
				session.setAttribute("vendorId",  Long.parseLong(obj[2].toString()));
				if(obj[3] != null) {
					session.setAttribute("feedbackStatus",  obj[3].toString());
				} else {
					session.setAttribute("feedbackStatus",  null);
				}
				if(obj[4] != null) {
					session.setAttribute("maxRating",  Long.parseLong(obj[4].toString()));
				} else {
					session.setAttribute("maxRating",  0);
				}
				if(obj[5] != null) {
					session.setAttribute("maxUsersRating", Long.parseLong(obj[5].toString()));
				} else {
					session.setAttribute("maxUsersRating", 0);
				}
			}
			
			DirectoryCategoryInfoFormBean categoryInfo = categoryInfoService
					.loadEditVenueDetails((Long) session.getAttribute("vendorId"));
			
			categoryInfoBean.put("id",
					categoryInfo.getId());
			categoryInfoBean.put("companyName", categoryInfo.getCompanyName());
			categoryInfoBean.put("price", categoryInfo.getPrice());
			categoryInfoBean.put("fileName", categoryInfo.getFileName());

			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String loadAllMasters() {

		orgMap = commonMasterService.loadOrg();
		roleMap = commonMasterService.loadRole();
		countryMap = commonMasterService.loadCountry();
		System.out.println("****" + countryMap);
		System.out.println("****" + orgMap);
		System.out.println("****" + roleMap);

		return "success";
	}

	public String loadStateByCountryId() {
		try {
			stateMap = commonMasterService.loadState(getCountry_Id());

			return "success";
		} catch (Exception e) {
			// e.printStackTrace();
			return "error";
		}

	}

	public String loadCityByStateId() {
		try {
			cityMap = commonMasterService.loadCity(getState_Id());

			return "success";
		} catch (Exception e) {
			// e.printStackTrace();
			return "error";
		}

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

	public Map<Object, Object> getStateMap() {
		return stateMap;
	}

	public void setStateMap(Map<Object, Object> stateMap) {
		this.stateMap = stateMap;
	}

	public Map<Object, Object> getCountryMap() {
		return countryMap;
	}

	public void setCountryMap(Map<Object, Object> countryMap) {
		this.countryMap = countryMap;
	}

	public Map<String, Object> getMyPersonalDetailsBean() {
		return myPersonalDetailsBean;
	}

	public void setMyPersonalDetailsBean(
			Map<String, Object> myPersonalDetailsBean) {
		this.myPersonalDetailsBean = myPersonalDetailsBean;
	}

	public Map<Object, Object> getOrgMap() {
		return orgMap;
	}

	public void setOrgMap(Map<Object, Object> orgMap) {
		this.orgMap = orgMap;
	}

	public Map<Object, Object> getRoleMap() {
		return roleMap;
	}

	public void setRoleMap(Map<Object, Object> roleMap) {
		this.roleMap = roleMap;
	}

	public List<Map<String, Object>> getProfilePicturesInfoBean() {
		return profilePicturesInfoBean;
	}

	public void setProfilePicturesInfoBean(
			List<Map<String, Object>> profilePicturesInfoBean) {
		this.profilePicturesInfoBean = profilePicturesInfoBean;
	}

	public Map<String, Object> getPartnerPreferenceDetailsBean() {
		return partnerPreferenceDetailsBean;
	}

	public void setPartnerPreferenceDetailsBean(
			Map<String, Object> partnerPreferenceDetailsBean) {
		this.partnerPreferenceDetailsBean = partnerPreferenceDetailsBean;
	}

	public List<Map<String, Object>> getSimilarPartnerPreferenceInfoBean() {
		return similarPartnerPreferenceInfoBean;
	}

	public void setSimilarPartnerPreferenceInfoBean(
			List<Map<String, Object>> similarPartnerPreferenceInfoBean) {
		this.similarPartnerPreferenceInfoBean = similarPartnerPreferenceInfoBean;
	}

	public List<Map<String, Object>> getWhoViewedMyProfileInfoBean() {
		return whoViewedMyProfileInfoBean;
	}

	public void setWhoViewedMyProfileInfoBean(
			List<Map<String, Object>> whoViewedMyProfileInfoBean) {
		this.whoViewedMyProfileInfoBean = whoViewedMyProfileInfoBean;
	}

	public Long getDeactivateProfileDays() {
		return deactivateProfileDays;
	}

	public void setDeactivateProfileDays(Long deactivateProfileDays) {
		this.deactivateProfileDays = deactivateProfileDays;
	}

	public String getDeleteProfileReason() {
		return deleteProfileReason;
	}

	public void setDeleteProfileReason(String deleteProfileReason) {
		this.deleteProfileReason = deleteProfileReason;
	}

	public Map<Object, Object> getCityMap() {
		return cityMap;
	}

	public void setCityMap(Map<Object, Object> cityMap) {
		this.cityMap = cityMap;
	}

	public Long getReligionId() {
		return religionId;
	}

	public void setReligionId(Long religionId) {
		this.religionId = religionId;
	}

	public Long getCountry_Id() {
		return country_Id;
	}

	public void setCountry_Id(Long country_Id) {
		this.country_Id = country_Id;
	}

	public Long getState_Id() {
		return state_Id;
	}

	public void setState_Id(Long state_Id) {
		this.state_Id = state_Id;
	}

	public List<Map<String, Object>> getUpgradePlanInfoBean() {
		return upgradePlanInfoBean;
	}

	public void setUpgradePlanInfoBean(
			List<Map<String, Object>> upgradePlanInfoBean) {
		this.upgradePlanInfoBean = upgradePlanInfoBean;
	}

	public void prepare() throws Exception {
		
	}

	/**
	 * @return the categoryInfoBean
	 */
	public Map<String, Object> getCategoryInfoBean() {
		return categoryInfoBean;
	}

	/**
	 * @param categoryInfoBean the categoryInfoBean to set
	 */
	public void setCategoryInfoBean(Map<String, Object> categoryInfoBean) {
		this.categoryInfoBean = categoryInfoBean;
	}

}
