package com.Master_Dashboard.ex.util;
import java.util.UUID;

public class GenrateUniqueId {

	    public static String generateUniqueId() {
	      
	    	long currentTimeMillis = System.currentTimeMillis();
	        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 15); // Get a substring of length 15
	        String uniqueId = currentTimeMillis + uuid;
	        if (uniqueId.length() > 15) {
	            uniqueId = uniqueId.substring(0, 15);
	        }
	        return "KJ"+uniqueId.toUpperCase();
	    }
	    public static String generateUniqueMandateId() {
		      
	    	long currentTimeMillis = System.currentTimeMillis();
	        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 15); // Get a substring of length 15
	        String uniqueId = currentTimeMillis + uuid;
	        if (uniqueId.length() > 15) {
	            uniqueId = uniqueId.substring(0, 15);
	        }
	        return "69"+uniqueId.toUpperCase();
	    }
}