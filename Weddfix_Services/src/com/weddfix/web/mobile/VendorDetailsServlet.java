package com.weddfix.web.mobile;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weddfix.web.formbean.DirectoryCategoryInfoFormBean;
import com.weddfix.web.formbean.DirectoryCategoryReviewFormBean;
import com.weddfix.web.formbean.DirectoryUserProfileFormBean;
import com.weddfix.web.formbean.PhotoGalleryFormBean;
import com.weddfix.web.services.CategoryInfoService;
import com.weddfix.web.services.CommonMasterService;

public class VendorDetailsServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CategoryInfoService categoryInfoService = new CategoryInfoService();
	CommonMasterService commonMasterService = new CommonMasterService();
    LinkedHashMap<String, Object> rootMap = new LinkedHashMap<String, Object>();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LinkedHashMap<String, Object> main = new LinkedHashMap<String, Object>();
		ResourceBundle rb = ResourceBundle.getBundle("ApplicationResources");
		
        try {
        	
        	Long userId = 0L;
			Long vendorId = 0L;

			if(request.getParameter("userId") != null) {
				userId = Long.parseLong(request.getParameter("userId").toString());
			} 

			if(request.getParameter("vendorId") != null) {
				vendorId = Long.parseLong(request.getParameter("vendorId").toString());
			} 

			String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

		    Pattern compiledPattern = Pattern.compile(pattern);
		    Matcher matcher = null;

		    List<DirectoryCategoryInfoFormBean> vendorInfoList = categoryInfoService
						.loadVendorDetails(vendorId);
				
		    LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		    LinkedList<LinkedHashMap<String, Object>> youtubeVideoUrls = new LinkedList<LinkedHashMap<String, Object>>();
		    
		    Iterator<?> itr = vendorInfoList.iterator();
			
		    while (itr.hasNext()) {

					Object[] obj = (Object[]) itr.next();
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
					if(obj[10] != null) {
						map.put("maxRating", obj[10]);
					} else {
						map.put("maxRating", "");
					}
					if(obj[10] != null) {
						map.put("minRating", 5L-Long.parseLong(obj[10].toString()));
					} else {
						map.put("minRating", "");
					}
					if(obj[11] != null) {
						map.put("maxUsersRating", obj[11]);
					} else {
						map.put("maxUsersRating", "");
					}
					if(obj[12] != null) {
						map.put("aboutUs", obj[12]);
					} else {
						map.put("aboutUs", "");
					}
					if(obj[13] != null) {
						map.put("mapUrl", obj[13]);
					} else {
						map.put("mapUrl", "");
					}
					if(obj[14] != null) {
						map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+obj[14]);
					} else {
						map.put("fileName", "");
					}
					if(obj[15] != null) {
						map.put("vendorFullName", obj[15]);
					} else {
						map.put("vendorFullName", "");
					}
					if(obj[16] != null) {
						map.put("vendorCityName", obj[16]);
					} else {
						map.put("vendorCityName", "");
					}
					if(obj[17] != null) {
						map.put("vendorStateName", obj[17]);
					} else {
						map.put("vendorStateName", "");
					}
					if(obj[18] != null) {
						map.put("vendorCountryName", obj[18]);
					} else {
						map.put("vendorCountryName", "");
					}
					if(obj[19] != null) {
						map.put("vendorPincode", obj[19]);
					} else {
						map.put("vendorPincode", "");
					}
					if(obj[20] != null) {
						map.put("vendorWebsiteUrl", obj[20]);
					} else {
						map.put("vendorWebsiteUrl", "");
					}
					if(obj[21] != null) {
						map.put("vendorFacebookUrl", obj[21]);
					} else {
						map.put("vendorFacebookUrl", "");
					}
					if(obj[22] != null) {
						map.put("vendorTwitterUrl", obj[22]);
					} else {
						map.put("vendorTwitterUrl", "");
					}
					if(obj[23] != null) {
						map.put("vendorLinkedinUrl", obj[23]);
					} else {
						map.put("vendorLinkedinUrl", "");
					}
					if(obj[24] != null) {
						map.put("vendorPinterestUrl", obj[24]);
					} else {
						map.put("vendorPinterestUrl", "");
					}
					if(obj[25] != null) {
						map.put("vendorInstagramUrl", obj[25]);
					} else {
						map.put("vendorInstagramUrl", "");
					}
					if(obj[26] != null) {
						map.put("vendorProfilePicture", rb.getString("url")+"/ImageAction.action?imageId="+obj[26]);
					} else {
						map.put("vendorProfilePicture", "");
					}
					if(obj[27] != null) {
						map.put("vendorContactName", obj[27]);
					} else {
						map.put("vendorContactName", "");
					}
					if(obj[28] != null) {
						map.put("vendorMobile", obj[28]);
					} else {
						map.put("vendorMobile", "");
					}
					if(obj[29] != null) {
						map.put("vendorPhone", obj[29]);
					} else {
						map.put("vendorPhone", "");
					}
					if(obj[30] != null) {
						map.put("vendorEmail", obj[30]);
					} else {
						map.put("vendorEmail", "");
					}
					LinkedHashMap<String, Object> urlMap = new LinkedHashMap<String, Object>();
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
					} else {
						youtubeVideoUrls.add(urlMap);
				    }

				}
				
			main.put("vendorDetails", map);
			main.put("youtubeVideoUrls", youtubeVideoUrls);
			
			List<PhotoGalleryFormBean> morePhotoGalleryInfoList = categoryInfoService
					.loadPhotoGalleryDetailsByVendorId(vendorId);
			
			LinkedList<LinkedHashMap<String, Object>> morePhotoGalleryList = new LinkedList<LinkedHashMap<String, Object>>();
		    
					itr = morePhotoGalleryInfoList.iterator();
					while (itr.hasNext()) {
				
						Object[] obj = (Object[]) itr.next();
						map = new LinkedHashMap<String, Object>();
						map.put("id", obj[0]);
						if(obj[1] != null) {
							map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+obj[1]);
						} else {
							map.put("fileName", "");
						}
						map.put("orgId", obj[2]);
						map.put("photoType", obj[3]);
				
						morePhotoGalleryList.add(map);
					}
			
			main.put("photoGallery", morePhotoGalleryList);
			
			List<DirectoryCategoryReviewFormBean> reviewInfoList = categoryInfoService
					.loadReviewDetailsByVendorId(vendorId);
			
			LinkedList<LinkedHashMap<String, Object>> reviewList = new LinkedList<LinkedHashMap<String, Object>>();
			
					itr = reviewInfoList.iterator();
					while (itr.hasNext()) {
				
						Object[] obj = (Object[]) itr.next();
						map = new LinkedHashMap<String, Object>();
						map.put("id", obj[0]);
						map.put("maxRating", obj[1]);
						map.put("name", obj[2]);
						map.put("email", obj[3]);
						map.put("reviewTitle", obj[4]);
						map.put("review", obj[5]);
						map.put("createdDate", obj[6]);
						if(obj[7] != null) {
							map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+obj[7]);
						} else {
							map.put("fileName", "");
						}
						if(obj[1] != null) {
							map.put("minRating", 5L-Long.parseLong(obj[1].toString()));
						}
				
						reviewList.add(map);
					}
					
			main.put("reviews", reviewList);
			
			if(userId > 0) {		
				List<DirectoryUserProfileFormBean> personalDetailsInfoList = categoryInfoService
						.loadPersonalDetailsByUserId(userId);
				
						map = new LinkedHashMap<String, Object>();
				
						itr = personalDetailsInfoList.iterator();
						while (itr.hasNext()) {
					
							Object[] obj = (Object[]) itr.next();
							map.put("id", obj[0]);
							map.put("fullName", obj[1]);
							map.put("email", obj[2]);
							map.put("mobile", obj[3]);
							if(obj[4] != null) {
								map.put("weddingDate", obj[4]);
							} else {
								map.put("weddingDate", "");
							}
					
						}
				}
					
			main.put("myPersonalDetails", map);
			
			Map<Object, Object> countryMap = commonMasterService.loadCountry();
            
            LinkedList<LinkedHashMap<String, Object>> countryMapList = new LinkedList<LinkedHashMap<String, Object>>();
            Iterator<?> itrCountryMap = countryMap.entrySet().iterator();
            while (itrCountryMap.hasNext()) {
                @SuppressWarnings({ "rawtypes" })
				Map.Entry pair = (Map.Entry)itrCountryMap.next();
				LinkedHashMap<String, Object> countrysMap = new LinkedHashMap<String, Object>();
				countrysMap.put("id", pair.getKey());
				countrysMap.put("countryName", pair.getValue());
				countryMapList.add(countrysMap);
				itrCountryMap.remove(); // avoids a ConcurrentModificationException
            }
            
            main.put("country", countryMapList);
			
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(main);
            
            response.setContentType("application/json;charset=utf-8");
            byte[] out = json.getBytes("UTF-8");
            response.setContentLength(out.length);
            response.getOutputStream().write(out);
            return;
			
        } catch (Exception e) {
        	rootMap.put("status", "failure");
        	rootMap.put("message", e);
			main.put("vendorDetails", rootMap);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(main);
            
            response.setContentType("application/json;charset=utf-8");
            byte[] out = json.getBytes("UTF-8");
            response.setContentLength(out.length);
            response.getOutputStream().write(out);
			return;
        }
        
    }

}
