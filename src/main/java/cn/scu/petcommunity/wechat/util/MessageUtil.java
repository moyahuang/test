package cn.scu.petcommunity.wechat.util;

import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 将xml与bean之间进行相互转换
 * Created by luohui on 2017/7/17 16:11.
 */
public class MessageUtil {

    public static final String MESSAGE_TEXT="text";

    public static final String MESSAGE_IMAGE="image";

    public static final String MESSAGE_VOICE="voice";

    public static final String MESSAGE_VIDEO="video";

    public static final String MESSAGE_LOCATION="location";

    public static final String MESSAGE_LINK="link";

    public static final String MESSAGE_EVENT="event";

    public static final String MESSAGE_SUBSCRIBE="subscribe";

    public static final String MESSAGE_UNSUBSCRIBE="unsubscribe";

    public static final String MESSAGE_CLICK="CLICK";

    public static final String MESSAGE_VIEW="VIEW";

    private static final Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    public static String object2Xml(Object obj) {
        XStream xs = new XStream();
        xs.alias("xml", obj.getClass());
        return xs.toXML(obj).replace("__", "_");
    }

    public static <T> T xml2Object(String xmlStr, Class<T> clazz) {
        XStream xstream = new XStream();
        xstream.processAnnotations(clazz);
        T t = (T) xstream.fromXML(xmlStr);
        return t;
    }

    public static String stream2String(InputStream in)  {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = bf.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (IOException e) {
            logger.info("流转成String失败");
            throw new RuntimeException("流转成String失败");
        }
    }
}
