package com.Master_Dashboard.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "MERCHANT_INFO")
@EntityListeners(AuditingEntityListener.class)
public class MerchantInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MERCHANT_INFO_ID")
    private long merchantInfoId;

    @Column(name = "BANK_ID_JSON", nullable = false, length = 300)
    private String bankIdJson;

    @Column(name = "CLIENT_ID", nullable = false, length = 300)
    private String clientId;

    @Column(name = "CLIENT_SECRET", nullable = false, length = 300)
    private String clientSecret;

    @Column(name = "CREATION_DATE", nullable = false)
    private Timestamp creationDate;

    @Column(name = "ENACH_CALLBACK_URL", nullable = false, length = 300)
    private String enachCallbackUrl;

    @Column(name = "ENACH_REDIRECT_URL", nullable = false, length = 300)
    private String enachRedirectUrl;

    @Column(name = "IMAGE_URL", nullable = false, length = 500)
    private String imageUrl;

    @Column(name = "MERCHANT_BUSINESS_NAME", nullable = false, length = 500)
    private String merchantBusinessName;

    @Column(name = "MERCHANT_ID", nullable = false)
    private long merchantId;

    @Column(name = "PASSWORD", nullable = false, length = 300)
    private String password;

    @Column(name = "USERNAME", nullable = false, length = 300)
    private String username;

    @Column(name = "IS_MERCHANT_ACTIVE", nullable = false, length = 1)
    private String isMerchantActive;

    @Column(name = "DEBIT_PRESENTATION_CALLBACK_URL", nullable = true, length = 500)
    private String debitPresentationCallbackUrl;

    @Column(name = "ENACH_MERCHANT_HEADERS", nullable = true, length = 500)
    private String enachMerchantHeaders;

    @Column(name = "OTHER_INFO1", nullable = true, length = 500)
    private String otherInfo1;

    @Column(name = "OTHER_INFO2", nullable = true, length = 500)
    private String otherInfo2;

    @Column(name = "OTHER_INFO3", nullable = true, length = 500)
    private String otherInfo3;

	public long getMerchantInfoId() {
		return merchantInfoId;
	}

	public void setMerchantInfoId(long merchantInfoId) {
		this.merchantInfoId = merchantInfoId;
	}

	public String getBankIdJson() {
		return bankIdJson;
	}

	public void setBankIdJson(String bankIdJson) {
		this.bankIdJson = bankIdJson;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getEnachCallbackUrl() {
		return enachCallbackUrl;
	}

	public void setEnachCallbackUrl(String enachCallbackUrl) {
		this.enachCallbackUrl = enachCallbackUrl;
	}

	public String getEnachRedirectUrl() {
		return enachRedirectUrl;
	}

	public void setEnachRedirectUrl(String enachRedirectUrl) {
		this.enachRedirectUrl = enachRedirectUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMerchantBusinessName() {
		return merchantBusinessName;
	}

	public void setMerchantBusinessName(String merchantBusinessName) {
		this.merchantBusinessName = merchantBusinessName;
	}

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIsMerchantActive() {
		return isMerchantActive;
	}

	public void setIsMerchantActive(String isMerchantActive) {
		this.isMerchantActive = isMerchantActive;
	}

	public String getDebitPresentationCallbackUrl() {
		return debitPresentationCallbackUrl;
	}

	public void setDebitPresentationCallbackUrl(String debitPresentationCallbackUrl) {
		this.debitPresentationCallbackUrl = debitPresentationCallbackUrl;
	}

	public String getEnachMerchantHeaders() {
		return enachMerchantHeaders;
	}

	public void setEnachMerchantHeaders(String enachMerchantHeaders) {
		this.enachMerchantHeaders = enachMerchantHeaders;
	}

	public String getOtherInfo1() {
		return otherInfo1;
	}

	public void setOtherInfo1(String otherInfo1) {
		this.otherInfo1 = otherInfo1;
	}

	public String getOtherInfo2() {
		return otherInfo2;
	}

	public void setOtherInfo2(String otherInfo2) {
		this.otherInfo2 = otherInfo2;
	}

	public String getOtherInfo3() {
		return otherInfo3;
	}

	public void setOtherInfo3(String otherInfo3) {
		this.otherInfo3 = otherInfo3;
	}

}   