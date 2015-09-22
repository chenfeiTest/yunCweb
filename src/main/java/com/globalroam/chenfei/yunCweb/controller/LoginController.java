package com.globalroam.chenfei.yunCweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author Chen.f 
 * @createTime：2015年9月22日 下午2:07:44 
 *
 */
@Controller
@RequestMapping("/")
public class LoginController {
	
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String gotoLoginPage(HttpServletRequest request){
		String path = request.getContextPath();
		request.setAttribute("path", path);
		return "login";
	}
}
