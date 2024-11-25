package com.Master_Dashboard.serviceImpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Master_Dashboard.Encryption.Encryption;
import com.Master_Dashboard.entity.MerchantInfo;
import com.Master_Dashboard.entity.Merchants;
import com.Master_Dashboard.repository.MerchantInfoRepository;
import com.Master_Dashboard.repository.MerchantsRepository;
import com.Master_Dashboard.repository.VerificationRepository;
import com.Master_Dashboard.request.LoginRequest;
import com.Master_Dashboard.request.MerchantRagisterRequest;
import com.Master_Dashboard.request.ResponseMessage;
import com.Master_Dashboard.service.MerchantsInfoService;
import com.Master_Dashboard.service.MerchantsService;

@Service
public class MerchantServiceImpl implements MerchantsService {

	@Autowired
	private MerchantsRepository merchantsRepository;
	
	@Autowired
	private MerchantInfoRepository merchantInfoRepository;

	@Autowired
	private MerchantsInfoService merchantsInfoService;

	@Autowired
	private VerificationRepository verificationRepository;
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MerchantServiceImpl.class);

	@Override
	public synchronized Map<String, Object> merchantRegister(MerchantRagisterRequest merchantRagisterRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			Timestamp trxnDate = Timestamp.valueOf(com.Master_Dashboard.ex.util.DateAndTime.getCurrentTimeInIST());

			if (!(merchantsRepository
					.existsByMerchantEmail(Encryption.encString(merchantRagisterRequest.getMerchantEmail()))
					|| merchantsRepository
							.existsByMerchantPhone(Encryption.encString(merchantRagisterRequest.getMerchantPhone())))) {

				LOGGER.info("Merchant is valid");

				Merchants merchants = new Merchants();
				merchants.setMerchantEmail(Encryption.encString(merchantRagisterRequest.getMerchantEmail()));
				merchants.setMerchantPhone(Encryption.encString(merchantRagisterRequest.getMerchantPhone()));
				merchants.setMerchantPassword(Encryption.encString(merchantRagisterRequest.getMerchantPassword()));
				merchants.setMerchantFirstname(Encryption.encString(merchantRagisterRequest.getMerchantFirstname()));
				merchants.setMerchantLastname(Encryption.encString(merchantRagisterRequest.getMerchantLastname()));
				merchants.setMerchantAddress1(Encryption.encString("NA"));
				merchants.setMerchantAddress2(Encryption.encString("NA"));
				merchants.setMerchantCity(Encryption.encString("NA"));
				merchants.setMerchantState(Encryption.encString("NA"));
				merchants.setMerchantCountry(Encryption.encString("India"));
				merchants.setMerchantZipcode(Encryption.encString("NA"));
				merchants.setMerchantFromdate(trxnDate);
				merchants.setMerchantBankName(Encryption.encString("NA"));
				merchants.setMerchantBankBranch(Encryption.encString("NA"));
				merchants.setMerchantBankCode(Encryption.encString("NA"));
				merchants.setMerchantAccountNo(Encryption.encString("NA"));
				merchants.setIsMerchantActive('N');
				merchants.setMerchantLoginCount('N');
				merchants.setMerchantBusinessName(
						Encryption.encString(merchantRagisterRequest.getMerchantBusinessName()));
				merchants.setIsMerchantEmailVerified('N');
				merchants.setIsMerchantPhoneVerified('N');
				merchants.setGender('N');
				merchants.setMerchantTypeId(1L);
				merchants.setMerchantNationality(Encryption.encString("Indian"));
				merchants.setMerchantAlternateEmail(Encryption.encString("NA"));
				merchants.setMerchantRemark(Encryption.encString("NA"));
				merchantsRepository.save(merchants);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
				}

				map = merchantsInfoService.merchantRegister(merchants.getMerchantId());

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
		return map;
	}

	@Override
	public synchronized Map<String, Object> loginMerchant(@Valid LoginRequest loginRequest) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			LOGGER.info("Merchant is loginRequest: {}",Encryption.encString(Encryption.encString(loginRequest.getEmail())));
			LOGGER.info("Merchant is loginRequest: {}",Encryption.decString("4SfDr2K7R5kG7EVLFyoscQ=="));

			if ((merchantsRepository.existsByMerchantEmail(Encryption.encString(loginRequest.getEmail()))
					&& merchantsRepository
							.existsByMerchantPassword(Encryption.encString(loginRequest.getPassword())))) {

				LOGGER.info("Merchant is login : {}",Encryption.encString(loginRequest.getEmail()));
				
				Optional<Merchants> merchant = merchantsRepository
						.findByMerchantEmail(Encryption.encString(loginRequest.getEmail()));

				Optional<MerchantInfo> merchantInfo = merchantInfoRepository
						.findByClientSecret(Encryption.encString(Encryption.encString(loginRequest.getEmail())));

				if (merchantInfo.isPresent() && merchant.isPresent()) {
					Merchants Merchants = merchant.get();
					MerchantInfo MerchantInfo = merchantInfo.get();

					LOGGER.info("Merchant is loginRequest: {}",Merchants.getIsMerchantActive());
					LOGGER.info("Merchant is loginRequest: {}",MerchantInfo.getIsMerchantActive());

					
					if (!(MerchantInfo.getIsMerchantActive().equalsIgnoreCase("Y")
							&& MerchantInfo.getIsMerchantActive().equalsIgnoreCase("Y"))) {
						map.put(ResponseMessage.STATUS, ResponseMessage.API_STATUS_FAILED);
						map.put(ResponseMessage.CODE, ResponseMessage.FAILED);
						map.put(ResponseMessage.DESCRIPTION, "User is not active.");	
						map.put("pKy", verificationRepository.existsByMerchantIdAndVerificationType(merchantInfo.get().getMerchantId(),Encryption.encString("PAN")));
						map.put("AaKy", verificationRepository.existsByMerchantIdAndVerificationType(merchantInfo.get().getMerchantId(),Encryption.encString("AADHAAR")));
						map.put("BaKy", verificationRepository.existsByMerchantIdAndVerificationType(merchantInfo.get().getMerchantId(),Encryption.encString("BANKACCOUNT")));
						
					} else {

						SimpleDateFormat formateDate = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");

						map.put("bussinessName", Encryption.decString(Merchants.getMerchantBusinessName()));
						map.put("status", "Success");
						map.put("date", formateDate.format(Merchants.getMerchantFromdate()));
						map.put("merchantUserName", Encryption.decString(Merchants.getMerchantFirstname()) + " "
								+ Encryption.decString(Merchants.getMerchantLastname()));
						map.put("loggedIn", true);
						map.put("firstName", Encryption.decString(Merchants.getMerchantFirstname()));
						map.put("client-id", Encryption.decString(MerchantInfo.getClientId()));
						map.put("client-secret", Encryption.decString(MerchantInfo.getClientSecret()));
						map.put("code", "0x0200");
						
						
						map.put("pKy", verificationRepository.existsByMerchantIdAndVerificationType(merchantInfo.get().getMerchantId(),Encryption.encString("PAN")));
						map.put("AaKy", verificationRepository.existsByMerchantIdAndVerificationType(merchantInfo.get().getMerchantId(),Encryption.encString("AADHAAR")));
						map.put("BaKy", verificationRepository.existsByMerchantIdAndVerificationType(merchantInfo.get().getMerchantId(),Encryption.encString("BANKACCOUNT")));
						
						
						map.put("description", "Login Successfully");
					}

				} else {
					map.put(ResponseMessage.STATUS, ResponseMessage.API_STATUS_FAILED);
					map.put(ResponseMessage.CODE, ResponseMessage.FAILED);
					map.put(ResponseMessage.DESCRIPTION, "Invalid Merchantinfo.");
				}

			} else {
				map.put(ResponseMessage.STATUS, ResponseMessage.API_STATUS_FAILED);
				map.put(ResponseMessage.CODE, ResponseMessage.FAILED);
				map.put(ResponseMessage.DESCRIPTION, ResponseMessage.INVALID_EMAIL_OR_PASSWORD);
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put(ResponseMessage.STATUS, ResponseMessage.API_STATUS_FAILED);
			map.put(ResponseMessage.CODE, ResponseMessage.SOMETHING_WENT_WRONG);
			map.put(ResponseMessage.DESCRIPTION, ResponseMessage.SOMETHING_WENT_WRONG_DESCRIPTION);

		}
		return map;

	}

}
