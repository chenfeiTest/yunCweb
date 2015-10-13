package com.globalroam.chenfei.yunCweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.globalroam.chenfei.yunCweb.dao.IUserDao;
import com.globalroam.chenfei.yunCweb.domain.User;
import com.globalroam.chenfei.yunCweb.domain.Userinfo;
import com.globalroam.chenfei.yunCweb.redis.dao.UserRedisDao;
import com.globalroam.chenfei.yunCweb.service.UserService;
/**
 * 
 * @author Chen.f 
 * @createTime：2015年9月17日 下午4:37:40 
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private UserRedisDao userRedisDao;
	
	
	
	@Override
	public List<Userinfo> getUserList() {
		
		return userDao.getUser();
	}



	@Override
	public void putRedis(List<Userinfo> userList) {
		userRedisDao.putUser(userList);
	}



	@Override
	public String getRedis(String key) {
		return userRedisDao.getUserName(key);
	}



	@Override
	public void updateUserInfo() {
		userDao.deleteUser("3");
		Userinfo user = new Userinfo();
		user.setUserName("chnefei");
		user.setUserAddress("cd");
		userDao.addUser(user);
	}



	@Override
	public Userinfo getUserByCondition(Userinfo user) {
		Userinfo resUser = userDao.getUserByConditon(user);
		return resUser;
	}

}
