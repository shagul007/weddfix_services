package com.weddfix.web.implementation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.weddfix.web.formbean.DirectoryAccountDetailsFormBean;
import com.weddfix.web.formbean.DirectoryRoleDetailsFormBean;
import com.weddfix.web.formbean.DirectoryUpgradePlanFormBean;
import com.weddfix.web.formbean.DirectoryUserProfileFormBean;
import com.weddfix.web.formbean.DirectoryVendorAccountDetailsFormBean;
import com.weddfix.web.formbean.DirectoryVendorProfileFormBean;
import com.weddfix.web.formbean.DirectoryVendorRoleDetailsFormBean;
import com.weddfix.web.formbean.MyPersonalDetailsFormBean;
import com.weddfix.web.interfaces.RegisterDao;
import com.weddfix.web.util.CommonConstants;
import com.weddfix.web.util.HibernateUtil;
import com.weddfix.web.util.Util;

/**
 * 
 * 
 * @author Shagul
 * 
 */
public class RegisterDaoImpl implements RegisterDao, SessionAware {

	Session conn;

	private static final Logger logger = Logger
			.getLogger(RegisterDaoImpl.class);
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	Long user;

	public Long saveRegisterDetails(DirectoryUserProfileFormBean userProfileFormBean) {

		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Insert register details Method--------------");

			Transaction tx = conn.beginTransaction();
			if (userProfileFormBean.getId() != null) {
				conn.getNamedQuery("updateUserProfileDetailsById")
						.setLong("id", userProfileFormBean.getId())
						.setString("fullName",
								userProfileFormBean.getFullName())
						.setLong("mobile", userProfileFormBean.getMobile())
						.setLong("cityId",
								userProfileFormBean.getCityId())
						.setLong("stateId",
								userProfileFormBean.getStateId())
						.setLong("countryId",
								userProfileFormBean.getCountryId())
						.setLong("pincode",
								userProfileFormBean.getPincode())
						.setString("description",
								userProfileFormBean.getDescription())
						.setDate("weddingDate",
								userProfileFormBean.getWeddingDate())
						.setString("websiteUrl",
								userProfileFormBean.getWebsiteUrl())
						.setString("facebookUrl",
								userProfileFormBean.getFacebookUrl())
						.setString("twitterUrl",
								userProfileFormBean.getTwitterUrl())
						.setString("linkedinUrl",
								userProfileFormBean.getLinkedinUrl())
						.setString("pinterestUrl",
								userProfileFormBean.getPinterestUrl())
						.setString("instagramUrl",
								userProfileFormBean.getInstagramUrl())
						.setLong("updatedBy",
								userProfileFormBean.getUpdatedBy())
						.setDate("updatedDate", new Date()).executeUpdate();
				tx.commit();
				user = 1L;

			} else {
				userProfileFormBean.setEmail(userProfileFormBean.getEmail().toLowerCase());
				userProfileFormBean.setRoleId(CommonConstants.USER_ROLE);
				userProfileFormBean.setStatusId(CommonConstants.ACTIVE);
				userProfileFormBean.setCreatedBy(1l);
				userProfileFormBean.setCreatedDate(new Date());
				user = (Long) conn.save(userProfileFormBean);
				tx.commit();
				Transaction tx1 = conn.beginTransaction();
				DirectoryRoleDetailsFormBean roleDetailsFormBean = new DirectoryRoleDetailsFormBean();
				roleDetailsFormBean.setUserId(user);
				roleDetailsFormBean.setRoleId(CommonConstants.USER_ROLE);
				roleDetailsFormBean.setDescription(CommonConstants.USER_DESC);
				roleDetailsFormBean.setCreatedBy(1l);
				roleDetailsFormBean.setCreatedDate(new Date());
				conn.save(roleDetailsFormBean);
				DirectoryAccountDetailsFormBean accountDetailsFormBean = new DirectoryAccountDetailsFormBean();
				accountDetailsFormBean.setUserId(user);
				accountDetailsFormBean.setAccountType(CommonConstants.FREE_ID);
				accountDetailsFormBean.setIsProfileDeleted(false);
				accountDetailsFormBean.setVerifyMobileNumber(String.valueOf(Util.generatePin()));
				accountDetailsFormBean.setVerifyEmailId(String.valueOf(Util.generatePin()));
				accountDetailsFormBean.setVerifiedMobileNumber(false);
				accountDetailsFormBean.setVerifiedEmailId(false);
				accountDetailsFormBean.setStatus(CommonConstants.ACTIVE);
				accountDetailsFormBean.setCreatedBy(user);
				accountDetailsFormBean.setCreatedDate(new Date());
				conn.save(accountDetailsFormBean);
				tx1.commit();
			}

		} catch (HibernateException hibernateException) {

			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return user;
	}
	
	public Long saveVendorRegisterDetails(DirectoryVendorProfileFormBean vendorProfileFormBean) {

		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Insert vendor sregister details Method--------------");

			Transaction tx = conn.beginTransaction();
			if (vendorProfileFormBean.getId() != null) {
				conn.getNamedQuery("updateVendorProfileDetailsById")
						.setLong("id", vendorProfileFormBean.getId())
						.setString("fullName",
								vendorProfileFormBean.getFullName())
						.setLong("mobile", vendorProfileFormBean.getMobile())
						.setLong("cityId",
								vendorProfileFormBean.getCityId())
						.setLong("stateId",
								vendorProfileFormBean.getStateId())
						.setLong("countryId",
								vendorProfileFormBean.getCountryId())
						.setLong("pincode",
								vendorProfileFormBean.getPincode())
						.setString("description",
								vendorProfileFormBean.getDescription())
						.setString("websiteUrl",
								vendorProfileFormBean.getWebsiteUrl())
						.setString("facebookUrl",
								vendorProfileFormBean.getFacebookUrl())
						.setString("twitterUrl",
								vendorProfileFormBean.getTwitterUrl())
						.setString("linkedinUrl",
								vendorProfileFormBean.getLinkedinUrl())
						.setString("pinterestUrl",
								vendorProfileFormBean.getPinterestUrl())
						.setString("instagramUrl",
								vendorProfileFormBean.getInstagramUrl())
						.setLong("updatedBy",
								vendorProfileFormBean.getUpdatedBy())
						.setDate("updatedDate", new Date()).executeUpdate();
				tx.commit();
				user = 1L;

			} else {
				vendorProfileFormBean.setEmail(vendorProfileFormBean.getEmail().toLowerCase());
				vendorProfileFormBean.setRoleId(CommonConstants.VENDOR_ROLE);
				vendorProfileFormBean.setStatusId(CommonConstants.ACTIVE);
				vendorProfileFormBean.setCreatedBy(1l);
				vendorProfileFormBean.setCreatedDate(new Date());
				user = (Long) conn.save(vendorProfileFormBean);
				tx.commit();
				Transaction tx1 = conn.beginTransaction();
				DirectoryVendorRoleDetailsFormBean roleDetailsFormBean = new DirectoryVendorRoleDetailsFormBean();
				roleDetailsFormBean.setUserId(user);
				roleDetailsFormBean.setRoleId(CommonConstants.VENDOR_ROLE);
				roleDetailsFormBean.setDescription(CommonConstants.VENDOR_DESC);
				roleDetailsFormBean.setCreatedBy(1l);
				roleDetailsFormBean.setCreatedDate(new Date());
				conn.save(roleDetailsFormBean);
				DirectoryVendorAccountDetailsFormBean accountDetailsFormBean = new DirectoryVendorAccountDetailsFormBean();
				accountDetailsFormBean.setUserId(user);
				accountDetailsFormBean.setAccountType(CommonConstants.FREE_ID);
				accountDetailsFormBean.setIsProfileDeleted(false);
				accountDetailsFormBean.setVerifyMobileNumber(String.valueOf(Util.generatePin()));
				accountDetailsFormBean.setVerifyEmailId(String.valueOf(Util.generatePin()));
				accountDetailsFormBean.setVerifiedMobileNumber(false);
				accountDetailsFormBean.setVerifiedEmailId(false);
				accountDetailsFormBean.setStatus(CommonConstants.ACTIVE);
				accountDetailsFormBean.setCreatedBy(user);
				accountDetailsFormBean.setCreatedDate(new Date());
				conn.save(accountDetailsFormBean);
				tx1.commit();
			}

		} catch (HibernateException hibernateException) {

			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return user;
	}

	public DirectoryUserProfileFormBean loadUserProfileDetails(Long id) {
		DirectoryUserProfileFormBean userProfile = new DirectoryUserProfileFormBean();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> userProfiles = conn.getNamedQuery("getUserProfileById")
					.setLong("id", id).list();
			Iterator<?> itr = userProfiles.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				userProfile.setId(Long.parseLong(obj[0].toString()));
				userProfile.setFullName(obj[1].toString());
				userProfile.setEmail(obj[2].toString());
				userProfile.setMobile(Long.parseLong(obj[5].toString()));
				userProfile.setCityId(Long.parseLong(obj[6].toString()));
				userProfile.setStateId(Long.parseLong(obj[7].toString()));
				userProfile.setCountryId(Long.parseLong(obj[8].toString()));
				userProfile.setPincode(Long.parseLong(obj[9].toString()));
			}

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return userProfile;
	}
	
	public DirectoryVendorProfileFormBean loadVendorProfileDetails(Long id) {
		DirectoryVendorProfileFormBean userProfile = new DirectoryVendorProfileFormBean();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> userProfiles = conn.getNamedQuery("getVendorProfileById")
					.setLong("id", id).list();
			Iterator<?> itr = userProfiles.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				userProfile.setId(Long.parseLong(obj[0].toString()));
				userProfile.setFullName(obj[1].toString());
				userProfile.setEmail(obj[2].toString());
				userProfile.setMobile(Long.parseLong(obj[5].toString()));
				userProfile.setCityId(Long.parseLong(obj[6].toString()));
				userProfile.setStateId(Long.parseLong(obj[7].toString()));
				userProfile.setCountryId(Long.parseLong(obj[8].toString()));
				userProfile.setPincode(Long.parseLong(obj[9].toString()));
			}

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return userProfile;
	}

	public DirectoryUserProfileFormBean loadUserProfileByEmail(String email) {
		DirectoryUserProfileFormBean userProfile = new DirectoryUserProfileFormBean();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> userProfiles = conn.getNamedQuery("getUserProfileByEmail")
					.setString("email", email).list();
			Iterator<?> itr = userProfiles.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				userProfile.setId(Long.parseLong(obj[0].toString()));
				userProfile.setFullName(obj[1].toString());
				userProfile.setEmail(obj[2].toString());
			}

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return userProfile;
	}
	
	public DirectoryVendorProfileFormBean loadVendorProfileByEmail(String email) {
		DirectoryVendorProfileFormBean userProfile = new DirectoryVendorProfileFormBean();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> userProfiles = conn.getNamedQuery("getVendorProfileByEmail")
					.setString("email", email).list();
			Iterator<?> itr = userProfiles.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				userProfile.setId(Long.parseLong(obj[0].toString()));
				userProfile.setFullName(obj[1].toString());
				userProfile.setEmail(obj[2].toString());
			}

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return userProfile;
	}

	@SuppressWarnings("unchecked")
	public List<MyPersonalDetailsFormBean> loadMyDashBoardDetails(Long userId) {
		List<MyPersonalDetailsFormBean> myPersonalDetails = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			myPersonalDetails = conn
					.getNamedQuery("getMyDashBoardByUserId")
					.setLong("userId", userId).list();

		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return myPersonalDetails;
	}
	
	@SuppressWarnings("unchecked")
	public List<MyPersonalDetailsFormBean> loadVendorDashBoardDetails(Long userId) {
		List<MyPersonalDetailsFormBean> myPersonalDetails = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			myPersonalDetails = conn
					.getNamedQuery("getVendorDashBoardByUserId")
					.setLong("userId", userId).list();

		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return myPersonalDetails;
	}

	public MyPersonalDetailsFormBean loadPersonalDetailsByUserId(Long userId) {
		MyPersonalDetailsFormBean myPersonalDetails = new MyPersonalDetailsFormBean();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> userProfiles = conn
					.getNamedQuery("getPersonalDetailsByUserId")
					.setLong("userId", userId).list();
			Iterator<?> itr = userProfiles.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				myPersonalDetails.setId(Long.parseLong(obj[0].toString()));
				myPersonalDetails.setFullName(obj[1].toString());
				myPersonalDetails.setEmail(obj[2].toString());
				myPersonalDetails.setPassword(obj[3].toString());
				myPersonalDetails.setPasswordHash(obj[4].toString());
				myPersonalDetails.setMobile(Long.parseLong(obj[5].toString()));
				myPersonalDetails.setCityId(Long.parseLong(obj[6].toString()));
				myPersonalDetails.setStateId(Long.parseLong(obj[7].toString()));
				myPersonalDetails.setCountryId(Long.parseLong(obj[8].toString()));
				myPersonalDetails.setPincode(Long.parseLong(obj[9].toString()));
				if(obj[10] != null) {
					myPersonalDetails.setDescription(obj[10].toString());
				} else {
					myPersonalDetails.setDescription("");
				}
				if(obj[11] != null) {
					myPersonalDetails.setProfilePictureId(Long.parseLong(obj[11].toString()));
				} 
				if(obj[12] != null) {
					myPersonalDetails.setWeddingDateStr(obj[12].toString());
				} else {
					myPersonalDetails.setWeddingDateStr("");
				}
				if(obj[13] != null) {
					myPersonalDetails.setWebsiteUrl(obj[13].toString());
				} else {
					myPersonalDetails.setWebsiteUrl("");
				}
				if(obj[14] != null) {
					myPersonalDetails.setFacebookUrl(obj[14].toString());
				} else {
					myPersonalDetails.setFacebookUrl("");
				}
				if(obj[15] != null) {
					myPersonalDetails.setTwitterUrl(obj[15].toString());
				} else {
					myPersonalDetails.setTwitterUrl("");
				}
				if(obj[16] != null) {
					myPersonalDetails.setLinkedinUrl(obj[16].toString());
				} else {
					myPersonalDetails.setLinkedinUrl("");
				}
				if(obj[17] != null) {
					myPersonalDetails.setPinterestUrl(obj[17].toString());
				} else {
					myPersonalDetails.setPinterestUrl("");
				}
				if(obj[18] != null) {
					myPersonalDetails.setInstagramUrl(obj[18].toString());
				} else {
					myPersonalDetails.setInstagramUrl("");
				}
				if(obj[19] != null) {
					myPersonalDetails.setCountry(obj[19].toString());
				} else {
					myPersonalDetails.setCountry("");
				}
				if(obj[20] != null) {
					myPersonalDetails.setState(obj[20].toString());
				} else {
					myPersonalDetails.setState("");
				}
				if(obj[21] != null) {
					myPersonalDetails.setCity(obj[21].toString());
				} else {
					myPersonalDetails.setCity("");
				}
				if(obj[22] != null) {
					myPersonalDetails.setFileName(obj[22].toString());
				} else {
					myPersonalDetails.setFileName("");
				}
			}

		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return myPersonalDetails;
	}
	
	public MyPersonalDetailsFormBean loadVendorPersonalDetailsByUserId(Long userId) {
		MyPersonalDetailsFormBean myPersonalDetails = new MyPersonalDetailsFormBean();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> userProfiles = conn
					.getNamedQuery("getVendorPersonalDetailsByUserId")
					.setLong("userId", userId).list();
			Iterator<?> itr = userProfiles.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				myPersonalDetails.setId(Long.parseLong(obj[0].toString()));
				myPersonalDetails.setFullName(obj[1].toString());
				myPersonalDetails.setEmail(obj[2].toString());
				myPersonalDetails.setPassword(obj[3].toString());
				myPersonalDetails.setPasswordHash(obj[4].toString());
				myPersonalDetails.setMobile(Long.parseLong(obj[5].toString()));
				myPersonalDetails.setCityId(Long.parseLong(obj[6].toString()));
				myPersonalDetails.setStateId(Long.parseLong(obj[7].toString()));
				myPersonalDetails.setCountryId(Long.parseLong(obj[8].toString()));
				myPersonalDetails.setPincode(Long.parseLong(obj[9].toString()));
				if(obj[10] != null) {
					myPersonalDetails.setDescription(obj[10].toString());
				} else {
					myPersonalDetails.setDescription("");
				}
				if(obj[11] != null) {
					myPersonalDetails.setProfilePictureId(Long.parseLong(obj[11].toString()));
				} 
				if(obj[12] != null) {
					myPersonalDetails.setWebsiteUrl(obj[12].toString());
				} else {
					myPersonalDetails.setWebsiteUrl("");
				}
				if(obj[13] != null) {
					myPersonalDetails.setFacebookUrl(obj[13].toString());
				} else {
					myPersonalDetails.setFacebookUrl("");
				}
				if(obj[14] != null) {
					myPersonalDetails.setTwitterUrl(obj[14].toString());
				} else {
					myPersonalDetails.setTwitterUrl("");
				}
				if(obj[15] != null) {
					myPersonalDetails.setLinkedinUrl(obj[15].toString());
				} else {
					myPersonalDetails.setLinkedinUrl("");
				}
				if(obj[16] != null) {
					myPersonalDetails.setPinterestUrl(obj[16].toString());
				} else {
					myPersonalDetails.setPinterestUrl("");
				}
				if(obj[17] != null) {
					myPersonalDetails.setInstagramUrl(obj[17].toString());
				} else {
					myPersonalDetails.setInstagramUrl("");
				}
				if(obj[18] != null) {
					myPersonalDetails.setCountry(obj[18].toString());
				} else {
					myPersonalDetails.setCountry("");
				}
				if(obj[19] != null) {
					myPersonalDetails.setState(obj[19].toString());
				} else {
					myPersonalDetails.setState("");
				}
				if(obj[20] != null) {
					myPersonalDetails.setCity(obj[20].toString());
				} else {
					myPersonalDetails.setCity("");
				}
				if(obj[21] != null) {
					myPersonalDetails.setFileName(obj[21].toString());
				} else {
					myPersonalDetails.setFileName("");
				}
			}

		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return myPersonalDetails;
	}
	
	public Long deleteProfile(DirectoryAccountDetailsFormBean accountDetailsFormBean, Long userId) {

		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Update delete profile Method--------------");

			Transaction tx = conn.beginTransaction();
				conn.getNamedQuery("updateDeleteProfileByUserId")
						.setString("deleteProfileReason", accountDetailsFormBean.getDeleteProfileReason())
						.setBoolean("isProfileDeleted", true)
						.setLong("userId", userId)
						.setLong("updatedBy",
								accountDetailsFormBean.getUpdatedBy())
						.setDate("updatedDate", new Date()).executeUpdate();
				conn.getNamedQuery("updateUserProfileInActiveByUserId")
						.setLong("statusId", CommonConstants.INACTIVE)
						.setLong("userId", userId)
						.setLong("updatedBy", accountDetailsFormBean.getUpdatedBy())
						.setDate("updatedDate", new Date()).executeUpdate();
				user = 1L;

			tx.commit();

		} catch (HibernateException hibernateException) {

			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<DirectoryUpgradePlanFormBean> loadUpgradePlanDetails() {
		List<DirectoryUpgradePlanFormBean> upgradePlanDetails = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			upgradePlanDetails = conn
					.getNamedQuery("getUpgradePlanDetails").list();

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return upgradePlanDetails;
	}
	
	public String updateMobileVerificationCode(Long userId) {
		String verifyCode = String.valueOf(Util.generatePin());
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Update verification code Method--------------");

			Transaction tx = conn.beginTransaction();
				conn.getNamedQuery("updateMobileVerificationCodeByUserId")
						.setString("verifyCode", verifyCode)
						.setLong("userId", userId)
						.setLong("updatedBy", userId)
						.setDate("updatedDate", new Date()).executeUpdate();
				user = 1L;

			tx.commit();

		} catch (HibernateException hibernateException) {

			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return verifyCode;
	}
	
	public String updateVendorMobileVerificationCode(Long userId) {
		String verifyCode = String.valueOf(Util.generatePin());
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Update vendor verification code Method--------------");

			Transaction tx = conn.beginTransaction();
				conn.getNamedQuery("updateVendorMobileVerificationCodeByUserId")
						.setString("verifyCode", verifyCode)
						.setLong("userId", userId)
						.setLong("updatedBy", userId)
						.setDate("updatedDate", new Date()).executeUpdate();
				user = 1L;

			tx.commit();

		} catch (HibernateException hibernateException) {

			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return verifyCode;
	}
	
	public String verifyCodeAndUpdateMobile(Long mobile, Long userId) {
		String status = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Update verification code Method--------------");

			Transaction tx = conn.beginTransaction();
				conn.getNamedQuery("verifyCodeAndUpdateMobileByUserId")
						.setLong("mobile", mobile)
						.setLong("userId", userId)
						.setLong("updatedBy", userId)
						.setDate("updatedDate", new Date()).executeUpdate();
				conn.getNamedQuery("updateVerifiedMobileNumberByUserId")
						.setBoolean("verifiedMobileNumber", true)
						.setLong("userId", userId)
						.setLong("updatedBy", userId)
						.setDate("updatedDate", new Date()).executeUpdate();
				status = "success";

			tx.commit();

		} catch (HibernateException hibernateException) {

			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return status;
	}
	
	public String verifyCodeAndUpdateVendorMobile(Long mobile, Long userId) {
		String status = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Update vendor verification code Method--------------");

			Transaction tx = conn.beginTransaction();
				conn.getNamedQuery("verifyCodeAndUpdateVendorMobileByUserId")
						.setLong("mobile", mobile)
						.setLong("userId", userId)
						.setLong("updatedBy", userId)
						.setDate("updatedDate", new Date()).executeUpdate();
				conn.getNamedQuery("updateVerifiedVendorMobileNumberByUserId")
						.setBoolean("verifiedMobileNumber", true)
						.setLong("userId", userId)
						.setLong("updatedBy", userId)
						.setDate("updatedDate", new Date()).executeUpdate();
				status = "success";

			tx.commit();

		} catch (HibernateException hibernateException) {

			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return status;
	}
	
	public String updateMobileVerificationCodeDetails(Long userId) {
		String verifyCode = String.valueOf(Util.generatePin());
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Update verification code details Method--------------");

			Transaction tx = conn.beginTransaction();
				conn.getNamedQuery("updateMobileVerificationCodeDetailsByUserId")
						.setString("verifyCode", verifyCode)
						.setBoolean("verifiedMobileNumber", false)
						.setLong("userId", userId)
						.setLong("updatedBy", userId)
						.setDate("updatedDate", new Date()).executeUpdate();
				user = 1L;

			tx.commit();

		} catch (HibernateException hibernateException) {

			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return verifyCode;
	}
	
	public String updateVendorMobileVerificationCodeDetails(Long userId) {
		String verifyCode = String.valueOf(Util.generatePin());
		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Update verification code details Method--------------");

			Transaction tx = conn.beginTransaction();
				conn.getNamedQuery("updateVendorMobileVerificationCodeDetailsByUserId")
						.setString("verifyCode", verifyCode)
						.setBoolean("verifiedMobileNumber", false)
						.setLong("userId", userId)
						.setLong("updatedBy", userId)
						.setDate("updatedDate", new Date()).executeUpdate();
				user = 1L;

			tx.commit();

		} catch (HibernateException hibernateException) {

			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return verifyCode;
	}
	
	public void setSession(Map<String, Object> arg0) {

	}

}
