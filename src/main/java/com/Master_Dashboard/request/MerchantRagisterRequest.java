package com.Master_Dashboard.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MerchantRagisterRequest{
	
		@Email
		@Size(max = 50,min=8,message = "Invalid Email. Please try using valid email.")
		@NotBlank(message = "Email can not be blank.")
		private String merchantEmail;

		@NotBlank(message = "Mobile number can not be blank.")
		@Pattern(regexp = "^[0-9]{10,10}$", message = "Invalid Mobile number.")
		private String merchantPhone;
		
		@NotBlank(message = "Password can not be blank.")
		@Pattern(regexp = "^[a-zA-Z0-9@]{5,20}$", message = "Invalid Password.")
		private String merchantPassword;
		
		@NotBlank(message = "Firstname can not be blank.")
		@Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "Invalid Firstname.")
		private String merchantFirstname;
		
		@NotBlank(message = "Lastname can not be blank.")
		private String merchantLastname;
		
		@NotBlank(message = "BusinessName can not be blank.")
		@Pattern(regexp = "^[a-zA-Z0-9., - ']{1,20}$", message = "Invalid BusinessName.")
		private String merchantBusinessName;

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

		public String getMerchantBusinessName() {
			return merchantBusinessName;
		}

		public void setMerchantBusinessName(String merchantBusinessName) {
			this.merchantBusinessName = merchantBusinessName;
		}
		
}

