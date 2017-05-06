package com.weddfix.web.interfaces;

import java.util.List;

import com.weddfix.web.formbean.DirectoryCategoryInfoFormBean;
import com.weddfix.web.formbean.DirectoryCategoryReviewFormBean;
import com.weddfix.web.formbean.DirectoryCategoryShortlistedFormBean;
import com.weddfix.web.formbean.DirectorySendInfoFormBean;
import com.weddfix.web.formbean.DirectoryUserProfileFormBean;
import com.weddfix.web.formbean.OrganizationFormBean;
import com.weddfix.web.formbean.PhotoGalleryFormBean;

public interface CategoryInfoDao {

	public Long saveCatagoryInfoDetails(
			DirectoryCategoryInfoFormBean catagoryInfoFormBean, Long photoId);

	public List<DirectoryCategoryInfoFormBean> loadAllClientInfos(Long orgId,
			String limit);

	public DirectoryCategoryInfoFormBean loadClientInfo(Long id);

	public List<DirectoryCategoryInfoFormBean> searchByCategory(
			Long categoryId, Long userId);

	public List<DirectoryCategoryInfoFormBean> searchCategoryByCity(
			Long categoryId, Long cityId, Long userId);

	public List<DirectoryCategoryInfoFormBean> loadVendorDetails(Long id);

	public List<DirectoryCategoryInfoFormBean> loadVenueDetails(Long userId);

	public DirectoryCategoryInfoFormBean loadEditVenueDetails(Long id);

	public Long deleteCategoryInfo(Long deleteCategoryId);

	public Long deletePhoto(Long delPhotoId);

	public List<PhotoGalleryFormBean> loadPhotoGalleryDetails(Long userId,
			Long orgId, String photoType);

	public List<PhotoGalleryFormBean> loadPhotoGalleryDetailsByVendorId(Long id);

	public List<PhotoGalleryFormBean> loadPhotoGalleryInfo();

	public List<PhotoGalleryFormBean> loadProfilePictures(Long userId,
			Long profileId);

	public List<DirectoryCategoryReviewFormBean> loadReviewDetailsByVendorId(
			Long vendorId);

	public List<DirectoryUserProfileFormBean> loadPersonalDetailsByUserId(
			Long userId);

	public Long saveShortlistDetails(
			DirectoryCategoryShortlistedFormBean catagoryShortlistedFormBean);

	public Long saveReviewDetails(
			DirectoryCategoryReviewFormBean categoryReviewFormBean);

	public Long saveSendInfoDetails(DirectorySendInfoFormBean sendInfoFormBean);

	public List<DirectoryCategoryInfoFormBean> loadMyWishlistDetails(Long userId);

	public Long deleteShortlistDetails(Long id);

	public Long savePhotoGalleryDetails(
			PhotoGalleryFormBean photoGalleryFormBean);

	public Long saveProfilePicture(PhotoGalleryFormBean photoGalleryFormBean,
			String profilePic);

	public List<OrganizationFormBean> loadAllOrgInfos();

	public Long saveCategoryProfilePicture(
			PhotoGalleryFormBean photoGalleryFormBean, String profilePic);

	public Long saveActiveBannerPhoto(PhotoGalleryFormBean photoGalleryFormBean);

	public List<PhotoGalleryFormBean> loadBannerPhotoDetails();

	public List<DirectorySendInfoFormBean> loadMyBookingDetails(Long userId);

	public List<DirectorySendInfoFormBean> loadVendorBookingDetails(Long userId);

	public Long saveVendorProfilePicture(
			PhotoGalleryFormBean photoGalleryFormBean, String profilePic);

	public List<DirectoryCategoryInfoFormBean> loadVendorDetailsByCompanyName(
			String companyName);

	public List<DirectoryCategoryInfoFormBean> searchCategoryByLocation(
			Long pincode, Long userId);

	public List<DirectoryCategoryInfoFormBean> searchCategoryByCityId(
			Long cityId, Long userId);

	public List<DirectoryCategoryInfoFormBean> searchCategoryByUserId(
			Long userId);

	public List<DirectoryCategoryShortlistedFormBean> loadShortlistedDetailsByUserId(
			Long userId, Long vendorId);

}
