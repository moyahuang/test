package cn.scu.petcommunity.onenet.api.mqtt;


import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpGetMethod;
import cn.scu.petcommunity.onenet.request.RequestInfo;
import cn.scu.petcommunity.onenet.response.BasicResponse;
import cn.scu.petcommunity.onenet.utils.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetDevicesTopicsApi extends AbstractAPI {
	private String devId;
	private HttpGetMethod HttpMethod;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 查询设备订阅的Topic列表
	 * @param devId：设备ID（必选）,String
	 * @param key：masterkey
	 */
	public GetDevicesTopicsApi(String devId,String key) {
		this.devId = devId;
		this.key=key;
		this.method = RequestInfo.Method.GET;

        Map<String, Object> headmap = new HashMap<String, Object>();
        HttpMethod = new HttpGetMethod(method);
        this.url = Config.getString("test.url") +"/mqtt"+"/device_topic/"+devId;
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        HttpMethod.setcompleteUrl(url,null);
	}


	public BasicResponse<List<String>> executeApi() {
		BasicResponse response = null;
		HttpResponse httpResponse = HttpMethod.execute();
		try {
			response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
			Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), new TypeReference<List<String>>(){});
			response.setData(newData);

		} catch (Exception e) {
			logger.error("error:" + e.getMessage());
			throw new OnenetApiException();
		}
		return response;

	}
}
