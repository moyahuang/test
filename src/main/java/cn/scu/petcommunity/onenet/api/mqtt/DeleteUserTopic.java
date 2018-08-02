package cn.scu.petcommunity.onenet.api.mqtt;


import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpDeleteMethod;
import cn.scu.petcommunity.onenet.request.RequestInfo;
import cn.scu.petcommunity.onenet.response.BasicResponse;
import cn.scu.petcommunity.onenet.utils.Config;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DeleteUserTopic extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String name;
	private HttpDeleteMethod HttpMethod;
	/**
	 * 删除产品的Topic
	 * @param name：topic名（必选）
	 * @param key：masterkey
	 */
	public DeleteUserTopic(String name,String key) {
		this.name = name;
		this.key = key;
		this.method = RequestInfo.Method.DELETE;
        Map<String, Object> headmap = new HashMap<String, Object>();
        Map<String, Object> urlmap = new HashMap<String, Object>();
        if(name!=null){
            urlmap.put("name", name);
        }
        HttpMethod = new HttpDeleteMethod(method);
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/mqtt" + "/topic" ;
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
