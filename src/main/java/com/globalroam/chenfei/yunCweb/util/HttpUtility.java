package com.globalroam.chenfei.yunCweb.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.alibaba.druid.util.StringUtils;

/**
 * 
 * @author Chen.f 
 * @createTime：2015年10月10日 下午4:18:00 
 *
 */
public class HttpUtility {
	public static final TimeZone TIMEZONE_CHINA_SHANGHAI = TimeZone
			.getTimeZone("Asia/Shanghai");
	public static final TimeZone TIMEZONE_AMERICAN_NEWYORK = TimeZone
			.getTimeZone("America/New_York");
	public static final String TIMEZONE_GMT_TWO = "GMT+2";
	public static final String TIMEZONE_GMT_THREE = "GMT+3";
	public static final String TIMEZONE_GMT_FOUR = "GMT+4";
	public static final String TIMEZONE_GMT_FIVE = "GMT+5";
	public static final String TIMEZONE_GMT_SIX = "GMT+6";
	public static final String TIMEZONE_GMT_SEVEN = "GMT+7";
	public static final String TIMEZONE_GMT_ENGHT = "GMT+8";
	public static final String TIMEZONE_GMT_NINE = "GMT+9";
	public static final String TOKEN_FOR_REPEAT_SUBMIT = "TOKEN_FOR_REPEAT_SUBMIT";
	
	public static final String EN = "EN";
	public static final String CN = "CN";
	public static final String ZH = "zh";

	public static Locale getI18nLocal(HttpServletRequest request) {
		Locale locale = (Locale) request.getSession().getAttribute(
				SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		return locale;
	}

	public static String getI18nLang(HttpServletRequest request) {
		Locale locale = (Locale) request.getSession().getAttribute(
				SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		String lang = locale == null ? HttpUtility.ZH : locale.getLanguage();
		return lang;
	}

	public static String getI18nIsoCode(HttpServletRequest request) {
		Locale locale = (Locale) request.getSession().getAttribute(
				SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		String isocode = null;
		if (locale != null)
			isocode = locale.getCountry();
		return isocode;
	}


	/**
	 * 获取带参数的请求URL
	 * 
	 * @param request
	 * @return URL字符串
	 */
	public static String getRequestURLWithQueryParrams(
			HttpServletRequest request) {
		StringBuffer sb = request.getRequestURL();
		Map<String, String[]> map = request.getParameterMap();
		if (map.size() > 0)
			sb.append("?");
		Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			String key = entry.getKey();
			String[] value = entry.getValue();
			String parram = key + "=" + value[0] + "&";
			sb.append(parram);
		}
		return sb.toString();
	}

	public static String getHostURL(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String tempContextUrl = url.delete(
				url.length() - request.getRequestURI().length(), url.length())
				.toString();
		return tempContextUrl;
	}

	/**
	 * 写入客户端
	 * @param response
	 * @param msg
	 * @param code
	 */
	public static void writeToClient(HttpServletResponse response, String msg,
			String code) {
		String defaultCode = "UTF-8";
		if (StringUtils.isEmpty(code))
			defaultCode = code;
		resp(response, msg, defaultCode);
	}

	public static void writeToClient(HttpServletResponse response, String msg) {
		String defaultCode = "UTF-8";
		resp(response, msg, defaultCode);
	}

	private static void resp(HttpServletResponse response, String msg,
			String code) {
		try {
			response.setCharacterEncoding(code);
			response.setContentType("text/html;charset=" + code);
			response.getWriter().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * get browser language.
	 */
	public static String getBrowserLanuage(HttpServletRequest request) {
		Locale locale = (Locale) request.getSession().getAttribute(
				SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		String lanuage = null;
		if (locale != null) {
			lanuage = locale.getLanguage();
		} else {
			lanuage = request.getLocale().getLanguage();
		}
		if (lanuage == null || lanuage == "") {
			lanuage = Locale.getDefault().getLanguage();
		}
		return lanuage;
	}

	/**
	 * to prevent repeat submit
	 */
	public static void makeToken(HttpServletRequest request) {
		String token = UUID.randomUUID().toString();
		resetToken(request);
		request.getSession().setAttribute(TOKEN_FOR_REPEAT_SUBMIT, token);
	}

	public static String getToken(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(
				TOKEN_FOR_REPEAT_SUBMIT);
	}

	public static boolean isValidatedToken(HttpServletRequest request) {
		boolean result = false;
		String tokenFromRequest = request.getParameter("token");
		String tokenFromSession = (String) request.getSession().getAttribute(
				TOKEN_FOR_REPEAT_SUBMIT);
		if (StringUtils.isEmpty(tokenFromSession) && StringUtils.isEmpty(tokenFromRequest)) {
			if (tokenFromRequest.equals(tokenFromSession)) {
				result = true;
			}
		}
		return result;
	}

	public static void resetToken(HttpServletRequest request) {
		request.getSession().removeAttribute(TOKEN_FOR_REPEAT_SUBMIT);
	}

	public static String removePlus(String prefix) {
		if (StringUtils.isEmpty(prefix)) {
			if (prefix.contains("+")) {
				prefix = prefix.substring(prefix.indexOf("+") + 1,
						prefix.length());
			}
		} else {
			prefix = "";
		}
		return prefix.trim();
	}

	/**
	 * get identified code
	 * 
	 * @return
	 */
	public static String getVerifyCodeByCreated(HttpServletRequest request) {
		if (request != null) {
			return (String) request.getSession().getAttribute(
					com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		} else {
			return null;
		}
	}

	public static String getIpFromHead(HttpServletRequest request) {
		String ip = null;
		ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isEmpty(ip))
			ip = request.getRemoteAddr();
		return ip;
	}

	public static void disableBrowserCache(HttpServletResponse response) {
		response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -1);
	}

	/**
	 * 得到request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 得到session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request.getSession();
	}

	/**
	 * 得到context
	 * 
	 * @return
	 */
	public static ServletContext getServletContext() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request.getSession().getServletContext();
	}
	
	public static String getRefererURL(HttpServletRequest request) {
		return request.getHeader("Referer");
	}
	

}
