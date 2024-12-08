package com.Master_Dashboard.service;

import java.util.Map;

import javax.validation.Valid;

import com.Master_Dashboard.request.CreateMandateRequest;
import com.Master_Dashboard.request.CreatePrasentationRequest;

public interface CreateNachService {

	Map<String, Object> createNach(CreateMandateRequest createMandateRequest , long merchantId);

	Map<String, Object> createPrasentation(CreatePrasentationRequest createPrasentationRequest, long merchantId);

}
