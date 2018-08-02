package cn.scu.petcommunity.wechat.model;

/**
 * Created by luohui on 2017/7/20 21:30.
 */
public class JsapiTicket {

    private static String jsapi_ticket;

    public static String getJsapi_ticket() {
        return jsapi_ticket;
    }

    public static void setJsapi_ticket(String jsapi_ticket) {
        JsapiTicket.jsapi_ticket = jsapi_ticket;
    }

    public static int getExpires_In() {
        return expires_In;
    }

    public static void setExpires_In(int expires_In) {
        JsapiTicket.expires_In = expires_In;
    }

    private static  int expires_In ;
}
