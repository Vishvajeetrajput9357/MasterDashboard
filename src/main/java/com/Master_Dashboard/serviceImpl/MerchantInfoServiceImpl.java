package com.Master_Dashboard.serviceImpl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Master_Dashboard.Encryption.Encryption;
import com.Master_Dashboard.entity.MerchantInfo;
import com.Master_Dashboard.entity.Merchants;
import com.Master_Dashboard.repository.MerchantInfoRepository;
import com.Master_Dashboard.repository.MerchantsRepository;
import com.Master_Dashboard.request.ResponseMessage;
import com.Master_Dashboard.service.MerchantsInfoService;

@Service
public class MerchantInfoServiceImpl implements MerchantsInfoService {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MerchantInfoServiceImpl.class);

	
	@Autowired
	private MerchantInfoRepository merchantInfoRepository;

	@Autowired
	private MerchantsRepository merchantsRepository;

	@Override
	public synchronized Map<String, Object> merchantRegister(long merchantId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			Timestamp trxnDate = Timestamp.valueOf(com.Master_Dashboard.ex.util.DateAndTime.getCurrentTimeInIST());
			Optional<Merchants> merchants = merchantsRepository.findById(merchantId);

			if (merchants.isPresent()) {
				Merchants merchantDetails = merchants.get();
				LOGGER.info("MerchantInfo is valid");

				MerchantInfo merchantInfo = new MerchantInfo();

				merchantInfo.setBankIdJson("NA");
				merchantInfo.setClientId(Encryption.encString(Encryption.encString("" + merchantId)));
				merchantInfo.setClientSecret(Encryption.encString(merchantDetails.getMerchantEmail()));
				merchantInfo.setCreationDate(trxnDate);
				merchantInfo.setEnachCallbackUrl(Encryption.encString("NA"));
				merchantInfo.setEnachRedirectUrl(Encryption.encString("NA"));
				merchantInfo.setImageUrl(Encryption.encString("NA"));
				merchantInfo.setMerchantBusinessName(Encryption.encString(merchantDetails.getMerchantBusinessName()));
				merchantInfo.setMerchantId(merchantId);
				merchantInfo.setPassword(Encryption.encString(merchantDetails.getMerchantPassword()));
				merchantInfo.setUsername(Encryption.encString(merchantDetails.getMerchantFirstname()));
				merchantInfo.setIsMerchantActive("N");
				merchantInfo.setDebitPresentationCallbackUrl(Encryption.encString("NA"));
				merchantInfo.setEnachMerchantHeaders(Encryption.encString("NA"));
				merchantInfo.setOtherInfo1(Encryption.encString("NA"));
				merchantInfo.setOtherInfo2(Encryption.encString("NA"));
				merchantInfo.setOtherInfo3(Encryption.encString("NA"));

				merchantInfoRepository.save(merchantInfo);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
				}

				map.put(ResponseMessage.STATUS, ResponseMessage.API_STATUS_SUCCESS);
				map.put(ResponseMessage.CODE, ResponseMessage.SUCCESS);
				map.put(ResponseMessage.DESCRIPTION, ResponseMessage.MERCHANT_SUCCESS_REGISTERED);
				map.put("Client-Id", Encryption.encString("" + merchantId));
				map.put("Client-Secret", merchantDetails.getMerchantEmail());

			} else {
				map.put(ResponseMessage.STATUS, ResponseMessage.API_STATUS_FAILED);
				map.put(ResponseMessage.CODE, ResponseMessage.FAILED);
				map.put(ResponseMessage.DESCRIPTION, ResponseMessage.MERCHANT_ALREADY_EXIST);
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put(ResponseMessage.STATUS, ResponseMessage.API_STATUS_FAILED);
			map.put(ResponseMessage.CODE, ResponseMessage.SOMETHING_WENT_WRONG);
			map.put(ResponseMessage.DESCRIPTION, ResponseMessage.SOMETHING_WENT_WRONG_DESCRIPTION);
		}
		
		System.out.println(map);
		return map;
	}

}
