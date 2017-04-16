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
import com.weddfix.web.formbean.DirectoryCategoryShortlistedFormBean;
import com.weddfix.web.services.CategoryInfoService;

public class MyWishListServlet extends HttpServlet {

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
        	Long shortlistedId = 0L;
        	Long shortlistedVendorId = 0L;

			if(request.getParameter("userId") != null) {
				userId = Long.parseLong(request.getParameter("userId").toString());
			} 
			if(request.getParameter("shortlistedId") != null) {
				shortlistedId = Long.parseLong(request.getParameter("shortlistedId").toString());
			} 
			if(request.getParameter("shortlistedVendorId") != null) {
				shortlistedVendorId = Long.parseLong(request.getParameter("shortlistedVendorId").toString());
			} 
			
			String save = request.getParameter("save");

			if(save != null) {
				if(save.equals("shortlist") && shortlistedVendorId > 0 && userId > 0) {
					DirectoryCategoryShortlistedFormBean catagoryShortlistedFormBean = new DirectoryCategoryShortlistedFormBean();
					
					catagoryShortlistedFormBean.setCreatedBy(userId);
					catagoryShortlistedFormBean.setVendorId(shortlistedVendorId);
					catagoryShortlistedFormBean.setUserId(userId);
					catagoryShortlistedFormBean.setShortlisted(true);
					Long shortlistId = categoryInfoService
							.saveShortlistDetails(catagoryShortlistedFormBean);
					
					if (shortlistId != null) {
							rootMap.put("status", "success");
			    			rootMap.put("message", "shortlisted Successfully.");
			    			rootMap.put("shortlistedVendorId", shortlistedVendorId);
			    			rootMap.put("shortlistedId", shortlistId);
							main.put("shortlist", rootMap);
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
							main.put("shortlist", rootMap);
							Gson gson = new GsonBuilder().setPrettyPrinting().create();
				            String json = gson.toJson(main);
				            
				            response.setContentType("application/json;charset=utf-8");
				            byte[] out = json.getBytes("UTF-8");
				            response.setContentLength(out.length);
				            response.getOutputStream().write(out);
							return;
						}
						}
				if(save.equals("unShortlist") && shortlistedId > 0) {
					Long status = categoryInfoService
							.deleteShortlistDetails(shortlistedId);
					
					if (status != null) {
							rootMap.put("status", "success");
			    			rootMap.put("message", "UnShortlisted Successfully.");
							main.put("unShortlist", rootMap);
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
							main.put("unShortlist", rootMap);
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
			if(userId > 0) {
			List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoService
						.loadMyWishlistDetails(userId);
				
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
						map.put("shortlistedTotalCount", obj[13]);
					} else {
						map.put("shortlistedTotalCount", 0);
					}
					if(obj[14] != null) {
						map.put("shortlistedId", obj[14]);
					} else {
						map.put("shortlistedId", "");
					}
					
					categoryList.add(map);
				}
				
				main.put("myWishList", categoryList);
				
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
				main.put("myWishList", rootMap);
				
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
			main.put("myWishList", rootMap);
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
