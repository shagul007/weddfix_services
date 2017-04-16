package com.weddfix.web.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.weddfix.web.formbean.CityFormBean;
import com.weddfix.web.formbean.CommentsFormBean;
import com.weddfix.web.formbean.CountryFormBean;
import com.weddfix.web.formbean.CurrencyFormBean;
import com.weddfix.web.formbean.DirectoryCartDetailsFormBean;
import com.weddfix.web.formbean.DirectoryCategoryInfoAccountDetailsFormBean;
import com.weddfix.web.formbean.DirectoryRoleFormBean;
import com.weddfix.web.formbean.DirectoryUpgradePlanFormBean;
import com.weddfix.web.formbean.DirectoryUserProfileFormBean;
import com.weddfix.web.formbean.DirectoryVendorProfileFormBean;
import com.weddfix.web.formbean.DirectoryVendorPwResetFormBean;
import com.weddfix.web.formbean.LoginFormBean;
import com.weddfix.web.formbean.OrganizationFormBean;
import com.weddfix.web.formbean.DirectoryPromotionDetailsFormBean;
import com.weddfix.web.formbean.DirectoryUserPwResetFormBean;
import com.weddfix.web.formbean.StateFormBean;
import com.weddfix.web.formbean.StatusFormBean;
import com.weddfix.web.formbean.SubscribersFormBean;
import com.weddfix.web.interfaces.CommonMasterDao;
import com.weddfix.web.util.CommonConstants;
import com.weddfix.web.util.HibernateUtil;
import com.weddfix.web.util.MailMessage;

/**
 * 
 * 
 * @author Shagul Hameed
 * 
 */
public class CommonMasterDaoImpl extends ActionSupport implements CommonMasterDao, SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Session conn;

	private Map<Object, Object> orgMasterMap = new HashMap<Object, Object>();
	private Map<Object, Object> roleMasterMap = new HashMap<Object, Object>();
	private Map<Object, Object> statusMasterMap = new HashMap<Object, Object>();
	private Map<Object, Object> planMasterMap = new HashMap<Object, Object>();
	private Map<Object, Object> currencyMasterMap = new HashMap<Object, Object>();
	private Map<Object, Object> countryMasterMap = new HashMap<Object, Object>();
	private Map<Object, Object> stateMasterMap = new HashMap<Object, Object>();
	private Map<Object, Object> cityMasterMap = new HashMap<Object, Object>();

	List<DirectoryRoleFormBean> roleMasterList;
	List<OrganizationFormBean> orgMasterList;
	List<StatusFormBean> statusMasterList;
	List<CurrencyFormBean> currencyMasterList;
	List<CountryFormBean> countryMasterList;
	List<StateFormBean> stateMasterList;
	List<CityFormBean> cityMasterList;
	List<DirectoryUpgradePlanFormBean> upgradePlanMasterList;

	OrganizationFormBean orgMasterFormBean;
	DirectoryRoleFormBean roleMasterFormBean;
	StatusFormBean statusMasterFormBean;
	CurrencyFormBean currencyMasterFormBean;
	CountryFormBean countryMasterFormBean;
	StateFormBean stateMasterFormBean;
	CityFormBean cityMasterFormBean;
	DirectoryUpgradePlanFormBean upgradePlanMasterFormBean;

	private static final Logger logger = Logger
			.getLogger(CommonMasterDaoImpl.class);

	public Map<Object, Object> loadRoleMaster() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> getRoleList = conn.getNamedQuery("getRoleMaster").list();
			Iterator<?> itr = getRoleList.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				roleMasterMap.put(obj[0], obj[1]);
				logger.info("DAO IMPL =" + obj[0] + "" + obj[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		Map<Object, Object> sortedMap = sortMapByValues(roleMasterMap);
		return sortedMap;
	}
	
	public Map<Object, Object> loadStatusMaster() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> getStatusList = conn.getNamedQuery("getStatusMaster").list();
			Iterator<?> itr = getStatusList.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				statusMasterMap.put(obj[0], obj[1]);
				logger.info("DAO IMPL =" + obj[0] + "" + obj[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		Map<Object, Object> sortedMap = sortMapByValues(statusMasterMap);
		return sortedMap;
	}
	
	public Map<Object, Object> loadPlanMaster() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> getPlanList = conn.getNamedQuery("getPlanMaster").list();
			Iterator<?> itr = getPlanList.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				planMasterMap.put(obj[0], obj[1]);
				logger.info("DAO IMPL =" + obj[0] + "" + obj[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		Map<Object, Object> sortedMap = sortMapByValues(planMasterMap);
		return sortedMap;
	}
	
	public Map<Object, Object> loadOrgMaster() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> getOrgList = conn.getNamedQuery("getOrgMaster").list();
			Iterator<?> itr = getOrgList.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				orgMasterMap.put(obj[0], obj[1]);
				logger.info("DAO IMPL =" + obj[0] + "" + obj[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		Map<Object, Object> sortedMap = sortMapByValues(orgMasterMap);
		return sortedMap;
	}
	
	public Map<Object, Object> loadCurrencyMaster() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> getCurrencyList = conn.getNamedQuery("getCurrencyMaster").list();
			Iterator<?> itr = getCurrencyList.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				currencyMasterMap.put(obj[0], obj[1]);
				logger.info("DAO IMPL =" + obj[0] + "" + obj[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		Map<Object, Object> sortedMap = sortMapByValues(currencyMasterMap);
		return sortedMap;
	}
	
	public Map<Object, Object> loadStateMaster() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> getStateList = conn.getNamedQuery("getStatesMaster").list();
			Iterator<?> itr = getStateList.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				stateMasterMap.put(obj[0], obj[1]);
				logger.info("DAO IMPL =" + obj[0] + "" + obj[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		Map<Object, Object> sortedMap = sortMapByValues(stateMasterMap);
		return sortedMap;
	}
	
	public Map<Object, Object> loadStateMaster(Long countryId) {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> getStateList = conn.getNamedQuery("getStateMaster").setLong("countryId", countryId).list();
			Iterator<?> itr = getStateList.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				stateMasterMap.put(obj[0], obj[1]);
				logger.info("DAO IMPL =" + obj[0] + "" + obj[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		Map<Object, Object> sortedMap = sortMapByValues(stateMasterMap);
		return sortedMap;
	}

	public Map<Object, Object> loadCountryMaster() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> getCountryList = conn.getNamedQuery("getCountryMaster")
					.list();
			Iterator<?> itr = getCountryList.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				countryMasterMap.put(obj[0], obj[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		Map<Object, Object> sortedMap = sortMapByValues(countryMasterMap);
		return sortedMap;
	}
	
	public Map<Object, Object> loadCityMaster(Long stateId) {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> getCityList = conn.getNamedQuery("getCityMaster").setLong("stateId", stateId).list();
			Iterator<?> itr = getCityList.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				cityMasterMap.put(obj[0], obj[1]);
				logger.info("DAO IMPL =" + obj[0] + "" + obj[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		Map<Object, Object> sortedMap = sortMapByValues(cityMasterMap);
		return sortedMap;
	}
	
	public Map<Object, Object> loadAllCity() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> getCityList = conn.getNamedQuery("getAllCity").list();
			Iterator<?> itr = getCityList.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				cityMasterMap.put(obj[0], obj[1]);
				logger.info("DAO IMPL =" + obj[0] + "" + obj[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		Map<Object, Object> sortedMap = sortMapByValues(cityMasterMap);
		return sortedMap;
	}
	
	public Map<Object, Object> loadPaidVendorCity() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> getCityList = conn.getNamedQuery("getPaidVendorCity").list();
			Iterator<?> itr = getCityList.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				cityMasterMap.put(obj[0], obj[1]);
				logger.info("DAO IMPL =" + obj[0] + "" + obj[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		Map<Object, Object> sortedMap = sortMapByValues(cityMasterMap);
		return sortedMap;
	}
	
	public LoginFormBean getUserProfileByEmail(String email) {
		LoginFormBean loginFormBean = new LoginFormBean();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			List<?> userProfile = conn.getNamedQuery("getUserProfileByEmail")
					.setString("email", email.toLowerCase()).list();
			Iterator<?> itr = userProfile.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				loginFormBean.setId(Long.parseLong(obj[0].toString()));
				loginFormBean.setFullName(obj[1].toString());
				loginFormBean.setEmail(obj[2].toString());
				loginFormBean.setPassword(obj[3].toString());
				loginFormBean.setPasswordHash(obj[4].toString());
				loginFormBean.setMobile(Long.parseLong(obj[5].toString()));
				loginFormBean.setRole(obj[6].toString());
				loginFormBean.setStatus(obj[7].toString());
				if (obj[8] != null) {
					loginFormBean.setDeleteProfileReason(obj[8].toString());
				}
				if (obj[9] != null) {
					loginFormBean.setIsProfileDeleted(Boolean.valueOf(obj[9].toString()));
				}
				
				if (obj[10] != null) {
					loginFormBean.setVerifyMobileNumber(obj[10].toString());
				}
				if (obj[11] != null) {
					loginFormBean.setVerifyEmailId(obj[11].toString());
				}
				if (obj[12] != null) {
					loginFormBean.setVerifiedMobileNumber(Boolean.valueOf(obj[12].toString()));
				}
				if (obj[13] != null) {
					loginFormBean.setVerifiedEmailId(Boolean.valueOf(obj[13].toString()));
				}
				if (obj[14] != null) {
					loginFormBean.setAccountType(obj[14].toString());
				}
				if (obj[15] != null) {
					loginFormBean.setMyPlanId(Long.parseLong(obj[15].toString()));
				}
				if (obj[16] != null) {
					loginFormBean.setCategoryInfoId(Long.parseLong(obj[16].toString()));
				}
			}
			if (loginFormBean != null) {
				return loginFormBean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return loginFormBean;
	}
	
	public LoginFormBean getVendorProfileByEmail(String email) {
		LoginFormBean loginFormBean = new LoginFormBean();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			List<?> userProfile = conn.getNamedQuery("getVendorProfileByEmail")
					.setString("email", email.toLowerCase()).list();
			Iterator<?> itr = userProfile.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				loginFormBean.setId(Long.parseLong(obj[0].toString()));
				loginFormBean.setFullName(obj[1].toString());
				loginFormBean.setEmail(obj[2].toString());
				loginFormBean.setPassword(obj[3].toString());
				loginFormBean.setPasswordHash(obj[4].toString());
				loginFormBean.setMobile(Long.parseLong(obj[5].toString()));
				loginFormBean.setRole(obj[6].toString());
				loginFormBean.setStatus(obj[7].toString());
				if (obj[8] != null) {
					loginFormBean.setDeleteProfileReason(obj[8].toString());
				}
				if (obj[9] != null) {
					loginFormBean.setIsProfileDeleted(Boolean.valueOf(obj[9].toString()));
				}
				
				if (obj[10] != null) {
					loginFormBean.setVerifyMobileNumber(obj[10].toString());
				}
				if (obj[11] != null) {
					loginFormBean.setVerifyEmailId(obj[11].toString());
				}
				if (obj[12] != null) {
					loginFormBean.setVerifiedMobileNumber(Boolean.valueOf(obj[12].toString()));
				}
				if (obj[13] != null) {
					loginFormBean.setVerifiedEmailId(Boolean.valueOf(obj[13].toString()));
				}
				if (obj[14] != null) {
					loginFormBean.setAccountType(obj[14].toString());
				}
				if (obj[15] != null) {
					loginFormBean.setMyPlanId(Long.parseLong(obj[15].toString()));
				}
			}
			if (loginFormBean != null) {
				return loginFormBean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return loginFormBean;
	}

	public String checkUserAlreadyExist(String email) {
		String checkUser = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			List<?> userProfile = conn.getNamedQuery("getUserProfileByEmail")
					.setString("email", email).list();
			Iterator<?> itr = userProfile.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				checkUser = obj[2].toString();
			}
			if (checkUser != null) {
				return checkUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return checkUser;
	}
	
	public String checkCompanyNameAlredyExist(String companyName) {
		String checkCompanyName = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			List<?> categoryInfo = conn.getNamedQuery("getCategoryInfoByCompanyName")
					.setString("companyName", companyName.trim().toLowerCase()).list();
			Iterator<?> itr = categoryInfo.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				checkCompanyName = obj[1].toString();
			}
			if (checkCompanyName != null) {
				return checkCompanyName;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return checkCompanyName;
	}
	
	public String checkOtherCompanyNameAlredyExist(String companyName, String oldCompanyName) {
		String checkCompanyName = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			List<?> categoryInfo = conn.getNamedQuery("getCategoryInfoByNotMyCompanyName")
					.setString("companyName", companyName.trim().toLowerCase()).setString("oldCompanyName", oldCompanyName.trim().toLowerCase()).list();
			Iterator<?> itr = categoryInfo.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				checkCompanyName = obj[1].toString();
			}
			if (checkCompanyName != null) {
				return checkCompanyName;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return checkCompanyName;
	}
	
	public String checkVendorAlreadyExist(String email) {
		String checkUser = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			List<?> userProfile = conn.getNamedQuery("getVendorProfileByEmail")
					.setString("email", email).list();
			Iterator<?> itr = userProfile.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				checkUser = obj[2].toString();
			}
			if (checkUser != null) {
				return checkUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return checkUser;
	}
	
	public String checkPartnerPreferenceAlreadyExist(Long userId) {
		String checkUser = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			List<?> userProfile = conn.getNamedQuery("getPartnerPreferenceByUserId")
					.setLong("userId", userId).list();
			Iterator<?> itr = userProfile.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				checkUser = obj[0].toString();
			}
			if (checkUser != null) {
				return checkUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return checkUser;
	}

	public String checkSubscriberEmailAlreadyExist(String email) {
		String checkUser = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			List<?> userProfile = conn.getNamedQuery("getSubscriberByEmail")
					.setString("email", email)
					.setLong("statusId", CommonConstants.ACTIVE).list();
			Iterator<?> itr = userProfile.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				checkUser = obj[1].toString();
			}
			if (checkUser != null) {
				return checkUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return checkUser;
	}

	Long id;

	public Long saveCommentsDetails(CommentsFormBean commentsFormBean) {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Insert comment details Method--------------");

			Transaction tx = conn.beginTransaction();
			commentsFormBean.setCreatedDate(new Date());
			id = (Long) conn.save(commentsFormBean);

			tx.commit();

		} catch (HibernateException hibernateException) {

			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return id;
	}

	public Long saveSubscriberDetails(SubscribersFormBean subscribersFormBean) {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Insert subscriber details Method--------------");

			Transaction tx = conn.beginTransaction();
			subscribersFormBean.setSubscriberEmail(subscribersFormBean.getSubscriberEmail().toLowerCase());
			subscribersFormBean.setStatusId(CommonConstants.ACTIVE);
			subscribersFormBean.setCreatedDate(new Date());
			id = (Long) conn.save(subscribersFormBean);

			tx.commit();

		} catch (HibernateException hibernateException) {

			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return id;
	}

	@SuppressWarnings("unchecked")
	public List<DirectoryUserProfileFormBean> loadAllUserRoleDetails() {
		List<DirectoryUserProfileFormBean> userProfileList = new ArrayList<DirectoryUserProfileFormBean>();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			userProfileList = conn.getNamedQuery("getAllUserProfiles").list();

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return userProfileList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectoryVendorProfileFormBean> loadAllVendorRoleDetails() {
		List<DirectoryVendorProfileFormBean> userProfileList = new ArrayList<DirectoryVendorProfileFormBean>();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			userProfileList = conn.getNamedQuery("getAllVendorProfiles").list();

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return userProfileList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectoryUpgradePlanFormBean> loadMyAccountDetails(Long userId) {
		List<DirectoryUpgradePlanFormBean> myAccountDetailList = new ArrayList<DirectoryUpgradePlanFormBean>();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			myAccountDetailList = conn.getNamedQuery("getAccountDetailsByUserId").setLong("userId", userId).list();

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return myAccountDetailList;
	}
	
	public String updateUserRoleAndStatus(Long userId, Long userRole, String userRoleDesc,
			Long userStatus, Long updatedBy) {
		String status = null;
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		try {
			Boolean isProfileDeleted = false;
			if(userStatus == CommonConstants.ACTIVE){
				isProfileDeleted = false;
			} else {
				isProfileDeleted = true;
			}
			conn.getNamedQuery("updateUserRoleAndStatusById")
					.setLong("roleId", userRole)
					.setLong("statusId", userStatus)
					.setLong("id", userId)
					.setLong("updatedBy", updatedBy)
					.setDate("updatedDate", new Date()).executeUpdate();
			conn.getNamedQuery("updateIsProfileDeletedByUserId")
					.setBoolean("isProfileDeleted", isProfileDeleted)
					.setLong("userId", userId)
					.setLong("updatedBy", updatedBy)
					.setDate("updatedDate", new Date()).executeUpdate();
			conn.getNamedQuery("updateRoleDetailsByUserId")
					.setLong("userId", userId)
					.setLong("roleId", userRole)
					.setString("description", userRoleDesc)
					.setLong("updatedBy", updatedBy)
					.setDate("updatedDate", new Date()).executeUpdate();
			tx.commit();
			status = "success";

		} catch (Exception e) {
			tx.rollback();
			status = null;
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return status;
	}
	
	public String updateVendorRoleAndStatus(Long userId, Long userRole, String userRoleDesc,
			Long userStatus, Long updatedBy, Long userPlan) {
		String status = null;
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		try {
			Boolean isProfileDeleted = false;
			if(userStatus == CommonConstants.ACTIVE){
				isProfileDeleted = false;
			} else {
				isProfileDeleted = true;
			}
			conn.getNamedQuery("updateVendorRoleAndStatusById")
					.setLong("roleId", userRole)
					.setLong("statusId", userStatus)
					.setLong("id", userId)
					.setLong("updatedBy", updatedBy)
					.setDate("updatedDate", new Date()).executeUpdate();
			conn.getNamedQuery("updateVendorProfileDeletedByUserId")
					.setBoolean("isProfileDeleted", isProfileDeleted)
					.setLong("accountType", userPlan)
					.setLong("userId", userId)
					.setLong("updatedBy", updatedBy)
					.setDate("updatedDate", new Date()).executeUpdate();
			conn.getNamedQuery("updateVendorRoleDetailsByUserId")
					.setLong("userId", userId)
					.setLong("roleId", userRole)
					.setString("description", userRoleDesc)
					.setLong("updatedBy", updatedBy)
					.setDate("updatedDate", new Date()).executeUpdate();
			tx.commit();
			status = "success";

		} catch (Exception e) {
			tx.rollback();
			status = null;
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return status;
	}

	public DirectoryUserPwResetFormBean getUserByPasswordResetKey(String key)
			throws Exception {
		DirectoryUserPwResetFormBean pwReset = new DirectoryUserPwResetFormBean();

		conn = HibernateUtil.getSessionFactory().openSession();
		try {
			List<?> userProfile = conn.getNamedQuery("getUserIdByResetKey")
					.setString("resetKey", key).list();
			if (userProfile.isEmpty()) {
				pwReset = null;
			} else {
				Iterator<?> itr = userProfile.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					pwReset.setUserId(Long.parseLong(obj[1].toString()));
				}
			}

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return pwReset;
	}
	
	public DirectoryVendorPwResetFormBean getVendorByPasswordResetKey(String key)
			throws Exception {
		DirectoryVendorPwResetFormBean pwReset = new DirectoryVendorPwResetFormBean();

		conn = HibernateUtil.getSessionFactory().openSession();
		try {
			List<?> userProfile = conn.getNamedQuery("getVendorUserIdByResetKey")
					.setString("resetKey", key).list();
			if (userProfile.isEmpty()) {
				pwReset = null;
			} else {
				Iterator<?> itr = userProfile.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					pwReset.setUserId(Long.parseLong(obj[1].toString()));
				}
			}

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return pwReset;
	}

	public String resetPassword(Long userId, String pwdHash) {
		String status = null;
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		try {
			conn.getNamedQuery("updateResetPasswordById")
					.setString("password", pwdHash)
					.setString("passwordHash", pwdHash).setLong("id", userId)
					.setLong("updatedBy", userId)
					.setDate("updatedDate", new Date())
					.executeUpdate();
			conn.getNamedQuery("deletePwResetByUserId")
					.setLong("userId", userId).executeUpdate();
			tx.commit();
			status = "success";

		} catch (Exception e) {
			tx.rollback();
			status = null;
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return status;
	}
	
	public String resetVendorPassword(Long userId, String pwdHash) {
		String status = null;
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		try {
			conn.getNamedQuery("updateResetVendorPasswordById")
					.setString("password", pwdHash)
					.setString("passwordHash", pwdHash).setLong("id", userId)
					.setLong("updatedBy", userId)
					.setDate("updatedDate", new Date())
					.executeUpdate();
			conn.getNamedQuery("deleteVendorPwResetByUserId")
					.setLong("userId", userId).executeUpdate();
			tx.commit();
			status = "success";

		} catch (Exception e) {
			tx.rollback();
			status = null;
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public List<DirectoryRoleFormBean> loadRoleMasterList() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			roleMasterList = conn.createSQLQuery(
					"select id, rolename, roledesc, isactive from directoryrole").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return roleMasterList;
	}

	public void updateRoleMaster(Long id, String roleName, String roleDesc,
			Boolean isActive, Long updatedBy) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			roleMasterFormBean = (DirectoryRoleFormBean) conn
					.get(DirectoryRoleFormBean.class, id);
			roleMasterFormBean.setRoleName(roleName);
			roleMasterFormBean.setRoleDesc(roleDesc);
			roleMasterFormBean.setIsActive(isActive);
			roleMasterFormBean.setUpdatedBy(updatedBy);
			roleMasterFormBean.setUpdatedDate(new Date());
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void addRoleMaster(DirectoryRoleFormBean roleMasterFormBean) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			conn.save(roleMasterFormBean);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void deleteRoleMaster(Long id) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = conn.beginTransaction();
			roleMasterFormBean = (DirectoryRoleFormBean) conn
					.get(DirectoryRoleFormBean.class, id);
			conn.delete(roleMasterFormBean);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}
	

	
	@SuppressWarnings("unchecked")
	public List<StatusFormBean> loadStatusMasterList() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			statusMasterList = conn.createSQLQuery(
					"select id, statusname, statusdesc, isactive from status").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return statusMasterList;
	}

	public void updateStatusMaster(Long id, String statusName, String statusDesc,
			Boolean isActive, Long updatedBy) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			statusMasterFormBean = (StatusFormBean) conn
					.get(StatusFormBean.class, id);
			statusMasterFormBean.setStatusName(statusName);
			statusMasterFormBean.setStatusDesc(statusDesc);
			statusMasterFormBean.setIsActive(isActive);
			statusMasterFormBean.setUpdatedBy(updatedBy);
			statusMasterFormBean.setUpdatedDate(new Date());
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void addStatusMaster(StatusFormBean statusMasterFormBean) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			conn.save(statusMasterFormBean);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void deleteStatusMaster(Long id) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = conn.beginTransaction();
			statusMasterFormBean = (StatusFormBean) conn
					.get(StatusFormBean.class, id);
			conn.delete(statusMasterFormBean);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<OrganizationFormBean> loadOrganizationMasterList() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			orgMasterList = conn.createSQLQuery(
					"select id, orgname, orgdesc, isactive from organization").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return orgMasterList;
	}

	public void updateOrganizationMaster(Long id, String orgName, String orgDesc,
			Boolean isActive, Long updatedBy) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			orgMasterFormBean = (OrganizationFormBean) conn
					.get(OrganizationFormBean.class, id);
			orgMasterFormBean.setOrgName(orgName);
			orgMasterFormBean.setOrgDesc(orgDesc);
			orgMasterFormBean.setIsActive(isActive);
			orgMasterFormBean.setUpdatedBy(updatedBy);
			orgMasterFormBean.setUpdatedDate(new Date());
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void addOrganizationMaster(OrganizationFormBean organizationMasterFormBean) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			conn.save(organizationMasterFormBean);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void deleteOrganizationMaster(Long id) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = conn.beginTransaction();
			orgMasterFormBean = (OrganizationFormBean) conn
					.get(OrganizationFormBean.class, id);
			conn.delete(orgMasterFormBean);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CurrencyFormBean> loadCurrencyMasterList() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			currencyMasterList = conn.createSQLQuery(
					"select id, currency, currencydesc, isactive from currency").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return currencyMasterList;
	}

	public void updateCurrencyMaster(Long id, String currency, String currencyDesc,
			Boolean isActive, Long updatedBy) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			currencyMasterFormBean = (CurrencyFormBean) conn
					.get(CurrencyFormBean.class, id);
			currencyMasterFormBean.setCurrency(currency);
			currencyMasterFormBean.setCurrencyDesc(currencyDesc);
			currencyMasterFormBean.setIsActive(isActive);
			currencyMasterFormBean.setUpdatedBy(updatedBy);
			currencyMasterFormBean.setUpdatedDate(new Date());
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void addCurrencyMaster(CurrencyFormBean currencyMasterFormBean) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			conn.save(currencyMasterFormBean);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void deleteCurrencyMaster(Long id) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = conn.beginTransaction();
			currencyMasterFormBean = (CurrencyFormBean) conn
					.get(CurrencyFormBean.class, id);
			conn.delete(currencyMasterFormBean);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CountryFormBean> loadCountryMasterList() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			countryMasterList = conn.createSQLQuery(
					"select id, countrycode, countryname, countrydesc, isactive from country").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return countryMasterList;
	}

	public void updateCountryMaster(Long id, String countryCode, String countryName, String countryDesc,
			Boolean isActive, Long updatedBy) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			countryMasterFormBean = (CountryFormBean) conn
					.get(CountryFormBean.class, id);
			countryMasterFormBean.setCountryCode(countryCode);
			countryMasterFormBean.setCountryName(countryName);
			countryMasterFormBean.setCountryDesc(countryDesc);
			countryMasterFormBean.setIsActive(isActive);
			countryMasterFormBean.setUpdatedBy(updatedBy);
			countryMasterFormBean.setUpdatedDate(new Date());
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void addCountryMaster(CountryFormBean countryMasterFormBean) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			conn.save(countryMasterFormBean);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void deleteCountryMaster(Long id) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = conn.beginTransaction();
			countryMasterFormBean = (CountryFormBean) conn
					.get(CountryFormBean.class, id);
			conn.delete(currencyMasterFormBean);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<StateFormBean> loadStateMasterList() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			stateMasterList = conn.createSQLQuery(
					"SELECT s.id, s.countryid, s.statename, s.isactive, c.countryname FROM state s JOIN country c ON c.id = s.countryid").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return stateMasterList;
	}

	public void updateStateMaster(Long id, String stateName, Long countryId,
			Boolean isActive, Long updatedBy) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			stateMasterFormBean = (StateFormBean) conn
					.get(StateFormBean.class, id);
			stateMasterFormBean.setStateName(stateName);
//			stateMasterFormBean.setStateDesc(stateDesc);
			stateMasterFormBean.setCountryId(countryId);
			stateMasterFormBean.setIsActive(isActive);
			stateMasterFormBean.setUpdatedBy(updatedBy);
			stateMasterFormBean.setUpdatedDate(new Date());
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void addStateMaster(StateFormBean stateMasterFormBean) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			conn.save(stateMasterFormBean);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void deleteStateMaster(Long id) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = conn.beginTransaction();
			stateMasterFormBean = (StateFormBean) conn
					.get(StateFormBean.class, id);
			conn.delete(stateMasterFormBean);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CityFormBean> loadCityMasterList() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			cityMasterList = conn.createSQLQuery(
					"SELECT c.id, c.stateid, c.cityname, c.isactive, s.statename FROM city c JOIN state s ON s.id = c.stateid").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return cityMasterList;
	}

	public void updateCityMaster(Long id, String cityName, Long stateId,
			Boolean isActive, Long updatedBy) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			cityMasterFormBean = (CityFormBean) conn
					.get(CityFormBean.class, id);
			cityMasterFormBean.setCityName(cityName);
//			cityMasterFormBean.setCityDesc(cityDesc);
			cityMasterFormBean.setStateId(stateId);
			cityMasterFormBean.setIsActive(isActive);
			cityMasterFormBean.setUpdatedBy(updatedBy);
			cityMasterFormBean.setUpdatedDate(new Date());
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void addCityMaster(CityFormBean cityMasterFormBean) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			conn.save(cityMasterFormBean);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void deleteCityMaster(Long id) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = conn.beginTransaction();
			cityMasterFormBean = (CityFormBean) conn
					.get(CityFormBean.class, id);
			conn.delete(cityMasterFormBean);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectoryUpgradePlanFormBean> loadUpgradePlanMasterList() {
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			upgradePlanMasterList = conn.createSQLQuery(
					"SELECT id, planName, amount, validity, getsmsalert, isactive FROM directoryupgradeplandetails").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return upgradePlanMasterList;
	}

	public void updateUpgradePlanMaster(Long id, String planName, Long amount, Long validity, Boolean getSMSAlert,
			Boolean isActive, Long updatedBy) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			upgradePlanMasterFormBean = (DirectoryUpgradePlanFormBean) conn
					.get(DirectoryUpgradePlanFormBean.class, id);
			upgradePlanMasterFormBean.setPlanName(planName);
			upgradePlanMasterFormBean.setAmount(amount);
			upgradePlanMasterFormBean.setValidity(validity);
			upgradePlanMasterFormBean.setGetSMSAlert(getSMSAlert);
			upgradePlanMasterFormBean.setIsActive(isActive);
			upgradePlanMasterFormBean.setUpdatedBy(updatedBy);
			upgradePlanMasterFormBean.setUpdatedDate(new Date());
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void addUpgradePlanMaster(DirectoryUpgradePlanFormBean upgradePlanMasterFormBean) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = conn.beginTransaction();
			conn.save(upgradePlanMasterFormBean);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}

	public void deleteUpgradePlanMaster(Long id) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = conn.beginTransaction();
			upgradePlanMasterFormBean = (DirectoryUpgradePlanFormBean) conn
					.get(DirectoryUpgradePlanFormBean.class, id);
			conn.delete(upgradePlanMasterFormBean);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
	}
	
	public void setSession(Map<String, Object> arg0) {

	}

	private static Map<Object, Object> sortMapByValues(Map<Object, Object> heightMasterMap2) {
        
        Set<Entry<Object, Object>> mapEntries = heightMasterMap2.entrySet();
        
        /*System.out.println("Values and Keys before sorting ");
        for(Entry<Object, Object> entry : mapEntries) {
            System.out.println(entry.getValue() + " - "+ entry.getKey());
        }*/
        
        // used linked list to sort, because insertion of elements in linked list is faster than an array list. 
        List<Entry<Object, Object>> aList = new LinkedList<Entry<Object, Object>>(mapEntries);

        // sorting the List
        Collections.sort(aList, new Comparator<Entry<Object, Object>>() {

            public int compare(Entry<Object, Object> ele1,
                    Entry<Object, Object> ele2) {
                
                return ((String) ele1.getValue()).compareTo((String) ele2.getValue());
            }
        });
        
        // Storing the list into Linked HashMap to preserve the order of insertion. 
        Map<Object, Object> aMap2 = new LinkedHashMap<Object, Object>();
        for(Entry<Object, Object> entry: aList) {
            aMap2.put(entry.getKey(), entry.getValue());
        }
        
        // printing values after soring of map
        /*System.out.println("Value " + " - " + "Key");
        for(Entry<Object, Object> entry : aMap2.entrySet()) {
            System.out.println(entry.getValue() + " - " + entry.getKey());
        }*/
		return aMap2;
        
    }
	
	@SuppressWarnings("unchecked")
	public List<DirectoryPromotionDetailsFormBean> loadPromotionDetails() {
		List<DirectoryPromotionDetailsFormBean> promotionDetailsList = new ArrayList<DirectoryPromotionDetailsFormBean>();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			promotionDetailsList = conn.getNamedQuery("getPromotionDetails").list();

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return promotionDetailsList;
	}
	
	Long user;
	
	public Long savePromotionDetails(DirectoryPromotionDetailsFormBean promotionDetailsFormBean) {

		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Insert promotion details Method--------------");

			Transaction tx = conn.beginTransaction();
			promotionDetailsFormBean.setStatusId(CommonConstants.ACTIVE);
			promotionDetailsFormBean.setCreatedDate(new Date());
			user = (Long) conn.save(promotionDetailsFormBean);
			tx.commit();

		} catch (Exception hibernateException) {

			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return user;
	}
	
	public Long deletePromotion(Long promoId) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		Long id = null;
		try {
			conn.getNamedQuery("deletePromotionById")
					.setLong("id", promoId).executeUpdate();
			tx.commit();
			id = 1L;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return id;
	}
	
	@SuppressWarnings("unchecked")
	public Long sendPromotion(String promoEmailId, String promoCode, String sendPromoForAll, Long userId) {
		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		Long id = null;
		
		List<DirectoryPromotionDetailsFormBean> promtionDetails = conn.getNamedQuery("getPromotionDetailsByPromoCode")
		.setString("promoCode", promoCode).list();
		
		try {
		if(!promtionDetails.isEmpty()) {
		
		Properties props = new Properties();
		Iterator<?> itr = promtionDetails.iterator();
		while (itr.hasNext()) {
			Object[] obj = (Object[]) itr.next();
	        props.put("validity", obj[0].toString());
	        props.put("discount", obj[1].toString());
		}
		
		com.weddfix.web.util.MailMessage msg = null;
		
		String sentTo = null;
		if(sendPromoForAll.equals("true")) {
			sentTo = "All";
			List<String> emails = conn.getNamedQuery("getAllEmails").list();
			for(String email : emails) {
			props.put("promocode", promoCode);
	        props.put("email", email);
	        props.put("url", getText("url")+ "/promotion_check_valid.jsp?promocode="+promoCode);
	        msg = new com.weddfix.web.util.MailMessage(props, "promotion.vm", email, "Your Weddfix Promo Code");
	        msg.send();
			}
		} else {
			sentTo = promoEmailId;
			props.put("promocode", promoCode);
	        props.put("email", promoEmailId);
	        props.put("url", getText("url")+ "/promotion_check_valid.jsp?promocode="+promoCode);
	        msg = new com.weddfix.web.util.MailMessage(props, "promotion.vm", promoEmailId,"Your Weddfix Promo Code");
	        msg.send();
		}
		
			conn.getNamedQuery("updatePromoDetailsByPromoCode")
					.setString("emailTo", sentTo).setDate("sentDate", new Date()).setLong("updatedBy", userId).setDate("updatedDate", new Date()).setString("promoCode", promoCode).executeUpdate();
			tx.commit();
			id = 1L;
		}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return id;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DirectoryCartDetailsFormBean> loadCartDetails(Long userId) {
		List<DirectoryCartDetailsFormBean> cartDetailsList = new ArrayList<DirectoryCartDetailsFormBean>();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			cartDetailsList = conn.getNamedQuery("getCartDetails").setLong("userId", userId).list();

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return cartDetailsList;
	}
	
	public Long saveCartDetails(DirectoryCartDetailsFormBean cartDetailsFormBean) {

		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Insert cart details Method--------------");

			Transaction tx = conn.beginTransaction();
			
			if(cartDetailsFormBean.getUpdatedBy() != null) {
				conn.getNamedQuery("updateCartDetailsById")
				.setLong("id", cartDetailsFormBean.getId())
				.setLong("planId",
						cartDetailsFormBean.getPlanId())
				.setLong("updatedBy",
						cartDetailsFormBean.getUpdatedBy())
				.setDate("updatedDate", new Date()).executeUpdate();
				tx.commit();
				user = 1L;
			} else {
				cartDetailsFormBean.setStatusId(CommonConstants.ACTIVE);
				cartDetailsFormBean.setCreatedDate(new Date());
				user = (Long) conn.save(cartDetailsFormBean);
				tx.commit();
			}

		} catch (Exception hibernateException) {

			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectoryPromotionDetailsFormBean> validatePromoCode(String promoCode) {
		List<DirectoryPromotionDetailsFormBean> promotionDetailsList = new ArrayList<DirectoryPromotionDetailsFormBean>();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			promotionDetailsList = conn.getNamedQuery("validatePromoCode").setString("promoCode", promoCode).list();

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return promotionDetailsList;
	}
	
	public void updateAccountDetails(String txnId, Long planId, Long userId, String acceptedPromoCode, Long categoryInfoId) {

		try {
			
			conn = HibernateUtil.getSessionFactory().openSession();
			
			logger.info("-----------Update account details Method--------------");
			
			Transaction tx = conn.beginTransaction();
			
			DirectoryCategoryInfoAccountDetailsFormBean categoryInfoAccountDetailsFormBean = new DirectoryCategoryInfoAccountDetailsFormBean(); 

				categoryInfoAccountDetailsFormBean.setUserId(userId);
				categoryInfoAccountDetailsFormBean.setCategoryInfoId(categoryInfoId);
				categoryInfoAccountDetailsFormBean.setAccountType(planId);
				categoryInfoAccountDetailsFormBean.setTransactionId(txnId);
				categoryInfoAccountDetailsFormBean.setTransactionStatus(CommonConstants.SUCCESS);
				categoryInfoAccountDetailsFormBean.setStatusId(CommonConstants.ACTIVE);
				categoryInfoAccountDetailsFormBean.setCreatedBy(userId);
				categoryInfoAccountDetailsFormBean.setCreatedDate(new Date());
				id = (Long) conn.save(categoryInfoAccountDetailsFormBean);

		        conn.getNamedQuery("deleteCartDetailsByUserId")
		        .setLong("userId", userId).executeUpdate();
		        if(acceptedPromoCode != null) {
			        conn.getNamedQuery("updateAcceptedPromoDetailsByUserId")
			        .setString("acceptedPromoCode", acceptedPromoCode)
			        .setLong("userId", userId)
			        .setDate("acceptedDate", new Date())
			        .setLong("updatedBy", userId)
			        .setDate("updatedDate", new Date()).executeUpdate();
		        }
		        
		        tx.commit();
		        
		        ResourceBundle rb = ResourceBundle.getBundle("ApplicationResources");
		        
		        conn = HibernateUtil.getSessionFactory().openSession();
				List<?> invoiceDetails = conn.getNamedQuery("getInvoiceDetailsById").setLong("id", id).setString("promocode", acceptedPromoCode).list();
				Iterator<?> itr = invoiceDetails.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					Properties props = new Properties();
			        props.put("no", obj[0].toString());
		            props.put("orderdate", obj[1].toString());
		            props.put("billedto", obj[2].toString());
		            props.put("transactionno", obj[3].toString());
		            props.put("company", obj[4].toString());
		            props.put("itemname", obj[5].toString());
		            props.put("price", obj[6].toString());
		            if(obj[7] != null) {
		            	java.math.BigDecimal price = new BigDecimal(obj[6].toString());
		            	java.math.BigDecimal discount = new BigDecimal(obj[7].toString());
		        		java.math.BigDecimal pricePercentage = price.multiply(discount);
		        		java.math.BigDecimal priceDivHundred = pricePercentage.divide(new BigDecimal("100"));
		        		java.math.BigDecimal total = price.subtract(priceDivHundred);
		        		props.put("discount", "-"+priceDivHundred);
		            	props.put("total", total.toString());
		            } else {
		            	props.put("discount", "0");
		            	props.put("total", obj[6].toString());
		            }
		            
		            MailMessage msg = new MailMessage(props, "invoice.vm", obj[8].toString(), 
		            		"Weddfix Invoice");

					msg.send();
					
					msg = new MailMessage(props, "invoice.vm", rb.getString("contact.admin.email"), 
		            		"Weddfix Invoice");

					msg.send();
				}
				
		        user = 1L;
			
		} catch (Exception hibernateException) {

			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
	}
}
