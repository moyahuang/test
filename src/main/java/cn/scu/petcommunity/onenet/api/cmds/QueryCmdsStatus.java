package cn.scu.petcommunity.onenet.api.cmds;

import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpGetMethod;
import cn.scu.petcommunity.onenet.request.RequestInfo.Method;
import cn.scu.petcommunity.onenet.response.BasicResponse;
import cn.scu.petcommunity.onenet.response.cmds.CmdsResponse;
import cn.scu.petcommunity.onenet.utils.Config;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class QueryCmdsStatus extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private HttpGetMethod HttpMethod;
	private  String cmdUuid;
	
	
	/**
	 * @param cmdUuid:命令id,String
	 * @param key:masterkey或者设备apikey
	 */
	public QueryCmdsStatus(String cmdUuid,String key) {
		this.cmdUuid = cmdUuid;
		this.key=key;
		this.method= Method.GET;
		this.HttpMethod=new HttpGetMethod(method);

        Map<String, Object> headmap = new HashMap<String, Object>();
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/cmds/" + cmdUuid;
        HttpMethod.setcompleteUrl(url,null);
	}


	public BasicResponse<CmdsResponse> executeApi() {
		BasicResponse response=null;
		HttpResponse httpResponse=HttpMethod.execute();
		try {
			response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
			Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), CmdsResponse.class);
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
