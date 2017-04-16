package com.weddfix.web.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.classic.Session;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.weddfix.web.formbean.DirectoryCategoryInfoFormBean;
import com.weddfix.web.formbean.DirectoryCategoryReviewFormBean;
import com.weddfix.web.formbean.DirectoryCategoryShortlistedFormBean;
import com.weddfix.web.formbean.DirectorySendInfoFormBean;
import com.weddfix.web.formbean.DirectoryUserProfileFormBean;
import com.weddfix.web.formbean.PhotoGalleryFormBean;
import com.weddfix.web.services.CategoryInfoService;
import com.weddfix.web.services.CommonMasterService;
import com.weddfix.web.util.CommonConstants;
import com.weddfix.web.util.FilesUtil;
import com.weddfix.web.util.HibernateUtil;
import com.weddfix.web.util.Util;

public class CategoryInfoAction extends ActionSupport implements
		ModelDriven<DirectoryCategoryInfoFormBean>, Preparable, ServletRequestAware,
		ServletResponseAware, SessionAware, ServletContextAware {

	private static final long serialVersionUID = 1L;
	private static final long MILLIS_IN_DAY = 60*60*24*1000;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	private Map<String, Object> session;

	private Map<String, Object> categoryInfoBean = new HashMap<String, Object>();
	private List<Map<String, Object>> weddingHalls = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> studios = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> decorations = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> beautyParlours = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> jewelShops = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> caterings = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> entertainments = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> weddingClothes = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> textiles = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> travels = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> hotels = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> weddingAstrologers = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> categoryInfoListBean = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> moreCategoryPhotos = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> reviewListBean = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> personalDetailsBean = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> vendorDetails = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> youtubeVideoUrls = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> vendorList = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> myWishlist = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> myBookinglist = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> activeBannerPhotosBean = new ArrayList<Map<String, Object>>();
	private Map<Object, Object> stateMap;
	private Map<Object, Object> countryMap;
	private Map<Object, Object> orgMap;
	private Map<Object, Object> allCityMap;

	@SuppressWarnings("unused")
	private ServletContext context;
	private String filesPath;
	private String userFileFileName;
	private File userFile[] = new File[1];
	private String filenames[] = new String[1];
	private Long fileSize;
	private String fileContentType;
	private Long clientInfoId;
	private String profilePic;
	private Long editCategoryId;
	private Long deleteCategoryId;
	private Long editCategoryTypeId;
	private String editPhotoType;
	private Long pgOrgId;
	private String pgPhotoType;
	private Long delPhotoId;
	private Long headerSearchCategoryId;
	private Long headerSearchCityId;
	private Long vendorId;
	private Long shortlistedVendorId;
	private Long shortlistId;
	private Long shortlistedId;
	private Long bannerId;
	private String bannerStatus;
	private String compName;
	

	PhotoGalleryFormBean photoGalleryFormBean = new PhotoGalleryFormBean();
	DirectoryCategoryInfoFormBean catagoryInfoFormBean = new DirectoryCategoryInfoFormBean();
	DirectoryCategoryShortlistedFormBean catagoryShortlistedFormBean = new DirectoryCategoryShortlistedFormBean();
	CategoryInfoService categoryInfoService = new CategoryInfoService();
	CommonMasterService commonMasterService = new CommonMasterService();

	public String saveCatagoryInfoDetails() {
		HttpSession session = request.getSession(true);
		try {
			Long photoId = 0L;
			if(getUserFileFileName() != null) {
				photoId = uploadCategoryProfilePicture();
			}
			if (catagoryInfoFormBean.getId() != null) {
				session.setAttribute("update", "UPDATE");
				catagoryInfoFormBean.setUpdatedBy(Long.parseLong(session
						.getAttribute("userId").toString()));
			} else {
				session.setAttribute("update", null);
				catagoryInfoFormBean.setCreatedBy(Long.parseLong(session
						.getAttribute("userId").toString()));
			}
			catagoryInfoFormBean.setCompanyName(catagoryInfoFormBean.getCompanyName().trim());
			Long status = categoryInfoService
					.saveCatagoryInfoDetails(catagoryInfoFormBean, photoId);
			if (status != null) {
				if (session.getAttribute("update") != null) {
					session.setAttribute("successMessage",
							"Updated Successfully...");
					session.setAttribute("hrefParamSuccess", "client_info");
					System.out.println("Updated Successfully...");
				} else {
					session.setAttribute("successMessage",
							"Inserted Successfully...");
					session.setAttribute("hrefParamSuccess", "home");
					System.out.println("Inserted Successfully...");
				}

				return "success";
			} else {
				session.setAttribute("errorMessage",
						"Something went wrong. Please try again later.");
				session.setAttribute("hrefParamError", "home");
				return "error";
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage",
					"Something went wrong. Please try again later.");
			session.setAttribute("hrefParamError", "home");
			return "error";
		}
	}
	
	public String checkCompanyNameAlredyExist() {
		String companyExist = commonMasterService.checkCompanyNameAlredyExist(compName);
		if (companyExist != null) {
			return "success";
		} else {
			setCompName(null);
			return "error";
		}
	}
	
	public String checkOtherCompanyNameAlredyExist() {
		Session conn = HibernateUtil.getSessionFactory().openSession();
		String oldCompanyName = null;
		try {
			List<?> categoryInfo = conn.getNamedQuery("getCategoryInfoByInfoId")
					.setLong("id", vendorId).list();
			Iterator<?> itr = categoryInfo.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				oldCompanyName = obj[1].toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		String companyExist = commonMasterService.checkOtherCompanyNameAlredyExist(compName, oldCompanyName);
		if (companyExist != null) {
			return "success";
		} else {
			setCompName(null);
			return "error";
		}
	}
	
	public String saveShortlistDetails() {
		HttpSession session = request.getSession(true);
		try {
			catagoryShortlistedFormBean.setCreatedBy(Long.parseLong(session
					.getAttribute("userId").toString()));
			catagoryShortlistedFormBean.setVendorId(shortlistedVendorId);
			catagoryShortlistedFormBean.setUserId(Long.parseLong(session
					.getAttribute("userId").toString()));
			catagoryShortlistedFormBean.setShortlisted(true);
			shortlistId = categoryInfoService
					.saveShortlistDetails(catagoryShortlistedFormBean);
			if (shortlistId != null) {
					session.setAttribute("successMessage",
							"Shortlisted Successfully...");
					session.setAttribute("hrefParamSuccess", "vendor_list");
					System.out.println("Shortlisted Successfully...");

				return "success";
			} else {
				session.setAttribute("errorMessage",
						"Something went wrong. Please try again later.");
				session.setAttribute("hrefParamError", "home");
				return "error";
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage",
					"Something went wrong. Please try again later.");
			session.setAttribute("hrefParamError", "home");
			return "error";
		}
	}
	
	public String deleteShortlistDetails() {
		try {
			Long status = categoryInfoService
					.deleteShortlistDetails(shortlistedId);
			if (status != null) {
					System.out.println("Deleted Successfully...");
				return "success";
			} else {
				return "error";
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String saveActiveBannerPhoto() {
		HttpSession session = request.getSession(true);
		try {
			photoGalleryFormBean.setCreatedBy(Long.parseLong(session
					.getAttribute("userId").toString()));
			if(getBannerStatus().equals("ACTIVE")) {
				photoGalleryFormBean.setStatusId(CommonConstants.ACTIVE);
			} else {
				photoGalleryFormBean.setStatusId(CommonConstants.INACTIVE);
			}
			photoGalleryFormBean.setId(getBannerId());
			Long status = categoryInfoService
					.saveActiveBannerPhoto(photoGalleryFormBean);
			if (status != null) {
					session.setAttribute("successMessage",
							"Activate Banner Photo Successfully...");
					session.setAttribute("hrefParamSuccess", "banner_photos");
					System.out.println("Activate Banner Photo Successfully...");

				return "success";
			} else {
				session.setAttribute("errorMessage",
						"Something went wrong. Please try again later.");
				session.setAttribute("hrefParamError", "home");
				return "error";
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage",
					"Something went wrong. Please try again later.");
			session.setAttribute("hrefParamError", "home");
			return "error";
		}
	}
	
	public String loadMyWishlistDetails() {
		HttpSession session = request.getSession(true);
		try {

			List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoService
					.loadMyWishlistDetails((Long) session.getAttribute("userId"));
			Iterator<?> itr = categoryInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1].toString().substring(0, obj[1].toString().length()-1));
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				if(obj[13] != null) {
					map.put("shortlistedTotalCount", obj[13]);
				} else {
					map.put("shortlistedTotalCount", 0);
				}
				map.put("shortlistedId", obj[14]);
				String companyName = obj[2].toString().replaceAll(" ", "_").toLowerCase();
				map.put("vendorName", companyName.trim());

				myWishlist.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}
	
	public String loadMyBookingDetails() {
		HttpSession session = request.getSession(true);
		try {

			List<DirectorySendInfoFormBean> bookingInfoList = categoryInfoService
					.loadMyBookingDetails((Long) session.getAttribute("userId"));
			Iterator<?> itr = bookingInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1].toString().substring(0, obj[1].toString().length()-1));
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				if(obj[13] != null) {
					map.put("bookingTotalCount", obj[13]);
				} else {
					map.put("bookingTotalCount", 0);
				}
				map.put("bookingId", obj[14]);
				map.put("contactName", obj[15]);
				map.put("contactEmail", obj[16]);
				map.put("contactMobile", obj[17]);

				myBookinglist.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}
	
	public String loadVendorBookingDetails() {
		HttpSession session = request.getSession(true);
		try {

			List<DirectorySendInfoFormBean> bookingInfoList = categoryInfoService
					.loadVendorBookingDetails((Long) session.getAttribute("userId"));
			Iterator<?> itr = bookingInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("fullName", obj[1]);
				map.put("email", obj[2]);
				map.put("mobile", obj[3]);
				map.put("cityName", obj[4]);
				map.put("stateName", obj[5]);
				map.put("countryName", obj[6]);
				map.put("pincode", obj[7]);
				map.put("cid", obj[8]);
				map.put("categoryName", obj[9].toString().substring(0, obj[9].toString().length()-1));
				map.put("companyName", obj[10]);
				map.put("price", obj[11]);
				map.put("fileName", obj[12]);
				map.put("bookingId", obj[13]);
				map.put("bookingStatus", obj[14]);
				map.put("bookingDate", obj[15]);
				map.put("feedbackRequested", obj[16]);
				map.put("feedbackStatus", obj[17]);

				myBookinglist.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}
	
	public Long uploadCategoryProfilePicture() {
		HttpSession session = request.getSession(true);
		try {
			uploadPhotoGalleryFile();
			if (!getProfilePic().equals("null")) {
				photoGalleryFormBean.setUpdatedBy(Long.parseLong(session
						.getAttribute("userId").toString()));
			} else {
				session.setAttribute("update", null);
				photoGalleryFormBean.setCreatedBy(Long.parseLong(session
						.getAttribute("userId").toString()));
			}
			Long id = categoryInfoService
					.saveCategoryProfilePicture(photoGalleryFormBean, getProfilePic());
			if (id != null) {
				if (session.getAttribute("update") != null) {
					session.setAttribute("successMessage",
							"Updated Successfully...");
					session.setAttribute("hrefParamSuccess", "client_info");
					System.out.println("Updated Successfully...");
				} else {
					session.setAttribute("successMessage",
							"Inserted Successfully...");
					session.setAttribute("hrefParamSuccess", "home");
					System.out.println("Inserted Successfully...");
				}

				return id;
			} else {
				session.setAttribute("errorMessage",
						"Something went wrong. Please try again later.");
				session.setAttribute("hrefParamError", "home");
				return 0L;
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage",
					"Something went wrong. Please try again later.");
			session.setAttribute("hrefParamError", "home");
			return 0L;
		}
	}
	
	public Long uploadMorePhotos() {
		HttpSession session = request.getSession(true);
		try {
			Long id = uploadMorePhotoGalleryFile();
			
			if (id != null) {
				if (session.getAttribute("update") != null) {
					session.setAttribute("successMessage",
							"Updated Successfully...");
					session.setAttribute("hrefParamSuccess", "vendor_edit_listing");
					System.out.println("Updated Successfully...");
				} else {
					session.setAttribute("successMessage",
							"Inserted Successfully...");
					session.setAttribute("hrefParamSuccess", "home");
					System.out.println("Inserted Successfully...");
				}

				return id;
			} else {
				session.setAttribute("errorMessage",
						"Something went wrong. Please try again later.");
				session.setAttribute("hrefParamError", "home");
				return 0L;
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage",
					"Something went wrong. Please try again later.");
			session.setAttribute("hrefParamError", "home");
			return 0L;
		}
	}
	
	public Long uploadMorePhotoGalleryFile() {
		HttpSession session = request.getSession(true);
		Long id = 0L;
		try {
			String fileName = "";
			String filePath = getText("image.path");
			// creates the directory if it does not exist
			File uploadDir = new File(filePath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			String[] nameOfImages = this.userFileFileName.split(",");

			for (int i = 0; i < userFile.length; i++) {
				fileName = nameOfImages[i].trim();
				String fileType = fileName
						.substring(fileName.lastIndexOf(".") + 1);
				String fileFullName = fileName
						.substring(0, fileName.lastIndexOf("."));
//				filenames[i] = nameOfImages[i];
				Long imageId = Util.getSequenceId("next_image_id_seq");
				String customFileName = null;
				if (photoGalleryFormBean.getOrgId() != null) {
					customFileName = photoGalleryFormBean.getOrgId() + "_"
							+ imageId + "." + fileType;
				} else {
					customFileName = fileFullName + "_"
							+ imageId + "." + fileType;
				}
				String filePathAndFileName = filePath + customFileName;
				// System.out.println("filepath >>" + filePathAndFileName);
				FilesUtil.saveFile(userFile[i], customFileName, filePath);
				// FileUtils.copyFile(this.userFile[0],new File(getPath1));
				setFileSize(userFile[i].length());
				if (this.userFileFileName.endsWith(".jpeg")
						|| this.userFileFileName.endsWith(".jpg")) {
					fileContentType = "Image File";
				} else if (this.userFileFileName.endsWith(".png")
						|| this.userFileFileName.endsWith(".gif")) {
					fileContentType = "Image File";
				}
					photoGalleryFormBean.setFileName(customFileName);
					photoGalleryFormBean.setFileType(fileContentType);
					photoGalleryFormBean.setFileSize(fileSize);
					photoGalleryFormBean.setFilePath(filePathAndFileName);
					
					if (!getProfilePic().equals("null")) {
						photoGalleryFormBean.setUpdatedBy(Long.parseLong(session
								.getAttribute("userId").toString()));
					} else {
						session.setAttribute("update", null);
						photoGalleryFormBean.setOrgId(getPgOrgId());
						photoGalleryFormBean.setPhotoType(getPgPhotoType());
						photoGalleryFormBean.setCreatedBy(Long.parseLong(session
								.getAttribute("userId").toString()));
					}
					id = categoryInfoService
							.saveCategoryProfilePicture(photoGalleryFormBean, getProfilePic());
					photoGalleryFormBean.setOrgId(null);

				// FileUtils.copyFile(userFile[i], new File(getPath1));
			}
			// FileUtils.copyFile(this.userFile[0], fileToCreate);
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return id;
	}
	
	public Long uploadBannerPhotos() {
		HttpSession session = request.getSession(true);
		try {
			uploadPhotoGalleryFile();
			if (!getProfilePic().equals("null")) {
				photoGalleryFormBean.setUpdatedBy(Long.parseLong(session
						.getAttribute("userId").toString()));
			} else {
				session.setAttribute("update", null);
//				photoGalleryFormBean.setOrgId(getPgOrgId());
				photoGalleryFormBean.setPhotoType(getPgPhotoType());
				photoGalleryFormBean.setCreatedBy(Long.parseLong(session
						.getAttribute("userId").toString()));
			}
			Long id = categoryInfoService
					.saveCategoryProfilePicture(photoGalleryFormBean, getProfilePic());
			if (id != null) {
				if (session.getAttribute("update") != null) {
					session.setAttribute("successMessage",
							"Updated Successfully...");
					session.setAttribute("hrefParamSuccess", "banner_photos");
					System.out.println("Updated Successfully...");
				} else {
					session.setAttribute("successMessage",
							"Inserted Successfully...");
					session.setAttribute("hrefParamSuccess", "home");
					System.out.println("Inserted Successfully...");
				}

				return id;
			} else {
				session.setAttribute("errorMessage",
						"Something went wrong. Please try again later.");
				session.setAttribute("hrefParamError", "home");
				return 0L;
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage",
					"Something went wrong. Please try again later.");
			session.setAttribute("hrefParamError", "home");
			return 0L;
		}
	}

	public void uploadPhotoGalleryFile() {

		try {
			String fileName = "";
			String filePath = getText("image.path");
			// creates the directory if it does not exist
			File uploadDir = new File(filePath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			String[] nameOfImages = this.userFileFileName.split(",");

			for (int i = 0; i < userFile.length; i++) {
				fileName = nameOfImages[i].trim();
				String fileType = fileName
						.substring(fileName.lastIndexOf(".") + 1);
				String fileFullName = fileName
						.substring(0, fileName.lastIndexOf("."));
				filenames[i] = nameOfImages[i];
				Long imageId = Util.getSequenceId("next_image_id_seq");
				String customFileName = null;
				if (photoGalleryFormBean.getOrgId() != null) {
					customFileName = photoGalleryFormBean.getOrgId() + "_"
							+ imageId + "." + fileType;
				} else {
					customFileName = fileFullName + "_"
							+ imageId + "." + fileType;
				}
				String filePathAndFileName = filePath + customFileName;
				// System.out.println("filepath >>" + filePathAndFileName);
				FilesUtil.saveFile(userFile[i], customFileName, filePath);
				// FileUtils.copyFile(this.userFile[0],new File(getPath1));
				setFileSize(userFile[i].length());
				if (this.userFileFileName.endsWith(".jpeg")
						|| this.userFileFileName.endsWith(".jpg")) {
					fileContentType = "Image File";
				} else if (this.userFileFileName.endsWith(".png")
						|| this.userFileFileName.endsWith(".gif")) {
					fileContentType = "Image File";
				}
				if (i == 0) {
					photoGalleryFormBean.setFileName(customFileName);
					photoGalleryFormBean.setFileType(fileContentType);
					photoGalleryFormBean.setFileSize(fileSize);
					photoGalleryFormBean.setFilePath(filePathAndFileName);

				}
				// FileUtils.copyFile(userFile[i], new File(getPath1));
			}
			// FileUtils.copyFile(this.userFile[0], fileToCreate);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}
	
	public String loadVenueDetails() {
		HttpSession session = request.getSession(true);
		String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

	    Pattern compiledPattern = Pattern.compile(pattern);
	    Matcher matcher = null;
	    
		try {
			List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoService
					.loadVenueDetails((Long) session.getAttribute("userId"));
			session.setAttribute("totalCount", categoryInfoList.size());
			Iterator<?> itr = categoryInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("companyName", obj[1]);
				map.put("address", obj[2]);
				map.put("location", obj[3]);
				map.put("cityName", obj[4]);
				map.put("stateName", obj[5]);
				map.put("countryName", obj[6]);
				map.put("pincode", obj[7]);
				map.put("price", obj[8]);
				map.put("fileName", obj[9]);
				map.put("photoType", obj[10]);
				map.put("categoryPictureId", obj[11]);
				if(obj[12] != null && !obj[12].equals("")) {
					matcher = compiledPattern.matcher(obj[12].toString());
					if(matcher.find()){
				    	map.put("categoryVideoUrl", "//www.youtube.com/embed/"+matcher.group());
				    } 
				} else {
			    	map.put("categoryVideoUrl", "");
			    }
				map.put("categoryId", obj[13]);
				map.put("planName", obj[14]);
				map.put("amount", obj[15]);
				if(obj[14] != null) {
				DateFormat dateFormat= new SimpleDateFormat("dd MM yyyy");
				Date cDate = null;
				try {
					cDate = dateFormat.parse(obj[17].toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
	        	Date createdDate = DateUtils.addMonths(cDate, Integer.valueOf(obj[16].toString()));
	        	//it's still active if the created date + 11 days is greater than the current time
	        	int daysIntoTrial = Math.round(((createdDate.getTime() - System.currentTimeMillis()) / MILLIS_IN_DAY));
	        	
	        	int reminingDays =  daysIntoTrial;
	        	map.put("validity", reminingDays);
				}
				categoryInfoListBean.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}
	
	public String loadEditVenueDetails() {
		HttpSession session = request.getSession(true);
		try {
			DirectoryCategoryInfoFormBean categoryInfo = categoryInfoService
					.loadEditVenueDetails((Long) session.getAttribute("editCategoryId"));
			
			session.setAttribute("countryId",
					categoryInfo.getCountryId());
			session.setAttribute("stateId",
					categoryInfo.getStateId());
			session.setAttribute("cityId",
					categoryInfo.getCityId());
			session.setAttribute("categoryId", categoryInfo.getCategoryId());
			session.setAttribute("subCategoryId", categoryInfo.getSubCategoryId());
			session.setAttribute("seatCapacityId", categoryInfo.getSeatCapacityId());
			
			categoryInfoBean.put("id",
					categoryInfo.getId());
			categoryInfoBean.put("companyName", categoryInfo.getCompanyName());
			categoryInfoBean.put("contactName", categoryInfo.getContactName());
			categoryInfoBean.put("mobile", categoryInfo.getMobile());
			categoryInfoBean.put("phone", categoryInfo.getPhone());
			categoryInfoBean.put("email", categoryInfo.getEmail());
			categoryInfoBean.put("websiteUrl",
					categoryInfo.getWebsiteUrl());
			categoryInfoBean.put("categoryId",
					categoryInfo.getCategoryId());
			categoryInfoBean.put("subCategoryId",
					categoryInfo.getSubCategoryId());
			categoryInfoBean.put("location", categoryInfo.getLocation());
			categoryInfoBean.put("address", categoryInfo.getAddress());
			categoryInfoBean.put("pincode",
					categoryInfo.getPincode());
			categoryInfoBean.put("description",
					categoryInfo.getDescription());
			categoryInfoBean.put("price", categoryInfo.getPrice());
			categoryInfoBean.put("latitude", categoryInfo.getLatitude());
			categoryInfoBean.put("longitude", categoryInfo.getLongitude());
			categoryInfoBean.put("categoryPictureId", categoryInfo.getcategoryPictureId());
			categoryInfoBean.put("categoryVideoUrl", categoryInfo.getCategoryVideoUrl());
			categoryInfoBean.put("photoType", categoryInfo.getPhotoType());
			categoryInfoBean.put("fileName", categoryInfo.getFileName());
			
			countryMap = commonMasterService.loadCountry();
			orgMap = commonMasterService.loadOrg();
			
			List<PhotoGalleryFormBean> morePhotoGalleryList = categoryInfoService
			.loadPhotoGalleryDetails((Long) session.getAttribute("userId"), (Long) session.getAttribute("editCategoryTypeId"), (String) session.getAttribute("editPhotoType"));
			Iterator<?> itr = morePhotoGalleryList.iterator();
			while (itr.hasNext()) {
		
				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("fileName", obj[1]);
				map.put("orgId", obj[2]);
				map.put("photoType", obj[3]);
		
				moreCategoryPhotos.add(map);
			}

			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}
	
	public String deleteCategoryInfo() {
			try {
				Long status = categoryInfoService
						.deleteCategoryInfo(deleteCategoryId);
				if (status != null) {
						System.out.println("Deleted Successfully...");
					return "success";
				} else {
					return "error";
				}

			}

			catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
	}
	
	public String deletePhoto() {
		try {
			Long status = categoryInfoService
					.deletePhoto(delPhotoId);
			if (status != null) {
					System.out.println("Deleted Successfully...");
				return "success";
			} else {
				return "error";
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
}
	
	public String editVenueDetailsSession() {
		HttpSession session = request.getSession(true);
		try {
			session.setAttribute("editCategoryId", getEditCategoryId());
			session.setAttribute("editCategoryTypeId", getEditCategoryTypeId());
			session.setAttribute("editPhotoType", getEditPhotoType());
			return "success";
		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}


	public String loadMasters() {
		try {
			orgMap = commonMasterService.loadOrg();
			countryMap = commonMasterService.loadCountry();
			System.out.println("****" + orgMap);
			System.out.println("****" + countryMap);
			
			return "success";
		}

		catch (Exception e) {
//			e.printStackTrace();
			return "error";
		}

	}
	
	public String loadCategoryAndCityMasters() {
		try {
			orgMap = commonMasterService.loadOrg();
			allCityMap = commonMasterService.loadPaidVendorCity();
//			System.out.println("****" + orgMap);
//			System.out.println("****" + allCityMap);
			
			return "success";
		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}
	
	public String loadHomeImages() {
//		HttpSession session = request.getSession();
		try {
			/*List<String> orgNameList = new ArrayList<String>();
					
			List<OrganizationFormBean> orgInfoList = clientInfoService
					.loadAllOrgInfos();
			Iterator<?> itr = orgInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				orgNameList.add(obj[1].toString());
			}
			
			orgMap = commonMasterService.loadOrg();
			allCityMap = commonMasterService.loadAllCity();
			System.out.println("****" + orgMap);
			System.out.println("****" + allCityMap);*/
			
			loadWeddingHallsImages();
			loadStudioImages();
			loadDecorationImages();
			loadBeautyParlourImages();
			loadJewelShopImages();
			loadCateringImages();
			loadEntertainmentImages();
			loadWeddingClothesImages();
			loadTextilesImages();
			loadTravelsImages();
			loadHotelsImages();
			loadWeddingAstrologersImages();
			
			List<PhotoGalleryFormBean> bannerPhotoGalleryList = categoryInfoService
					.loadBannerPhotoDetails();
					Iterator<?> itr1 = bannerPhotoGalleryList.iterator();
					while (itr1.hasNext()) {
				
						Object[] obj = (Object[]) itr1.next();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", obj[0]);
						map.put("orgId", obj[1]);
						map.put("fileName", obj[2]);
						map.put("statusName", obj[3]);
				
						activeBannerPhotosBean.add(map);
					}

				return "success";
		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}

	public String loadClentInfoImages() {
		try {
			loadWeddingHallsImages();
			loadStudioImages();
			loadDecorationImages();
			loadBeautyParlourImages();
			loadJewelShopImages();
			loadCateringImages();
			loadEntertainmentImages();
			loadWeddingClothesImages();
			loadTextilesImages();
			loadTravelsImages();
			loadHotelsImages();
			loadWeddingAstrologersImages();

			return "success";
		}

		catch (Exception e) {
			// e.printStackTrace();
			return "success";
		}
	}

	public String clientInfoSession() {
		HttpSession session = request.getSession(true);
		try {
			session.setAttribute("clientInfoId", getClientInfoId());
			return "success";
		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}
	
	public String searchCategoryByCitySession() {
		HttpSession session = request.getSession(true);
		try {
			session.setAttribute("headerSearchCategoryId", getHeaderSearchCategoryId());
			session.setAttribute("headerSearchCityId", getHeaderSearchCityId());
			return "success";
		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}
	
	public String vendorDetailsSession() {
		HttpSession session = request.getSession(true);
		try {
			session.setAttribute("vendorId", getVendorId());
			return "success";
		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}
	
	public String loadVendorDetails() {
		HttpSession session = request.getSession(true);
		session.setAttribute("vendorId", null);
		
		String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

		String uri = request.getParameter("vendor_name");
		String companyName = uri.replaceAll("_", " ").toLowerCase();
		
	    Pattern compiledPattern = Pattern.compile(pattern);
	    Matcher matcher = null;
		try {
			List<DirectoryCategoryInfoFormBean> vendorInfoList = categoryInfoService
					.loadVendorDetailsByCompanyName(companyName);
			
			Iterator<?> itr = vendorInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				session.setAttribute("vendorId", Long.parseLong(obj[0].toString()));
				session.setAttribute("vendorName", uri);
				map.put("categoryName", obj[1]);
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				map.put("maxUsersRating", obj[11]);
				map.put("description", obj[12]);
				map.put("latitude", obj[13]);
				map.put("fileName", obj[14]);
				map.put("userFullName", obj[15]);
				map.put("userCityName", obj[16]);
				map.put("userStateName", obj[17]);
				map.put("userCountryName", obj[18]);
				map.put("userPincode", obj[19]);
				map.put("userWebsiteUrl", obj[20]);
				map.put("userFacebookUrl", obj[21]);
				map.put("userTwitterUrl", obj[22]);
				map.put("userLinkedinUrl", obj[23]);
				map.put("userPinterestUrl", obj[24]);
				map.put("userInstagramUrl", obj[25]);
				map.put("userFileName", obj[26]);
				map.put("contactName", obj[27]);
				map.put("mobile", obj[28]);
				map.put("phone", obj[29]);
				map.put("email", obj[30]);
				Map<String, Object> urlMap = new HashMap<String, Object>();
				if(obj[31] != null) {
					String[] split =  obj[31].toString().split(",");
					for(String url : split) {
						matcher = compiledPattern.matcher(url);
						if(matcher.find()){
							urlMap.put("categoryVideoImage", "https://img.youtube.com/vi/"+matcher.group()+"/maxresdefault.jpg");
						}
						urlMap.put("categoryVideoUrl", url.trim());
						youtubeVideoUrls.add(urlMap);
					}
				} 

				vendorDetails.add(map);
			}
			
			List<PhotoGalleryFormBean> morePhotoGalleryList = categoryInfoService
					.loadPhotoGalleryDetailsByVendorId((Long) session.getAttribute("vendorId"));
					Iterator<?> itr1 = morePhotoGalleryList.iterator();
					while (itr1.hasNext()) {
				
						Object[] obj = (Object[]) itr1.next();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", obj[0]);
						map.put("fileName", obj[1]);
						map.put("orgId", obj[2]);
						map.put("photoType", obj[3]);
				
						moreCategoryPhotos.add(map);
					}
					
			List<DirectoryCategoryReviewFormBean> reviewList = categoryInfoService
					.loadReviewDetailsByVendorId((Long) session.getAttribute("vendorId"));
					Iterator<?> itr2 = reviewList.iterator();
					while (itr2.hasNext()) {
				
						Object[] obj = (Object[]) itr2.next();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", obj[0]);
						map.put("maxRating", obj[1]);
						map.put("name", obj[2]);
						map.put("email", obj[3]);
						map.put("reviewTitle", obj[4]);
						map.put("review", obj[5]);
						map.put("createdDate", obj[6]);
						map.put("fileName", obj[7]);
						if(obj[1] != null) {
							map.put("minRating", 5L-Long.parseLong(obj[1].toString()));
						}
				
						reviewListBean.add(map);
					}
			if(session.getAttribute("userId") != null) {		
			List<DirectoryUserProfileFormBean> personalDetailsList = categoryInfoService
					.loadPersonalDetailsByUserId((Long) session.getAttribute("userId"));
					Iterator<?> itr3 = personalDetailsList.iterator();
					while (itr3.hasNext()) {
				
						Object[] obj = (Object[]) itr3.next();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", obj[0]);
						map.put("fullName", obj[1]);
						map.put("email", obj[2]);
						map.put("mobile", obj[3]);
						map.put("weddingDate", obj[4]);
				
						personalDetailsBean.add(map);
					}
			}
					countryMap = commonMasterService.loadCountry();
					
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}
	
	public String searchCategoryByCity() {
		HttpSession session = request.getSession(true);
		try {
			session.setAttribute("headerSearchCategoryId", null);
			Session conn = null;
			String uri = request.getParameter("search");
			String orgName = uri.replaceAll("_", " ").toLowerCase();
			String cityName = request.getParameter("city");
			if(cityName != null) {
				if(cityName.equals("any")) {
					session.setAttribute("headerSearchCityId", -1L);
				} else {
					try {
						conn = HibernateUtil.getSessionFactory().openSession();
						List<?> cityInfo = conn.getNamedQuery("getCityByCityName")
								.setString("cityName", cityName).list();
						Iterator<?> itr = cityInfo.iterator();
						while (itr.hasNext()) {
							Object[] obj = (Object[]) itr.next();
							session.setAttribute("headerSearchCityId", Long.parseLong(obj[0].toString()));
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						conn.flush();
						conn.close();
					}
				}
			} else {
				session.setAttribute("headerSearchCityId", -1L);
			}
			
			Long orgId = 0L;
			try {
				conn = HibernateUtil.getSessionFactory().openSession();
				List<?> categoryInfo = conn.getNamedQuery("getOrgInfoByOrgName")
						.setString("orgName", orgName).list();
				Iterator<?> itr = categoryInfo.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					orgId = Long.parseLong(obj[0].toString());
					session.setAttribute("headerSearchCategoryId", orgId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn.flush();
				conn.close();
			}
			
			Long userId =0L;
			if(session.getAttribute("userId") != null) {
				userId = Long.parseLong(session.getAttribute("userId").toString());
			} 
			
			List<DirectoryCategoryInfoFormBean> categoryInfoList = null;
			
			if((Long) session.getAttribute("headerSearchCityId") > 0) {
				categoryInfoList = categoryInfoService
						.searchCategoryByCity((Long) session.getAttribute("headerSearchCategoryId"), (Long) session.getAttribute("headerSearchCityId"), userId);
			} else {
				categoryInfoList = categoryInfoService
						.searchByCategory((Long) session.getAttribute("headerSearchCategoryId"), userId);
			}

			Iterator<?> itr = categoryInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1]);
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				map.put("shortlisted", obj[13]);
				map.put("shortlistedId", obj[14]);
				String companyName = obj[2].toString().replaceAll(" ", "_").toLowerCase();
				map.put("vendorName", companyName.trim());

				vendorList.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}
	
	public String searchByCategory() {
		HttpSession session = request.getSession(true);
		try {
			Long userId =0L;
			if(session.getAttribute("userId") != null) {
				userId = Long.parseLong(session.getAttribute("userId").toString());
			} 
			
			List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoService
					.searchByCategory((Long) session.getAttribute("headerSearchCategoryId"), userId);
			Iterator<?> itr = categoryInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1]);
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				map.put("shortlisted", obj[13]);
				map.put("shortlistedId", obj[14]);

				vendorList.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}

	public String loadWeddingHallsImages() {
		try {
			String limit = null;
			if (ActionContext.getContext().getName().equals("home")) {
				limit = getText("home.image.limit");
			}

			List<DirectoryCategoryInfoFormBean> clientInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.WEDDING_HALLS, limit);
			Iterator<?> itr = clientInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1]);
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				String companyName = obj[2].toString().replaceAll(" ", "_").toLowerCase();
				map.put("vendorName", companyName.trim());

				weddingHalls.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}

	public String loadStudioImages() {
		try {
			String limit = null;
			if (ActionContext.getContext().getName().equals("home")) {
				limit = getText("home.image.limit");
			}
			List<DirectoryCategoryInfoFormBean> clientInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.STUDIOS, limit);
			Iterator<?> itr = clientInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1]);
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				String companyName = obj[2].toString().replaceAll(" ", "_").toLowerCase();
				map.put("vendorName", companyName.trim());

				studios.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}

	public String loadDecorationImages() {
		try {
			String limit = null;
			if (ActionContext.getContext().getName().equals("home")) {
				limit = getText("home.image.limit");
			}
			List<DirectoryCategoryInfoFormBean> clientInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.DECORATIONS, limit);
			Iterator<?> itr = clientInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1]);
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				String companyName = obj[2].toString().replaceAll(" ", "_").toLowerCase();
				map.put("vendorName", companyName.trim());

				decorations.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}

	public String loadBeautyParlourImages() {
		try {
			String limit = null;
			if (ActionContext.getContext().getName().equals("home")) {
				limit = getText("home.image.limit");
			}
			List<DirectoryCategoryInfoFormBean> clientInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.BEAUTY_PARLOURS, limit);
			Iterator<?> itr = clientInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1]);
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				String companyName = obj[2].toString().replaceAll(" ", "_").toLowerCase();
				map.put("vendorName", companyName.trim());

				beautyParlours.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}

	public String loadJewelShopImages() {
		try {
			String limit = null;
			if (ActionContext.getContext().getName().equals("home")) {
				limit = getText("home.image.limit");
			}
			List<DirectoryCategoryInfoFormBean> clientInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.JEWEL_SHOPS, limit);
			Iterator<?> itr = clientInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1]);
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				String companyName = obj[2].toString().replaceAll(" ", "_").toLowerCase();
				map.put("vendorName", companyName.trim());

				jewelShops.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}

	public String loadCateringImages() {
		try {
			String limit = null;
			if (ActionContext.getContext().getName().equals("home")) {
				limit = getText("home.image.limit");
			}
			List<DirectoryCategoryInfoFormBean> clientInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.CATERINGS, limit);
			Iterator<?> itr = clientInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1]);
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				String companyName = obj[2].toString().replaceAll(" ", "_").toLowerCase();
				map.put("vendorName", companyName.trim());

				caterings.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}

	public String loadEntertainmentImages() {
		try {
			String limit = null;
			if (ActionContext.getContext().getName().equals("home")) {
				limit = getText("home.image.limit");
			}
			List<DirectoryCategoryInfoFormBean> clientInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.ENTERTAINMENTS, limit);
			Iterator<?> itr = clientInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1]);
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				String companyName = obj[2].toString().replaceAll(" ", "_").toLowerCase();
				map.put("vendorName", companyName.trim());
				
				entertainments.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}
	
	public String loadWeddingClothesImages() {
		try {
			String limit = null;
			if (ActionContext.getContext().getName().equals("home")) {
				limit = getText("home.image.limit");
			}

			List<DirectoryCategoryInfoFormBean> clientInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.WEDDING_CLOTHES, limit);
			Iterator<?> itr = clientInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1]);
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				String companyName = obj[2].toString().replaceAll(" ", "_").toLowerCase();
				map.put("vendorName", companyName.trim());
				
				weddingClothes.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}

	public String loadTextilesImages() {
		try {
			String limit = null;
			if (ActionContext.getContext().getName().equals("home")) {
				limit = getText("home.image.limit");
			}
			List<DirectoryCategoryInfoFormBean> clientInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.TEXTILES, limit);
			Iterator<?> itr = clientInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1]);
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				String companyName = obj[2].toString().replaceAll(" ", "_").toLowerCase();
				map.put("vendorName", companyName.trim());

				textiles.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}

	public String loadTravelsImages() {
		try {
			String limit = null;
			if (ActionContext.getContext().getName().equals("home")) {
				limit = getText("home.image.limit");
			}

			List<DirectoryCategoryInfoFormBean> clientInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.TRAVELS, limit);
			Iterator<?> itr = clientInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1]);
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				String companyName = obj[2].toString().replaceAll(" ", "_").toLowerCase();
				map.put("vendorName", companyName.trim());
				
				travels.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}
	
	public String loadHotelsImages() {
		try {
			String limit = null;
			if (ActionContext.getContext().getName().equals("home")) {
				limit = getText("home.image.limit");
			}

			List<DirectoryCategoryInfoFormBean> clientInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.HOTELS, limit);
			Iterator<?> itr = clientInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1]);
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				String companyName = obj[2].toString().replaceAll(" ", "_").toLowerCase();
				map.put("vendorName", companyName.trim());
				
				hotels.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}
	
	public String loadWeddingAstrologersImages() {
		try {
			String limit = null;
			if (ActionContext.getContext().getName().equals("home")) {
				limit = getText("home.image.limit");
			}

			List<DirectoryCategoryInfoFormBean> clientInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.WEDDING_ASTROLOGERS, limit);
			Iterator<?> itr = clientInfoList.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("categoryName", obj[1]);
				map.put("companyName", obj[2]);
				map.put("address", obj[3]);
				map.put("location", obj[4]);
				map.put("cityName", obj[5]);
				map.put("stateName", obj[6]);
				map.put("countryName", obj[7]);
				map.put("pincode", obj[8]);
				map.put("price", obj[9]);
				map.put("maxRating", obj[10]);
				map.put("maxUsersRating", obj[11]);
				map.put("fileName", obj[12]);
				if(obj[10] != null) {
					map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
				}
				String companyName = obj[2].toString().replaceAll(" ", "_").toLowerCase();
				map.put("vendorName", companyName.trim());

				weddingAstrologers.add(map);
			}
			return "success";

		}

		catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
	}
	
	public void prepare() throws Exception {
		catagoryInfoFormBean = new DirectoryCategoryInfoFormBean();
	}

	public DirectoryCategoryInfoFormBean getModel() {
		return catagoryInfoFormBean;
	}

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String getFilesPath() {
		return filesPath;
	}

	public void setFilesPath(String filesPath) {
		this.filesPath = filesPath;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public File[] getUserFile() {
		return userFile;
	}

	public void setUserFile(File[] userFile) {
		this.userFile = userFile;
	}

	public String[] getFilenames() {
		return filenames;
	}

	public void setFilenames(String[] filenames) {
		this.filenames = filenames;
	}

	public String getUserFileFileName() {
		return userFileFileName;
	}

	public void setUserFileFileName(String userFileFileName) {
		this.userFileFileName = userFileFileName;
	}

	public List<Map<String, Object>> getWeddingHalls() {
		return weddingHalls;
	}

	public void setWeddingHalls(List<Map<String, Object>> weddingHalls) {
		this.weddingHalls = weddingHalls;
	}

	public List<Map<String, Object>> getStudios() {
		return studios;
	}

	public void setStudios(List<Map<String, Object>> studios) {
		this.studios = studios;
	}

	public List<Map<String, Object>> getDecorations() {
		return decorations;
	}

	public void setDecorations(List<Map<String, Object>> decorations) {
		this.decorations = decorations;
	}

	public List<Map<String, Object>> getBeautyParlours() {
		return beautyParlours;
	}

	public void setBeautyParlours(List<Map<String, Object>> beautyParlours) {
		this.beautyParlours = beautyParlours;
	}

	public List<Map<String, Object>> getJewelShops() {
		return jewelShops;
	}

	public void setJewelShops(List<Map<String, Object>> jewelShops) {
		this.jewelShops = jewelShops;
	}

	public List<Map<String, Object>> getCaterings() {
		return caterings;
	}

	public void setCaterings(List<Map<String, Object>> caterings) {
		this.caterings = caterings;
	}

	public List<Map<String, Object>> getEntertainments() {
		return entertainments;
	}

	public void setEntertainments(List<Map<String, Object>> entertainments) {
		this.entertainments = entertainments;
	}

	public List<Map<String, Object>> getTextiles() {
		return textiles;
	}

	public void setTextiles(List<Map<String, Object>> textiles) {
		this.textiles = textiles;
	}

	public Long getClientInfoId() {
		return clientInfoId;
	}

	public void setClientInfoId(Long clientInfoId) {
		this.clientInfoId = clientInfoId;
	}

	public Map<String, Object> getCategoryInfoBean() {
		return categoryInfoBean;
	}

	public void setCategoryInfoBean(Map<String, Object> categoryInfoBean) {
		this.categoryInfoBean = categoryInfoBean;
	}

	public Map<Object, Object> getStateMap() {
		return stateMap;
	}

	public void setStateMap(Map<Object, Object> stateMap) {
		this.stateMap = stateMap;
	}

	public Map<Object, Object> getCountryMap() {
		return countryMap;
	}

	public void setCountryMap(Map<Object, Object> countryMap) {
		this.countryMap = countryMap;
	}

	public List<Map<String, Object>> getWeddingClothes() {
		return weddingClothes;
	}

	public void setWeddingClothes(List<Map<String, Object>> weddingClothes) {
		this.weddingClothes = weddingClothes;
	}

	public List<Map<String, Object>> getTravels() {
		return travels;
	}

	public void setTravels(List<Map<String, Object>> travels) {
		this.travels = travels;
	}

	public List<Map<String, Object>> getHotels() {
		return hotels;
	}

	public void setHotels(List<Map<String, Object>> hotels) {
		this.hotels = hotels;
	}

	public List<Map<String, Object>> getWeddingAstrologers() {
		return weddingAstrologers;
	}

	public void setWeddingAstrologers(List<Map<String, Object>> weddingAstrologers) {
		this.weddingAstrologers = weddingAstrologers;
	}

	public Map<Object, Object> getOrgMap() {
		return orgMap;
	}

	public void setOrgMap(Map<Object, Object> orgMap) {
		this.orgMap = orgMap;
	}

	public Map<Object, Object> getAllCityMap() {
		return allCityMap;
	}

	public void setAllCityMap(Map<Object, Object> allCityMap) {
		this.allCityMap = allCityMap;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public List<Map<String, Object>> getCategoryInfoListBean() {
		return categoryInfoListBean;
	}

	public void setCategoryInfoListBean(
			List<Map<String, Object>> categoryInfoListBean) {
		this.categoryInfoListBean = categoryInfoListBean;
	}

	public Long getEditCategoryId() {
		return editCategoryId;
	}

	public void setEditCategoryId(Long editCategoryId) {
		this.editCategoryId = editCategoryId;
	}

	public Long getDeleteCategoryId() {
		return deleteCategoryId;
	}

	public void setDeleteCategoryId(Long deleteCategoryId) {
		this.deleteCategoryId = deleteCategoryId;
	}

	public List<Map<String, Object>> getMoreCategoryPhotos() {
		return moreCategoryPhotos;
	}

	public void setMoreCategoryPhotos(List<Map<String, Object>> moreCategoryPhotos) {
		this.moreCategoryPhotos = moreCategoryPhotos;
	}

	public Long getEditCategoryTypeId() {
		return editCategoryTypeId;
	}

	public void setEditCategoryTypeId(Long editCategoryTypeId) {
		this.editCategoryTypeId = editCategoryTypeId;
	}

	public String getEditPhotoType() {
		return editPhotoType;
	}

	public void setEditPhotoType(String editPhotoType) {
		this.editPhotoType = editPhotoType;
	}

	public Long getPgOrgId() {
		return pgOrgId;
	}

	public void setPgOrgId(Long pgOrgId) {
		this.pgOrgId = pgOrgId;
	}

	public String getPgPhotoType() {
		return pgPhotoType;
	}

	public void setPgPhotoType(String pgPhotoType) {
		this.pgPhotoType = pgPhotoType;
	}

	public Long getDelPhotoId() {
		return delPhotoId;
	}

	public void setDelPhotoId(Long delPhotoId) {
		this.delPhotoId = delPhotoId;
	}

	public Long getHeaderSearchCategoryId() {
		return headerSearchCategoryId;
	}

	public void setHeaderSearchCategoryId(Long headerSearchCategoryId) {
		this.headerSearchCategoryId = headerSearchCategoryId;
	}

	public Long getHeaderSearchCityId() {
		return headerSearchCityId;
	}

	public void setHeaderSearchCityId(Long headerSearchCityId) {
		this.headerSearchCityId = headerSearchCityId;
	}

	public List<Map<String, Object>> getVendorList() {
		return vendorList;
	}

	public void setVendorList(List<Map<String, Object>> vendorList) {
		this.vendorList = vendorList;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public List<Map<String, Object>> getVendorDetails() {
		return vendorDetails;
	}

	public void setVendorDetails(List<Map<String, Object>> vendorDetails) {
		this.vendorDetails = vendorDetails;
	}

	public List<Map<String, Object>> getReviewListBean() {
		return reviewListBean;
	}

	public void setReviewListBean(List<Map<String, Object>> reviewListBean) {
		this.reviewListBean = reviewListBean;
	}

	public List<Map<String, Object>> getPersonalDetailsBean() {
		return personalDetailsBean;
	}

	public void setPersonalDetailsBean(List<Map<String, Object>> personalDetailsBean) {
		this.personalDetailsBean = personalDetailsBean;
	}

	public Long getShortlistedVendorId() {
		return shortlistedVendorId;
	}

	public void setShortlistedVendorId(Long shortlistedVendorId) {
		this.shortlistedVendorId = shortlistedVendorId;
	}

	/**
	 * @return the shortlistedId
	 */
	public Long getShortlistedId() {
		return shortlistedId;
	}

	/**
	 * @param shortlistedId the shortlistedId to set
	 */
	public void setShortlistedId(Long shortlistedId) {
		this.shortlistedId = shortlistedId;
	}

	public Long getShortlistId() {
		return shortlistId;
	}

	public void setShortlistId(Long shortlistId) {
		this.shortlistId = shortlistId;
	}

	public List<Map<String, Object>> getMyWishlist() {
		return myWishlist;
	}

	public void setMyWishlist(List<Map<String, Object>> myWishlist) {
		this.myWishlist = myWishlist;
	}

	public Long getBannerId() {
		return bannerId;
	}

	public void setBannerId(Long bannerId) {
		this.bannerId = bannerId;
	}

	public String getBannerStatus() {
		return bannerStatus;
	}

	public void setBannerStatus(String bannerStatus) {
		this.bannerStatus = bannerStatus;
	}

	public List<Map<String, Object>> getActiveBannerPhotosBean() {
		return activeBannerPhotosBean;
	}

	public void setActiveBannerPhotosBean(
			List<Map<String, Object>> activeBannerPhotosBean) {
		this.activeBannerPhotosBean = activeBannerPhotosBean;
	}

	public List<Map<String, Object>> getMyBookinglist() {
		return myBookinglist;
	}

	public void setMyBookinglist(List<Map<String, Object>> myBookinglist) {
		this.myBookinglist = myBookinglist;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public List<Map<String, Object>> getYoutubeVideoUrls() {
		return youtubeVideoUrls;
	}

	public void setYoutubeVideoUrls(List<Map<String, Object>> youtubeVideoUrls) {
		this.youtubeVideoUrls = youtubeVideoUrls;
	}

}
