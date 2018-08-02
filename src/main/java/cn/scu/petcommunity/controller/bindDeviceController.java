package cn.scu.petcommunity.controller;

import cn.scu.petcommunity.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by luohui on 2017/7/12 20:02.
 */
@Controller
public class bindDeviceController {
    @Resource
    private UserService userService;

    @RequestMapping("/bindDevice")
    public String bindDeviceMethod(HttpServletRequest request) {
        /*System.out.println("enter bindDeviceController");
        JSONObject json=new JSONObject();*/

        //User user = userService.getUserById(2);
        //json.put("user",user);
        return "bindDevice";
    }

}
