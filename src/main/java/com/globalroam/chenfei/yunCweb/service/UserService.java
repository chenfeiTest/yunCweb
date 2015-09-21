package com.globalroam.chenfei.yunCweb.service;

import java.util.List;

import com.globalroam.chenfei.yunCweb.domain.Userinfo;

public interface UserService {
	public List<Userinfo> getUserList();
	
	public void putRedis(List<Userinfo> userList);
	
	public String getRedis(String key);
}
