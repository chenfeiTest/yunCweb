package com.globalroam.chenfei.yunCweb.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

public class Userinfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5478112489546588917L;

	private String userId;
	
	private String userName;
	
	private String userAddress;
	
	private String userAge;
	
	private String userPassword;

	public Userinfo() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}
	
	

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "Userinfo [userId=" + userId + ", userName=" + userName
				+ ", userAddress=" + userAddress + ", userAge=" + userAge + "]";
	}
	
	
}
