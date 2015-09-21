package com.globalroam.chenfei.yunCweb.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.globalroam.chenfei.yunCweb.domain.Userinfo;
import com.globalroam.chenfei.yunCweb.service.UserService;

/**
 * 
 * @author Chen.f 
 * @createTime：2015年9月17日 下午4:35:56 
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String returnIndexPage(){
		System.out.println("comin");
		return "index";
	}
	
	
	
	@RequestMapping(value="/putRedis",method = RequestMethod.GET)
	public void putRedis(){
		Long Stime = new Date().getTime();
		List<Userinfo> userList = userService.getUserList();
		String username = userList.get(0).getUserName();
		Long etime = new Date().getTime();
		System.out.println("结果"+username);
		System.out.println("执行时间---"+(etime-Stime));
		userService.putRedis(userList);
		Long Sstime = new Date().getTime();
		String name = userService.getRedis("1");
		Long Setime = new Date().getTime();
		System.out.println("结果"+name);
		System.out.println("redis执行时间 "+(Setime-Sstime));
	}
	
	
	@RequestMapping(value="/saveCkInfo",method = RequestMethod.POST)
	public void saveCkInfo(@RequestParam("ckContext")Object ckContext){
		System.out.println("内容"+ckContext.toString());
	}
}
