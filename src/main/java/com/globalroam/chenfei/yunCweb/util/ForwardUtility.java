package com.globalroam.chenfei.yunCweb.util;

import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 
 * @author Chen.f 
 * @createTime：2015年9月22日 下午5:56:57 
 *
 */
public class ForwardUtility {
	private static Logger log = Logger.getLogger(ForwardUtility.class);
	// private static HttpServletRequest request;

	public static String REGISTER_SUCCESS = "/user/signup_success_email";
	public static String REGISTER_PAGE = "/signup";
	public static String LOGIN_PAGE = "/login";
	public static String SEND_EMAIL_PAGE = "/user/signup_success_email";
	public static String BIND_MOBILE_PAGE = "/user/phone_num";
	public static String GET_LINK_PAGE = "/user/get_link";
	public static String RESET_PASSWORD_PAGE = "/user/get_password";
	public static String EMAIL_ACTIVE_ERROR = "/user/activeError";
	public static String REPEAT_SUBMIT_ERROR = "/user/repeatSubmit";
	public static String LOGIN_TIMEOUT = "/user/loginTimeout";
	public static String GET_PASSWORD = "/user/get_password";
	public static String SET_PASSWORD = "/user/set_password";
	public static String RESET_SUCCESS = "/user/set_password_success";
	public static String INVITE_FRIENDS = "/tell_friends";
	public static String ADMIN_INDEX = "/admin/index";
	public static String ADMIN_PROFILE = "/admin/personal_info";
	public static String ADMIN_CALL_RECORD = "/admin/call_records";
	public static String ADMIN_LINKS = "/admin/gnum_link";
	public static String ADMIN_PWD = "/admin/change_pwd";

	public static String FORWARD_BASE_PATH = "forward:";
	public static String REDIRECT_BASE_PATH = "redirect:";
	public static String REDIRECT_FOR_BIND_MOBILE_PAGE = REDIRECT_BASE_PATH
			+ "/gnum/bindmobile/toBind";
	public static String REDIRECT_FOR_SEND_EMAIL_SUCCESS = REDIRECT_BASE_PATH
			+ "/gnum/emailactive/success";
	public static String REDIRECT_FOR_GET_LINK_SUCCESS = REDIRECT_BASE_PATH
			+ "/gnum/bindmobile/link";
	public static String REDIRECT_FOR_ADMIN_INDEX = REDIRECT_BASE_PATH
			+ "/gnum/usermanage/personinfo";
	public static String REDIRECT_FOR_LOGIN = REDIRECT_BASE_PATH
			+ "/gnum/signup/toLogin";
	public static String REDIRECT_FOR_EMAIL_ACTIVE_ERROR = REDIRECT_BASE_PATH
			+ "/gnum/emailactive/error";
	public static String REDIRECT_FOR_REPEAT_SUBMIT_ERROR = REDIRECT_BASE_PATH
			+ "/gnum/userregister/repeatSubmit";
	public static String REDIRECT_FOR_LOGIN_TIMEOUT = REDIRECT_BASE_PATH
			+ "/gnum/signup/timeout";
	public static String REDIRECT_FOR_SET_PASSWORD = REDIRECT_BASE_PATH
			+ "/gnum/resetpassword/toSet";
	public static String REDIRECT_FOR_GET_PASSWORD = REDIRECT_BASE_PATH
			+ "/gnum/resetpassword/toGet";
	public static String REDIRECT_FOR_RESET_SUCCESS = REDIRECT_BASE_PATH
			+ "/gnum/resetpassword/resetSuccess";

	public static String URL_FOR_RESETEMAIL = "/resetpassword/change";
	public static String URL_FOR_ACTIVEEAMIL = "/emailactive/active";
	public static String URL_FOR_INVITE_FRIENDS = "/invitefriends/toRegister";

	private static Properties prop = null;
	private static String prefixPath = "/gnumUS";
	static {
		try {
			prop = new Properties();
			InputStream in = ForwardUtility.class.getClassLoader()
					.getResourceAsStream("httpClientPath.properties");
			prop.load(in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String forwardAdminView(String path){
		return "/admin_template" + processSlash(path);
	}
	public static String forwardSiteView(String path){
		return "/site_template" + processSlash(path);
	}
	public static String forwardWithSpringMVC(String path){
		return processSlash(path);
	}
	
	public static String redirectWithSpringMVC(String path) {
		return REDIRECT_BASE_PATH + processSlash(path);
	}
	
	private static String processSlash(String str) {
		String result = null;
		if(str!=null&&!"".equals(str)&&str.length()!=0) {
			if(str.startsWith("/")) {
				result = str;
			}else{
				result = "/" + str;
			}
		}
		return result;
	}
	
	public static String redirectMappingPath(HttpSession session, String path) {
		String pathInSession = (String) session
				.getAttribute("prefixPath");
		String appName = session.getServletContext().getContextPath();
		String mappingPath = removeContextPath(pathInSession, appName);
		// mappingPath = pathInSession;
		return REDIRECT_BASE_PATH + mappingPath + path;
	}
	
	public static String redirectMappingPath(String path) {
		return REDIRECT_BASE_PATH + path;
	}

	private static String removeContextPath(String path, String appName) {
		// TODO Auto-generated method stub
		if (WebUtility.isVerify(path) && WebUtility.isVerify(appName)) {
			if (path.startsWith(appName)) {
				int indexBegin = appName.length();
				int indexEnd = path.length();
				path = path.substring(indexBegin, indexEnd);
			}
		}
		return path.trim();
	}

	public static String getPrefixPath() {
		return prefixPath;
	}

	public static String getPrefixPath(Locale locale) {
		String lanuage = locale.getLanguage();
		// log.info("lanuage:"+lanuage);
		if ("zh".equals(lanuage)) {
			prefixPath = prop.getProperty("jspPrefixPath.CN");
		} else if ("en".equals(lanuage)) {
			prefixPath = prop.getProperty("jspPrefixPath.US");
		}
		// log.info("prefixPath:"+prefixPath);
		if (prefixPath.indexOf("/") == -1) {
			prefixPath = "/" + prefixPath;
		}
		return prefixPath;
	}

	public static String forwardToPage(String location) {
		if (location.indexOf("/") == -1) {
			location = "/" + location;
		}
		return prefixPath + location;
	}

	public static String changeViewByCountry(Locale locale, String viewName) {
		if (locale != null && viewName != null && viewName != "") {
			String path = getPrefixPath(locale);

			if (viewName.startsWith("redirect:")
					|| viewName.indexOf("forward:") != -1) {
			} else {
				if (viewName.indexOf("/") == -1) {
					viewName = "/" + viewName;
				}
				if (viewName.startsWith("/gnum")) {
					if (!viewName.contains("US") && !viewName.contains("CN")) {
						viewName = viewName.replaceFirst("/gnum", path);
					}
				} else {
					viewName = path + viewName;
				}
			}
		}
		return viewName;
	}

	/*
	 * public static void main(String[] args) { String viewName = "/gnum/index";
	 * if(viewName.startsWith("/gnum")) { if(!viewName.contains("US")
	 * &&!viewName.contains("CN")) { viewName =
	 * viewName.replace("/gnum","/gnumCN"); } }
	 * System.out.println("viewName is matches('^/gnum'):"
	 * +viewName.startsWith("/gnum"));
	 * System.out.println("viewName is:"+viewName); }
	 */
}
