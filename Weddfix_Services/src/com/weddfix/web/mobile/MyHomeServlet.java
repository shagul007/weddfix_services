package com.weddfix.web.mobile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weddfix.web.formbean.MyPersonalDetailsFormBean;
import com.weddfix.web.services.RegisterService;

public class MyHomeServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final long MILLIS_IN_DAY = 60 * 60 * 24 * 1000;
	
	RegisterService registerService = new RegisterService();
	LinkedHashMap<String, Object> rootMap = new LinkedHashMap<String, Object>();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LinkedHashMap<String, Object> main = new LinkedHashMap<String, Object>();
		ResourceBundle rb = ResourceBundle.getBundle("ApplicationResources");
		
        try {
        	
        	Long userId = 0L;

			if(request.getParameter("userId") != null) {
				userId = Long.parseLong(request.getParameter("userId").toString());
			} 

			if(userId > 0) {
		    List<MyPersonalDetailsFormBean> myPersonalInfoDetails = registerService
					.loadMyDashBoardDetails(userId);
				
		    LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		    
		    Iterator<?> itr = myPersonalInfoDetails.iterator();
			
		    while (itr.hasNext()) {

					Object[] obj = (Object[]) itr.next();
					map.put("id",
							Long.parseLong(obj[0].toString()));
					map.put("fullName", obj[1].toString());
					if (obj[2] != null) {
						map.put("description", obj[2].toString());
					} else {
						map.put("description", "");
					}
					if (obj[3] != null) {
						map.put("weddingDateStr",
								obj[3].toString());
					} else {
						map.put("weddingDateStr", "");
					}
					if (obj[4] != null) {
						map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+obj[4].toString());
					} else {
						map.put("fileName", "");
					}
					map.put("planName", obj[5].toString());
					if (obj[5].toString().equalsIgnoreCase("FREE")) {
						DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
						Date cDate = null;
						try {
							cDate = dateFormat.parse(obj[6].toString());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						Date createdDate = DateUtils.addMonths(cDate, 3);
						// it's still active if the created date + 11 days is
						// greater than the current time
						int daysIntoTrial = Math
								.round(((createdDate.getTime() - System
										.currentTimeMillis()) / MILLIS_IN_DAY));

						int reminingDays = daysIntoTrial - 1;
						map.put("validityDaysLeft", reminingDays);
					} else {
						Date createdDate = DateUtils.addMonths(new Date(),
								Integer.valueOf(obj[7].toString()));
						// it's still active if the created date + 11 days is
						// greater than the current time
						int daysIntoTrial = Math
								.round(((createdDate.getTime() - System
										.currentTimeMillis()) / MILLIS_IN_DAY));

						int reminingDays = daysIntoTrial;
						map.put("validityDaysLeft", reminingDays);
					}
					if (obj[8] != null) {
						map.put("myListingTotalCount",
								Long.parseLong(obj[8].toString()));
					} else {
						map.put("myListingTotalCount", 0);
					}
					if (obj[9] != null) {
						map.put("myShortlistedTotalCount",
								Long.parseLong(obj[9].toString()));
					} else {
						map.put("myShortlistedTotalCount", 0);
					}
					if (obj[10] != null) {
						if (obj[10].toString().startsWith("-")) {
							map.put("weddingDaysLeft", null);
						} else {
							map.put("weddingDaysLeft", obj[10]
									.toString().split("\\.")[0]);
						}
					} else {
						map.put("weddingDaysLeft", "");
					}
					if (obj[11] != null) {
						map.put("myBookingTotalCount",
								Long.parseLong(obj[11].toString()));
					} else {
						map.put("myBookingTotalCount", "");
					}

				}
				
				main.put("myHome", map);
				
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
				main.put("myHome", rootMap);
				
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
			main.put("myHome", rootMap);
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
