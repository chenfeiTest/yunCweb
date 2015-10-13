package com.globalroam.chenfei.yunCweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globalroam.chenfei.yunCweb.domain.User;
import com.globalroam.chenfei.yunCweb.service.UserService;


/**
 * 
 * @author Chen.f 
 * @createTime：2015年10月12日 下午3:37:18 
 *
 */
@Controller
@RequestMapping("/mydemo")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/getUserInfo")
	public String getUserInfo(HttpServletRequest request){
		String currentUser = (String)request.getSession().getAttribute("currentUser");
		System.out.println("当前登录的用户为[" + currentUser + "]");
		request.setAttribute("currUser", currentUser);
		return "/user/info";
	}
	
	
	
	@RequestMapping(value="getUserByCondition",method=RequestMethod.POST)
	public User getUserByCondition(HttpServletRequest request){
		return null;
	}
	
	
	
}