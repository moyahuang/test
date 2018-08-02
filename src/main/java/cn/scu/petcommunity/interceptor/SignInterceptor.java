package cn.scu.petcommunity.interceptor;

import cn.scu.petcommunity.wechat.model.JsapiTicket;
import cn.scu.petcommunity.wechat.util.WxJSsignUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by luohui on 2017/7/20 22:07.
 */
public class SignInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        StringBuffer buffer = httpServletRequest.getRequestURL();
        buffer.append("?");
        buffer.append(httpServletRequest.getQueryString());
        Map<String, String> map = WxJSsignUtil.sign(JsapiTicket.getJsapi_ticket(), buffer.toString());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            httpServletRequest.setAttribute(entry.getKey(), entry.getValue());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
