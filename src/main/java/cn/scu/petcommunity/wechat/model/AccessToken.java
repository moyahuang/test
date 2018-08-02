package cn.scu.petcommunity.wechat.model;

/**
 * access_token
 * Created by luohui on 2017/7/17 21:08.
 */
public class AccessToken {
    /**
     * access_token
     */
    private static String access_token;
    /**
     * access_token的过期时间
     */
    private static int  expires_in;

    public static String getAccess_token() {
        return access_token;
    }

    public static void setAccess_token(String access_token) {
        AccessToken.access_token = access_token;
    }

    public static int getExpires_in() {
        return expires_in;
    }

    public static void setExpires_in(int expires_in) {
        AccessToken.expires_in = expires_in;
    }
}
