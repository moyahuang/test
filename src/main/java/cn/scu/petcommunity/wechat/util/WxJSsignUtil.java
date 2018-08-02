package cn.scu.petcommunity.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.scu.petcommunity.wechat.model.Configuration;

/**
 * Created by luohui on 2017/7/20 21:58.
 */
public class WxJSsignUtil  {

    private static final Logger logger = LoggerFactory.getLogger(WxJSsignUtil.class);

    private static final String STRING = "jsapi_ticket=TICKET&noncestr=NONCESTR&timestamp=TIMESTAMP&url=URL";

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    public static Map<String, String > sign(String jsapi_ticket, String url) throws NoSuchAlgorithmException {
        String signature = null;					//签名，在该方法中被生成
        String noncestr = create_nonce_str();		//生成签名的随机串
        String timestamp = create_timestamp();					//生成签名的时间戳
        String str = STRING.replace("TICKET", jsapi_ticket).replace("NONCESTR", noncestr).replace("TIMESTAMP", timestamp).replace("URL", url);
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");		//使用SHA1加密
            md.update(str.getBytes());
            byte[] bytes = md.digest();
            signature = getFormattedText(bytes);					//加密后即生成签名
            logger.debug("signature: " + signature);
        } catch (NoSuchAlgorithmException e) {
            logger.error("sha1加密失败.");

        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("appId", Configuration.getProperty("weixin4j.oauth.appid"));
        map.put("timestamp", timestamp);
        map.put("nonceStr", noncestr);
        map.put("signature", signature);
        return map;
    }

}
