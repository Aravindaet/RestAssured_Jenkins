package com.RestAssured.Details;

public class TokenDetails {

	private String userName;
	private String password;

	public TokenDetails() {
		userName = readuserName();
		password = readPassword();

	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String readuserName() {
		String userName = "admin";
		return userName;

	}

	public String readPassword() {
		String password = "password123";
		return password;

	}

}
