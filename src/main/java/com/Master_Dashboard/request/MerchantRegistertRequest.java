package com.Master_Dashboard.request;

import javax.validation.constraints.NotBlank;

public class MerchantRegistertRequest {
	
	@NotBlank(message = "merchantEmail cannot be empty")
	private String merchantEmail;

	@NotBlank(message = "merchantPhone cannot be empty")
	private String merchantPhone;

	@NotBlank(message = "merchantPassword cannot be empty")
	private String merchantPassword;

	@NotBlank(message = "merchantFirstname cannot be empty")
	private String merchantFirstname;

	@NotBlank(message = "merchantLastname cannot be empty")
	private String merchantLastname;

	@NotBlank(message = "merchantAddress1 cannot be empty")
	private String merchantAddress1;

	@NotBlank(message = "merchantAddress2 cannot be empty")
	private String merchantAddress2;

	@NotBlank(message = "merchantCity cannot be empty")
	private String merchantCity;

	@NotBlank(message = "merchantState cannot be empty")
	private String merchantState;

	@NotBlank(message = "merchantCountry cannot be empty")
	private String merchantCountry;

	@NotBlank(message = "merchantZipcode cannot be empty")
	private String merchantZipcode;

	@NotBlank(message = "merchantBusinessName cannot be empty")
	private String merchantBusinessName;

	@NotBlank(message = "gender cannot be empty")
	private String gender;

	@NotBlank(message = "merchantNationality cannot be empty")
	private String merchantNationality;

	public String getMerchantEmail() {
		return merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}

	public String getMerchantPhone() {
		return merchantPhone;
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public String getMerchantFirstname() {
		return merchantFirstname;
	}

	public void setMerchantFirstname(String merchantFirstname) {
		this.merchantFirstname = merchantFirstname;
	}

	public String getMerchantLastname() {
		return merchantLastname;
	}

	public void setMerchantLastname(String merchantLastname) {
		this.merchantLastname = merchantLastname;
	}

	public String getMerchantAddress1() {
		return merchantAddress1;
	}

	public void setMerchantAddress1(String merchantAddress1) {
		this.merchantAddress1 = merchantAddress1;
	}

	public String getMerchantAddress2() {
		return merchantAddress2;
	}

	public void setMerchantAddress2(String merchantAddress2) {
		this.merchantAddress2 = merchantAddress2;
	}

	public String getMerchantCity() {
		return merchantCity;
	}

	public void setMerchantCity(String merchantCity) {
		this.merchantCity = merchantCity;
	}

	public String getMerchantState() {
		return merchantState;
	}

	public void setMerchantState(String merchantState) {
		this.merchantState = merchantState;
	}

	public String getMerchantCountry() {
		return merchantCountry;
	}

	public void setMerchantCountry(String merchantCountry) {
		this.merchantCountry = merchantCountry;
	}

	public String getMerchantZipcode() {
		return merchantZipcode;
	}

	public void setMerchantZipcode(String merchantZipcode) {
		this.merchantZipcode = merchantZipcode;
	}

	public String getMerchantBusinessName() {
		return merchantBusinessName;
	}

	public void setMerchantBusinessName(String merchantBusinessName) {
		this.merchantBusinessName = merchantBusinessName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMerchantNationality() {
		return merchantNationality;
	}

	public void setMerchantNationality(String merchantNationality) {
		this.merchantNationality = merchantNationality;
	}
	
	

}
