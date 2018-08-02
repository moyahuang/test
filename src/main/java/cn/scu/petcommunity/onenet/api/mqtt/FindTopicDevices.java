package cn.scu.petcommunity.onenet.api.mqtt;


import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpGetMethod;
import cn.scu.petcommunity.onenet.request.RequestInfo;
import cn.scu.petcommunity.onenet.response.BasicResponse;
import cn.scu.petcommunity.onenet.response.mqtt.TopicDeviceList;
import cn.scu.petcommunity.onenet.utils.Config;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class FindTopicDevices  extends AbstractAPI {
	private Integer page;
	private Integer perPage;
	private String topic;
	private HttpGetMethod HttpMethod;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * @param page
	 * @param perPage
	 * @param topic
	 * @param key
	 */
	public FindTopicDevices(Integer page, Integer perPage, String topic,String key) {
		this.page = page;
		this.perPage = perPage;
		this.topic = topic;
		this.method = RequestInfo.Method.GET;
		this.key = key;

        Map<String, Object> headmap = new HashMap<String, Object>();
        Map<String, Object> urlmap = new HashMap<String, Object>();
        HttpMethod = new HttpGetMethod(method);
        this.url = Config.getString("test.url") +"/mqtt"+"/topic_device";
        if (topic != null) {
            urlmap.put("topic", topic);
        }
        if (perPage != null) {
            urlmap.put("per_page", perPage);
        }
        if (page != null) {
            urlmap.put("page", page);
        }
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        HttpMethod.setcompleteUrl(url,urlmap);
	}

	public BasicResponse<TopicDeviceList> executeApi(){
		BasicResponse response=null;
		HttpResponse httpResponse=HttpMethod.execute();
		try {
			response = mapper.readValue(httpResponse.getEntity().getContent(),BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
			Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), TopicDeviceList.class);
			response.setData(newData);
			
		} catch (Exception e) {
			logger.error("json error", e.getMessage());
			throw new OnenetApiException();
		}
		try{
			HttpMethod.httpClient.close();
		}catch (Exception e) {
			logger.error("http close error:" + e.getMessage());
			throw new OnenetApiException();
		}
		return response;
	}
}
