package com.weddfix.web.services;

import java.util.List;

import com.weddfix.web.formbean.DirectoryAccountDetailsFormBean;
import com.weddfix.web.formbean.DirectoryUpgradePlanFormBean;
import com.weddfix.web.formbean.DirectoryUserProfileFormBean;
import com.weddfix.web.formbean.DirectoryVendorProfileFormBean;
import com.weddfix.web.formbean.MyPersonalDetailsFormBean;
import com.weddfix.web.implementation.RegisterDaoImpl;
import com.weddfix.web.interfaces.RegisterDao;

public class RegisterService {

	RegisterDao registerDao = new RegisterDaoImpl();

	public Long saveRegisterDetails(DirectoryUserProfileFormBean userProfileFormBean) {
		Long id = registerDao.saveRegisterDetails(userProfileFormBean);
		return id;
	}
	
	public Long saveVendorRegisterDetails(DirectoryVendorProfileFormBean vendorProfileFormBean) {
		Long id = registerDao.saveVendorRegisterDetails(vendorProfileFormBean);
		return id;
	}

	public DirectoryUserProfileFormBean loadUserProfileDetails(Long id) {
		DirectoryUserProfileFormBean userProfileFormBean = registerDao.loadUserProfileDetails(id);
		return userProfileFormBean;
	}
	
	public DirectoryVendorProfileFormBean loadVendorProfileDetails(Long id) {
		DirectoryVendorProfileFormBean userProfileFormBean = registerDao.loadVendorProfileDetails(id);
		return userProfileFormBean;
	}

	public DirectoryUserProfileFormBean loadUserProfileByEmail(String email) {
		DirectoryUserProfileFormBean userProfileFormBean = registerDao.loadUserProfileByEmail(email);
		return userProfileFormBean;
	}
	
	public DirectoryVendorProfileFormBean loadVendorProfileByEmail(String email) {
		DirectoryVendorProfileFormBean userProfileFormBean = registerDao.loadVendorProfileByEmail(email);
		return userProfileFormBean;
	}

	public List<MyPersonalDetailsFormBean> loadMyDashBoardDetails(Long userId) {
		List<MyPersonalDetailsFormBean> myPersonalDetailsFormBean = registerDao.loadMyDashBoardDetails(userId);
		return myPersonalDetailsFormBean;
	}
	
	public List<MyPersonalDetailsFormBean> loadVendorDashBoardDetails(Long userId) {
		List<MyPersonalDetailsFormBean> myPersonalDetailsFormBean = registerDao.loadVendorDashBoardDetails(userId);
		return myPersonalDetailsFormBean;
	}

	public MyPersonalDetailsFormBean loadPersonalDetails(Long userId) {
		MyPersonalDetailsFormBean myPersonalDetailsFormBean = registerDao.loadPersonalDetailsByUserId(userId);
		return myPersonalDetailsFormBean;
	}
	
	public MyPersonalDetailsFormBean loadVendorPersonalDetails(Long userId) {
		MyPersonalDetailsFormBean myPersonalDetailsFormBean = registerDao.loadVendorPersonalDetailsByUserId(userId);
		return myPersonalDetailsFormBean;
	}

	public Long deleteProfile(DirectoryAccountDetailsFormBean accountDetailsFormBean,
			Long userId) {
		Long id = registerDao.deleteProfile(accountDetailsFormBean, userId);
		return id;
	}

	public List<DirectoryUpgradePlanFormBean> loadUpgradePlanDetails() {
		List<DirectoryUpgradePlanFormBean> upgradePlanDetailsFormBean = registerDao.loadUpgradePlanDetails();
		return upgradePlanDetailsFormBean;
	}

	public String updateMobileVerificationCode(Long userId) {
		String id = registerDao.updateMobileVerificationCode(userId);
		return id;
	}
	
	public String updateVendorMobileVerificationCode(Long userId) {
		String id = registerDao.updateVendorMobileVerificationCode(userId);
		return id;
	}

	public String verifyCodeAndUpdateMobile(Long mobile, Long userId) {
		String id = registerDao.verifyCodeAndUpdateMobile(mobile, userId);
		return id;
	}
	
	public String verifyCodeAndUpdateVendorMobile(Long mobile, Long userId) {
		String id = registerDao.verifyCodeAndUpdateVendorMobile(mobile, userId);
		return id;
	}
	
	public String updateMobileVerificationCodeDetails(Long userId) {
		String id = registerDao.updateMobileVerificationCodeDetails(userId);
		return id;
	}
	
	public String updateVendorMobileVerificationCodeDetails(Long userId) {
		String id = registerDao.updateVendorMobileVerificationCodeDetails(userId);
		return id;
	}

}
