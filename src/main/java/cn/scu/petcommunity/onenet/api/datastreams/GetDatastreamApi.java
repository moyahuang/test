package cn.scu.petcommunity.onenet.api.datastreams;


import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpGetMethod;
import cn.scu.petcommunity.onenet.request.RequestInfo.Method;
import cn.scu.petcommunity.onenet.response.BasicResponse;
import cn.scu.petcommunity.onenet.response.datastreams.DatastreamsResponse;
import cn.scu.petcommunity.onenet.utils.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class GetDatastreamApi extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private HttpGetMethod HttpMethod;
	private String datastreamId;
	private String devId;
	/**
	 * 查询单个数据流
	 * @param devId
	 * @param datastreamId
	 * @param key
	 */
	public GetDatastreamApi( String devId,String datastreamId,String key) {

		//Devices deviceUtil = new Devices();

		this.datastreamId = datastreamId;
		this.devId = devId;
		this.key = key;
		this.method = Method.GET;
        this.HttpMethod=new HttpGetMethod(method);
        Map<String, Object> headmap = new HashMap<String, Object>();
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/devices/"+ devId+"/datastreams/"+datastreamId;
        System.out.println("this.url="+url);
		//this.url = deviceUtil.getUrl() + "/devices/"+ devId+"/datastreams/"+datastreamId;
        HttpMethod.setcompleteUrl(url,null);
		
	}
	//执行API
	public BasicResponse<DatastreamsResponse> executeApi() {

		ObjectMapper mapper = new ObjectMapper();
		BasicResponse response=null;
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		HttpResponse httpResponse=HttpMethod.execute();
		try {
			response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
			Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), DatastreamsResponse.class);
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
