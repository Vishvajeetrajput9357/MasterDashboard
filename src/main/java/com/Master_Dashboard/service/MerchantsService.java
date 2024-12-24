package com.Master_Dashboard.service;

import java.util.Map;

import javax.validation.Valid;

import com.Master_Dashboard.request.LoginRequest;
import com.Master_Dashboard.request.MerchantRagisterRequest;

public interface MerchantsService {

	public Map<String, Object> merchantRegister(MerchantRagisterRequest merchantRagisterRequest);

	public Map<String, Object> loginMerchant(@Valid LoginRequest loginRequest);
	
}