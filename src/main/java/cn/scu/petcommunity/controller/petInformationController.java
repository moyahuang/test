package cn.scu.petcommunity.controller;

import cn.scu.petcommunity.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by liulongfeng on 2017/7/27 0027.
 */
@Controller
public class petInformationController {
    @Resource
    private UserService userService;
    //@ResponseBody
    @RequestMapping("/petInformation")
    public JSONObject petInformationMethod(HttpServletRequest request) {
        System.out.println("enter petInformationController");
        JSONObject json=new JSONObject();

        //User user = userService.getUserById(2);
        //json.put("user",user);
        return json;
    }
}
