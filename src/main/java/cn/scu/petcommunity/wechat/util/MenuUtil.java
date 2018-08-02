package cn.scu.petcommunity.wechat.util;

import cn.scu.petcommunity.wechat.model.view.Button;
import cn.scu.petcommunity.wechat.model.view.Menu;
import cn.scu.petcommunity.wechat.model.view.ViewButton;
import cn.scu.petcommunity.wechat.service.OAuthService;
import cn.scu.petcommunity.wechat.service.impl.OAuthServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * 与微信菜单有关的创建
 * Created by luohui on 2017/7/19 10:47.
 */
public class MenuUtil {

    private static final Logger logger = LoggerFactory.getLogger(MenuUtil.class);

    private static OAuthService oAuthService = new OAuthServiceImpl();

    /**
     * 初始化菜单
     */
    public static Menu initMenu() throws UnsupportedEncodingException {
        Menu menu = new Menu();

        ViewButton button1 = new ViewButton();
        button1.setName("一键溜宠");
        button1.setType("view");
        button1.setUrl(oAuthService.getCodeURL(WechatUtil.DOMAIN_URL + "/petWalk", false, "10"));

        Button button2 = new Button();
        button2.setName("更多操作");

        ViewButton button21 = new ViewButton();
        button21.setName("宠物商店");
        button21.setType("view");
        button21.setUrl(oAuthService.getCodeURL(WechatUtil.DOMAIN_URL + "/user/showUser", false, "21"));


        ViewButton button22 = new ViewButton();
        button22.setName("宠物医院");
        button22.setType("view");
        button22.setUrl(oAuthService.getCodeURL(WechatUtil.DOMAIN_URL + "/user/showUser", false, "22"));
        button2.setSub_button(new Button[]{button21, button22});

        ViewButton button3 = new ViewButton();
        button3.setName("更多信息");
        button3.setType("view");
        button3.setUrl(oAuthService.getCodeURL(WechatUtil.DOMAIN_URL + "/user/getCode", false, "30"));
        menu.setButton(new Button[]{button1, button2, button3});
        return menu;
    }

    public static void createMenu(String token, Menu menu) {
        int resultCode = 0;
        String url = WechatUtil.CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
        String temp=JSON.toJSONString(menu);
        JSONObject resultJson = WechatUtil.httpsRequest(url, "POST", null, JSON.toJSONString(menu));
        //转化为json后判断
        if (null != resultJson) {
            resultCode = resultJson.getInteger("errcode");
            if (0 != resultCode) {
                logger.error("***********菜单创建失败***********");
            } else {
                logger.info("***********菜单创建成功***********");
            }
        }
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        String token = WechatUtil.getAccessToken().getAccess_token();
        logger.info("获取的token:" +  token);
        if(null != token && !"".equals(token)){
            Menu menu = initMenu();
          
            createMenu(token, menu);
        }
    }
}
