package com.Master_Dashboard.ex.util;

import java.util.Map;
import com.Master_Dashboard.request.ResponseMessage;

public class SetErrorResponses {


	
	public Map<String, Object> setUnauthorised(Map<String, Object> map) {
		map.put(ResponseMessage.CODE, ResponseMessage.UNAUTHORISED);
		map.put(ResponseMessage.DESCRIPTION, ResponseMessage.UNAUTHORISED_DESCRIPTION);
		map.put(ResponseMessage.FIELD, ResponseMessage.FIELD_I);
		return map;
	}
	
	public void setApiStatusSomethingWent(Map<String, Object> map) {
		map.put(ResponseMessage.STATUS, ResponseMessage.API_STATUS_FAILED);
		map.put(ResponseMessage.CODE, ResponseMessage.SOMETHING_WENT_WRONG);
		map.put(ResponseMessage.DESCRIPTION, ResponseMessage.SOMETHING_WENT_WRONG_DESCRIPTION);
	}
	
}
