package cn.scu.petcommunity.onenet.api.cmds;


import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpGetMethod;
import cn.scu.petcommunity.onenet.request.RequestInfo;
import cn.scu.petcommunity.onenet.utils.Config;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class QueryCmdsRespApi extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private HttpGetMethod HttpMethod;
	private  String cmdUuid;


	/**
	 * @param cmdUuid:命令id,String
	 * @param key:masterkey或者设备apikey
	 */
	public QueryCmdsRespApi(String cmdUuid,String key) {
		this.cmdUuid = cmdUuid;
		this.key=key;
		this.method= RequestInfo.Method.GET;
		this.HttpMethod=new HttpGetMethod(method);

        Map<String, Object> headmap = new HashMap<String, Object>();
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/cmds/" + cmdUuid+"/resp";
        HttpMethod.setcompleteUrl(url,null);
	}

    
	public String executeApi() {
        String resp=null;
		HttpResponse httpResponse=HttpMethod.execute();
		try {
			 resp= EntityUtils.toString(httpResponse.getEntity());
			
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
		return resp;
	}
}
