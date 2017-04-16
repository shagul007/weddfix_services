package com.weddfix.web.mobile;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.classic.Session;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weddfix.web.formbean.DirectoryCategoryInfoFormBean;
import com.weddfix.web.formbean.PhotoGalleryFormBean;
import com.weddfix.web.services.CategoryInfoService;
import com.weddfix.web.services.CommonMasterService;
import com.weddfix.web.util.HibernateUtil;

public class VendorListingServlet extends HttpServlet {

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
        	Long editListingId = 0L;

			if(request.getParameter("userId") != null) {
				userId = Long.parseLong(request.getParameter("userId").toString());
			} 
			if(request.getParameter("editListingId") != null) {
				editListingId = Long.parseLong(request.getParameter("editListingId").toString());
			} 
			
			String find = request.getParameter("find");
			
			if(find != null) {
			if(find.equals("addListing")) {
				Map<Object, Object> orgMap = commonMasterService.loadOrg();
	            
	            LinkedList<LinkedHashMap<String, Object>> orgMapList = new LinkedList<LinkedHashMap<String, Object>>();
	            Iterator<?> itrOrgMap = orgMap.entrySet().iterator();
	            while (itrOrgMap.hasNext()) {
	                @SuppressWarnings({ "rawtypes" })
					Map.Entry pair = (Map.Entry)itrOrgMap.next();
					LinkedHashMap<String, Object> orgsMap = new LinkedHashMap<String, Object>();
					orgsMap.put("id", pair.getKey());
					orgsMap.put("categoryName", pair.getValue());
					orgMapList.add(orgsMap);
					itrOrgMap.remove(); // avoids a ConcurrentModificationException
	            }
	            
	            main.put("category", orgMapList);
	            
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
			}
			
			if(find.equals("editListing") && editListingId > 0 && userId > 0) {
				DirectoryCategoryInfoFormBean categoryInfo = categoryInfoService
						.loadEditVenueDetails(editListingId);
				
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				
				map.put("id",
						categoryInfo.getId());
				if(categoryInfo.getFileName() != null) {
					map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+categoryInfo.getFileName());
				} else {
					map.put("fileName", "");
				}
				map.put("companyName", categoryInfo.getCompanyName());
				map.put("contactName", categoryInfo.getContactName());
				map.put("mobile", categoryInfo.getMobile());
				if(categoryInfo.getPhone() != null) {
					map.put("phone", categoryInfo.getPhone());
				} else {
					map.put("phone", "");
				}
				map.put("email", categoryInfo.getEmail());
				if(categoryInfo.getWebsiteUrl() != null) {
					map.put("websiteUrl", categoryInfo.getWebsiteUrl());
				} else {
					map.put("websiteUrl", "");
				}
				map.put("categoryId",
						categoryInfo.getCategoryId());
				if(categoryInfo.getSubCategoryId() != null) {
					map.put("subCategoryId", categoryInfo.getSubCategoryId());
				} else {
					map.put("subCategoryId", "");
				}
				if(categoryInfo.getSeatCapacityId() != null) {
					map.put("seatCapacityId", categoryInfo.getSeatCapacityId());
				} else {
					map.put("seatCapacityId", "");
				}
				map.put("location", categoryInfo.getLocation());
				map.put("address", categoryInfo.getAddress());
				map.put("countryId",
						categoryInfo.getCountryId());
				map.put("stateId",
						categoryInfo.getStateId());
				map.put("cityId",
						categoryInfo.getCityId());
				map.put("pincode",
						categoryInfo.getPincode());
				if(categoryInfo.getDescription() != null) {
					map.put("aboutUs", categoryInfo.getDescription());
				} else {
					map.put("aboutUs", "");
				}
				map.put("price", categoryInfo.getPrice());
				if(categoryInfo.getLatitude() != null) {
					map.put("mapUrl", categoryInfo.getLatitude());
				} else {
					map.put("mapUrl", "");
				}
				if(categoryInfo.getcategoryPictureId() != null) {
					map.put("categoryPictureId", categoryInfo.getcategoryPictureId());
				} else {
					map.put("categoryPictureId", "");
				}
				if(categoryInfo.getCategoryVideoUrl() != null) {
					map.put("categoryVideoUrl", categoryInfo.getCategoryVideoUrl());
				} else {
					map.put("categoryVideoUrl", "");
				}
				if(categoryInfo.getPhotoType() != null) {
					map.put("photoType", categoryInfo.getPhotoType());
				} else {
					map.put("photoType", "");
				}
				
				main.put("editListing", map);
				
				Map<Object, Object> orgMap = commonMasterService.loadOrg();
	            
	            LinkedList<LinkedHashMap<String, Object>> orgMapList = new LinkedList<LinkedHashMap<String, Object>>();
	            Iterator<?> itrOrgMap = orgMap.entrySet().iterator();
	            while (itrOrgMap.hasNext()) {
	                @SuppressWarnings({ "rawtypes" })
					Map.Entry pair = (Map.Entry)itrOrgMap.next();
					LinkedHashMap<String, Object> orgsMap = new LinkedHashMap<String, Object>();
					orgsMap.put("id", pair.getKey());
					orgsMap.put("categoryName", pair.getValue());
					orgMapList.add(orgsMap);
					itrOrgMap.remove(); // avoids a ConcurrentModificationException
	            }
	            
	            main.put("category", orgMapList);
	            
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
				
				List<PhotoGalleryFormBean> morePhotoGalleryList = categoryInfoService
				.loadPhotoGalleryDetails(userId, categoryInfo.getCategoryId(), categoryInfo.getPhotoType());
				
				LinkedList<LinkedHashMap<String, Object>> moreCategoryPhotos = new LinkedList<LinkedHashMap<String, Object>>();
				
				Iterator<?> itr = morePhotoGalleryList.iterator();
				while (itr.hasNext()) {
			
					Object[] obj = (Object[]) itr.next();
					map = new LinkedHashMap<String, Object>();
					map.put("id", obj[0]);
					map.put("fileName", obj[1]);
					map.put("orgId", obj[2]);
					map.put("photoType", obj[3]);
			
					moreCategoryPhotos.add(map);
				}
				
				main.put("moreCategoryPhotos", moreCategoryPhotos);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
	            String json = gson.toJson(main);
	            
	            response.setContentType("application/json;charset=utf-8");
	            byte[] out = json.getBytes("UTF-8");
	            response.setContentLength(out.length);
	            response.getOutputStream().write(out);
	            return;
			} else {
				rootMap.put("status", "failure");
				rootMap.put("message", "Missing Parameters.");
				main.put("listing", rootMap);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
	            String json = gson.toJson(main);
	            
	            response.setContentType("application/json;charset=utf-8");
	            byte[] out = json.getBytes("UTF-8");
	            response.setContentLength(out.length);
	            response.getOutputStream().write(out);
	            return;
			}
			 
		} else {
			rootMap.put("status", "failure");
			rootMap.put("message", "Missing Parameters.");
			main.put("listing", rootMap);
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(main);
            
            response.setContentType("application/json;charset=utf-8");
            byte[] out = json.getBytes("UTF-8");
            response.setContentLength(out.length);
            response.getOutputStream().write(out);
            return;
		}
			
        } catch (Exception e) {
        	rootMap.put("status", "failure");
        	rootMap.put("message", e);
			main.put("listing", rootMap);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(main);
            
            response.setContentType("application/json;charset=utf-8");
            byte[] out = json.getBytes("UTF-8");
            response.setContentLength(out.length);
            response.getOutputStream().write(out);
			return;
        }
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LinkedHashMap<String, Object> main = new LinkedHashMap<String, Object>();
		
        try {
        	
        	Long userId = 0L;
        	Long photoId = 0L;
        	Long id = 0L;

			if(request.getParameter("userId") != null) {
				userId = Long.parseLong(request.getParameter("userId").toString());
			} 
			if(request.getParameter("photoId") != null) {
				photoId = Long.parseLong(request.getParameter("photoId").toString());
			} 
			if(request.getParameter("id") != null) {
				id = Long.parseLong(request.getParameter("id").toString());
			} 
			
        	String save = request.getParameter("save");
            
    		if(save != null) {
    		if(save.equals("addListing") && userId > 0) {
    			DirectoryCategoryInfoFormBean catagoryInfo = new DirectoryCategoryInfoFormBean();
    			
    			catagoryInfo.setCompanyName(request.getParameter("companyName").toString());
    			catagoryInfo.setContactName(request.getParameter("contactName").toString());
    			catagoryInfo.setMobile(Long.parseLong(request.getParameter("mobile").toString()));
    			catagoryInfo.setPhone(request.getParameter("phone"));
    			catagoryInfo.setEmail(request.getParameter("email").toString());
    			catagoryInfo.setWebsiteUrl(request.getParameter("websiteUrl"));
    			catagoryInfo.setCategoryId(Long.parseLong(request.getParameter("categoryId").toString()));
    			catagoryInfo.setPrice(Long.parseLong(request.getParameter("price").toString()));
    			catagoryInfo.setAddress(request.getParameter("address").toString());
    			catagoryInfo.setLocation(request.getParameter("location").toString());
    			catagoryInfo.setCityId(Long.parseLong(request.getParameter("cityId").toString()));
    			catagoryInfo.setStateId(Long.parseLong(request.getParameter("stateId").toString()));
    			catagoryInfo.setCountryId(Long.parseLong(request.getParameter("countryId").toString()));
    			catagoryInfo.setPincode(Long.parseLong(request.getParameter("pincode").toString()));
    			catagoryInfo.setDescription(request.getParameter("aboutUs"));
    			catagoryInfo.setLatitude(request.getParameter("mapUrl"));
    			catagoryInfo.setCategoryVideoUrl(request.getParameter("categoryVideoUrl"));
    			catagoryInfo.setCreatedBy(userId);
    			
    			try {
    				String companyExist = commonMasterService.checkCompanyNameAlredyExist(request.getParameter("companyName").toString());
    				if (companyExist != null) {
    					rootMap.put("status", "failure");
    					rootMap.put("message", companyExist+" This Company Name Alerady Exist.");
    					main.put("addListing", rootMap);
    					Gson gson = new GsonBuilder().setPrettyPrinting().create();
    		            String json = gson.toJson(main);
    		            
    		            response.setContentType("application/json;charset=utf-8");
    		            byte[] out = json.getBytes("UTF-8");
    		            response.setContentLength(out.length);
    		            response.getOutputStream().write(out);
    					return;
    				} else {
    				Long status = categoryInfoService
    						.saveCatagoryInfoDetails(catagoryInfo, photoId);
    				if (status != null) {
    					rootMap.put("status", "success");
    					rootMap.put("message", "Listing Added Successfully.");
    					main.put("addListing", rootMap);
    					Gson gson = new GsonBuilder().setPrettyPrinting().create();
    		            String json = gson.toJson(main);
    		            
    		            response.setContentType("application/json;charset=utf-8");
    		            byte[] out = json.getBytes("UTF-8");
    		            response.setContentLength(out.length);
    		            response.getOutputStream().write(out);
    					return;
    				} else {
    					rootMap.put("status", "failure");
    					rootMap.put("message", "Something went wrong. Please try again later.");
    					main.put("addListing", rootMap);
    					Gson gson = new GsonBuilder().setPrettyPrinting().create();
    		            String json = gson.toJson(main);
    		            
    		            response.setContentType("application/json;charset=utf-8");
    		            byte[] out = json.getBytes("UTF-8");
    		            response.setContentLength(out.length);
    		            response.getOutputStream().write(out);
    					return;
    				}
    				}
    			} catch (Exception e) {
    					rootMap.put("status", "failure");
    					rootMap.put("message", e);
    					main.put("listing", rootMap);
    					Gson gson = new GsonBuilder().setPrettyPrinting().create();
    			        String json = gson.toJson(main);
    			        
    			        response.setContentType("application/json;charset=utf-8");
    			        byte[] out = json.getBytes("UTF-8");
    			        response.setContentLength(out.length);
    			        response.getOutputStream().write(out);
    					return;
    		    }
    				
    		}
    		
    		if(save.equals("editListing") && userId > 0 && id > 0) {
    			DirectoryCategoryInfoFormBean catagoryInfo = new DirectoryCategoryInfoFormBean();
    			
    			catagoryInfo.setId(id);
    			catagoryInfo.setCompanyName(request.getParameter("companyName").toString());
    			catagoryInfo.setContactName(request.getParameter("contactName").toString());
    			catagoryInfo.setMobile(Long.parseLong(request.getParameter("mobile").toString()));
    			catagoryInfo.setPhone(request.getParameter("phone"));
    			catagoryInfo.setEmail(request.getParameter("email").toString());
    			catagoryInfo.setWebsiteUrl(request.getParameter("websiteUrl"));
    			catagoryInfo.setCategoryId(Long.parseLong(request.getParameter("categoryId").toString()));
    			catagoryInfo.setPrice(Long.parseLong(request.getParameter("price").toString()));
    			catagoryInfo.setAddress(request.getParameter("address").toString());
    			catagoryInfo.setLocation(request.getParameter("location").toString());
    			catagoryInfo.setCityId(Long.parseLong(request.getParameter("cityId").toString()));
    			catagoryInfo.setStateId(Long.parseLong(request.getParameter("stateId").toString()));
    			catagoryInfo.setCountryId(Long.parseLong(request.getParameter("countryId").toString()));
    			catagoryInfo.setPincode(Long.parseLong(request.getParameter("pincode").toString()));
    			catagoryInfo.setDescription(request.getParameter("aboutUs"));
    			catagoryInfo.setLatitude(request.getParameter("mapUrl"));
    			catagoryInfo.setCategoryVideoUrl(request.getParameter("categoryVideoUrl"));
    			catagoryInfo.setUpdatedBy(userId);
    			
    			try {

    				Session conn = HibernateUtil.getSessionFactory().openSession();
    				String oldCompanyName = null;
    				try {
    					List<?> categoryInfo = conn.getNamedQuery("getCategoryInfoByInfoId")
    							.setLong("id", id).list();
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
    				String companyExist = commonMasterService.checkOtherCompanyNameAlredyExist(request.getParameter("companyName").toString(), oldCompanyName);
    				if (companyExist != null) {
    					rootMap.put("status", "failure");
    					rootMap.put("message", companyExist+" This Company Name Alerady Exist.");
    					main.put("editListing", rootMap);
    					Gson gson = new GsonBuilder().setPrettyPrinting().create();
    		            String json = gson.toJson(main);
    		            
    		            response.setContentType("application/json;charset=utf-8");
    		            byte[] out = json.getBytes("UTF-8");
    		            response.setContentLength(out.length);
    		            response.getOutputStream().write(out);
    					return;
    				} else {
    				Long status = categoryInfoService
    						.saveCatagoryInfoDetails(catagoryInfo, photoId);
    				if (status != null) {
    					rootMap.put("status", "success");
    					rootMap.put("message", "Listing Updated Successfully.");
    					main.put("editListing", rootMap);
    					Gson gson = new GsonBuilder().setPrettyPrinting().create();
    		            String json = gson.toJson(main);
    		            
    		            response.setContentType("application/json;charset=utf-8");
    		            byte[] out = json.getBytes("UTF-8");
    		            response.setContentLength(out.length);
    		            response.getOutputStream().write(out);
    					return;
    				} else {
    					rootMap.put("status", "failure");
    					rootMap.put("message", "Something went wrong. Please try again later.");
    					main.put("editListing", rootMap);
    					Gson gson = new GsonBuilder().setPrettyPrinting().create();
    		            String json = gson.toJson(main);
    		            
    		            response.setContentType("application/json;charset=utf-8");
    		            byte[] out = json.getBytes("UTF-8");
    		            response.setContentLength(out.length);
    		            response.getOutputStream().write(out);
    					return;
    				}
    				}
    			} catch (Exception e) {
    					rootMap.put("status", "failure");
    					rootMap.put("message", e);
    					main.put("listing", rootMap);
    					Gson gson = new GsonBuilder().setPrettyPrinting().create();
    			        String json = gson.toJson(main);
    			        
    			        response.setContentType("application/json;charset=utf-8");
    			        byte[] out = json.getBytes("UTF-8");
    			        response.setContentLength(out.length);
    			        response.getOutputStream().write(out);
    					return;
    		    }
    				
    		}
    		
    		} else {
    			rootMap.put("status", "failure");
    			rootMap.put("message", "Missing Parameters.");
    			main.put("listing", rootMap);
    			Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	        String json = gson.toJson(main);
    	        
    	        response.setContentType("application/json;charset=utf-8");
    	        byte[] out = json.getBytes("UTF-8");
    	        response.setContentLength(out.length);
    	        response.getOutputStream().write(out);
    			return;
    		}

        } catch (Exception e) {
        	rootMap.put("status", "failure");
        	rootMap.put("message", e);
			main.put("listing", rootMap);
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
