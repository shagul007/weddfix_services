package com.weddfix.web.mobile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weddfix.web.formbean.DirectoryCartDetailsFormBean;
import com.weddfix.web.formbean.DirectoryCategoryInfoAccountDetailsFormBean;
import com.weddfix.web.formbean.DirectoryCategoryInfoFormBean;
import com.weddfix.web.formbean.DirectoryPromotionDetailsFormBean;
import com.weddfix.web.formbean.DirectoryUpgradePlanFormBean;
import com.weddfix.web.services.CategoryInfoService;
import com.weddfix.web.services.CommonMasterService;
import com.weddfix.web.services.RegisterService;
import com.weddfix.web.util.CommonConstants;
import com.weddfix.web.util.HibernateUtil;
import com.weddfix.web.util.MailMessage;

public class UpgradeServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	RegisterService registerService = new RegisterService();
	CommonMasterService commonMasterService = new CommonMasterService();
	CategoryInfoService categoryInfoService = new CategoryInfoService();
	DirectoryCartDetailsFormBean cartDetailsFormBean = new DirectoryCartDetailsFormBean();
	LinkedHashMap<String, Object> rootMap = new LinkedHashMap<String, Object>();
	
	Session conn;
	
	private static final Logger logger = Logger
			.getLogger(UpgradeServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LinkedHashMap<String, Object> main = new LinkedHashMap<String, Object>();
		ResourceBundle rb = ResourceBundle.getBundle("ApplicationResources");
		
		Long userId = 0L;
		Long planId = 0L;
		Long categoryInfoId = 0L;
		String promoCode = null;
		
		if(request.getParameter("userId") != null) {
			userId = Long.parseLong(request.getParameter("userId").toString());
		}
		
		if(request.getParameter("planId") != null) {
			planId = Long.parseLong(request.getParameter("planId").toString());
		}
		
		if(request.getParameter("promoCode") != null) {
			promoCode = request.getParameter("promoCode").toString();
		}
		
		if(request.getParameter("categoryInfoId") != null) {
			categoryInfoId = Long.parseLong(request.getParameter("categoryInfoId").toString());
		}
		
        String find = request.getParameter("find");
        String save = request.getParameter("save");
        String validate = request.getParameter("validate");
        
        if(find != null) {
        	if(find.equals("cart") && categoryInfoId > 0 && userId > 0) {
        		
        		DirectoryCategoryInfoFormBean categoryInfo = categoryInfoService
						.loadEditVenueDetails(categoryInfoId);
		        
		        LinkedHashMap<String, Object> map= new LinkedHashMap<String, Object>();
				
		        map.put("id",
						categoryInfo.getId());
		        map.put("companyName", categoryInfo.getCompanyName());
		        map.put("price", categoryInfo.getPrice());
		        if (categoryInfo.getFileName() != null) {
					map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+categoryInfo.getFileName());
				} else {
					map.put("fileName", "");
				}
				
				main.put("categoryInfo", map);
        		
        		List<DirectoryCartDetailsFormBean> cartDetailsInfoList = commonMasterService
        				.loadCartDetails(userId);
        		
        		LinkedHashMap<String, Object> cartDetailsMap = new LinkedHashMap<String, Object>();
        		
        		Iterator<?> itr = cartDetailsInfoList.iterator();
				while (itr.hasNext()) {

					Object[] obj = (Object[]) itr.next();
					cartDetailsMap.put("id", obj[0]);
					cartDetailsMap.put("planId", obj[1]);
					cartDetailsMap.put("planName", obj[2]);
					cartDetailsMap.put("planType", obj[3]);
					cartDetailsMap.put("amount", obj[4]);
					cartDetailsMap.put("validity", obj[5]);
					cartDetailsMap.put("status", obj[6]);

			}
				
				main.put("cartDetails", cartDetailsMap);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
	            String json = gson.toJson(main);
	            
	            response.setContentType("application/json;charset=utf-8");
	            byte[] out = json.getBytes("UTF-8");
	            response.setContentLength(out.length);
	            response.getOutputStream().write(out);
	            
				return;
        	}
        }
        
        if(validate != null) {
        	if(validate.equals("promoCode") && promoCode != null) {
        		
        		List<DirectoryPromotionDetailsFormBean> promotionDetailsInfoList = commonMasterService
    					.validatePromoCode(promoCode);
        		
        		LinkedHashMap<String, Object> promotionDetailsMap = new LinkedHashMap<String, Object>();
        		
        		Iterator<?> itr = promotionDetailsInfoList.iterator();
				while (itr.hasNext()) {

					Object[] obj = (Object[]) itr.next();
					promotionDetailsMap.put("id", obj[0]);
					promotionDetailsMap.put("promoCode", obj[1]);
					promotionDetailsMap.put("expires", obj[2]);
					promotionDetailsMap.put("discount", obj[3]);
					if(obj[4] != null) {
						promotionDetailsMap.put("email", obj[4]);
					} else {
						promotionDetailsMap.put("email", "");
					}
					if(obj[5] != null) {
						promotionDetailsMap.put("sent", obj[5]);
					} else {
						promotionDetailsMap.put("sent", "");
					}
					promotionDetailsMap.put("status", obj[6]);
					if(obj[7] != null) {
						promotionDetailsMap.put("userId", obj[7]);
					} else {
						promotionDetailsMap.put("userId", "");
					}
					if(obj[8] != null) {
						promotionDetailsMap.put("sentTo", obj[8]);
					} else {
						promotionDetailsMap.put("sentTo", "");
					}
					if(obj[7] != null && obj[8] == null) {
						promotionDetailsMap.put("alreadyUsedPromoCode", true);
					} else {
						promotionDetailsMap.put("alreadyUsedPromoCode", false);
					}

			}
				
				main.put("promoDetails", promotionDetailsMap);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
	            String json = gson.toJson(main);
	            
	            response.setContentType("application/json;charset=utf-8");
	            byte[] out = json.getBytes("UTF-8");
	            response.setContentLength(out.length);
	            response.getOutputStream().write(out);
	            
				return;
        	}
        }
        if(save != null) {
        	if(save.equals("cart") && categoryInfoId > 0 && userId > 0 && planId > 0) {
        		
        		List<DirectoryCartDetailsFormBean> cartDetailsList = commonMasterService
        				.loadCartDetails(userId);
        		if(!cartDetailsList.isEmpty()) {
        		Iterator<?> itr = cartDetailsList.iterator();
        		while (itr.hasNext()) {

        			Object[] obj = (Object[]) itr.next();
        			
        			if(Long.parseLong(obj[1].toString()) != planId) {
        				
        				cartDetailsFormBean.setId(Long.parseLong(obj[0].toString()));
        				cartDetailsFormBean.setPlanId(planId);
        				cartDetailsFormBean.setUpdatedBy(userId);
        				Long status = commonMasterService
        						.saveCartDetails(cartDetailsFormBean);
        				if (status != null) {
        					
        					DirectoryCategoryInfoFormBean categoryInfo = categoryInfoService
        							.loadEditVenueDetails(categoryInfoId);
        			        
        			        LinkedHashMap<String, Object> map= new LinkedHashMap<String, Object>();
        					
        			        map.put("id",
        							categoryInfo.getId());
        			        map.put("companyName", categoryInfo.getCompanyName());
        			        map.put("price", categoryInfo.getPrice());
        			        if (categoryInfo.getFileName() != null) {
        						map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+categoryInfo.getFileName());
        					} else {
        						map.put("fileName", "");
        					}
        					
        					main.put("categoryInfo", map);
        					
        					List<DirectoryCartDetailsFormBean> cartDetailsInfoList = commonMasterService
        	        				.loadCartDetails(userId);
            				
            				LinkedHashMap<String, Object> cartDetailsMap = new LinkedHashMap<String, Object>();
        	        		
        	        		Iterator<?> itr1 = cartDetailsInfoList.iterator();
        					while (itr1.hasNext()) {

        						Object[] obj1 = (Object[]) itr1.next();
        						cartDetailsMap.put("id", obj1[0]);
        						cartDetailsMap.put("planId", obj1[1]);
        						cartDetailsMap.put("planName", obj1[2]);
        						cartDetailsMap.put("planType", obj1[3]);
        						cartDetailsMap.put("amount", obj1[4]);
        						cartDetailsMap.put("validity", obj1[5]);
        						cartDetailsMap.put("status", obj1[6]);

        					}
        					
        					main.put("cartDetails", cartDetailsMap);
        					
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
        					main.put("notInterested", rootMap);
        					Gson gson = new GsonBuilder().setPrettyPrinting().create();
        		            String json = gson.toJson(main);
        		            
        		            response.setContentType("application/json;charset=utf-8");
        		            byte[] out = json.getBytes("UTF-8");
        		            response.setContentLength(out.length);
        		            response.getOutputStream().write(out);
        					return;
            		}
        		} else {
        			
        			DirectoryCategoryInfoFormBean categoryInfo = categoryInfoService
        					.loadEditVenueDetails(categoryInfoId);
        	        
        	        LinkedHashMap<String, Object> map= new LinkedHashMap<String, Object>();
        			
        	        map.put("id",
        					categoryInfo.getId());
        	        map.put("companyName", categoryInfo.getCompanyName());
        	        map.put("price", categoryInfo.getPrice());
        	        if (categoryInfo.getFileName() != null) {
        				map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+categoryInfo.getFileName());
        			} else {
        				map.put("fileName", "");
        			}
        			
        			main.put("categoryInfo", map);
        			
        			List<DirectoryCartDetailsFormBean> cartDetailsInfoList = commonMasterService
	        				.loadCartDetails(userId);
    				
    				LinkedHashMap<String, Object> cartDetailsMap = new LinkedHashMap<String, Object>();
	        		
	        		Iterator<?> itr2 = cartDetailsInfoList.iterator();
					while (itr2.hasNext()) {

						Object[] obj2 = (Object[]) itr2.next();
						cartDetailsMap.put("id", obj2[0]);
						cartDetailsMap.put("planId", obj2[1]);
						cartDetailsMap.put("planName", obj2[2]);
						cartDetailsMap.put("planType", obj2[3]);
						cartDetailsMap.put("amount", obj2[4]);
						cartDetailsMap.put("validity", obj2[5]);
						cartDetailsMap.put("status", obj2[6]);

					}
					
					main.put("cartDetails", cartDetailsMap);
					
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
		            String json = gson.toJson(main);
		            
		            response.setContentType("application/json;charset=utf-8");
		            byte[] out = json.getBytes("UTF-8");
		            response.setContentLength(out.length);
		            response.getOutputStream().write(out);
					return;
        			
        		}}} else {
            		cartDetailsFormBean.setUserId(userId);
            		cartDetailsFormBean.setPlanId(planId);
            		cartDetailsFormBean.setPlanType("UPGRADE");
            		cartDetailsFormBean.setCreatedBy(userId);

        			Long status = commonMasterService
        					.saveCartDetails(cartDetailsFormBean);
        			
        			if (status != null) {
        				
        				DirectoryCategoryInfoFormBean categoryInfo = categoryInfoService
        						.loadEditVenueDetails(categoryInfoId);
        		        
        		        LinkedHashMap<String, Object> map= new LinkedHashMap<String, Object>();
        				
        		        map.put("id",
        						categoryInfo.getId());
        		        map.put("companyName", categoryInfo.getCompanyName());
        		        map.put("price", categoryInfo.getPrice());
        		        if (categoryInfo.getFileName() != null) {
        					map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+categoryInfo.getFileName());
        				} else {
        					map.put("fileName", "");
        				}
        				
        				main.put("categoryInfo", map);
        				
        				List<DirectoryCartDetailsFormBean> cartDetailsInfoList = commonMasterService
    	        				.loadCartDetails(userId);
        				
        				LinkedHashMap<String, Object> cartDetailsMap = new LinkedHashMap<String, Object>();
    	        		
    	        		Iterator<?> itr2 = cartDetailsInfoList.iterator();
    					while (itr2.hasNext()) {

    						Object[] obj2 = (Object[]) itr2.next();
    						cartDetailsMap.put("id", obj2[0]);
    						cartDetailsMap.put("planId", obj2[1]);
    						cartDetailsMap.put("planName", obj2[2]);
    						cartDetailsMap.put("planType", obj2[3]);
    						cartDetailsMap.put("amount", obj2[4]);
    						cartDetailsMap.put("validity", obj2[5]);
    						cartDetailsMap.put("status", obj2[6]);

    					}
    					
    					main.put("cartDetails", cartDetailsMap);
    					
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
    					main.put("notInterested", rootMap);
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
        }
        
        if(categoryInfoId > 0) {
        DirectoryCategoryInfoFormBean categoryInfo = categoryInfoService
				.loadEditVenueDetails(categoryInfoId);
        
        LinkedHashMap<String, Object> map= new LinkedHashMap<String, Object>();
		
        map.put("id",
				categoryInfo.getId());
        map.put("companyName", categoryInfo.getCompanyName());
        map.put("price", categoryInfo.getPrice());
        if (categoryInfo.getFileName() != null) {
			map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+categoryInfo.getFileName());
		} else {
			map.put("fileName", "");
		}
		
		main.put("categoryInfo", map);
        
        List<DirectoryUpgradePlanFormBean> upgradePlanInfoList = registerService
				.loadUpgradePlanDetails();
				
		LinkedList<LinkedHashMap<String, Object>> upgradePlanList = new LinkedList<LinkedHashMap<String, Object>>();
		
		Iterator<?> itr = upgradePlanInfoList.iterator();
		while (itr.hasNext()) {

			Object[] obj = (Object[]) itr.next();
			map = new LinkedHashMap<String, Object>();
			map.put("id", obj[4]);
			map.put("planName", obj[0]);
			map.put("amount", obj[1]);
			if(Long.parseLong(obj[2].toString()) > 1 && Long.parseLong(obj[2].toString()) < 11) {
				map.put("validity", obj[2]+" Months");
			} else if(Long.parseLong(obj[2].toString()) > 11 && Long.parseLong(obj[2].toString()) < 23) {
				int months = Integer.valueOf(obj[2].toString());
				int years = months / 12; // 1
				int remainingMonths = months % 12; // 6
				if(remainingMonths == 0) {
					map.put("validity", years+" Year");
				} else if(remainingMonths > 0 && remainingMonths < 2) {
					map.put("validity", years+" Year"+remainingMonths+" Month");
				} else {
					map.put("validity", years+" Year"+remainingMonths+" Months");
				}
			} else if(Long.parseLong(obj[2].toString()) > 23) {
				int months = Integer.valueOf(obj[2].toString());
				int years = months / 12; // 1
				int remainingMonths = months % 12; // 6
				if(remainingMonths == 0) {
					map.put("validity", years+" Years");
				} else if(remainingMonths > 0 && remainingMonths < 2) {
					map.put("validity", years+" Years"+remainingMonths+" Month");
				} else {
					map.put("validity", years+" Years"+remainingMonths+" Months");
				}
			} else {
				map.put("validity", obj[2]+" Month");
			}
			map.put("getSMSAlert", Boolean.valueOf(obj[3].toString()));

			upgradePlanList.add(map);
		}
		
			main.put("upgradePlans", upgradePlanList);
			
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
			main.put("upgradePlans", rootMap);
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
		ResourceBundle rb = ResourceBundle.getBundle("ApplicationResources");
		
		Long userId = 0L;
		Long categoryInfoId = 0L;
		Long myPlanId = 0L;
		String txnId = null;
		String acceptedPromoCode = null;
		
		if(request.getParameter("userId") != null) {
			userId = Long.parseLong(request.getParameter("userId").toString());
		}
		
		if(request.getParameter("categoryInfoId") != null) {
			categoryInfoId = Long.parseLong(request.getParameter("categoryInfoId").toString());
		}
		
		if(request.getParameter("myPlanId") != null) {
			myPlanId = Long.parseLong(request.getParameter("myPlanId").toString());
		}
		
		if(request.getParameter("txnId") != null) {
			txnId = request.getParameter("txnId").toString();
		}
		
		if(request.getParameter("acceptedPromoCode") != null) {
			acceptedPromoCode = request.getParameter("acceptedPromoCode").toString();
		}
		
        String save = request.getParameter("save");
		
		if(save != null) {
        	if(save.equals("payment") && txnId != null && myPlanId > 0 && userId > 0 && categoryInfoId > 0) {
        		try {
        		conn = HibernateUtil.getSessionFactory().openSession();
    			
    			logger.info("-----------Update account details Method--------------");
    			
    			Transaction tx = conn.beginTransaction();

    			DirectoryCategoryInfoAccountDetailsFormBean categoryInfoAccountDetailsFormBean = new DirectoryCategoryInfoAccountDetailsFormBean(); 

				categoryInfoAccountDetailsFormBean.setUserId(userId);
				categoryInfoAccountDetailsFormBean.setCategoryInfoId(categoryInfoId);
				categoryInfoAccountDetailsFormBean.setAccountType(myPlanId);
				categoryInfoAccountDetailsFormBean.setTransactionId(txnId);
				categoryInfoAccountDetailsFormBean.setTransactionStatus(CommonConstants.SUCCESS);
				categoryInfoAccountDetailsFormBean.setStatusId(CommonConstants.ACTIVE);
				categoryInfoAccountDetailsFormBean.setCreatedBy(userId);
				categoryInfoAccountDetailsFormBean.setCreatedDate(new Date());
				Long id = (Long) conn.save(categoryInfoAccountDetailsFormBean);

		        conn.getNamedQuery("deleteCartDetailsByUserId")
		        .setLong("userId", userId).executeUpdate();
		        if(acceptedPromoCode != null) {
			        conn.getNamedQuery("updateAcceptedPromoDetailsByUserId")
			        .setString("acceptedPromoCode", acceptedPromoCode)
			        .setLong("userId", userId)
			        .setDate("acceptedDate", new Date())
			        .setLong("updatedBy", userId)
			        .setDate("updatedDate", new Date()).executeUpdate();
		        }
    		        
    		        tx.commit();
    		        
    		        conn = HibernateUtil.getSessionFactory().openSession();
    				List<?> invoiceDetails = conn.getNamedQuery("getInvoiceDetailsById").setLong("id", id).setString("promocode", acceptedPromoCode).list();
    				Iterator<?> itr = invoiceDetails.iterator();
    				while (itr.hasNext()) {
    					Object[] obj = (Object[]) itr.next();
    					Properties props = new Properties();
    			        props.put("no", obj[0].toString());
    		            props.put("orderdate", obj[1].toString());
    		            props.put("billedto", obj[2].toString());
    		            props.put("transactionno", obj[3].toString());
    		            props.put("company", obj[4].toString());
    		            props.put("itemname", obj[5].toString());
    		            props.put("price", obj[6].toString());
    		            if(obj[7] != null) {
    		            	java.math.BigDecimal price = new BigDecimal(obj[6].toString());
    		            	java.math.BigDecimal discount = new BigDecimal(obj[7].toString());
    		        		java.math.BigDecimal pricePercentage = price.multiply(discount);
    		        		java.math.BigDecimal priceDivHundred = pricePercentage.divide(new BigDecimal("100"));
    		        		java.math.BigDecimal total = price.subtract(priceDivHundred);
    		        		props.put("discount", "-"+priceDivHundred);
    		            	props.put("total", total.toString());
    		            } else {
    		            	props.put("discount", "0");
    		            	props.put("total", obj[6].toString());
    		            }
    		            
    		            MailMessage msg = new MailMessage(props, "invoice.vm", obj[8].toString(), 
    		            		"Weddfix Invoice");

    					msg.send();
    					
    					msg = new MailMessage(props, "invoice.vm", rb.getString("contact.admin.email"), 
    		            		"Weddfix Invoice");

    					msg.send();
    				}
    		        
        		} catch (Exception e) {

        			logger.error("Exception ocured while inserting data into database");
        			rootMap.put("status", "failure");
    				rootMap.put("message", e);
    				main.put("payment", rootMap);
    				Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	            String json = gson.toJson(main);
    	            
    	            response.setContentType("application/json;charset=utf-8");
    	            byte[] out = json.getBytes("UTF-8");
    	            response.setContentLength(out.length);
    	            response.getOutputStream().write(out);
    				return;

        		} finally {
        			conn.flush();
        			conn.close();
        		}
    			
    			rootMap.put("status", "success");
				rootMap.put("message", "Payment Details Inserted Successfully.");
				main.put("payment", rootMap);
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
				main.put("payment", rootMap);
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
}
	
