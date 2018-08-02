package cn.scu.petcommunity.onenet.api.datastreams;


import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpGetMethod;
import cn.scu.petcommunity.onenet.request.RequestInfo.Method;
import cn.scu.petcommunity.onenet.response.BasicResponse;
import cn.scu.petcommunity.onenet.response.datastreams.DatastreamsResponse;
import cn.scu.petcommunity.onenet.utils.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDatastreamListApi extends AbstractAPI {
	private HttpGetMethod HttpMethod;
	private String datastreamids;
	private String devId;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 查询多个数据流
	 * @param datastreamids:数据流名称 ,String
	 * @param devId:设备ID,String
	 * @param key:masterkey 或者 设备apikey
	 */
	public FindDatastreamListApi(String datastreamids, String devId, String key) {
		this.datastreamids = datastreamids;
		this.devId = devId;
		this.key = key;
		this.method = Method.GET;
		this.HttpMethod = new HttpGetMethod(method);
        Map<String, Object> headmap = new HashMap<String, Object>();
        Map<String, Object> urlmap = new HashMap<String, Object>();
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/devices/" + devId + "/datastreams";
        // url参数
        if (datastreamids != null) {
            urlmap.put("datastream_ids", datastreamids);
        }
        HttpMethod.setcompleteUrl(url, urlmap);
	}



	public BasicResponse<List<DatastreamsResponse>> executeApi() {
		BasicResponse response = null;
		ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		HttpResponse httpResponse = HttpMethod.execute();
		try {
			response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
			Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()),  new TypeReference<List<DatastreamsResponse>>(){});
			response.setData(newData);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error:" + e.getMessage());
			throw new OnenetApiException();
		}
		return response;

	}
}
