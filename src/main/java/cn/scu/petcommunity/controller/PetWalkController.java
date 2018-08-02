package cn.scu.petcommunity.controller;

import cn.scu.petcommunity.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by luohui on 2017/7/12 20:02.
 */
@Controller
public class PetWalkController {
    @Resource
    private UserService userService;
    //@ResponseBody
    @RequestMapping("/petWalk")
    public JSONObject petWalkMethod(HttpServletRequest request) {
        System.out.println("enter PetWalkController");
        JSONObject json=new JSONObject();

        //User user = userService.getUserById(2);
        //json.put("user",user);
        return json;
    }

}
