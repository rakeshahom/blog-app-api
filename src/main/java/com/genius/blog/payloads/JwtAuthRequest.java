package com.genius.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class JwtAuthRequest {

	private String username;
	private String password;
	public JwtAuthRequest() {
		super();
	}
	public JwtAuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "JwtAuthRequest [username=" + username + ", password=" + password + "]";
	}
	
}
