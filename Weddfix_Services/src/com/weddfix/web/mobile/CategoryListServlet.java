package com.weddfix.web.mobile;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weddfix.web.services.CommonMasterService;

public class CategoryListServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CommonMasterService commonMasterService = new CommonMasterService();
    LinkedHashMap<String, Object> rootMap = new LinkedHashMap<String, Object>();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LinkedHashMap<String, Object> main = new LinkedHashMap<String, Object>();
		
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
			main.put("CategoryList", rootMap);
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
