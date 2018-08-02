package cn.scu.petcommunity.onenet.api.device;


import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpGetMethod;
import cn.scu.petcommunity.onenet.request.RequestInfo;
import cn.scu.petcommunity.onenet.response.BasicResponse;
import cn.scu.petcommunity.onenet.response.device.DeviceResponse;
import cn.scu.petcommunity.onenet.utils.Config;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class GetDeviceApi extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private HttpGetMethod HttpMethod;
	private String devId;
	
	/**
	 * 精确查询单个设备
	 * 参数顺序与构造函数顺序一致
	 * @param devId:设备名，String
	 * @param key:masterkey 或者 设备apikey,String
	 */
	public GetDeviceApi(String devId, String key) {
		this.devId = devId;
		this.key = key;
		this.method = RequestInfo.Method.GET;
		this.HttpMethod=new HttpGetMethod(method);
        Map<String, Object> headmap = new HashMap<String, Object>();
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/devices" + "/" + devId;
        HttpMethod.setcompleteUrl(url,null);
	}


	public BasicResponse<DeviceResponse> executeApi() {
		BasicResponse response=null;
		HttpResponse httpResponse=HttpMethod.execute();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		try {
			response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
			Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), DeviceResponse.class);
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
