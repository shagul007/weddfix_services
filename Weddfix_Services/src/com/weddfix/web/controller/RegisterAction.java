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
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.weddfix.web.formbean.DirectoryUpgradePlanFormBean;
import com.weddfix.web.formbean.DirectoryUserProfileFormBean;
import com.weddfix.web.formbean.DirectoryUserPwResetFormBean;
import com.weddfix.web.formbean.LoginFormBean;
import com.weddfix.web.formbean.PhotoGalleryFormBean;
import com.weddfix.web.services.CategoryInfoService;
import com.weddfix.web.services.CommonMasterService;
import com.weddfix.web.services.LoginService;
import com.weddfix.web.services.RegisterService;
import com.weddfix.web.util.CommonConstants;
import com.weddfix.web.util.FilesUtil;
import com.weddfix.web.util.HibernateUtil;
import com.weddfix.web.util.MailMessage;
import com.weddfix.web.util.SendSMS;
import com.weddfix.web.util.Util;

public class RegisterAction extends ActionSupport implements
		ModelDriven<DirectoryUserProfileFormBean>, Preparable, ServletRequestAware,
		ServletResponseAware, SessionAware {

	private static final long serialVersionUID = 1L;
	private static final long MILLIS_IN_DAY = 60*60*24*1000;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	private Map<String, Object> session;
	private Map<Object, Object> currencyMap;
	private Map<Object, Object> countryMap;
	private Map<Object, Object> orgMap;
	private Map<Object, Object> roleMap;
	private Map<Object, Object> statusMap;
	private Map<String, Object> userProfileBean = new HashMap<String, Object>();
	private Map<String, Object> personalDetailsBean = new HashMap<String, Object>();
	private Map<String, Object> partnerPreferenceBean = new HashMap<String, Object>();
	private List<Map<String, Object>> userProfiles = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> myAccountDetails = new ArrayList<Map<String, Object>>();
	private String emailId;
	private String wdStr;
	private Long userId;
	private Long userRole;
	private String userRoleDesc;
	private Long userStatus;
	private String userPassword;
	private String verifyMobile;
	private String verifyCode;
	private String verifyMsg;
	private String profilePic;
	private String filesPath;
	private String userFileFileName;
	private File userFile[] = new File[1];
	private String filenames[] = new String[1];
	private Long fileSize;
	private String fileContentType;

	PhotoGalleryFormBean photoGalleryFormBean = new PhotoGalleryFormBean();
	CategoryInfoService clientInfoService = new CategoryInfoService();
	DirectoryUserProfileFormBean userProfileFormBean = new DirectoryUserProfileFormBean();
	RegisterService registerService = new RegisterService();
	CommonMasterService commonMasterService = new CommonMasterService();
	SendSMS sms = new SendSMS();

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public String saveRegisterDetails() {
		HttpSession session = request.getSession(true);
		if (userProfileFormBean.getMobile() != null) {
			String pwdHash = null;
			Date dob = null;
			try {
				if(userProfileFormBean.getWdStr() != null && !userProfileFormBean.getWdStr().equals("")) {
					dob = sdf.parse(userProfileFormBean.getWdStr());
					userProfileFormBean.setWeddingDate(dob);
				}
			} catch (ParseException e) {
				// e.printStackTrace();
			}
			try {
				if (userProfileFormBean.getPassword() != null) {
					pwdHash = CommonConstants
							.generateEncryptedPwd(userProfileFormBean
									.getPassword());
				}
				if (pwdHash != null) {
					userProfileFormBean.setPassword(pwdHash);
					userProfileFormBean.setPasswordHash(pwdHash);
				}

				if (userProfileFormBean.getId() != null) {
					if(getUserFileFileName() != null) {
						uploadProfilePicture();
					}
					session.setAttribute("update", "UPDATE");
					userProfileFormBean.setUpdatedBy(Long.parseLong(session
							.getAttribute("userId").toString()));
				} else {
					session.setAttribute("update", null);
				}

				Long userId = registerService
						.saveRegisterDetails(userProfileFormBean);
				if (userId != null) {
					if (session.getAttribute("update") != null) {
						session.setAttribute("successMessage",
								"Updated Successfully...");
						session.setAttribute("hrefParamSuccess", "user_home");
						if(!userProfileFormBean.getMobile().toString().equals(session.getAttribute("mobile").toString())) {
							session.setAttribute("mobile", userProfileFormBean.getMobile());
							String code = registerService
									.updateMobileVerificationCodeDetails((Long) session.getAttribute("userId"));
							session.setAttribute("verifyMobileNumber", code);
							session.setAttribute("verifyedMobileNumber", false);
						}
						System.out.println("Updated Successfully...");
					} else {
						userProfileFormBean.setPassword(null);
						session.setAttribute("successMessage",
								"Registered Successfully...");
						session.setAttribute("hrefParamSuccess", "register_2");
						System.out.println("Registered Successfully...");
				        
						Properties props = new Properties();
				        props.put("fullName", userProfileFormBean.getFullName());
			            
			            MailMessage msg = new MailMessage(props, "welcome.vm", userProfileFormBean.getEmail(), 
			            		"Welcome to Weddfix");

						msg.send();
							
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
		} else {
			return "success";
		}
	}
	
	public String saveRegisterJsonDetails() {
		HttpSession session = request.getSession(true);
		if (userProfileFormBean.getMobile() != null) {
			String pwdHash = null;
			String pwd = null;
			Date dob = null;
			try {
				if(userProfileFormBean.getWdStr() != null && !userProfileFormBean.getWdStr().equals("")) {
					dob = sdf.parse(userProfileFormBean.getWdStr());
					userProfileFormBean.setWeddingDate(dob);
				}
			} catch (ParseException e) {
				// e.printStackTrace();
			}
			try {
				pwd = userProfileFormBean.getPassword();
				if (userProfileFormBean.getPassword() != null) {
					pwdHash = CommonConstants
							.generateEncryptedPwd(userProfileFormBean
									.getPassword());
				}
				if (pwdHash != null) {
					userProfileFormBean.setPassword(pwdHash);
					userProfileFormBean.setPasswordHash(pwdHash);
				}

				if (userProfileFormBean.getId() != null) {
					if(getUserFileFileName() != null) {
						uploadProfilePicture();
					}
					session.setAttribute("update", "UPDATE");
					userProfileFormBean.setUpdatedBy(Long.parseLong(session
							.getAttribute("userId").toString()));
				} else {
					session.setAttribute("update", null);
				}

				Long userId = registerService
						.saveRegisterDetails(userProfileFormBean);
				if (userId != null) {
					if (session.getAttribute("update") != null) {
						session.setAttribute("successMessage",
								"Updated Successfully...");
						session.setAttribute("hrefParamSuccess", "user_home");
						if(!userProfileFormBean.getMobile().toString().equals(session.getAttribute("mobile").toString())) {
							session.setAttribute("mobile", userProfileFormBean.getMobile());
							String code = registerService
									.updateMobileVerificationCodeDetails((Long) session.getAttribute("userId"));
							session.setAttribute("verifyMobileNumber", code);
							session.setAttribute("verifyedMobileNumber", false);
						}
						System.out.println("Updated Successfully...");
					} else {
						LoginService loginService = new LoginService();
						LoginFormBean loginFormBean = new LoginFormBean();
						loginFormBean = loginService.checkLogin(
								userProfileFormBean.getEmail(), pwd);

						this.session.put("loginId", loginFormBean.getId());
						this.session.put("authenticated", new Boolean(true));
						session.setAttribute("userId", loginFormBean.getId());
						session.setAttribute("email", loginFormBean.getEmail());
						session.setAttribute("mobile", loginFormBean.getMobile());
						session.setAttribute("role", loginFormBean.getRole());
						session.setAttribute("status", loginFormBean.getStatus());
						session.setAttribute("password", loginFormBean.getPassword());
						session.setAttribute("userName", loginFormBean.getFullName());
						session.setAttribute("loginType", "public");
						session.setAttribute("loginCheck", "loggedin");
						session.setAttribute("accountType", loginFormBean.getAccountType());
						session.setAttribute("deleteProfileReason", loginFormBean.getDeleteProfileReason());
						session.setAttribute("isProfileDeleted", loginFormBean.getIsProfileDeleted());
						session.setAttribute("verifyMobileNumber", loginFormBean.getVerifyMobileNumber());
						session.setAttribute("verifyEmailId", loginFormBean.getVerifyEmailId());
						session.setAttribute("verifiedMobileNumber", loginFormBean.getVerifiedMobileNumber());
						session.setAttribute("verifiedEmailId", loginFormBean.getVerifiedEmailId());
						session.setAttribute("myPlanId", loginFormBean.getMyPlanId());
						session.setAttribute("url", CommonConstants.URL);
						session.setAttribute("successMessage",
								"Registered Successfully...");
						session.setAttribute("hrefParamSuccess", "vendor_details");
						System.out.println("Registered Successfully...");
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
		} else {
			return "success";
		}
	}
	
	public String uploadProfilePicture() {
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
			Long status = clientInfoService
					.saveProfilePicture(photoGalleryFormBean, getProfilePic());
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

	public String loadUserProfileDetails() {
		HttpSession session = request.getSession();
		try {
			DirectoryUserProfileFormBean userProfile = registerService
					.loadUserProfileDetails((Long) session
							.getAttribute("userId"));

			session.setAttribute("cityId", userProfile.getCityId());
			session.setAttribute("stateId", userProfile.getStateId());
			session.setAttribute("countryId", userProfile.getCountryId());

			userProfileBean.put("id", userProfile.getId());
			userProfileBean.put("fullName", userProfile.getFullName());
			userProfileBean.put("email", userProfile.getEmail());
			userProfileBean.put("mobile", userProfile.getMobile());
			userProfileBean.put("pincode", userProfile.getPincode());
			loadRegisterMasters();
			return "success";
		}

		catch (Exception e) {
			// e.printStackTrace();
			return "error";
		}
	}

	public String sessionPasswordHash() {
		HttpSession session = request.getSession();
		try {
			LoginService loginService = new LoginService();
			LoginFormBean loginFormBean = loginService
					.sessionPasswordHash((Long) session.getAttribute("userId"));

			session.setAttribute("password", loginFormBean.getPassword());
			return "success";
		}

		catch (Exception e) {
			// e.printStackTrace();
			return "error";
		}
	}

	public String loadRegisterMasters() {

		try {
			countryMap = commonMasterService.loadCountry();
			System.out.println("****" + countryMap);

			return "success";
		} catch (Exception e) {
			// e.printStackTrace();
			return "error";
		}
	}
	
	public String loadMasters() {

		countryMap = commonMasterService.loadCountry();
		System.out.println("****" + countryMap);

		return "success";
	}

	public String loadRoleAndStatusMasters() {

		roleMap = commonMasterService.loadRole();
		statusMap = commonMasterService.loadStatus();
		System.out.println("****" + roleMap);
		System.out.println("****" + statusMap);

		return "success";
	}

	public String loadRegister2Masters() {

		currencyMap = commonMasterService.loadCurrency();
		countryMap = commonMasterService.loadCountry();
		System.out.println("****" + countryMap);

		return "success";
	}

	public String checkUserAlreadyExist() {
		String userExist = commonMasterService.checkUserAlreadyExist(emailId.toLowerCase());
		if (userExist != null) {
			return "success";
		} else {
			setEmailId(null);
			return "error";
		}
	}

	public String loadAllUserRoleDetails() {
		List<DirectoryUserProfileFormBean> userprofileList = commonMasterService
				.loadAllUserRoleDetails();
		Iterator<?> itr = userprofileList.iterator();
		while (itr.hasNext()) {

			Object[] obj = (Object[]) itr.next();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", obj[0]);
			map.put("fullName", obj[1]);
			map.put("email", obj[2]);
			map.put("mobile", obj[3]);
			map.put("roleId", obj[4]);
			map.put("statusId", obj[5]);
			map.put("role", obj[6]);
			map.put("status", obj[7]);

			userProfiles.add(map);
		}

		loadRoleAndStatusMasters();

		return "success";
	}
	
	public String loadMyAccountDetails() {
		HttpSession session = request.getSession(true);
		List<DirectoryUpgradePlanFormBean> mAccountDetailsList = commonMasterService
				.loadMyAccountDetails((Long) session.getAttribute("userId"));
		Iterator<?> itr = mAccountDetailsList.iterator();
		while (itr.hasNext()) {

			Object[] obj = (Object[]) itr.next();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", obj[0]);
			map.put("planName", obj[1]);
			map.put("amount", obj[2]);


			if (obj[1].toString().equalsIgnoreCase("FREE")) {
				DateFormat dateFormat= new SimpleDateFormat("dd MM yyyy");
				Date cDate = null;
				try {
					cDate = dateFormat.parse(obj[4].toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Date createdDate = DateUtils.addMonths(cDate, 3);
				//it's still active if the created date + 11 days is greater than the current time
	        	int daysIntoTrial = Math.round(((createdDate.getTime() - System.currentTimeMillis()) / MILLIS_IN_DAY));
	        	
	        	int reminingDays =  daysIntoTrial - 1;
	        	map.put("validity", reminingDays);
	        } else {
	        	DateFormat dateFormat= new SimpleDateFormat("dd MM yyyy");
				Date cDate = null;
				try {
					cDate = dateFormat.parse(obj[4].toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
	        	Date createdDate = DateUtils.addMonths(cDate, Integer.valueOf(obj[3].toString()));
	        	//it's still active if the created date + 11 days is greater than the current time
	        	int daysIntoTrial = Math.round(((createdDate.getTime() - System.currentTimeMillis()) / MILLIS_IN_DAY));
	        	
	        	int reminingDays =  daysIntoTrial;
	        	map.put("validity", reminingDays);
	        }
	        
			map.put("createdDate", obj[4]);
			map.put("smsAlert", obj[5]);

			myAccountDetails.add(map);
		}

		return "success";
	}

	public String updateUserRoleAndStatus() {
		HttpSession session = request.getSession(true);
		Long updatedBy = (Long) session.getAttribute("userId");
		String status = commonMasterService.updateUserRoleAndStatus(userId,
				userRole, userRoleDesc, userStatus, updatedBy);
		if (status != null) {
			return "success";
		} else {
			return "error";
		}
	}

	public String sendForgotPassword() {
		HttpSession session = request.getSession(true);
		try {
			DirectoryUserProfileFormBean userProfile = registerService
					.loadUserProfileByEmail(emailId);
			String key = startPasswordReset(userProfile.getId());
			Properties props = new Properties();
			props.put("fullName", userProfile.getFullName());
			props.put("email", userProfile.getEmail());
			props.put("url", getText("url") + "/reset?key=" + key);
			MailMessage msg = new MailMessage(props, "pwreset.vm",
					userProfile.getEmail(), "Password reset request");
			msg.send();
			session.setAttribute("successMessage", "We've sent a password reset link to your email address.");
			session.setAttribute("hrefParamSuccess", "home");
			System.out.println("We've sent a password reset link to your email address.");
			return "success";
		}

		catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage",
					"Something went wrong. Please try again later.");
			session.setAttribute("hrefParamError", "forgot_password");
			return "error";
		}
	}

	public String startPasswordReset(Long userId) throws Exception {
		String key = UUID.randomUUID().toString();
		Session conn = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = conn.beginTransaction();
		try {
			DirectoryUserPwResetFormBean pwResetFormBean = new DirectoryUserPwResetFormBean();
			conn.getNamedQuery("deletePwResetByUserId")
					.setLong("userId", userId).executeUpdate();
			pwResetFormBean.setUserId(userId);
			pwResetFormBean.setResetKey(key);
			conn.save(pwResetFormBean);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			conn.flush();
			conn.close();
		}
		return key;
	}

	public String resetPassword() {
		HttpSession session = request.getSession(true);
		try {
			String pwdHash = CommonConstants.generateEncryptedPwd(userPassword);
			commonMasterService.resetPassword(userId, pwdHash);
			session.setAttribute("successMessage",
					"Your password has been changed...");
			session.setAttribute("hrefParamSuccess", "login");
			System.out.println("Your password has been changed...");
			return "success";
		}

		catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage",
					"Something went wrong. Please try again later.");
			session.setAttribute("hrefParamError", "forgot_password");
			return "error";
		}
	}
	
	public String reSendCode() {
		HttpSession session = request.getSession(true);
		try {
			if(!getVerifyMobile().equals(session.getAttribute("mobile").toString())) {
				session.setAttribute("mobile", getVerifyMobile());
				String code = registerService
						.updateMobileVerificationCode((Long) session.getAttribute("userId"));
				session.setAttribute("verifyMobileNumber", code);
			}
			String msg = "Your Weddfix mobile verification code is "+session.getAttribute("verifyMobileNumber").toString();
			sms.sendSms(msg, getVerifyMobile());
			System.out.println("Verification code sent successfully...");
			return "success";
		}

		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String verifyCodeAndUpdateMobile() {
		HttpSession session = request.getSession(true);
		try {
			if(session.getAttribute("verifyMobileNumber").toString().equals(getVerifyCode())) {
				String status = registerService
						.verifyCodeAndUpdateMobile((Long) session.getAttribute("mobile"), (Long) session.getAttribute("userId"));
				if(status != null) {
					session.setAttribute("verifiedMobileNumber", true);
					verifyMsg = "success";
					return "success";
				} else {
					verifyMsg = "error";
					return "error";
				}
			} else {
				verifyMsg = "error";
				return "error";
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			verifyMsg = "error";
			return "error";
		}
	}

	public void prepare() throws Exception {
		userProfileFormBean = new DirectoryUserProfileFormBean();
	}

	public DirectoryUserProfileFormBean getModel() {
		return userProfileFormBean;
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

	public Map<Object, Object> getCountryMap() {
		return countryMap;
	}

	public void setCountryMap(Map<Object, Object> countryMap) {
		this.countryMap = countryMap;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Map<String, Object> getUserProfileBean() {
		return userProfileBean;
	}

	public void setUserProfileBean(Map<String, Object> userProfileBean) {
		this.userProfileBean = userProfileBean;
	}

	public List<Map<String, Object>> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(List<Map<String, Object>> userProfiles) {
		this.userProfiles = userProfiles;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserRole() {
		return userRole;
	}

	public void setUserRole(Long userRole) {
		this.userRole = userRole;
	}

	public Long getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Long userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Map<Object, Object> getOrgMap() {
		return orgMap;
	}

	public void setOrgMap(Map<Object, Object> orgMap) {
		this.orgMap = orgMap;
	}

	public Map<Object, Object> getRoleMap() {
		return roleMap;
	}

	public void setRoleMap(Map<Object, Object> roleMap) {
		this.roleMap = roleMap;
	}

	public Map<Object, Object> getCurrencyMap() {
		return currencyMap;
	}

	public void setCurrencyMap(Map<Object, Object> currencyMap) {
		this.currencyMap = currencyMap;
	}

	public Map<String, Object> getPartnerPreferenceBean() {
		return partnerPreferenceBean;
	}

	public void setPartnerPreferenceBean(
			Map<String, Object> partnerPreferenceBean) {
		this.partnerPreferenceBean = partnerPreferenceBean;
	}

	public Map<String, Object> getPersonalDetailsBean() {
		return personalDetailsBean;
	}

	public void setPersonalDetailsBean(Map<String, Object> personalDetailsBean) {
		this.personalDetailsBean = personalDetailsBean;
	}

	public Map<Object, Object> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<Object, Object> statusMap) {
		this.statusMap = statusMap;
	}

	public String getUserRoleDesc() {
		return userRoleDesc;
	}

	public void setUserRoleDesc(String userRoleDesc) {
		this.userRoleDesc = userRoleDesc;
	}

	public List<Map<String, Object>> getMyAccountDetails() {
		return myAccountDetails;
	}

	public void setMyAccountDetails(List<Map<String, Object>> myAccountDetails) {
		this.myAccountDetails = myAccountDetails;
	}

	public String getVerifyMobile() {
		return verifyMobile;
	}

	public void setVerifyMobile(String verifyMobile) {
		this.verifyMobile = verifyMobile;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getVerifyMsg() {
		return verifyMsg;
	}

	public void setVerifyMsg(String verifyMsg) {
		this.verifyMsg = verifyMsg;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getFilesPath() {
		return filesPath;
	}

	public void setFilesPath(String filesPath) {
		this.filesPath = filesPath;
	}

	public String getUserFileFileName() {
		return userFileFileName;
	}

	public void setUserFileFileName(String userFileFileName) {
		this.userFileFileName = userFileFileName;
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

	public String getWdStr() {
		return wdStr;
	}

	public void setWdStr(String wdStr) {
		this.wdStr = wdStr;
	}

}
