package cn.scu.petcommunity.onenet.api.key;


import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpPostMethod;
import cn.scu.petcommunity.onenet.model.Permissions;
import cn.scu.petcommunity.onenet.request.RequestInfo;
import cn.scu.petcommunity.onenet.response.BasicResponse;
import cn.scu.petcommunity.onenet.response.key.NewKeyResponse;
import cn.scu.petcommunity.onenet.utils.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddKeyApi extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String title;
	private List<Permissions>permissions;
	private HttpPostMethod HttpMethod;
	/**
	 * @param title:名称（可选）,String
	 * @param permissions,List<Permissions>permissions
	 * @param key:masterkey 或者 该设备的设备key
	 */
	public AddKeyApi(String title, List<Permissions> permissions,String key) {
		this.title = title;
		this.permissions = permissions;
		this.key=key;
		this.method = RequestInfo.Method.POST;
        Map<String, Object> headmap = new HashMap<String, Object>();
        HttpMethod=  new HttpPostMethod(method);
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url= Config.getString("test.url")+"/keys";
        Map<String, Object> bodymap = new HashMap<String, Object>();
        if (title != null) {
            bodymap.put("title", title);
        }
        if(permissions!=null){
            bodymap.put("permissions", permissions);
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

	public BasicResponse<NewKeyResponse> executeApi(){
		BasicResponse response=null;
		HttpResponse httpResponse=HttpMethod.execute();
		try {
			response = mapper.readValue(httpResponse.getEntity().getContent(),BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
			Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), NewKeyResponse.class);
			response.setData(newData);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
			logger.error("json error", e.getMessage());
			throw new OnenetApiException();
		}
		try{
			HttpMethod.httpClient.close();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("http close error:" + e.getMessage());
			throw new OnenetApiException();
		}
		return response;
	}
}
