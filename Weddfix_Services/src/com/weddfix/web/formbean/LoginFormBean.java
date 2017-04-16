package com.weddfix.web.formbean;

import java.io.Serializable;

public class LoginFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String fullName;
	private String email;
	private Long mobile;
	private String password;
	private String passwordHash;
	private String role;
	private String status;
	private String accountType;
	private String deleteProfileReason;
	private Boolean isProfileDeleted;
	private String verifyMobileNumber;
	private String verifyEmailId;
	private Boolean verifiedMobileNumber;
	private Boolean verifiedEmailId;
	private Long myPlanId;
	private Long categoryInfoId;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public Long getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the passwordHash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * @param passwordHash
	 *            the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType
	 *            the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the deleteProfileReason
	 */
	public String getDeleteProfileReason() {
		return deleteProfileReason;
	}

	/**
	 * @param deleteProfileReason
	 *            the deleteProfileReason to set
	 */
	public void setDeleteProfileReason(String deleteProfileReason) {
		this.deleteProfileReason = deleteProfileReason;
	}

	/**
	 * @return the isProfileDeleted
	 */
	public Boolean getIsProfileDeleted() {
		return isProfileDeleted;
	}

	/**
	 * @param isProfileDeleted
	 *            the isProfileDeleted to set
	 */
	public void setIsProfileDeleted(Boolean isProfileDeleted) {
		this.isProfileDeleted = isProfileDeleted;
	}

	/**
	 * @return the verifyMobileNumber
	 */
	public String getVerifyMobileNumber() {
		return verifyMobileNumber;
	}

	/**
	 * @param verifyMobileNumber
	 *            the verifyMobileNumber to set
	 */
	public void setVerifyMobileNumber(String verifyMobileNumber) {
		this.verifyMobileNumber = verifyMobileNumber;
	}

	/**
	 * @return the verifyEmailId
	 */
	public String getVerifyEmailId() {
		return verifyEmailId;
	}

	/**
	 * @param verifyEmailId
	 *            the verifyEmailId to set
	 */
	public void setVerifyEmailId(String verifyEmailId) {
		this.verifyEmailId = verifyEmailId;
	}

	/**
	 * @return the verifiedMobileNumber
	 */
	public Boolean getVerifiedMobileNumber() {
		return verifiedMobileNumber;
	}

	/**
	 * @param verifiedMobileNumber
	 *            the verifiedMobileNumber to set
	 */
	public void setVerifiedMobileNumber(Boolean verifiedMobileNumber) {
		this.verifiedMobileNumber = verifiedMobileNumber;
	}

	/**
	 * @return the verifiedEmailId
	 */
	public Boolean getVerifiedEmailId() {
		return verifiedEmailId;
	}

	/**
	 * @param verifiedEmailId
	 *            the verifiedEmailId to set
	 */
	public void setVerifiedEmailId(Boolean verifiedEmailId) {
		this.verifiedEmailId = verifiedEmailId;
	}

	/**
	 * @return the myPlanId
	 */
	public Long getMyPlanId() {
		return myPlanId;
	}

	/**
	 * @param myPlanId
	 *            the myPlanId to set
	 */
	public void setMyPlanId(Long myPlanId) {
		this.myPlanId = myPlanId;
	}

	/**
	 * @return the categoryInfoId
	 */
	public Long getCategoryInfoId() {
		return categoryInfoId;
	}

	/**
	 * @param categoryInfoId
	 *            the categoryInfoId to set
	 */
	public void setCategoryInfoId(Long categoryInfoId) {
		this.categoryInfoId = categoryInfoId;
	}

	public String toString() {
		return "Datbase User Record loginid : " + id + "Password : " + password
				+ "User Role : " + role + "User Email : " + email
				+ "User Status : " + status;
	}

}
