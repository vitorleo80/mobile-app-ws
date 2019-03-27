package com.appsdeveloperblog.app.ws.ui.model.request;

/**
 * 
 * @author Vitor Correa
 * @date 27 Mar 2019
 */
public class PasswordResetModel {

	private String token;
	private String password;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
