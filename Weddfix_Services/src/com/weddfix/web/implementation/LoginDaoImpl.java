package com.weddfix.web.implementation;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.weddfix.web.formbean.DirectoryUserProfileFormBean;
import com.weddfix.web.formbean.LoginFormBean;
import com.weddfix.web.interfaces.LoginDao;
import com.weddfix.web.util.CommonConstants;
import com.weddfix.web.util.HibernateUtil;
import com.weddfix.web.util.InvalidLoginException;

/**
 * 
 * 
 * @author Shagul
 * 
 */
public class LoginDaoImpl implements LoginDao, SessionAware {

	Session conn;

	DirectoryUserProfileFormBean registerFormBean = new DirectoryUserProfileFormBean();

	private static final Logger logger = Logger.getLogger(LoginDaoImpl.class);

	Long user;

	public LoginFormBean checkLogin(String userName, String password)
			throws InvalidLoginException {
		CommonMasterDaoImpl commonMasterDaoImpl = new CommonMasterDaoImpl();
		LoginFormBean loginBean = commonMasterDaoImpl
				.getUserProfileByEmail(userName.toLowerCase());

		try {
			if (loginBean != null) {
				if (CommonConstants.generateEncryptedPwd(password).equals(
						loginBean.getPasswordHash()))
					return loginBean;
			}
		} catch (Exception e) {
			String msg = "Invalid Login Attempt made by : " + userName + ":"
					+ password;
			throw new InvalidLoginException(msg);
		}
		return null;
	}
	
	public LoginFormBean checkVendorLogin(String userName, String password)
			throws InvalidLoginException {
		CommonMasterDaoImpl commonMasterDaoImpl = new CommonMasterDaoImpl();
		LoginFormBean loginBean = commonMasterDaoImpl
				.getVendorProfileByEmail(userName.toLowerCase());

		try {
			if (loginBean != null) {
				if (CommonConstants.generateEncryptedPwd(password).equals(
						loginBean.getPasswordHash()))
					return loginBean;
			}
		} catch (Exception e) {
			String msg = "Invalid Login Attempt made by : " + userName + ":"
					+ password;
			throw new InvalidLoginException(msg);
		}
		return null;
	}
	
	public LoginFormBean sessionPasswordHash(Long userId) {

		LoginFormBean loginFormBean = new LoginFormBean();
		try {
			logger.info("-----------Get Login details Method--------------");
				conn = HibernateUtil.getSessionFactory().openSession();

				List<?> userProfile = conn.getNamedQuery("getUserProfileByUserId")
						.setLong("userId", userId).list();
				Iterator<?> itr = userProfile.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					loginFormBean.setId(Long.parseLong(obj[0].toString()));
					loginFormBean.setFullName(obj[1].toString());
					loginFormBean.setEmail(obj[2].toString());
					loginFormBean.setPassword(obj[3].toString());
					loginFormBean.setPasswordHash(obj[4].toString());
					loginFormBean.setRole(obj[5].toString());
					loginFormBean.setStatus(obj[6].toString());
				}
				if (loginFormBean != null) {
					return loginFormBean;
				}

		} catch (HibernateException hibernateException) {

//			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return loginFormBean;
	}
	
	public LoginFormBean sessionVendorPasswordHash(Long userId) {

		LoginFormBean loginFormBean = new LoginFormBean();
		try {
			logger.info("-----------Get Vendor Login details Method--------------");
				conn = HibernateUtil.getSessionFactory().openSession();

				List<?> userProfile = conn.getNamedQuery("getVendorProfileByUserId")
						.setLong("userId", userId).list();
				Iterator<?> itr = userProfile.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					loginFormBean.setId(Long.parseLong(obj[0].toString()));
					loginFormBean.setFullName(obj[1].toString());
					loginFormBean.setEmail(obj[2].toString());
					loginFormBean.setPassword(obj[3].toString());
					loginFormBean.setPasswordHash(obj[4].toString());
					loginFormBean.setRole(obj[5].toString());
					loginFormBean.setStatus(obj[6].toString());
				}
				if (loginFormBean != null) {
					return loginFormBean;
				}

		} catch (HibernateException hibernateException) {

//			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return loginFormBean;
	}
	
	public LoginFormBean loadProfileSessionDetails(String email) {
		LoginFormBean loginFormBean = new LoginFormBean();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			List<?> userProfile = conn.getNamedQuery("getUserProfileByEmail")
					.setString("email", email).list();
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
	
	public void setSession(Map<String, Object> arg0) {

	}

}
