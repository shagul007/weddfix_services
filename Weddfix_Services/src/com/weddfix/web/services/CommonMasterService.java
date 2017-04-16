package com.weddfix.web.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.weddfix.web.formbean.CityFormBean;
import com.weddfix.web.formbean.CommentsFormBean;
import com.weddfix.web.formbean.CountryFormBean;
import com.weddfix.web.formbean.CurrencyFormBean;
import com.weddfix.web.formbean.DirectoryCartDetailsFormBean;
import com.weddfix.web.formbean.DirectoryRoleFormBean;
import com.weddfix.web.formbean.DirectoryUpgradePlanFormBean;
import com.weddfix.web.formbean.DirectoryUserProfileFormBean;
import com.weddfix.web.formbean.DirectoryVendorProfileFormBean;
import com.weddfix.web.formbean.OrganizationFormBean;
import com.weddfix.web.formbean.DirectoryPromotionDetailsFormBean;
import com.weddfix.web.formbean.StateFormBean;
import com.weddfix.web.formbean.StatusFormBean;
import com.weddfix.web.formbean.SubscribersFormBean;
import com.weddfix.web.implementation.CommonMasterDaoImpl;
import com.weddfix.web.interfaces.CommonMasterDao;

public class CommonMasterService {

	private Map<Object, Object> orgMasterMap = new HashMap<Object, Object>();
	private Map<Object, Object> roleMasterMap = new HashMap<Object, Object>();
	private Map<Object, Object> statusMasterMap = new HashMap<Object, Object>();
	private Map<Object, Object> currencyMasterMap = new HashMap<Object, Object>();
	private Map<Object, Object> countryMasterMap = new HashMap<Object, Object>();
	private Map<Object, Object> stateMasterMap = new HashMap<Object, Object>();
	private Map<Object, Object> cityMasterMap = new HashMap<Object, Object>();
	private Map<Object, Object> cityAllMasterMap = new HashMap<Object, Object>();

	List<DirectoryRoleFormBean> roleMasterList;
	List<OrganizationFormBean> orgMasterList;
	List<StatusFormBean> statusMasterList;
	List<CurrencyFormBean> currencyMasterList;
	List<CountryFormBean> countryMasterList;
	List<StateFormBean> stateMasterList;
	List<CityFormBean> cityMasterList;
	List<DirectoryUpgradePlanFormBean> upgradePlanMasterList;

	CommonMasterDao commonMasterDao = new CommonMasterDaoImpl();
	CommonMasterDaoImpl commonMasterDaoImpl = new CommonMasterDaoImpl();

	public Map<Object, Object> loadOrg() {

		orgMasterMap = commonMasterDao.loadOrgMaster();
		return orgMasterMap;
	}

	public Map<Object, Object> loadRole() {

		roleMasterMap = commonMasterDao.loadRoleMaster();
		return roleMasterMap;
	}
	
	public Map<Object, Object> loadStatus() {

		statusMasterMap = commonMasterDao.loadStatusMaster();
		return statusMasterMap;
	}
	
	public Map<Object, Object> loadPlan() {

		statusMasterMap = commonMasterDao.loadPlanMaster();
		return statusMasterMap;
	}
	
	public Map<Object, Object> loadCurrency() {

		currencyMasterMap = commonMasterDao.loadCurrencyMaster();
		return currencyMasterMap;
	}

	public Map<Object, Object> loadState() {

		stateMasterMap = commonMasterDao.loadStateMaster();
		return stateMasterMap;
	}
	
	public Map<Object, Object> loadState(Long countryId) {

		stateMasterMap = commonMasterDao.loadStateMaster(countryId);
		return stateMasterMap;
	}
	
	public Map<Object, Object> loadCity(Long stateId) {

		cityMasterMap = commonMasterDao.loadCityMaster(stateId);
		return cityMasterMap;
	}
	
	public Map<Object, Object> loadAllCity() {

		cityAllMasterMap = commonMasterDao.loadAllCity();
		return cityAllMasterMap;
	}
	
	public Map<Object, Object> loadPaidVendorCity() {

		cityAllMasterMap = commonMasterDao.loadPaidVendorCity();
		return cityAllMasterMap;
	}
	
	public Map<Object, Object> loadCountry() {

		countryMasterMap = commonMasterDao.loadCountryMaster();
		return countryMasterMap;
	}

	public String checkUserAlreadyExist(String email) {
		String checkUser = commonMasterDao.checkUserAlreadyExist(email);
		return checkUser;
	}
	
	public String checkCompanyNameAlredyExist(String companyName) {
		String checkUser = commonMasterDao.checkCompanyNameAlredyExist(companyName);
		return checkUser;
	}
	
	public String checkOtherCompanyNameAlredyExist(String companyName, String oldCompanyName) {
		String checkUser = commonMasterDao.checkOtherCompanyNameAlredyExist(companyName, oldCompanyName);
		return checkUser;
	}
	
	public String checkVendorAlreadyExist(String email) {
		String checkUser = commonMasterDao.checkVendorAlreadyExist(email);
		return checkUser;
	}
	
	public String checkPartnerPreferenceAlreadyExist(Long userId) {
		String checkUser = commonMasterDao.checkPartnerPreferenceAlreadyExist(userId);
		return checkUser;
	}

	public Long saveCommentDetails(CommentsFormBean commentsFormBean) {
		Long id = commonMasterDao.saveCommentsDetails(commentsFormBean);
		return id;
	}

	public Long saveSubscriberDetails(SubscribersFormBean subscribersFormBean) {
		Long id = commonMasterDao.saveSubscriberDetails(subscribersFormBean);
		return id;
	}

	public String checkSubscriberEmailAlreadyExist(String emailId) {
		String checkUser = commonMasterDao
				.checkSubscriberEmailAlreadyExist(emailId);
		return checkUser;
	}

	public List<DirectoryUserProfileFormBean> loadAllUserRoleDetails() {
		List<DirectoryUserProfileFormBean> userProfileList = commonMasterDao
				.loadAllUserRoleDetails();
		return userProfileList;
	}
	
	public List<DirectoryVendorProfileFormBean> loadAllVendorRoleDetails() {
		List<DirectoryVendorProfileFormBean> userProfileList = commonMasterDao
				.loadAllVendorRoleDetails();
		return userProfileList;
	}
	
	public List<DirectoryUpgradePlanFormBean> loadMyAccountDetails(Long userId) {
		List<DirectoryUpgradePlanFormBean> myAccountDetails = commonMasterDao
				.loadMyAccountDetails(userId);
		return myAccountDetails;
	}
	
	public List<DirectoryPromotionDetailsFormBean> loadPromotionDetails() {
		List<DirectoryPromotionDetailsFormBean> promotionDetailsList = commonMasterDao
				.loadPromotionDetails();
		return promotionDetailsList;
	}
	
	public List<DirectoryCartDetailsFormBean> loadCartDetails(Long userId) {
		List<DirectoryCartDetailsFormBean> cartDetailsList = commonMasterDao
				.loadCartDetails(userId);
		return cartDetailsList;
	}

	public String updateUserRoleAndStatus(Long userId, Long userRole, String userRoleDesc,
			Long userStatus, Long updatedBy) {
		String status = commonMasterDao.updateUserRoleAndStatus(userId,
				userRole, userRoleDesc, userStatus, updatedBy);
		return status;
	}
	
	public String updateVendorRoleAndStatus(Long userId, Long userRole, String userRoleDesc,
			Long userStatus, Long updatedBy, Long userPlan) {
		String status = commonMasterDao.updateVendorRoleAndStatus(userId,
				userRole, userRoleDesc, userStatus, updatedBy, userPlan);
		return status;
	}

	public String resetPassword(Long userId, String pwdHash) {
		String status = commonMasterDao.resetPassword(userId, pwdHash);
		return status;
	}
	
	public String resetVendorPassword(Long userId, String pwdHash) {
		String status = commonMasterDao.resetVendorPassword(userId, pwdHash);
		return status;
	}

	public List<DirectoryRoleFormBean> loadRoleMasterList() {
		roleMasterList = commonMasterDaoImpl.loadRoleMasterList();
		return roleMasterList;
	}

	public void updateRoleMaster(Long id, String roleName, String roleDesc,
			Boolean isActive, Long updatedBy) {
		commonMasterDaoImpl.updateRoleMaster(id, roleName, roleDesc, isActive,
				updatedBy);
	}

	public void addRoleMaster(DirectoryRoleFormBean roleFormBean) {
		commonMasterDaoImpl.addRoleMaster(roleFormBean);
	}

	public void deleteRoleMaster(Long id) {
		commonMasterDaoImpl.deleteRoleMaster(id);
	}

	public List<StatusFormBean> loadStatusMasterList() {
		statusMasterList = commonMasterDaoImpl.loadStatusMasterList();
		return statusMasterList;
	}

	public void updateStatusMaster(Long id, String statusName,
			String statusDesc, Boolean isActive, Long updatedBy) {
		commonMasterDaoImpl.updateStatusMaster(id, statusName, statusDesc,
				isActive, updatedBy);
	}

	public void addStatusMaster(StatusFormBean statusFormBean) {
		commonMasterDaoImpl.addStatusMaster(statusFormBean);
	}

	public void deleteStatusMaster(Long id) {
		commonMasterDaoImpl.deleteStatusMaster(id);
	}

	public List<OrganizationFormBean> loadOrganizationMasterList() {
		orgMasterList = commonMasterDaoImpl.loadOrganizationMasterList();
		return orgMasterList;
	}

	public void updateOrganizationMaster(Long id, String orgName,
			String orgDesc, Boolean isActive, Long updatedBy) {
		commonMasterDaoImpl.updateOrganizationMaster(id, orgName, orgDesc,
				isActive, updatedBy);
	}

	public void addOrganizationMaster(OrganizationFormBean organizationFormBean) {
		commonMasterDaoImpl.addOrganizationMaster(organizationFormBean);
	}

	public void deleteOrganizationMaster(Long id) {
		commonMasterDaoImpl.deleteOrganizationMaster(id);
	}

	public List<CurrencyFormBean> loadCurrencyMasterList() {
		currencyMasterList = commonMasterDaoImpl.loadCurrencyMasterList();
		return currencyMasterList;
	}

	public void updateCurrencyMaster(Long id, String currency,
			String currencyDesc, Boolean isActive, Long updatedBy) {
		commonMasterDaoImpl.updateCurrencyMaster(id, currency, currencyDesc,
				isActive, updatedBy);
	}

	public void addCurrencyMaster(CurrencyFormBean currencyFormBean) {
		commonMasterDaoImpl.addCurrencyMaster(currencyFormBean);
	}

	public void deleteCurrencyMaster(Long id) {
		commonMasterDaoImpl.deleteCurrencyMaster(id);
	}
	
	public List<CountryFormBean> loadCountryMasterList() {
		countryMasterList = commonMasterDaoImpl.loadCountryMasterList();
		return countryMasterList;
	}

	public void updateCountryMaster(Long id, String countryCode, String countryName, 
			String countryDesc, Boolean isActive, Long updatedBy) {
		commonMasterDaoImpl.updateCountryMaster(id, countryCode, countryName, countryDesc,
				isActive, updatedBy);
	}

	public void addCountryMaster(CountryFormBean countryFormBean) {
		commonMasterDaoImpl.addCountryMaster(countryFormBean);
	}

	public void deleteCountryMaster(Long id) {
		commonMasterDaoImpl.deleteCountryMaster(id);
	}
	
	public List<StateFormBean> loadStateMasterList() {
		stateMasterList = commonMasterDaoImpl.loadStateMasterList();
		return stateMasterList;
	}

	public void updateStateMaster(Long id, String stateName, 
			Long countryId, Boolean isActive, Long updatedBy) {
		commonMasterDaoImpl.updateStateMaster(id, stateName, countryId,
				isActive, updatedBy);
	}

	public void addStateMaster(StateFormBean stateFormBean) {
		commonMasterDaoImpl.addStateMaster(stateFormBean);
	}

	public void deleteStateMaster(Long id) {
		commonMasterDaoImpl.deleteStateMaster(id);
	}
	
	public List<CityFormBean> loadCityMasterList() {
		cityMasterList = commonMasterDaoImpl.loadCityMasterList();
		return cityMasterList;
	}

	public void updateCityMaster(Long id, String cityName, 
			Long stateId, Boolean isActive, Long updatedBy) {
		commonMasterDaoImpl.updateCityMaster(id, cityName, stateId,
				isActive, updatedBy);
	}

	public void addCityMaster(CityFormBean cityFormBean) {
		commonMasterDaoImpl.addCityMaster(cityFormBean);
	}

	public void deleteCityMaster(Long id) {
		commonMasterDaoImpl.deleteCityMaster(id);
	}
	
	public List<DirectoryUpgradePlanFormBean> loadUpgradePlanMasterList() {
		upgradePlanMasterList = commonMasterDaoImpl.loadUpgradePlanMasterList();
		return upgradePlanMasterList;
	}

	public void updateUpgradePlanMaster(Long id, String planName, Long amount, Long validity, Boolean getSMSAlert,
			Boolean isActive, Long updatedBy) {
		commonMasterDaoImpl.updateUpgradePlanMaster(id, planName, amount, validity, getSMSAlert,
				isActive, updatedBy);
	}

	public void addUpgradePlanMaster(DirectoryUpgradePlanFormBean upgradePlanFormBean) {
		commonMasterDaoImpl.addUpgradePlanMaster(upgradePlanFormBean);
	}

	public void deleteUpgradePlanMaster(Long id) {
		commonMasterDaoImpl.deleteUpgradePlanMaster(id);
	}

	public Long savePromotionDetails(
			DirectoryPromotionDetailsFormBean promotionDetailsFormBean) {
		Long id = commonMasterDao.savePromotionDetails(promotionDetailsFormBean);
		return id;
	}

	public Long deletePromotion(Long promoId) {
		Long id = commonMasterDao.deletePromotion(promoId);
		return id;
	}

	public Long sendPromotion(String promoEmailId, String sendPromoCode, String sendPromoForAll, Long userId) {
		Long id = commonMasterDao.sendPromotion(promoEmailId, sendPromoCode, sendPromoForAll, userId);
		return id;
	}
	
	public Long saveCartDetails(
			DirectoryCartDetailsFormBean cartDetailsFormBean) {
		Long id = commonMasterDao.saveCartDetails(cartDetailsFormBean);
		return id;
	}
	
	public List<DirectoryPromotionDetailsFormBean> validatePromoCode(String promoCode) {
		List<DirectoryPromotionDetailsFormBean> promotionDetailsList = commonMasterDao
				.validatePromoCode(promoCode);
		return promotionDetailsList;
	}

}
