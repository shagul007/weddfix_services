package com.weddfix.web.interfaces;

import java.util.List;

import com.weddfix.web.formbean.DirectoryAccountDetailsFormBean;
import com.weddfix.web.formbean.DirectoryUpgradePlanFormBean;
import com.weddfix.web.formbean.DirectoryUserProfileFormBean;
import com.weddfix.web.formbean.DirectoryVendorProfileFormBean;
import com.weddfix.web.formbean.MyPersonalDetailsFormBean;

public interface RegisterDao {

	public Long saveRegisterDetails(DirectoryUserProfileFormBean userProfileFormBean);

	public DirectoryUserProfileFormBean loadUserProfileDetails(Long id);

	public DirectoryUserProfileFormBean loadUserProfileByEmail(String email);

	public List<MyPersonalDetailsFormBean> loadMyDashBoardDetails(Long userId);

	public Long deleteProfile(DirectoryAccountDetailsFormBean accountDetailsFormBean,
			Long userId);

	public List<DirectoryUpgradePlanFormBean> loadUpgradePlanDetails();

	public MyPersonalDetailsFormBean loadPersonalDetailsByUserId(Long userId);

	public String updateMobileVerificationCode(Long userId);

	public String verifyCodeAndUpdateMobile(Long mobile, Long userId);

	public String updateMobileVerificationCodeDetails(Long userId);

	public Long saveVendorRegisterDetails(
			DirectoryVendorProfileFormBean vendorProfileFormBean);

	public String updateVendorMobileVerificationCodeDetails(Long userId);

	public MyPersonalDetailsFormBean loadVendorPersonalDetailsByUserId(
			Long userId);

	public DirectoryVendorProfileFormBean loadVendorProfileByEmail(String email);

	public String updateVendorMobileVerificationCode(Long userId);

	public String verifyCodeAndUpdateVendorMobile(Long mobile, Long userId);

	public List<MyPersonalDetailsFormBean> loadVendorDashBoardDetails(
			Long userId);

	public DirectoryVendorProfileFormBean loadVendorProfileDetails(Long id);

	
}
