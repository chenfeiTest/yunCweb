package com.globalroam.chenfei.yunCweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.globalroam.chenfei.yunCweb.util.CommonUtility;

public class GlobalFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("filter destory");
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String URI = request.getRequestURI();
		String appName = request.getServletContext().getContextPath();
		URI = URI.replace(appName, "");
		/*//如果是静态资源请求则直接放行
		if(isStaticResources(URI)){
			chain.doFilter(request, response);
			return;
		}
		// 如果是以"/"结尾或者URI包含"/"，说明是路径，直接放行
		if (!CommonUtility.isNonEmpty(URI) || URI.split("/").length > 2
			|| URI.endsWith("/") ) {
			chain.doFilter(request, response);
			return;
		}*/
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("globalFilter init");
	}
	
	
	private boolean isStaticResources(String uri){
		boolean isStatic = false;
		if(uri.endsWith(".css")||
			uri.endsWith(".js")||
			uri.endsWith(".map")||
			uri.endsWith(".png")||
			uri.endsWith(".jpg")||
			uri.endsWith(".ico")||
			uri.endsWith(".gif")
				){
			isStatic = true;
		}
		return isStatic;
	}
	
}
