package com.weddfix.web.mobile;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weddfix.web.formbean.DirectoryCategoryInfoFormBean;
import com.weddfix.web.services.CategoryInfoService;
import com.weddfix.web.util.CommonConstants;

public class VendorListByLocationServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CategoryInfoService categoryInfoService = new CategoryInfoService();
    LinkedHashMap<String, Object> rootMap = new LinkedHashMap<String, Object>();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LinkedHashMap<String, Object> main = new LinkedHashMap<String, Object>();
		ResourceBundle rb = ResourceBundle.getBundle("ApplicationResources");
		
        try {
        	
			Long userId = 0L;
			Long searchPincode = 0L;
			Long searchCityId = 0L;
			
			if(request.getParameter("userId") != null) {
				userId = Long.parseLong(request.getParameter("userId").toString());
			} 
			if(request.getParameter("searchCityId") != null) {
				searchCityId = Long.parseLong(request.getParameter("searchCityId").toString());
			} 
			if(request.getParameter("searchPincode") != null) {
				searchPincode = Long.parseLong(request.getParameter("searchPincode").toString());
			} 
			
			if(searchCityId > 0) {
	        	
	        	LinkedList<LinkedHashMap<String, Object>> weddingHallList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> studioList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> decorationList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> beautyParlourList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> jewelShopList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> cateringList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> entertainmentList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> weddingClotheList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> textileList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> travelList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> hotelList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> weddingAstrologerList = new LinkedList<LinkedHashMap<String, Object>>();
	        	
	        	List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoService
	        			.searchCategoryByCityId(searchCityId, userId);
	        	
				Iterator<?> itr = categoryInfoList.iterator();

				while (itr.hasNext()) {

					Object[] obj = (Object[]) itr.next();
					LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
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
						map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+obj[12]);
					} else {
						map.put("fileName", "");
					}
					if(obj[13] != null) {
						map.put("shortlisted", obj[13]);
					} else {
						map.put("shortlisted", "");
					}
					if(obj[14] != null) {
						map.put("shortlistedId", obj[14]);
					} else {
						map.put("shortlistedId", "");
					}
					
					if(Long.parseLong(obj[15].toString()) == CommonConstants.WEDDING_HALLS) {
						weddingHallList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.STUDIOS) {
						studioList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.DECORATIONS) {
						decorationList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.BEAUTY_PARLOURS) {
						beautyParlourList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.JEWEL_SHOPS) {
						jewelShopList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.CATERINGS) {
						cateringList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.ENTERTAINMENTS) {
						entertainmentList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.WEDDING_CLOTHES) {
						weddingClotheList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.TEXTILES) {
						textileList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.TRAVELS) {
						travelList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.HOTELS) {
						hotelList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.WEDDING_ASTROLOGERS) {
						weddingAstrologerList.add(map);
					}
					
				}
				
					rootMap.put("weddingHalls", weddingHallList);
					rootMap.put("studios", studioList);
					rootMap.put("decorations", decorationList);
					rootMap.put("beautyParlours", beautyParlourList);
					rootMap.put("jewelShops", jewelShopList);
					rootMap.put("caterings", cateringList);
					rootMap.put("entertainments", entertainmentList);
					rootMap.put("weddingClothes", weddingClotheList);
					rootMap.put("textiles", textileList);
					rootMap.put("travels", travelList);
					rootMap.put("hotels", hotelList);
					rootMap.put("weddingAstrologers", weddingAstrologerList);
					
					main.put("vendorServices", rootMap);
					
		            Gson gson = new GsonBuilder().setPrettyPrinting().create();
		            String json = gson.toJson(main);
		            
		            response.setContentType("application/json;charset=utf-8");
		            byte[] out = json.getBytes("UTF-8");
		            response.setContentLength(out.length);
		            response.getOutputStream().write(out);
		            return;
				}
			
			if(searchPincode > 0) {
	        	
	        	LinkedList<LinkedHashMap<String, Object>> weddingHallList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> studioList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> decorationList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> beautyParlourList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> jewelShopList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> cateringList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> entertainmentList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> weddingClotheList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> textileList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> travelList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> hotelList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> weddingAstrologerList = new LinkedList<LinkedHashMap<String, Object>>();
	        	
	        	List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoService
	        			.searchCategoryByLocation(searchPincode, userId);

	        	Iterator<?> itr = categoryInfoList.iterator();

				while (itr.hasNext()) {
	
					Object[] obj = (Object[]) itr.next();
					LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
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
						map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+obj[12]);
					} else {
						map.put("fileName", "");
					}
					if(obj[13] != null) {
						map.put("shortlisted", obj[13]);
					} else {
						map.put("shortlisted", "");
					}
					if(obj[14] != null) {
						map.put("shortlistedId", obj[14]);
					} else {
						map.put("shortlistedId", "");
					}
					
					if(Long.parseLong(obj[15].toString()) == CommonConstants.WEDDING_HALLS) {
						weddingHallList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.STUDIOS) {
						studioList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.DECORATIONS) {
						decorationList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.BEAUTY_PARLOURS) {
						beautyParlourList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.JEWEL_SHOPS) {
						jewelShopList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.CATERINGS) {
						cateringList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.ENTERTAINMENTS) {
						entertainmentList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.WEDDING_CLOTHES) {
						weddingClotheList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.TEXTILES) {
						textileList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.TRAVELS) {
						travelList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.HOTELS) {
						hotelList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.WEDDING_ASTROLOGERS) {
						weddingAstrologerList.add(map);
					}
					
				}
				
					rootMap.put("weddingHalls", weddingHallList);
					rootMap.put("studios", studioList);
					rootMap.put("decorations", decorationList);
					rootMap.put("beautyParlours", beautyParlourList);
					rootMap.put("jewelShops", jewelShopList);
					rootMap.put("caterings", cateringList);
					rootMap.put("entertainments", entertainmentList);
					rootMap.put("weddingClothes", weddingClotheList);
					rootMap.put("textiles", textileList);
					rootMap.put("travels", travelList);
					rootMap.put("hotels", hotelList);
					rootMap.put("weddingAstrologers", weddingAstrologerList);
					
					main.put("vendorServices", rootMap);
					
		            Gson gson = new GsonBuilder().setPrettyPrinting().create();
		            String json = gson.toJson(main);
		            
		            response.setContentType("application/json;charset=utf-8");
		            byte[] out = json.getBytes("UTF-8");
		            response.setContentLength(out.length);
		            response.getOutputStream().write(out);
		            return;
			} 
			
			if(searchCityId == 0) {
	        	
	        	LinkedList<LinkedHashMap<String, Object>> weddingHallList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> studioList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> decorationList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> beautyParlourList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> jewelShopList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> cateringList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> entertainmentList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> weddingClotheList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> textileList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> travelList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> hotelList = new LinkedList<LinkedHashMap<String, Object>>();
	        	LinkedList<LinkedHashMap<String, Object>> weddingAstrologerList = new LinkedList<LinkedHashMap<String, Object>>();
	        	
	        	List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoService
	        			.searchCategoryByUserId(userId);
	        	
				Iterator<?> itr = categoryInfoList.iterator();

				while (itr.hasNext()) {

					Object[] obj = (Object[]) itr.next();
					LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
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
						map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+obj[12]);
					} else {
						map.put("fileName", "");
					}
					if(obj[13] != null) {
						map.put("shortlisted", obj[13]);
					} else {
						map.put("shortlisted", "");
					}
					if(obj[14] != null) {
						map.put("shortlistedId", obj[14]);
					} else {
						map.put("shortlistedId", "");
					}
					
					if(Long.parseLong(obj[15].toString()) == CommonConstants.WEDDING_HALLS) {
						weddingHallList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.STUDIOS) {
						studioList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.DECORATIONS) {
						decorationList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.BEAUTY_PARLOURS) {
						beautyParlourList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.JEWEL_SHOPS) {
						jewelShopList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.CATERINGS) {
						cateringList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.ENTERTAINMENTS) {
						entertainmentList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.WEDDING_CLOTHES) {
						weddingClotheList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.TEXTILES) {
						textileList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.TRAVELS) {
						travelList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.HOTELS) {
						hotelList.add(map);
					} else if(Long.parseLong(obj[15].toString()) == CommonConstants.WEDDING_ASTROLOGERS) {
						weddingAstrologerList.add(map);
					}
					
				}
				
					rootMap.put("weddingHalls", weddingHallList);
					rootMap.put("studios", studioList);
					rootMap.put("decorations", decorationList);
					rootMap.put("beautyParlours", beautyParlourList);
					rootMap.put("jewelShops", jewelShopList);
					rootMap.put("caterings", cateringList);
					rootMap.put("entertainments", entertainmentList);
					rootMap.put("weddingClothes", weddingClotheList);
					rootMap.put("textiles", textileList);
					rootMap.put("travels", travelList);
					rootMap.put("hotels", hotelList);
					rootMap.put("weddingAstrologers", weddingAstrologerList);
					
					main.put("vendorServices", rootMap);
					
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
	        		main.put("vendorServices", rootMap);
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
		            String json = gson.toJson(main);
		            
		            response.setContentType("application/json;charset=utf-8");
		            byte[] out = json.getBytes("UTF-8");
		            response.getOutputStream().write(out);
					return;
			}
			
        } catch (Exception e) {
        	rootMap.put("status", "failure");
        	rootMap.put("message", e);
			main.put("vendorServices", rootMap);
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
