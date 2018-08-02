package cn.scu.petcommunity.onenet.api.triggers;


import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpGetMethod;
import cn.scu.petcommunity.onenet.request.RequestInfo;
import cn.scu.petcommunity.onenet.response.BasicResponse;
import cn.scu.petcommunity.onenet.response.triggers.TriggersResponse;
import cn.scu.petcommunity.onenet.utils.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class GetTriggersApi extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private HttpGetMethod HttpMethod;
	private String tirggerId;
	
	/**
	 * 查询单个触发器
	 * @param tirggerId:触发器ID,String
	 * @param key:masterkey 或者 设备apikey
	 */
	public GetTriggersApi(String tirggerId,String key) {
		this.tirggerId = tirggerId;
		this.key=key;
		this.method = RequestInfo.Method.GET;
		this.HttpMethod=new HttpGetMethod(method);
        Map<String, Object> headmap = new HashMap<String, Object>();
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/triggers" + "/" + tirggerId;
        HttpMethod.setcompleteUrl(url,null);
	}

	public BasicResponse<TriggersResponse> executeApi() {

		ObjectMapper mapper = new ObjectMapper();
		BasicResponse response=null;
		HttpResponse httpResponse=HttpMethod.execute();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		try {
			response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
			Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), TriggersResponse.class);
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
