package com.Master_Dashboard.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginRequest {

	@NotBlank
	@Email
	@Size(max = 50,min=8,message = "Invalid Email. Please try using valid email.")
	private String email;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9@]{5,20}$", message = "Invalid Password.")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}
