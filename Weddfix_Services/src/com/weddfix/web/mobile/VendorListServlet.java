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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weddfix.web.formbean.DirectoryCategoryInfoFormBean;
import com.weddfix.web.services.CategoryInfoService;
import com.weddfix.web.services.CommonMasterService;

public class VendorListServlet extends HttpServlet {

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
        	
        	Map<Object, Object> categoryMap = commonMasterService.loadOrg();
			
			LinkedList<LinkedHashMap<String, Object>> categoryMapList = new LinkedList<LinkedHashMap<String, Object>>();
            Iterator<?> itrCategoryMap = categoryMap.entrySet().iterator();
            while (itrCategoryMap.hasNext()) {
                @SuppressWarnings({ "rawtypes" })
				Map.Entry pair = (Map.Entry)itrCategoryMap.next();
				LinkedHashMap<String, Object> categorysMap = new LinkedHashMap<String, Object>();
				categorysMap.put("id", pair.getKey());
				categorysMap.put("categoryName", pair.getValue());
				categoryMapList.add(categorysMap);
				itrCategoryMap.remove(); // avoids a ConcurrentModificationException
            }
            
			main.put("category", categoryMapList);
			
			Map<Object, Object> cityMap = commonMasterService.loadPaidVendorCity();
			
			LinkedList<LinkedHashMap<String, Object>> cityMapList = new LinkedList<LinkedHashMap<String, Object>>();
            Iterator<?> itrCityMap = cityMap.entrySet().iterator();
            while (itrCityMap.hasNext()) {
                @SuppressWarnings({ "rawtypes" })
				Map.Entry pair = (Map.Entry)itrCityMap.next();
				LinkedHashMap<String, Object> citysMap = new LinkedHashMap<String, Object>();
				citysMap.put("id", pair.getKey());
				citysMap.put("cityName", pair.getValue());
				cityMapList.add(citysMap);
				itrCityMap.remove(); // avoids a ConcurrentModificationException
            }
            
			main.put("city", cityMapList);
			
			Long userId = 0L;
			Long searchCategoryId = 0L;
			Long searchCityId = 0L;
			
			if(request.getParameter("userId") != null) {
				userId = Long.parseLong(request.getParameter("userId").toString());
			} 
			if(request.getParameter("searchCategoryId") != null) {
				searchCategoryId = Long.parseLong(request.getParameter("searchCategoryId").toString());
			} 
			if(request.getParameter("searchCityId") != null) {
				searchCityId = Long.parseLong(request.getParameter("searchCityId").toString());
			} 
			
			List<DirectoryCategoryInfoFormBean> categoryInfoList = null;
			
			if(searchCityId > 0) {
				categoryInfoList = categoryInfoService
						.searchCategoryByCity(searchCategoryId, searchCityId, userId);
			} else {
				categoryInfoList = categoryInfoService
						.searchByCategory(searchCategoryId, userId);
			}
        	
        	LinkedList<LinkedHashMap<String, Object>> categoryList = new LinkedList<LinkedHashMap<String, Object>>();
        	
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
				
				categoryList.add(map);
			}
			
			main.put("vendorServices", categoryList);
			
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
