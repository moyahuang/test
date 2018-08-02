package cn.scu.petcommunity.wechat.service.impl;

import cn.scu.petcommunity.domain.view.UserVO;
import cn.scu.petcommunity.wechat.model.Configuration;
import cn.scu.petcommunity.wechat.model.WebAccessToken;
import cn.scu.petcommunity.wechat.service.OAuthService;
import cn.scu.petcommunity.wechat.util.WechatUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by luohui on 2017/7/20 9:32.
 */
@Service("oAuthService")
public class OAuthServiceImpl implements OAuthService {

    private static final Logger logger = LoggerFactory.getLogger(OAuthServiceImpl.class);

    /**
     * 获取网页授权的code的url
     */
    public static final String CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize" +
            "?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

    /**
     * 获取code后，获取access_token的url
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2" +
            "/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /**
     * 获取用户基本信息的url
     */
    public static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo" +
            "?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * 检测access_token是否过期的url
     *
     */
    public static final String CHECK_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";

    /**
     *  不弹出授权页面，直接跳转，只能获取用户openid
     */
    public static final String SCOPE_BASE = "snsapi_base";
    /**
     *  弹出授权页面，可通过openid拿到昵称、性别、所在地。未关注下，用户授权也能获得这些信息（用户关注后，通过菜单点击不会弹出授权页面）
     */
    public static final String SCOPE_USERINFO = "snsapi_userinfo";

    @Override
    public String getCodeURL(String redirectUrl, boolean oAuth, String state) throws UnsupportedEncodingException {
        // 使用urlencode对链接进行处理
        redirectUrl  = URLEncoder.encode(redirectUrl, "UTF-8");

        String codeUrl = CODE_URL.replace("APPID", Configuration.getOAuthAppId())
                .replace("REDIRECT_URI", redirectUrl ).replace("SCOPE",SCOPE_USERINFO)
                .replace("STATE", state);
        // 根据oAuth，向用户请求不同的scope授权
        if(oAuth) {
            // 组装要发送的getCodeUrl(base)
            codeUrl = codeUrl.replace("SCOPE",SCOPE_BASE );
        }
        return codeUrl;
    }

    @Override
    public WebAccessToken getWebAccessToken(String code) {
        // 页面获取用户信息的access_token，延迟初始化
        WebAccessToken accessToken = null;
        String accessTokenUrl = ACCESS_TOKEN_URL.replace("APPID", Configuration.getOAuthAppId())
                .replace("SECRET", Configuration.getOAuthSecret()).replace("CODE", code.trim());
        JSONObject jsonObject = WechatUtil.httpsRequest(accessTokenUrl, "GET", null, null);
        logger.info("返回的json:" + jsonObject.toJSONString());
        if(jsonObject != null && !jsonObject.containsKey("errcode")) {
            accessToken = JSONObject.parseObject(jsonObject.toJSONString(), WebAccessToken.class);
        }
        return accessToken;
    }

    @Override
    public UserVO getUserInfo(String accessToken, String open_id) {
        String userinfoUrl = USER_INFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", open_id);
        JSONObject jsonObject = WechatUtil.httpsRequest(userinfoUrl, "GET", null, null);
        UserVO userVO = null;
        if (jsonObject != null && !jsonObject.containsKey("errcode")) {
            userVO = JSONObject.parseObject(jsonObject.toJSONString(), UserVO.class);
        }else {
            logger.error("snsapi_userinfo请求用户信息失败 errcode{}, errmsg{}: " + jsonObject.getInteger("errcode")
                    + "  " + jsonObject.getString("errmsg"));
        }
        return userVO;
    }

    public JSONObject getUserInfo(String accessToken, String open_id, int state){
        String userinfoUrl = USER_INFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", open_id);
        JSONObject jsonObject = WechatUtil.httpsRequest(userinfoUrl, "GET", null, null);
        return jsonObject;
    }

    @Override
    public boolean checkOAuthAccessToken(String accessToken, String open_id) {
        String checkAcessTokenUrl = CHECK_ACCESS_TOKEN_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", open_id);
        JSONObject jsonObject = WechatUtil.httpsRequest(checkAcessTokenUrl, "GET", null, null);
        if(jsonObject != null) {
            if("ok".equals(jsonObject.getString("errmsg"))) {
                logger.info("网页授权AccessToken有效！");
                return true;
            } else {
                logger.error("网页授权AccessToken失效！ errcode{}， errmsg{}"+jsonObject.getInteger("errcode")+jsonObject.getString("errmsg"));
            }
        }
        return false;
    }
}
