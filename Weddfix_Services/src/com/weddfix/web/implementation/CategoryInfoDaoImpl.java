package com.weddfix.web.implementation;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.weddfix.web.formbean.DirectoryCategoryInfoFormBean;
import com.weddfix.web.formbean.DirectoryCategoryReviewFormBean;
import com.weddfix.web.formbean.DirectoryCategoryShortlistedFormBean;
import com.weddfix.web.formbean.DirectorySendInfoFormBean;
import com.weddfix.web.formbean.DirectoryUserProfileFormBean;
import com.weddfix.web.formbean.OrganizationFormBean;
import com.weddfix.web.formbean.PhotoGalleryFormBean;
import com.weddfix.web.interfaces.CategoryInfoDao;
import com.weddfix.web.util.CommonConstants;
import com.weddfix.web.util.HibernateUtil;

/**
 * 
 * 
 * @author Shagul
 * 
 */
public class CategoryInfoDaoImpl implements CategoryInfoDao, SessionAware {

	Session conn;

	private static final Logger logger = Logger
			.getLogger(CategoryInfoDaoImpl.class);

	Long id;

	public Long saveCatagoryInfoDetails(DirectoryCategoryInfoFormBean catagoryInfoFormBean, Long photoId) {

		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		try {

			logger.info("-----------Insert category info details Method--------------");

			if (catagoryInfoFormBean.getId() != null) {
					conn.getNamedQuery("updateCategoryInfoId")
							.setLong("id", catagoryInfoFormBean.getId())
							.setString("companyName", catagoryInfoFormBean.getCompanyName())
							.setString("contactName", catagoryInfoFormBean.getContactName())
							.setLong("mobile", catagoryInfoFormBean.getMobile())
							.setString("phone", catagoryInfoFormBean.getPhone())
							.setString("email", catagoryInfoFormBean.getEmail())
							.setString("websiteUrl", catagoryInfoFormBean.getWebsiteUrl())
							.setLong("categoryId",
									catagoryInfoFormBean.getCategoryId())
//							.setLong("subCategoryId", catagoryInfoFormBean.getSubCategoryId())
							.setString("location", catagoryInfoFormBean.getLocation())
							.setString("address", catagoryInfoFormBean.getAddress())
							.setLong("cityId",
									catagoryInfoFormBean.getCityId())
							.setLong("stateId",
									catagoryInfoFormBean.getStateId())
							.setLong("countryId",
									catagoryInfoFormBean.getCountryId())
							.setLong("pincode",
									catagoryInfoFormBean.getPincode())
							.setString("description",
									catagoryInfoFormBean.getDescription())
							.setLong("price", catagoryInfoFormBean.getPrice())
							.setString("latitude", catagoryInfoFormBean.getLatitude())
							.setString("categoryVideoUrl", catagoryInfoFormBean.getCategoryVideoUrl())
							.setLong("updatedBy",
									catagoryInfoFormBean.getUpdatedBy())
							.setDate("updatedDate", new Date()).executeUpdate();

					tx.commit();
					
					if(photoId > 0) {
						Transaction tx1 = conn.beginTransaction();
						conn.getNamedQuery("updateCategoryInfoByUserId")
						.setLong("categoryPictureId", photoId)
						.setString("photoType", "C"+100000+catagoryInfoFormBean.getId())
						.setLong("id", catagoryInfoFormBean.getId())
						.setLong("updatedBy",
								catagoryInfoFormBean.getUpdatedBy())
						.setDate("updatedDate", new Date()).executeUpdate();
						
						conn.getNamedQuery("updatePhotoGalleryPhotoTypeById")
						.setLong("id", photoId)
						.setString("photoType", "C"+100000+catagoryInfoFormBean.getId())
						.setLong("orgId", catagoryInfoFormBean.getCategoryId())
						.setLong("updatedBy",
								catagoryInfoFormBean.getUpdatedBy())
						.setDate("updatedDate", new Date()).executeUpdate();
						tx1.commit();
					}
					
				id = 1L;
			} else {
				catagoryInfoFormBean.setStatusId(CommonConstants.ACTIVE);
				catagoryInfoFormBean.setCreatedDate(new Date());
				id = (Long) conn.save(catagoryInfoFormBean);
				tx.commit();
				
					if(photoId > 0) {
						Transaction tx1 = conn.beginTransaction();
						conn.getNamedQuery("updateCategoryInfoByUserId")
						.setLong("categoryPictureId", photoId)
						.setString("photoType", "C"+100000+id)
						.setLong("id", id)
						.setLong("updatedBy",
								catagoryInfoFormBean.getCreatedBy())
						.setDate("updatedDate", new Date()).executeUpdate();
						
						conn.getNamedQuery("updatePhotoGalleryPhotoTypeById")
						.setLong("id", photoId)
						.setString("photoType", "C"+100000+id)
						.setLong("orgId", catagoryInfoFormBean.getCategoryId())
						.setLong("updatedBy",
								catagoryInfoFormBean.getCreatedBy())
						.setDate("updatedDate", new Date()).executeUpdate();
						tx1.commit();
					}
			}

		} catch (HibernateException hibernateException) {
			tx.rollback();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return id;
	}
	
	public Long saveReviewDetails(DirectoryCategoryReviewFormBean categoryReviewFormBean) {

		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		try {

			logger.info("-----------Insert review info details Method--------------");

				categoryReviewFormBean.setStatusId(CommonConstants.ACTIVE);
				categoryReviewFormBean.setCreatedDate(new Date());
				id = (Long) conn.save(categoryReviewFormBean);
				tx.commit();
				
				Transaction tx1 = conn.beginTransaction();
				if(categoryReviewFormBean.getRating() > categoryReviewFormBean.getMaxRating()) {
					conn.getNamedQuery("updateCategoryInfoMaxRatingById")
					.setLong("id", categoryReviewFormBean.getVendorId())
					.setLong("maxRating", categoryReviewFormBean.getRating())
					.setLong("maxUsersRating", categoryReviewFormBean.getMaxUsersRating()+1L)
					.setLong("updatedBy",
							categoryReviewFormBean.getCreatedBy())
					.setDate("updatedDate", new Date()).executeUpdate();
				} else {
					conn.getNamedQuery("updateCategoryInfoMaxUsersRatingById")
					.setLong("id", categoryReviewFormBean.getVendorId())
					.setLong("maxUsersRating", categoryReviewFormBean.getMaxUsersRating()+1L)
					.setLong("updatedBy",
							categoryReviewFormBean.getCreatedBy())
					.setDate("updatedDate", new Date()).executeUpdate();
				}
						
				conn.getNamedQuery("updateSendInfoFeedbackStatusById")
				.setLong("id", categoryReviewFormBean.getSendInfoId())
				.setString("feedbackStatus", CommonConstants.SUCCESS)
				.setLong("updatedBy",
						categoryReviewFormBean.getCreatedBy())
				.setDate("updatedDate", new Date()).executeUpdate();
						
				tx1.commit();

		} catch (HibernateException hibernateException) {
			tx.rollback();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return id;
	}
	
	public Long saveSendInfoDetails(DirectorySendInfoFormBean sendInfoFormBean) {

		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		try {

			logger.info("-----------Insert send info details Method--------------");
			if (sendInfoFormBean.getId() != null) {
				conn.getNamedQuery("updateSendInfoId")
						.setLong("id", sendInfoFormBean.getId())
						.setBoolean("feedbackRequested", sendInfoFormBean.getFeedbackRequested())
						.setLong("updatedBy",
								sendInfoFormBean.getUpdatedBy())
						.setDate("updatedDate", new Date()).executeUpdate();

				tx.commit();
			id = 1L;
		} else {
				sendInfoFormBean.setStatusId(CommonConstants.ACTIVE);
				sendInfoFormBean.setCreatedDate(new Date());
				id = (Long) conn.save(sendInfoFormBean);
				tx.commit();
		}
				
		} catch (HibernateException hibernateException) {
			tx.rollback();
			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return id;
	}
	
	public Long saveShortlistDetails(DirectoryCategoryShortlistedFormBean catagoryShortlistedFormBean) {

		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		try {

			logger.info("-----------Insert shortlisted details Method--------------");

				catagoryShortlistedFormBean.setStatusId(CommonConstants.ACTIVE);
				catagoryShortlistedFormBean.setCreatedDate(new Date());
				id = (Long) conn.save(catagoryShortlistedFormBean);
				tx.commit();
				
		} catch (HibernateException hibernateException) {
			tx.rollback();
			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return id;
	}
	
	public Long saveActiveBannerPhoto(PhotoGalleryFormBean photoGalleryFormBean) {

		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		try {

			logger.info("-----------Update photogallery banner details Method--------------");

			conn.getNamedQuery("updateBannerPhotoGalleryById")
			.setLong("id", photoGalleryFormBean.getId())
			.setLong("statusId", photoGalleryFormBean.getStatusId())
			.setLong("updatedBy",
					photoGalleryFormBean.getCreatedBy())
			.setDate("updatedDate", new Date()).executeUpdate();
				tx.commit();
			
			id = 1L;
				
		} catch (HibernateException hibernateException) {
			tx.rollback();
			hibernateException.printStackTrace();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return id;
	}

	public void setSession(Map<String, Object> arg0) {

	}
	
	@SuppressWarnings("unchecked")
	public List<OrganizationFormBean> loadAllOrgInfos() {
		List<OrganizationFormBean> orgInfoList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			orgInfoList = conn
						.getNamedQuery("getAllOrganization").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return orgInfoList;
	}

	@SuppressWarnings("unchecked")
	public List<DirectoryCategoryInfoFormBean> searchByCategory(Long categoryId, Long userId) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			categoryInfoList = conn
					.getNamedQuery("getCategoryInfoById")
					.setLong("categoryId", categoryId)
					.setLong("userId", userId).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return categoryInfoList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectoryCategoryInfoFormBean> searchCategoryByCity(Long categoryId,
			Long cityId, Long userId) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			categoryInfoList = conn
					.getNamedQuery("getCategoryInfoByCity")
					.setLong("categoryId", categoryId)
					.setLong("cityId", cityId)
					.setLong("userId", userId).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return categoryInfoList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectoryCategoryInfoFormBean> searchCategoryByLocation(Long pincode, Long userId) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			categoryInfoList = conn
					.getNamedQuery("getCategoryInfoByLocation")
					.setLong("pincode", pincode)
					.setLong("userId", userId).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return categoryInfoList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectoryCategoryInfoFormBean> searchCategoryByCityId(Long cityId, Long userId) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			categoryInfoList = conn
					.getNamedQuery("getCategoryInfoByCityId")
					.setLong("cityId", cityId)
					.setLong("userId", userId).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return categoryInfoList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectoryCategoryInfoFormBean> searchCategoryByUserId(Long userId) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			categoryInfoList = conn
					.getNamedQuery("getCategoryInfoByUserId")
					.setLong("userId", userId).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return categoryInfoList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectoryCategoryInfoFormBean> loadMyWishlistDetails(Long userId) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
				categoryInfoList = conn
						.getNamedQuery("getMyWishlistDetailsoByUserId")
						.setLong("userId", userId).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return categoryInfoList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectorySendInfoFormBean> loadMyBookingDetails(Long userId) {
		List<DirectorySendInfoFormBean> bookingInfoList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
				bookingInfoList = conn
						.getNamedQuery("getMyBookingDetailsoByUserId")
						.setLong("userId", userId).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return bookingInfoList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectorySendInfoFormBean> loadVendorBookingDetails(Long userId) {
		List<DirectorySendInfoFormBean> bookingInfoList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
				bookingInfoList = conn
						.getNamedQuery("getVendorBookingDetailsoByUserId")
						.setLong("userId", userId).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return bookingInfoList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectoryCategoryInfoFormBean> loadAllClientInfos(Long orgId,
			String limit) {
		List<DirectoryCategoryInfoFormBean> clientInfoList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			if (limit != null)
				clientInfoList = conn
						.getNamedQuery("getAllClientInfoByOrgNameAndLimit")
						.setLong("orgId", orgId)
						.setLong("limit", Long.parseLong(limit)).list();
			else
				clientInfoList = conn
						.getNamedQuery("getAllClientInfoByOrgName")
						.setLong("orgId", orgId).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return clientInfoList;
	}

	public DirectoryCategoryInfoFormBean loadClientInfo(Long id) {
		DirectoryCategoryInfoFormBean clientInfo = new DirectoryCategoryInfoFormBean();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> clientInfos = conn.getNamedQuery("getClientInfoById")
					.setLong("id", id).list();
			Iterator<?> itr = clientInfos.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				clientInfo.setId(Long.parseLong(obj[0].toString()));
				/*clientInfo.setOrgName(obj[1].toString());
				clientInfo.setName(obj[2].toString());
				clientInfo.setFileName(obj[3].toString());
				clientInfo.setPackageFrom((BigDecimal) obj[7]);
				clientInfo.setManagerName(obj[8].toString());
				clientInfo.setEmail(obj[9].toString());
				clientInfo.setMobile(Long.parseLong(obj[10].toString()));
				clientInfo.setPhone(Long.parseLong(obj[11].toString()));
				clientInfo.setAddress(obj[12].toString());
				clientInfo.setCity(obj[13].toString());
				clientInfo.setPincode(Long.parseLong(obj[14].toString()));
				clientInfo.setStateId(Long.parseLong(obj[15].toString()));*/
			}

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return clientInfo;
	}

	@SuppressWarnings("unchecked")
	public List<DirectoryCategoryInfoFormBean> loadVenueDetails(Long userId) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
				categoryInfoList = conn
						.getNamedQuery("getVenueDetailsByUserId")
						.setLong("userId", userId).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return categoryInfoList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectoryCategoryInfoFormBean> loadVendorDetails(Long id) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
				categoryInfoList = conn
						.getNamedQuery("getVendorDetailsById")
						.setLong("id", id).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return categoryInfoList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectoryCategoryInfoFormBean> loadVendorDetailsByCompanyName(String companyName) {
		List<DirectoryCategoryInfoFormBean> categoryInfoList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
				categoryInfoList = conn
						.getNamedQuery("getVendorDetailsByCompanyName")
						.setString("companyName", companyName).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return categoryInfoList;
	}
	
	public DirectoryCategoryInfoFormBean loadEditVenueDetails(Long id) {
		DirectoryCategoryInfoFormBean categoryInfo = new DirectoryCategoryInfoFormBean();
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			List<?> userProfiles = conn
					.getNamedQuery("getVenueDetailsById")
					.setLong("id", id).list();
			Iterator<?> itr = userProfiles.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				categoryInfo.setId(Long.parseLong(obj[0].toString()));
				categoryInfo.setCompanyName(obj[1].toString());
				categoryInfo.setContactName(obj[2].toString());
				categoryInfo.setMobile(Long.parseLong(obj[3].toString()));
				if(obj[4] != null) {
					categoryInfo.setPhone(obj[4].toString());
				} else {
					categoryInfo.setPhone("");
				}
				categoryInfo.setEmail(obj[5].toString());
				if(obj[6] != null) {
					categoryInfo.setWebsiteUrl(obj[6].toString());
				} else {
					categoryInfo.setWebsiteUrl("");
				}
				if(obj[7] != null) {
					categoryInfo.setCategoryId(Long.parseLong(obj[7].toString()));
				} 
				if(obj[8] != null) {
					categoryInfo.setSubCategoryId(Long.parseLong(obj[8].toString()));
				} 
				categoryInfo.setLocation(obj[9].toString());
				categoryInfo.setAddress(obj[10].toString());
				categoryInfo.setCityId(Long.parseLong(obj[11].toString()));
				categoryInfo.setStateId(Long.parseLong(obj[12].toString()));
				categoryInfo.setCountryId(Long.parseLong(obj[13].toString()));
				categoryInfo.setPincode(Long.parseLong(obj[14].toString()));
				if(obj[15] != null) {
					categoryInfo.setDescription(obj[15].toString());
				} else {
					categoryInfo.setDescription("");
				}
				if(obj[16] != null) {
					categoryInfo.setSeatCapacityId(Long.parseLong(obj[16].toString()));
				} 
				if(obj[17] != null) {
					categoryInfo.setPrice(Long.parseLong(obj[17].toString()));
				} 
				if(obj[18] != null) {
					categoryInfo.setLatitude(obj[18].toString());
				} else {
					categoryInfo.setLatitude("");
				}
				if(obj[19] != null) {
					categoryInfo.setLongitude(obj[19].toString());
				} else {
					categoryInfo.setLongitude("");
				}
				if(obj[20] != null) {
					categoryInfo.setcategoryPictureId(Long.parseLong(obj[20].toString()));
				} 
				if(obj[21] != null) {
					categoryInfo.setCategoryVideoUrl(obj[21].toString());
				} else {
					categoryInfo.setCategoryVideoUrl(null);
				}
				if(obj[22] != null) {
					categoryInfo.setPhotoType(obj[22].toString());
				} else {
					categoryInfo.setPhotoType(null);
				}
				if(obj[23] != null) {
					categoryInfo.setFileName(obj[23].toString());
				} else {
					categoryInfo.setFileName(null);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return categoryInfo;
	}
	
	Long user;
	
	public Long deleteCategoryInfo(Long deleteCategoryId) {

		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Delete category info Method--------------");

			Transaction tx = conn.beginTransaction();
				conn.getNamedQuery("deleteCategoryInfoById")
						.setLong("id", deleteCategoryId).executeUpdate();
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
	
	public Long deleteShortlistDetails(Long id) {

		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Delete shortlist info Method--------------");

			Transaction tx = conn.beginTransaction();
				conn.getNamedQuery("deleteCategoryShortlistedById")
						.setLong("id", id).executeUpdate();
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
	
	public Long deletePhoto(Long delPhotoId) {

		try {
			conn = HibernateUtil.getSessionFactory().openSession();

			logger.info("-----------Delete category info Method--------------");

			Transaction tx = conn.beginTransaction();
				conn.getNamedQuery("deletePhotoById")
						.setLong("id", delPhotoId).executeUpdate();
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
	public List<PhotoGalleryFormBean> loadPhotoGalleryDetails(Long userId, Long orgId, String photoType) {
		List<PhotoGalleryFormBean> photoGalleryList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			photoGalleryList = conn
						.getNamedQuery("getPhotoGalleryDetailsByUserId")
						.setLong("userId", userId).setLong("orgId", orgId).setString("photoType", photoType).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return photoGalleryList;
	}
	
	@SuppressWarnings("unchecked")
	public List<PhotoGalleryFormBean> loadPhotoGalleryDetailsByVendorId(Long id) {
		List<PhotoGalleryFormBean> photoGalleryList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			photoGalleryList = conn
						.getNamedQuery("getPhotoGalleryDetailsById")
						.setLong("id", id).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return photoGalleryList;
	}
	
	@SuppressWarnings("unchecked")
	public List<PhotoGalleryFormBean> loadBannerPhotoDetails() {
		List<PhotoGalleryFormBean> photoGalleryList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			photoGalleryList = conn
						.getNamedQuery("getActiveBannerPhotos").list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return photoGalleryList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectoryCategoryReviewFormBean> loadReviewDetailsByVendorId(Long vendorId) {
		List<DirectoryCategoryReviewFormBean> reviewList = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			reviewList = conn
						.getNamedQuery("getReviewDetailsByVendorId")
						.setLong("vendorId", vendorId).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return reviewList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectoryUserProfileFormBean> loadPersonalDetailsByUserId(Long userId) {
		List<DirectoryUserProfileFormBean> personalDetails = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			personalDetails = conn
						.getNamedQuery("getUserPersonalDetailsByUserId")
						.setLong("userId", userId).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return personalDetails;
	}
	
	@SuppressWarnings("unchecked")
	public List<PhotoGalleryFormBean> loadPhotoGalleryInfo() {
		List<PhotoGalleryFormBean> photoGalleryInfos = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			photoGalleryInfos = conn
					.getNamedQuery("getBannerPhotoGallery").list();

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return photoGalleryInfos;
	}

	public Long savePhotoGalleryDetails(
			PhotoGalleryFormBean photoGalleryFormBean) {

		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		try {

			logger.info("-----------Insert photo gallery details Method--------------");

			if (photoGalleryFormBean.getId() != null) {
				if (photoGalleryFormBean.getFileName() != null) {
					conn.getNamedQuery("updatePhotoGalleryImageById")
							.setLong("id", photoGalleryFormBean.getId())
							.setLong("orgId", photoGalleryFormBean.getOrgId())
							.setString("fileName",
									photoGalleryFormBean.getFileName())
							.setString("fileType",
									photoGalleryFormBean.getFileType())
							.setLong("fileSize",
									photoGalleryFormBean.getFileSize())
							.setString("filePath",
									photoGalleryFormBean.getFilePath())
							.setLong("updatedBy",
									photoGalleryFormBean.getUpdatedBy())
							.setDate("updatedDate", new Date()).executeUpdate();
				} else {
					conn.getNamedQuery("updatePhotoGalleryById")
							.setLong("id", photoGalleryFormBean.getId())
							.setLong("orgId", photoGalleryFormBean.getOrgId())
							.setLong("updatedBy",
									photoGalleryFormBean.getUpdatedBy())
							.setDate("updatedDate", new Date()).executeUpdate();
				}
				id = 1L;
			} else {
				photoGalleryFormBean.setStatusId(CommonConstants.ACTIVE);
				photoGalleryFormBean.setCreatedDate(new Date());
				id = (Long) conn.save(photoGalleryFormBean);
			}

			tx.commit();

		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
			tx.rollback();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return id;
	}

	public Long saveProfilePicture(
			PhotoGalleryFormBean photoGalleryFormBean, String profilePic) {

		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		try {

			logger.info("-----------Insert photo gallery details Method--------------");

			if (!profilePic.equals("null")) {
				List<?> imageExist = conn
						.getNamedQuery("getImageDetailsById")
						.setLong("id", Long.parseLong(profilePic)).list();
				
				if(!imageExist.isEmpty()) {
				conn.getNamedQuery("updateProfilePictureById")
						.setLong("id", Long.parseLong(profilePic))
						.setString("fileName",
								photoGalleryFormBean.getFileName())
						.setString("fileType",
								photoGalleryFormBean.getFileType())
						.setLong("fileSize",
								photoGalleryFormBean.getFileSize())
						.setString("filePath",
								photoGalleryFormBean.getFilePath())
						.setLong("updatedBy",
								photoGalleryFormBean.getUpdatedBy())
						.setDate("updatedDate", new Date()).executeUpdate();
				id = 1L;
				} else {
					photoGalleryFormBean.setStatusId(CommonConstants.ACTIVE);
					photoGalleryFormBean.setCreatedBy(photoGalleryFormBean.getUpdatedBy());
					photoGalleryFormBean.setCreatedDate(new Date());
					id = (Long) conn.save(photoGalleryFormBean);
						conn.getNamedQuery("updatePersonalDetailsByUserId")
								.setLong("userId",
										photoGalleryFormBean.getUpdatedBy())
								.setLong("profilePictureId", id)
								.setLong("updatedBy",
										photoGalleryFormBean.getUpdatedBy())
								.setDate("updatedDate", new Date()).executeUpdate();
				}
			} else {
				photoGalleryFormBean.setStatusId(CommonConstants.ACTIVE);
				photoGalleryFormBean.setCreatedDate(new Date());
				id = (Long) conn.save(photoGalleryFormBean);
					conn.getNamedQuery("updatePersonalDetailsByUserId")
							.setLong("userId",
									photoGalleryFormBean.getCreatedBy())
							.setLong("profilePictureId", id)
							.setLong("updatedBy",
									photoGalleryFormBean.getCreatedBy())
							.setDate("updatedDate", new Date()).executeUpdate();
			}

			tx.commit();

		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
			tx.rollback();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return id;
	}
	
	public Long saveVendorProfilePicture(
			PhotoGalleryFormBean photoGalleryFormBean, String profilePic) {

		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		try {

			logger.info("-----------Insert photo gallery details Method--------------");

			if (!profilePic.equals("null")) {
				List<?> imageExist = conn
						.getNamedQuery("getImageDetailsById")
						.setLong("id", Long.parseLong(profilePic)).list();
				
				if(!imageExist.isEmpty()) {
				conn.getNamedQuery("updateProfilePictureById")
						.setLong("id", Long.parseLong(profilePic))
						.setString("fileName",
								photoGalleryFormBean.getFileName())
						.setString("fileType",
								photoGalleryFormBean.getFileType())
						.setLong("fileSize",
								photoGalleryFormBean.getFileSize())
						.setString("filePath",
								photoGalleryFormBean.getFilePath())
						.setLong("updatedBy",
								photoGalleryFormBean.getUpdatedBy())
						.setDate("updatedDate", new Date()).executeUpdate();
				id = 1L;
				} else {
					photoGalleryFormBean.setStatusId(CommonConstants.ACTIVE);
					photoGalleryFormBean.setCreatedBy(photoGalleryFormBean.getUpdatedBy());
					photoGalleryFormBean.setCreatedDate(new Date());
					id = (Long) conn.save(photoGalleryFormBean);
					conn.getNamedQuery("updateVendorPersonalDetailsByUserId")
					.setLong("userId",
							photoGalleryFormBean.getUpdatedBy())
					.setLong("profilePictureId", id)
					.setLong("updatedBy",
							photoGalleryFormBean.getUpdatedBy())
					.setDate("updatedDate", new Date()).executeUpdate();
				}
			} else {
				photoGalleryFormBean.setStatusId(CommonConstants.ACTIVE);
				photoGalleryFormBean.setCreatedDate(new Date());
				id = (Long) conn.save(photoGalleryFormBean);
					conn.getNamedQuery("updateVendorPersonalDetailsByUserId")
							.setLong("userId",
									photoGalleryFormBean.getCreatedBy())
							.setLong("profilePictureId", id)
							.setLong("updatedBy",
									photoGalleryFormBean.getCreatedBy())
							.setDate("updatedDate", new Date()).executeUpdate();
			}

			tx.commit();

		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
			tx.rollback();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return id;
	}
	
	public Long saveCategoryProfilePicture(
			PhotoGalleryFormBean photoGalleryFormBean, String profilePic) {

		conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		try {

			logger.info("-----------Insert photo gallery details Method--------------");
			
			if (!profilePic.equals("null")) {
				List<?> imageExist = conn
						.getNamedQuery("getImageDetailsById")
						.setLong("id", Long.parseLong(profilePic)).list();
				
				if(!imageExist.isEmpty()) {
				conn.getNamedQuery("updateProfilePictureById")
						.setLong("id", Long.parseLong(profilePic))
						.setString("fileName",
								photoGalleryFormBean.getFileName())
						.setString("fileType",
								photoGalleryFormBean.getFileType())
						.setLong("fileSize",
								photoGalleryFormBean.getFileSize())
						.setString("filePath",
								photoGalleryFormBean.getFilePath())
						.setLong("updatedBy",
								photoGalleryFormBean.getUpdatedBy())
						.setDate("updatedDate", new Date()).executeUpdate();
				id = 1L;
				} else {
					photoGalleryFormBean.setStatusId(CommonConstants.ACTIVE);
					photoGalleryFormBean.setCreatedBy(photoGalleryFormBean.getUpdatedBy());
					photoGalleryFormBean.setCreatedDate(new Date());
					id = (Long) conn.save(photoGalleryFormBean);
				}
			} else {
	
					photoGalleryFormBean.setStatusId(CommonConstants.ACTIVE);
					photoGalleryFormBean.setCreatedDate(new Date());
					id = (Long) conn.save(photoGalleryFormBean);
			}

			tx.commit();

		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
			tx.rollback();
			logger.error("Exception ocured while inserting data into database");

		} finally {
			conn.flush();
			conn.close();
		}
		return id;
	}
	
	@SuppressWarnings("unchecked")
	public List<PhotoGalleryFormBean> loadProfilePictures(Long userId,
			Long profileId) {
		List<PhotoGalleryFormBean> photoGalleryInfo = null;
		try {
			conn = HibernateUtil.getSessionFactory().openSession();
			photoGalleryInfo = conn.getNamedQuery("getProfilePicturesByUserId")
					.setLong("userId", userId)
					.setString("profileId", "P" + profileId).list();

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return photoGalleryInfo;
	}

}
