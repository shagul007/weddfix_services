package com.weddfix.web.mobile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weddfix.web.formbean.DirectoryCategoryInfoFormBean;
import com.weddfix.web.formbean.PhotoGalleryFormBean;
import com.weddfix.web.services.CategoryInfoService;
import com.weddfix.web.services.CommonMasterService;
import com.weddfix.web.util.CommonConstants;

public class HomeServlet extends HttpServlet {

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
        	Map<Object, Object> sortedMap = sortMapByValues(categoryMap);
			
			LinkedList<LinkedHashMap<String, Object>> categoryMapList = new LinkedList<LinkedHashMap<String, Object>>();
            Iterator<?> itrCategoryMap = sortedMap.entrySet().iterator();
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
        	
        	List<PhotoGalleryFormBean> bannerPhotoGalleryInfoList = categoryInfoService
					.loadBannerPhotoDetails();
        	
        	LinkedList<LinkedHashMap<String, Object>> categoryList = new LinkedList<LinkedHashMap<String, Object>>();
        	
			Iterator<?> itr = bannerPhotoGalleryInfoList.iterator();
			while (itr.hasNext()) {
		
				Object[] obj = (Object[]) itr.next();
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("orgId", obj[1]);
				if(obj[2] != null) {
					map.put("fileName", rb.getString("url")+"/ImageAction.action?imageId="+obj[2]);
				} else {
					map.put("fileName", "");
				}
				map.put("statusName", obj[3]);
		
				categoryList.add(map);
			}
			
			main.put("bannerPhotos", categoryList);
					
        	List<DirectoryCategoryInfoFormBean> categoryInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.WEDDING_HALLS, rb.getString("home.image.limit"));
        	
        	categoryList = new LinkedList<LinkedHashMap<String, Object>>();
        	
			itr = categoryInfoList.iterator();
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

				categoryList.add(map);
			}
			
			main.put("weddingHalls", categoryList);
			
			categoryInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.STUDIOS, rb.getString("home.image.limit"));
        	
        	categoryList = new LinkedList<LinkedHashMap<String, Object>>();
        	
			itr = categoryInfoList.iterator();
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

				categoryList.add(map);
			}
			
			main.put("studios", categoryList);
			
			categoryInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.DECORATIONS, rb.getString("home.image.limit"));
        	
        	categoryList = new LinkedList<LinkedHashMap<String, Object>>();
        	
			itr = categoryInfoList.iterator();
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

				categoryList.add(map);
			}
			
			main.put("decorations", categoryList);
			
			categoryInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.BEAUTY_PARLOURS, rb.getString("home.image.limit"));
        	
        	categoryList = new LinkedList<LinkedHashMap<String, Object>>();
        	
			itr = categoryInfoList.iterator();
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

				categoryList.add(map);
			}
			
			main.put("beautyParlours", categoryList);
			
			categoryInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.JEWEL_SHOPS, rb.getString("home.image.limit"));
        	
        	categoryList = new LinkedList<LinkedHashMap<String, Object>>();
        	
			itr = categoryInfoList.iterator();
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

				categoryList.add(map);
			}
			
			main.put("jewelShops", categoryList);
			
			categoryInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.CATERINGS, rb.getString("home.image.limit"));
        	
        	categoryList = new LinkedList<LinkedHashMap<String, Object>>();
        	
			itr = categoryInfoList.iterator();
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

				categoryList.add(map);
			}
			
			main.put("caterings", categoryList);
			
			categoryInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.ENTERTAINMENTS, rb.getString("home.image.limit"));
        	
        	categoryList = new LinkedList<LinkedHashMap<String, Object>>();
        	
			itr = categoryInfoList.iterator();
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

				categoryList.add(map);
			}
			
			main.put("entertainments", categoryList);
			
			categoryInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.WEDDING_CLOTHES, rb.getString("home.image.limit"));
        	
        	categoryList = new LinkedList<LinkedHashMap<String, Object>>();
        	
			itr = categoryInfoList.iterator();
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

				categoryList.add(map);
			}
			
			main.put("weddingClothes", categoryList);
            
			categoryInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.TEXTILES, rb.getString("home.image.limit"));
        	
        	categoryList = new LinkedList<LinkedHashMap<String, Object>>();
        	
			itr = categoryInfoList.iterator();
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

				categoryList.add(map);
			}
			
			main.put("textiles", categoryList);
			
			categoryInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.TRAVELS, rb.getString("home.image.limit"));
        	
        	categoryList = new LinkedList<LinkedHashMap<String, Object>>();
        	
			itr = categoryInfoList.iterator();
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

				categoryList.add(map);
			}
			
			main.put("travels", categoryList);
			
			categoryInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.HOTELS, rb.getString("home.image.limit"));
        	
        	categoryList = new LinkedList<LinkedHashMap<String, Object>>();
        	
			itr = categoryInfoList.iterator();
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

				categoryList.add(map);
			}
			
			main.put("hotels", categoryList);
			
			categoryInfoList = categoryInfoService
					.loadAllClientInfos(CommonConstants.WEDDING_ASTROLOGERS, rb.getString("home.image.limit"));
        	
        	categoryList = new LinkedList<LinkedHashMap<String, Object>>();
        	
			itr = categoryInfoList.iterator();
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

				categoryList.add(map);
			}
			
			main.put("weddingAstrologers", categoryList);
			
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
			main.put("home", rootMap);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(main);
            
            response.setContentType("application/json;charset=utf-8");
            byte[] out = json.getBytes("UTF-8");
            response.setContentLength(out.length);
            response.getOutputStream().write(out);
			return;
        }
        
    }
    
    private static Map<Object, Object> sortMapByValues(Map<Object, Object> Map2) {
        
        Set<Entry<Object, Object>> mapEntries = Map2.entrySet();
        
        /*System.out.println("Values and Keys before sorting ");
        for(Entry<Object, Object> entry : mapEntries) {
            System.out.println(entry.getValue() + " - "+ entry.getKey());
        }*/
        
        // used linked list to sort, because insertion of elements in linked list is faster than an array list. 
        List<Entry<Object, Object>> aList = new LinkedList<Entry<Object, Object>>(mapEntries);

        // sorting the List
        Collections.sort(aList, new Comparator<Entry<Object, Object>>() {

            public int compare(Entry<Object, Object> ele1,
                    Entry<Object, Object> ele2) {
                
                return ((BigInteger) ele1.getKey()).compareTo((BigInteger) ele2.getKey());
            }
        });
        
        // Storing the list into Linked HashMap to preserve the order of insertion. 
        Map<Object, Object> aMap2 = new LinkedHashMap<Object, Object>();
        for(Entry<Object, Object> entry: aList) {
            aMap2.put(entry.getKey(), entry.getValue());
        }
        
        // printing values after soring of map
        /*System.out.println("Value " + " - " + "Key");
        for(Entry<Object, Object> entry : aMap2.entrySet()) {
            System.out.println(entry.getValue() + " - " + entry.getKey());
        }*/
		return aMap2;
        
    }

}
