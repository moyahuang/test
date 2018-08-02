package cn.scu.petcommunity.onenet.api.mqtt;


import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpPostMethod;
import cn.scu.petcommunity.onenet.request.RequestInfo;
import cn.scu.petcommunity.onenet.response.BasicResponse;
import cn.scu.petcommunity.onenet.utils.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CreateMqttTopicApi extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private HttpPostMethod HttpMethod;
	private String name;
	/**
	 * @param name:设备订阅的主题（必选）,String
	 * @param key：masterkey
	 */
	public CreateMqttTopicApi(String name,String key) {
		this.name = name;
		this.key = key;
		this.method = RequestInfo.Method.POST;

        Map<String, Object> headmap = new HashMap<String, Object>();
        HttpMethod=  new HttpPostMethod(method);
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url= Config.getString("test.url")+"/mqtt"+"/topic";
        Map<String, Object> bodymap = new HashMap<String, Object>();
        if(name!=null){
            bodymap.put("name", name);
        }
        String json=null;
        ObjectMapper remapper = new ObjectMapper();
        try {
            json = remapper.writeValueAsString(bodymap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            logger.error("json error", e.getMessage());
            throw new OnenetApiException();
        }
        ((HttpPostMethod)HttpMethod).setEntity(json);
        HttpMethod.setcompleteUrl(url,null);
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
