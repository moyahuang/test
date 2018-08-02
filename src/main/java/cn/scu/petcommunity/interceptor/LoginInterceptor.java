package cn.scu.petcommunity.interceptor;

import cn.scu.petcommunity.util.SecurityUtil;
import cn.scu.petcommunity.wechat.model.WebAccessToken;
import cn.scu.petcommunity.wechat.service.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 主要将用户的openid放入cookie中
 * Created by luohui on 2017/7/20 15:33.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private OAuthService oAuthService;

    private static final String COOKIE_NAME = "scu_petcommunity";

    private static final Logger logger = LoggerFactory.getLogger(HandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("***********进入LoginIntercepter************");
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIE_NAME.equals(cookie.getName())) {
                    String value = cookie.getValue();
                    String openid = SecurityUtil.decodeBase64(value);
                    httpServletRequest.getSession().setAttribute("openid", openid);
                    logger.info("******从cookie中获取的openid:{}******", openid);
                    return true;
                }
            }
        }
        String code = httpServletRequest.getParameter("code");
        // 用户同意授权
        if(null != code) {
            WebAccessToken  webAccessToken = oAuthService.getWebAccessToken(code);
            String openid = webAccessToken.getOpenid();
            String cookieValue = SecurityUtil.encodeBase64(openid);
            Cookie cookie = new Cookie(COOKIE_NAME, cookieValue);
            cookie.setMaxAge(60 * 30);// 设置为30min
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
            httpServletRequest.getSession().setAttribute("openid", openid);
            logger.info("******从服务器上中获取的openid:{}******", openid);
            return  true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
