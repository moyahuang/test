package cn.scu.petcommunity.wechat.service;

import cn.scu.petcommunity.domain.view.UserVO;
import cn.scu.petcommunity.wechat.model.WebAccessToken;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by luohui on 2017/7/19 15:35.
 */
public interface OAuthService {

    /**
     *
     * @param redirectUrl 重定向后的url
     * @param oAuth 用户是否同意
     * @param state
     * @return
     * @throws UnsupportedEncodingException
     */
    String getCodeURL(String redirectUrl, boolean oAuth, String state) throws UnsupportedEncodingException;

    /**
     * 通过code换取网页授权access_token
     * @param code 微信服务器通过get请求发来的code， 若是snsapi_base, 则code参数为null
     */
    WebAccessToken getWebAccessToken(String code);

    /**
     * 获取用户的基本信息
     * @param accessToken WebAccessToken中的access_token
     * @param open_id     WebAccessToken中的open_id
     * @return
     */
    UserVO getUserInfo(String accessToken, String open_id);

    /**
     *
     * @param accessToken WebAccessToken中access_token
     * @param open_id 用户的open_id
     * @return true access_token未过期，false access_token已过期
     */
    boolean checkOAuthAccessToken(String accessToken, String open_id);

    JSONObject getUserInfo(String accessToken, String open_id, int state);

}
