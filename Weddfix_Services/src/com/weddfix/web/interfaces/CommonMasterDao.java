package com.weddfix.web.interfaces;

import java.util.List;
import java.util.Map;

import com.weddfix.web.formbean.DirectoryCartDetailsFormBean;
import com.weddfix.web.formbean.CommentsFormBean;
import com.weddfix.web.formbean.DirectoryPromotionDetailsFormBean;
import com.weddfix.web.formbean.DirectoryVendorProfileFormBean;
import com.weddfix.web.formbean.SubscribersFormBean;
import com.weddfix.web.formbean.DirectoryUpgradePlanFormBean;
import com.weddfix.web.formbean.DirectoryUserProfileFormBean;

public interface CommonMasterDao {

	public Map<Object, Object> loadStateMaster(Long countryId);

	public Map<Object, Object> loadCountryMaster();

	public String checkUserAlreadyExist(String email);

	public Long saveCommentsDetails(CommentsFormBean commentsFormBean);

	public Long saveSubscriberDetails(SubscribersFormBean subscribersFormBean);

	public String checkSubscriberEmailAlreadyExist(String emailId);

	public List<DirectoryUserProfileFormBean> loadAllUserRoleDetails();

	public String updateUserRoleAndStatus(Long userId, Long userRole, String userRoleDesc, 
			Long userStatus, Long updatedBy);

	public String resetPassword(Long userId, String pwdHash);

	public Map<Object, Object> loadOrgMaster();

	public Map<Object, Object> loadRoleMaster();

	public Map<Object, Object> loadCurrencyMaster();

	public Map<Object, Object> loadCityMaster(Long stateId);
	
	public Map<Object, Object> loadStateMaster();

	public Map<Object, Object> loadStatusMaster();

	public String checkPartnerPreferenceAlreadyExist(Long userId);

	public List<DirectoryPromotionDetailsFormBean> loadPromotionDetails();

	public Long savePromotionDetails(
			DirectoryPromotionDetailsFormBean promotionDetailsFormBean);

	public Long deletePromotion(Long promoId);

	public Long sendPromotion(String promoEmailId, String sendPromoCode,
			String sendPromoForAll, Long userId);

	public List<DirectoryCartDetailsFormBean> loadCartDetails(Long userId);

	public Long saveCartDetails(DirectoryCartDetailsFormBean cartDetailsFormBean);

	public List<DirectoryPromotionDetailsFormBean> validatePromoCode(String promoCode);

	public List<DirectoryUpgradePlanFormBean> loadMyAccountDetails(Long userId);

	public Map<Object, Object> loadAllCity();

	public String resetVendorPassword(Long userId, String pwdHash);

	public String checkVendorAlreadyExist(String email);

	public List<DirectoryVendorProfileFormBean> loadAllVendorRoleDetails();

	public String updateVendorRoleAndStatus(Long userId, Long userRole,
			String userRoleDesc, Long userStatus, Long updatedBy, Long userPlan);

	public Map<Object, Object> loadPaidVendorCity();

	public Map<Object, Object> loadPlanMaster();

	public String checkCompanyNameAlredyExist(String companyName);

	public String checkOtherCompanyNameAlredyExist(String companyName, String oldCompanyName);

}
