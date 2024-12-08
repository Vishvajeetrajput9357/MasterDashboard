package com.Master_Dashboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "VERIFICATION_ENTITY")
@EntityListeners(AuditingEntityListener.class)
public class VerificationEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VERIFICATION_ID")
	private long verificationId;
	
	@Column(name = "MERCHANT_ID")
	private long merchantId;
	
	@Column(name = "VERIFICATION_TYPE")
	private String verificationType;
	
	@Column(name = "VERIFICATION_REQUEST_DETAILS")
	private String verificationRequestDetails;
	
	@Column(name = "IS_EKYC_VERIFIDE")
	private String isEkycVerifide;

	public long getVerificationId() {
		return verificationId;
	}

	public void setVerificationId(long verificationId) {
		this.verificationId = verificationId;
	}

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	
	public String getVerificationType() {
		return verificationType;
	}

	public void setVerificationType(String verificationType) {
		this.verificationType = verificationType;
	}

	public String getVerificationRequestDetails() {
		return verificationRequestDetails;
	}

	public void setVerificationRequestDetails(String verificationRequestDetails) {
		this.verificationRequestDetails = verificationRequestDetails;
	}

	public String getIsEkycVerifide() {
		return isEkycVerifide;
	}

	public void setIsEkycVerifide(String isEkycVerifide) {
		this.isEkycVerifide = isEkycVerifide;
	}

}
