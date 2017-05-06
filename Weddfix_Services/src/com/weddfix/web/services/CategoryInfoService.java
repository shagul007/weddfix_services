package com.weddfix.web.services;

import java.util.List;

import com.weddfix.web.formbean.DirectoryCategoryInfoFormBean;
import com.weddfix.web.formbean.DirectoryCategoryReviewFormBean;
import com.weddfix.web.formbean.DirectoryCategoryShortlistedFormBean;
import com.weddfix.web.formbean.DirectorySendInfoFormBean;
import com.weddfix.web.formbean.DirectoryUserProfileFormBean;
import com.weddfix.web.formbean.OrganizationFormBean;
import com.weddfix.web.formbean.PhotoGalleryFormBean;
import com.weddfix.web.implementation.CategoryInfoDaoImpl;
import com.weddfix.web.interfaces.CategoryInfoDao;

public class CategoryInfoService {

	CategoryInfoDao categoryInfoDao = new CategoryInfoDaoImpl();

	public Long saveCatagoryInfoDetails(DirectoryCategoryInfoFormBean catagoryInfoFormBean, Long photoId) {
		Long id = categoryInfoDao.saveCatagoryInfoDetails(catagoryInfoFormBean, photoId);
		return id;
	}
	
	public Long saveShortlistDetails(DirectoryCategoryShortlistedFormBean catagoryShortlistedFormBean) {
		Long id = categoryInfoDao.saveShortlistDetails(catagoryShortlistedFormBean);
		return id;
	}
	
	public Long saveActiveBannerPhoto(PhotoGalleryFormBean photoGalleryFormBean) {
		Long id = categoryInfoDao.saveActiveBannerPhoto(photoGalleryFormBean);
		return id;
	}
	
	public List<DirectoryCategoryInfoFormBean> loadMyWishlistDetails(Long userId) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoDao.loadMyWishlistDetails(userId);
		return categoryInfoList;
	}
	
	public List<DirectorySendInfoFormBean> loadMyBookingDetails(Long userId) {
		List<DirectorySendInfoFormBean> bookingInfoList = categoryInfoDao.loadMyBookingDetails(userId);
		return bookingInfoList;
	}
	
	public List<DirectorySendInfoFormBean> loadVendorBookingDetails(Long userId) {
		List<DirectorySendInfoFormBean> bookingInfoList = categoryInfoDao.loadVendorBookingDetails(userId);
		return bookingInfoList;
	}

	public List<DirectoryCategoryInfoFormBean> searchByCategory(Long categoryId, Long userId) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoDao.searchByCategory(categoryId, userId);
		return categoryInfoList;
	}
	
	public List<DirectoryCategoryInfoFormBean> searchCategoryByCity(Long categoryId, Long cityId, Long userId) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoDao.searchCategoryByCity(categoryId, cityId, userId);
		return categoryInfoList;
	}
	
	public List<DirectoryCategoryInfoFormBean> searchCategoryByLocation(Long pincode, Long userId) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoDao.searchCategoryByLocation(pincode, userId);
		return categoryInfoList;
	}
	
	public List<DirectoryCategoryInfoFormBean> searchCategoryByCityId(Long cityId, Long userId) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoDao.searchCategoryByCityId(cityId, userId);
		return categoryInfoList;
	}
	
	public List<DirectoryCategoryInfoFormBean> searchCategoryByUserId(Long userId) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoDao.searchCategoryByUserId(userId);
		return categoryInfoList;
	}
	
	public List<DirectoryCategoryInfoFormBean> loadAllClientInfos(Long orgId, String limit) {
		List<DirectoryCategoryInfoFormBean> clientInfoList = categoryInfoDao.loadAllClientInfos(orgId, limit);
		return clientInfoList;
	}
	
	public List<OrganizationFormBean> loadAllOrgInfos() {
		List<OrganizationFormBean> clientInfoList = categoryInfoDao.loadAllOrgInfos();
		return clientInfoList;
	}

	public DirectoryCategoryInfoFormBean loadClientInfo(Long id) {
		DirectoryCategoryInfoFormBean clientInfo = categoryInfoDao.loadClientInfo(id);
		return clientInfo;
	}
	
	public List<DirectoryCategoryInfoFormBean> loadVenueDetails(Long userId) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoDao.loadVenueDetails(userId);
		return categoryInfoList;
	}
	
	public List<DirectoryCategoryInfoFormBean> loadVendorDetails(Long id) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoDao.loadVendorDetails(id);
		return categoryInfoList;
	}
	
	public List<DirectoryCategoryInfoFormBean> loadVendorDetailsByCompanyName(String companyName) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoDao.loadVendorDetailsByCompanyName(companyName);
		return categoryInfoList;
	}
	
	public DirectoryCategoryInfoFormBean loadEditVenueDetails(Long id) {
		DirectoryCategoryInfoFormBean categoryInfo = categoryInfoDao.loadEditVenueDetails(id);
		return categoryInfo;
	}

	public Long deleteCategoryInfo(Long deleteCategoryId) {
		Long id = categoryInfoDao.deleteCategoryInfo(deleteCategoryId);
		return id;
	}
	
	public Long deleteShortlistDetails(Long id) {
		Long status = categoryInfoDao.deleteShortlistDetails(id);
		return status;
	}
	
	public Long deletePhoto(Long delPhotoId) {
		Long id = categoryInfoDao.deletePhoto(delPhotoId);
		return id;
	}
	
	public List<PhotoGalleryFormBean> loadPhotoGalleryDetails(Long userId,
			Long orgId, String photoType) {
		List<PhotoGalleryFormBean> photoGalleryList = categoryInfoDao.loadPhotoGalleryDetails(userId, orgId, photoType);
		return photoGalleryList;
	}
	
	public Long saveReviewDetails(DirectoryCategoryReviewFormBean categoryReviewFormBean) {
		Long id = categoryInfoDao.saveReviewDetails(categoryReviewFormBean);
		return id;
	}
	
	public Long saveSendInfoDetails(DirectorySendInfoFormBean sendInfoFormBean) {
		Long id = categoryInfoDao.saveSendInfoDetails(sendInfoFormBean);
		return id;
	}
	
	public List<PhotoGalleryFormBean> loadBannerPhotoDetails() {
		List<PhotoGalleryFormBean> photoGalleryList = categoryInfoDao.loadBannerPhotoDetails();
		return photoGalleryList;
	}
	
	public List<PhotoGalleryFormBean> loadPhotoGalleryDetailsByVendorId(Long id) {
		List<PhotoGalleryFormBean> photoGalleryList = categoryInfoDao.loadPhotoGalleryDetailsByVendorId(id);
		return photoGalleryList;
	}
	
	public List<DirectoryCategoryShortlistedFormBean> loadShortlistedDetailsByUserId(Long userId, Long vendorId) {
		List<DirectoryCategoryShortlistedFormBean> shortlistedList = categoryInfoDao.loadShortlistedDetailsByUserId(userId, vendorId);
		return shortlistedList;
	}
	
	public List<DirectoryCategoryReviewFormBean> loadReviewDetailsByVendorId(Long vendorId) {
		List<DirectoryCategoryReviewFormBean> reviewInfos = categoryInfoDao.loadReviewDetailsByVendorId(vendorId);
		return reviewInfos;
	}
	
	public List<DirectoryUserProfileFormBean> loadPersonalDetailsByUserId(Long userId) {
		List<DirectoryUserProfileFormBean> personalDetails = categoryInfoDao.loadPersonalDetailsByUserId(userId);
		return personalDetails;
	}

	public List<PhotoGalleryFormBean> loadPhotoGalleryInfo() {
		List<PhotoGalleryFormBean> photoGalleryInfo = categoryInfoDao.loadPhotoGalleryInfo();
		return photoGalleryInfo;
	}

	public Long savePhotoGalleryDetails(PhotoGalleryFormBean photoGalleryFormBean) {
		Long id = categoryInfoDao.savePhotoGalleryDetails(photoGalleryFormBean);
		return id;
	}
	
	public Long saveProfilePicture(PhotoGalleryFormBean photoGalleryFormBean, String profilePic) {
		Long id = categoryInfoDao.saveProfilePicture(photoGalleryFormBean, profilePic);
		return id;
	}
	
	public Long saveVendorProfilePicture(PhotoGalleryFormBean photoGalleryFormBean, String profilePic) {
		Long id = categoryInfoDao.saveVendorProfilePicture(photoGalleryFormBean, profilePic);
		return id;
	}
	
	public Long saveCategoryProfilePicture(PhotoGalleryFormBean photoGalleryFormBean, String profilePic) {
		Long id = categoryInfoDao.saveCategoryProfilePicture(photoGalleryFormBean, profilePic);
		return id;
	}

	public List<PhotoGalleryFormBean> loadProfilePictures(Long userId, Long profileId) {
		List<PhotoGalleryFormBean> photoGalleryInfo = categoryInfoDao.loadProfilePictures(userId, profileId);
		return photoGalleryInfo;
	}

}
