package com.globalroam.chenfei.yunCweb.dao;

import java.util.List;

import com.globalroam.chenfei.yunCweb.domain.Userinfo;

/**
 * 
 * @author Chen.f 
 * @createTime：2015年9月17日 下午4:42:52 
 *
 */
public interface IUserDao {

	public void addUser(Userinfo user);
	
	public List<Userinfo> getUser();
}
