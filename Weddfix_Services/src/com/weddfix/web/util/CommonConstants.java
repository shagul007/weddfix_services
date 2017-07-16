package com.weddfix.web.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Date;

public class CommonConstants {

	public static boolean TESTING_MODE = false;
	public static final String URL = "http://localhost:8080";
//	public static final String URL = "https://weddfix.com";
	public static final String SUCCESS = "SUCCESS";
	public static final String ADMIN_NAME = "SHAGUL";
	public static final String ADMIN = "ADMIN";
	public static final String USER_DESC = "USER";
	public static final String VENDOR_DESC = "VENDORADMIN";
	public static final String VENDORADMIN = "VENDORADMIN";
	public static final Long ZERO = 0l;
	public static final Long FREE_ID = 1l;
	public static final Long USER_ROLE = 3l;
	public static final Long VENDOR_ROLE = 4l;
	public static final String ACTIVE_STR = "ACTIVE";
	public static final String INACTIVE_STR = "INACTIVE";
	public static final Long ACTIVE = 1l;
	public static final Long INACTIVE = 2l;
	public static final Long WEDDING_HALLS = 1l;
	public static final Long STUDIOS = 2l;
	public static final Long DECORATIONS = 3l;
	public static final Long BEAUTY_PARLOURS = 4l;
	public static final Long JEWEL_SHOPS = 5l;
	public static final Long CATERINGS = 6l;
	public static final Long ENTERTAINMENTS = 7l;
	public static final Long WEDDING_CLOTHES = 8l;
	public static final Long WEDDING_CARDS = 9l;
	public static final Long TRAVELS = 10l;
	public static final Long HOTELS = 11l;
	public static final Long WEDDING_ASTROLOGERS = 12l;
	public static final String WEDDING_HALLS_STR = "wedding_halls";
	public static final String STUDIOS_STR = "studios";
	public static final String DECORATIONS_STR = "decorations";
	public static final String BEAUTY_PARLOURS_STR = "beauty_parlours";
	public static final String JEWEL_SHOPS_STR = "jewel_shops";
	public static final String CATERINGS_STR = "caterings";
	public static final String ENTERTAINMENTS_STR = "entertainments";
	public static final String WEDDING_CLOTHES_STR = "wedding_clothes";
	public static final String WEDDING_CARDS_STR = "wedding_cards";
	public static final String TRAVELS_STR = "travels";
	public static final String HOTELS_STR = "hotels";
	public static final String WEDDING_ASTROLOGERS_STR = "wedding_astrologers";

	public static final Date CURRENTDATE = new java.util.Date(
			System.currentTimeMillis());
	public static final String GUEST = "GUEST";

	// constants for URLPath
	public static String UploadUrlPath = "UPLOADURL001";

	public static String generateEncryptedPwd(String pwd) {

		try {

			MessageDigest md5;
			md5 = MessageDigest.getInstance("MD5");
			md5.update(pwd.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			return hash.toString(16);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Timestamp getTimeStamp() {
		Timestamp currentTimeStamp = new Timestamp(
				new java.util.Date().getTime());
		return currentTimeStamp;
	}

	public static Date getCurrentTimeMillis() {
		return new java.util.Date(System.currentTimeMillis());

	}
	
	public enum LoginReason {
	    admin ("Please log in using an admin account"),
	    login ("Please log in first");

	    private String text;

	    LoginReason(String text) {
	      this.text = text;
	    }

	    public String getText() {
	      return text;
	    }
	  }

}
