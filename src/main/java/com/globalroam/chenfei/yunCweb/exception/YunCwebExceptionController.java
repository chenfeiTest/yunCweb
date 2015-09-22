package com.globalroam.chenfei.yunCweb.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.globalroam.chenfei.yunCweb.util.CommonUtility;
import com.globalroam.chenfei.yunCweb.util.ForwardUtility;
import com.globalroam.chenfei.yunCweb.util.WebUtility;
/**
 * 
 * @author Chen.f 
 * @createTime：2015年9月22日 下午5:33:51 
 *
 */
@Controller
public class YunCwebExceptionController implements HandlerExceptionResolver  {

/*	private static Logger logger = Logger.getLogger(YunCwebExceptionController.class);
*/	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> msg = new HashMap<String, Object>();
        if (ex instanceof IllegalArgumentException) {
            msg.put("error", "parameter error");
        }
        if (request.getHeader("X-Requested-With") != null) {
            WebUtility.writeToClient(response, CommonUtility.toJson(msg));
            return null;
        }
        return new ModelAndView(ForwardUtility.forwardWithSpringMVC("error500"));
    }


}
