package cn.scu.petcommunity.onenet.api.bindata;


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

public class GetBindataApi extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private HttpGetMethod HttpMethod;
	private  String index;
	/**
	 * @param index:二进制数据索引号,String
	 * @param key:masterkey 或者 该设备的设备key
	 */
	public GetBindataApi(String index,String key) {
		this.index = index;
		this.key=key;
		this.method= RequestInfo.Method.GET;
		this.HttpMethod=new HttpGetMethod(method);
        Map<String, Object> headmap = new HashMap<String, Object>();
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/bindata" + "/" + index;
        HttpMethod.setcompleteUrl(url,null);
	}

	public String executeApi() {
		String response=null;
		HttpResponse httpResponse=HttpMethod.execute();
         try {
			response = EntityUtils.toString(httpResponse.getEntity());
		} catch (Exception e) {
			logger.error("error:" + e.getMessage());
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
