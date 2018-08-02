package cn.scu.petcommunity.onenet.api.mqtt;

import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpPostMethod;
import cn.scu.petcommunity.onenet.request.RequestInfo;
import cn.scu.petcommunity.onenet.response.BasicResponse;
import cn.scu.petcommunity.onenet.utils.Config;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class SendMqttApi extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String topic;
	private Object contents;// 用户自定义数据
	private HttpPostMethod HttpMethod;

	/**
	 * @param topic：设备订阅的主题（必选）,String
	 * @param contents:用户自定义数据：json、string、二进制数据（小于64K）
	 * @param key：masterkey
	 */
	public SendMqttApi(String topic, Object contents,String key) {
		this.topic = topic;
		this.contents = contents;
		this.key = key;
		this.method = RequestInfo.Method.POST;
        Map<String, Object> headmap = new HashMap<String, Object>();
        Map<String, Object> urlmap = new HashMap<String, Object>();
        HttpMethod = new HttpPostMethod(method);
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/mqtt";
        if (topic != null) {
            urlmap.put("topic", topic);
        }
        // body参数处理
        if (contents instanceof byte[]) {
            try {
                String s = new String((byte[]) contents, "UTF-8");
                ((HttpPostMethod) HttpMethod).setEntity(s);
            } catch (UnsupportedEncodingException e) {

                // e.printStackTrace();
                logger.error("bytes[]  error", e.getMessage());
                throw new OnenetApiException();
            }
        }
        if (contents instanceof String) {
            ((HttpPostMethod) HttpMethod).setEntity((String) contents);
        }
        HttpMethod.setcompleteUrl(url,urlmap);
	}

	public BasicResponse<Void> executeApi() {
		BasicResponse response = null;
		HttpResponse httpResponse = HttpMethod.execute();
		try {

			response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
		} catch (Exception e) {
			logger.error("json error", e.getMessage());
			throw new OnenetApiException();
		}
		try {
			HttpMethod.httpClient.close();
		} catch (Exception e) {
			logger.error("http close error:" + e.getMessage());
			throw new OnenetApiException();
		}
		return response;
	}

}
