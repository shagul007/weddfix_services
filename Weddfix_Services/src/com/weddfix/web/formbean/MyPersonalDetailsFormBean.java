package com.weddfix.web.formbean;

import java.io.Serializable;
import java.util.Date;

public class MyPersonalDetailsFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String fullName;
	private String email;
	private String password;
	private String passwordHash;
	private Long mobile;
	private Long countryId;
	private Long stateId;
	private Long cityId;
	private Long pincode;
	private String description;
	private Long profilePictureId;
	private Date weddingDate;
	private String weddingDateStr;
	private String websiteUrl;
	private String facebookUrl;
	private String twitterUrl;
	private String linkedinUrl;
	private String pinterestUrl;
	private String instagramUrl;
	private String country;
	private String state;
	private String city;
	private String fileName;

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
	 * @return the countryId
	 */
	public Long getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId
	 *            the countryId to set
	 */
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the stateId
	 */
	public Long getStateId() {
		return stateId;
	}

	/**
	 * @param stateId
	 *            the stateId to set
	 */
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the cityId
	 */
	public Long getCityId() {
		return cityId;
	}

	/**
	 * @param cityId
	 *            the cityId to set
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the pincode
	 */
	public Long getPincode() {
		return pincode;
	}

	/**
	 * @param pincode
	 *            the pincode to set
	 */
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the profilePictureId
	 */
	public Long getProfilePictureId() {
		return profilePictureId;
	}

	/**
	 * @param profilePictureId
	 *            the profilePictureId to set
	 */
	public void setProfilePictureId(Long profilePictureId) {
		this.profilePictureId = profilePictureId;
	}

	/**
	 * @return the weddingDate
	 */
	public Date getWeddingDate() {
		return weddingDate;
	}

	/**
	 * @param weddingDate
	 *            the weddingDate to set
	 */
	public void setWeddingDate(Date weddingDate) {
		this.weddingDate = weddingDate;
	}

	/**
	 * @return the weddingDateStr
	 */
	public String getWeddingDateStr() {
		return weddingDateStr;
	}

	/**
	 * @param weddingDateStr
	 *            the weddingDateStr to set
	 */
	public void setWeddingDateStr(String weddingDateStr) {
		this.weddingDateStr = weddingDateStr;
	}

	/**
	 * @return the websiteUrl
	 */
	public String getWebsiteUrl() {
		return websiteUrl;
	}

	/**
	 * @param websiteUrl
	 *            the websiteUrl to set
	 */
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	/**
	 * @return the facebookUrl
	 */
	public String getFacebookUrl() {
		return facebookUrl;
	}

	/**
	 * @param facebookUrl
	 *            the facebookUrl to set
	 */
	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}

	/**
	 * @return the twitterUrl
	 */
	public String getTwitterUrl() {
		return twitterUrl;
	}

	/**
	 * @param twitterUrl
	 *            the twitterUrl to set
	 */
	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

	/**
	 * @return the linkedinUrl
	 */
	public String getLinkedinUrl() {
		return linkedinUrl;
	}

	/**
	 * @param linkedinUrl
	 *            the linkedinUrl to set
	 */
	public void setLinkedinUrl(String linkedinUrl) {
		this.linkedinUrl = linkedinUrl;
	}

	/**
	 * @return the pinterestUrl
	 */
	public String getPinterestUrl() {
		return pinterestUrl;
	}

	/**
	 * @param pinterestUrl
	 *            the pinterestUrl to set
	 */
	public void setPinterestUrl(String pinterestUrl) {
		this.pinterestUrl = pinterestUrl;
	}

	/**
	 * @return the instagramUrl
	 */
	public String getInstagramUrl() {
		return instagramUrl;
	}

	/**
	 * @param instagramUrl
	 *            the instagramUrl to set
	 */
	public void setInstagramUrl(String instagramUrl) {
		this.instagramUrl = instagramUrl;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
