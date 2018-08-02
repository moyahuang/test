  package cn.scu.petcommunity.wechat.util;

import cn.scu.petcommunity.domain.view.UserVO;
import cn.scu.petcommunity.wechat.model.AccessToken;
import cn.scu.petcommunity.wechat.model.Configuration;
import cn.scu.petcommunity.wechat.model.JsapiTicket;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.util.Map;

/**
 * Created by luohui on 2017/7/19 10:51.
 */
public class WechatUtil {

    private static final Logger logger = LoggerFactory.getLogger(WechatUtil.class);
    /**
     * 获取access_token的url
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    /**
     * 创建菜单的url
     */
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 域名url
     */
//   public static final String DOMAIN_URL = "http://petcommunity.bigdatascu.cn/petcommunity";

    public static final String DOMAIN_URL = "http://6d8d4f04.ngrok.io/petcommunity";


    public static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    /**
     * 获取用户基本信息的url
     */
    public static final String USERINFO_URL =
            "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * 发送http,https请求,注意服务器返回的结果必须是json格式的数据
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param headers       提交的header信息
     * @param outputStr     提交的requestBody中的数据
     * @return JSONObject 请求返回的数据
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, Map<String, String> headers, String outputStr) {
        JSONObject jsonObject = null;
        HttpsURLConnection conn = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            //设置http header信息
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 从输入流读取返回内容
            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            jsonObject =  JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            logger.error("连接超时：{}", ce);
        } catch (Exception e) {
            logger.error("https请求异常：{}", e);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
            }
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
            }
            if (bufferedReader != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            if (bufferedReader != null) {
                conn.disconnect();
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }
        }
        return jsonObject;
    }

    /**
     * 获取access_token
	 * expires_in:凭证有效时间，7200秒
	 */
    public static AccessToken getAccessToken()  {
        String url = ACCESS_TOKEN_URL.replace("APPID", Configuration.getOAuthAppId()).replace("APPSECRET", Configuration.getOAuthSecret());
        JSONObject resultJson = httpsRequest(url, "GET", null, null);
        AccessToken accessToken = new AccessToken();
        if (null != resultJson) {
            //JSONObject.parseObject失效，无法解析成对象
            accessToken.setAccess_token(resultJson.getString("access_token"));
            accessToken.setExpires_in(resultJson.getInteger("expires_in"));
        }
        return accessToken;
    }

    public static JsapiTicket getJsapiTicket() {
        JsapiTicket jsapiTicket = null;
        String url = JSAPI_TICKET_URL.replace("ACCESS_TOKEN", AccessToken.getAccess_token());
        JSONObject jsonObject = WechatUtil.httpsRequest(url, "GET", null, null);
        if (jsonObject.containsKey("ticket")) {
            jsapiTicket = new JsapiTicket();
            jsapiTicket.setJsapi_ticket(jsonObject.getString("ticket"));
            jsapiTicket.setExpires_In(jsonObject.getInteger("expires_in"));
            logger.info("获取jsapi_ticket成功，ticket:{}, expire_time:{}", jsapiTicket.getJsapi_ticket(), jsapiTicket.getExpires_In());
        }else {
            logger.error("获取jsapi_ticket失败!");
        }
        return jsapiTicket;
    }

    /**
     * 获取用户的基本信息
     * @param accessToken 基础支持中的accessToken
     * @param openid 用户的open_id 等同于FromUserName
     */
    public static UserVO getUserInfo(String accessToken, String openid) {
        String url = USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openid);
        JSONObject jsonObject = httpsRequest(url, "GET", null, null);
        UserVO userVO = null;
        if(jsonObject != null && !jsonObject.containsKey("errcode")){
            userVO = JSONObject.parseObject(jsonObject.toJSONString(), UserVO.class);
        }
        return userVO;
    }

}
