package com.Master_Dashboard.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import com.Master_Dashboard.Encryption.Encryption;
import com.Master_Dashboard.entity.MerchantInfo;
import com.Master_Dashboard.ex.util.SetErrorResponses;
import com.Master_Dashboard.repository.MerchantInfoRepository;
import com.Master_Dashboard.request.CreateMandateRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Master_Dashboard.service.CreateNachService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/NachRequest")
public class CreateOrderMandate {

	private CreateNachService createNachService;
	
	private MerchantInfoRepository merchantInfoRepository;
	
	private SetErrorResponses setErrorResponses;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateOrderMandate.class);


	private CreateOrderMandate(CreateNachService createNachService, SetErrorResponses setErrorResponses,
			MerchantInfoRepository merchantInfoRepository) {
		this.createNachService = createNachService;
		this.setErrorResponses = setErrorResponses;
		this.merchantInfoRepository = merchantInfoRepository;
	}

	@PostMapping("/createMandate")
	public Map<String, Object> createMandate(@RequestHeader("Client-Id") String clientId,
			@RequestHeader("Client-Secret") String clientSecret,
			@Valid @RequestBody CreateMandateRequest createMandateRequest) {
		Map<String, Object> map = new HashMap<>();
		try {
			Optional<MerchantInfo> merchantInfoOpt = validateMerchant(clientId, clientSecret);
			if (!merchantInfoOpt.isPresent()) {
				return setErrorResponses.setUnauthorised(map);
			}
			LOGGER.info("createMandate : "+createMandateRequest.toString());
			return createNachService.createNach(createMandateRequest,merchantInfoOpt.get().getMerchantId());

		} catch (Exception e) {
			LOGGER.error("Error occurred during order creation : ", e);
			return setErrorResponses.setApiStatusSomethingWent(map);
		}
	}

	public Optional<MerchantInfo> validateMerchant(String clientId, String clientSecret) {
		try {
			String encryptedClientId = Encryption.encString(clientId);
			String encryptedClientSecret = Encryption.encString(clientSecret);
			return merchantInfoRepository.findByClientIdAndClientSecret(encryptedClientId, encryptedClientSecret);
		} catch (Exception e) {
			LOGGER.error("Error occurred during merchant validation", e);
			return Optional.empty();
		}
	}
	
}