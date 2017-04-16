package com.weddfix.web.mobile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weddfix.web.formbean.DirectoryCategoryInfoFormBean;
import com.weddfix.web.services.CategoryInfoService;

public class VendorMyListingServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final long MILLIS_IN_DAY = 60*60*24*1000;

	CategoryInfoService categoryInfoService = new CategoryInfoService();
	LinkedHashMap<String, Object> rootMap = new LinkedHashMap<String, Object>();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LinkedHashMap<String, Object> main = new LinkedHashMap<String, Object>();
		ResourceBundle rb = ResourceBundle.getBundle("ApplicationResources");
		
        try {
        	
        	Long userId = 0L;
        	Long deleteCategoryId = 0L;

			if(request.getParameter("userId") != null) {
				userId = Long.parseLong(request.getParameter("userId").toString());
			} 
			if(request.getParameter("deleteCategoryId") != null) {
				deleteCategoryId = Long.parseLong(request.getParameter("deleteCategoryId").toString());
			} 
			
			String delete = request.getParameter("delete");
			
			if(delete != null) {
			if(delete.equals("categoryInfo") && deleteCategoryId > 0) {
				Long status = categoryInfoService
						.deleteCategoryInfo(deleteCategoryId);
				
				if(status != null) {
					rootMap.put("status", "success");
					rootMap.put("message", "Category Info Deleted Successfully.");
					main.put("myListing", rootMap);
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
					main.put("myListing", rootMap);
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
				String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

			    Pattern compiledPattern = Pattern.compile(pattern);
			    Matcher matcher = null;
			    
			List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoService
						.loadVenueDetails(userId);
				
			LinkedList<LinkedHashMap<String, Object>> categoryList = new LinkedList<LinkedHashMap<String, Object>>();
		    
		    Iterator<?> itr = categoryInfoList.iterator();
			
		    while (itr.hasNext()) {

					Object[] obj = (Object[]) itr.next();
					LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
					map.put("id", obj[0]);
					map.put("companyName", obj[1]);
					map.put("address", obj[2]);
					map.put("location", obj[3]);
					map.put("cityName", obj[4]);
					map.put("stateName", obj[5]);
					map.put("countryName", obj[6]);
					map.put("pincode", obj[7]);
					map.put("price", obj[8]);
					if(obj[9] != null) {
						map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+obj[9]);
					} else {
						map.put("fileName", "");
					}
					if(obj[10] != null) {
						map.put("photoType", obj[10]);
					} else {
						map.put("photoType", "");
					}
					if(obj[11] != null) {
						map.put("categoryPictureId", obj[11]);
					} else {
						map.put("categoryPictureId", "");
					}
					if(obj[12] != null && !obj[12].equals("")) {
						matcher = compiledPattern.matcher(obj[12].toString());
						if(matcher.find()){
					    	map.put("categoryVideoUrl", "//www.youtube.com/embed/"+matcher.group());
					    } 
					} else {
				    	map.put("categoryVideoUrl", "");
				    }
					if(obj[13] != null) {
						map.put("categoryId", obj[13]);
					} else {
						map.put("categoryId", "");
					}
					if(obj[14] != null) {
						map.put("planName", obj[14]);
					} else {
						map.put("planName", "");
					}
					if(obj[15] != null) {
						map.put("amount", obj[15]);
					} else {
						map.put("amount", "");
					}
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
		        	map.put("validityDaysLeft", reminingDays);
					} else {
						map.put("validityDaysLeft", "");
					}
					
					categoryList.add(map);
				}
				
				main.put("myListing", categoryList);
				
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
				main.put("myListing", rootMap);
				
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
			main.put("myListing", rootMap);
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
