package com.globalroam.chenfei.yunCweb.redis.dao;

import java.util.List;

import com.globalroam.chenfei.yunCweb.domain.Userinfo;

public interface UserRedisDao {
	public void putUser(List<Userinfo> userList);
	
	public String getUserName(String key);
}
