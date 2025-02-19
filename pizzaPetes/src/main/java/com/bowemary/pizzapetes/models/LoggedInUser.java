package com.bowemary.pizzapetes.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoggedInUser {

    @Email(message = "Please enter a valid email address.")
    @NotEmpty(message = "Email is required.")
    private String email;
    
    @NotEmpty(message = "Password is required.")
    @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters.")
    private String password;

    public LoggedInUser() {}

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
