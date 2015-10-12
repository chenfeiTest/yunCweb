package com.globalroam.chenfei.yunCweb.interceptor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.globalroam.chenfei.yunCweb.domain.User;
import com.globalroam.chenfei.yunCweb.util.CommonUtility;
import com.globalroam.chenfei.yunCweb.util.CustomMessage;
import com.globalroam.chenfei.yunCweb.util.HttpUtility;
import com.globalroam.chenfei.yunCweb.util.WebUtility;
/**
 * 
 * @author Chen.f 
 * @createTime：2015年10月10日 上午11:28:13 
 *
 */
public class GlobalInterceptor extends HandlerInterceptorAdapter{
	
	
	private static Logger logger = Logger.getLogger(GlobalInterceptor.class);
	
	
	private static List<String> URIList = new ArrayList<String>();
	private static List<String> returnURLList = new ArrayList<String>();
	/**
	 * 无需要校验的URI,支持 * 通配符,
	 */
	static {
		URIList.add("/signin*");
		URIList.add("/login*");
		URIList.add("/signout/");
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws Exception {
		String LOGIN_PAGE = "/login";
		User user = null;
		HttpSession session = request.getSession();
		String basePath = request.getContextPath();
		String uri = request.getRequestURI();
		uri = uri.substring(basePath.length(), uri.length());
			user = (User) session.getAttribute("user");
		if(user == null && !permissionCheck(URIList,uri)) {
			//若是ajax请求
			/*String requestType = request.getHeader("X-Requested-With");
			String requestURI = request.getRequestURI();
			logger.info("[requestURI] = " + requestURI);
			if("XMLHttpRequest".equalsIgnoreCase(requestType)){
				if(requestURI.indexOf("calllog/isFirstCall") > 0){
					CustomMessage msg = new CustomMessage(); 
					msg.setCodeStatus("200");
					HttpUtility.writeToClient(response, CommonUtility.toJson(""));
				}else{
					CustomMessage msg = new CustomMessage();
					msg.setCodeStatus("301");
					msg.setCodeDescribe("Session Timeout! Please re-sign in");
					HttpUtility.writeToClient(response, CommonUtility.toJson(msg));
				}
			}else{*/
				response.sendRedirect(basePath + LOGIN_PAGE);
			/*}*/
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String host = request.getServerName();
		String path = request.getServletContext().getContextPath();
		request.setAttribute("host", host);
		request.setAttribute("path", path);
		request.setAttribute("sessionId", request.getSession().getId());
		request.setAttribute("remoteIP", HttpUtility.getIpFromHead(request));
		request.setAttribute("timestamp", new Date().getTime());
		//super.postHandle(request, response, handler, modelAndView);
		
	}

	@Override
	public void afterCompletion(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj,
			Exception exception) throws Exception {
		// TODO Auto-generated method stub
		
	}


	/**
	 * URI级别 - 检查访问权限 必须完全匹配才能通过，支持*通配符，更细粒度的校验
	 * 
	 * @param URI
	 * @return
	 */
	private boolean permissionCheck(List<String> uris,String URI) {
		boolean result = false;
		for (String regx : uris) {
			if (WebUtility.simpleWildcardMatch(regx, URI)) {
				result = true;
				break;
			}
		}
		return result;
	}
}
