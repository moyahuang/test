package cn.scu.petcommunity.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by luohui on 2017/7/20 15:58.
 */
public class SecurityUtil {
    /**
     * Base64加密字符串
     * @param plainText
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeBase64(String plainText) throws UnsupportedEncodingException {
        byte[] b = plainText.getBytes("UTF-8");
        return Base64.encodeBase64String(b);
    }

    /**
     * Base64解密字符串
     * @param encodeStr
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decodeBase64(String encodeStr) throws UnsupportedEncodingException {
        byte[] b = Base64.decodeBase64(encodeStr);
        return new String(b, "UTF-8");
    }
}
