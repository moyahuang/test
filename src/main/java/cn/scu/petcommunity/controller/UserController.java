package cn.scu.petcommunity.controller;

import cn.scu.petcommunity.service.UserService;
import cn.scu.petcommunity.wechat.service.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by luohui on 2017/7/12 20:02.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private OAuthService oAuthService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/showUser")
    public String  showUser(HttpServletRequest request) {
        String openid = (String)request.getSession().getAttribute("openid");
        logger.info("获取的openid:" + openid);
        return "showUser";
    }

    @RequestMapping(value = "/historyTrace")
    public String historyTrace(){
        return "historyTrace";
    }

    @RequestMapping(value = "/getCode")
    public String getCode(){
        return "getCode";
    }

}
