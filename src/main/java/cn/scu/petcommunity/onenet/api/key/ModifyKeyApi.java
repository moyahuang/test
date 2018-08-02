package cn.scu.petcommunity.onenet.api.key;


import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpPutMethod;
import cn.scu.petcommunity.onenet.model.Permissions;
import cn.scu.petcommunity.onenet.request.RequestInfo;
import cn.scu.petcommunity.onenet.response.BasicResponse;
import cn.scu.petcommunity.onenet.utils.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModifyKeyApi extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String title;
	private String apikey;
	private List<Permissions>permissions;
	private HttpPutMethod HttpMethod;
	/**
	 * @param title:名称（可选）,String
	 * @param apikey:需要修改的apikey,String
	 * @param permissions：apikey权限,List<Permissions>permissions
	 * @param key：masterkey(注：只能为master-key)
	 */
	public ModifyKeyApi(String title, String apikey, List<Permissions> permissions,String key) {
		this.title = title;
		this.apikey = apikey;
		this.permissions = permissions;
		this.key=key;
		this.method = RequestInfo.Method.PUT;

        Map<String, Object> headmap = new HashMap<String, Object>();
        HttpMethod=  new HttpPutMethod(method);
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url= Config.getString("test.url")+"/keys/"+apikey;
        Map<String, Object> bodymap = new HashMap<String, Object>();
        if (title != null) {
            bodymap.put("title", title);
        }
        if(permissions!=null){
            bodymap.put("permissions", permissions);
        }
        String json=null;
        try {
            json = mapper.writeValueAsString(bodymap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            logger.error("json error", e.getMessage());
            throw new OnenetApiException();
        }
        ((HttpPutMethod)HttpMethod).setEntity(json);
        HttpMethod.setcompleteUrl(url,null);
	}


	public BasicResponse<Void> executeApi(){
		ObjectMapper mapper = new ObjectMapper();
		BasicResponse response=null;
		HttpResponse httpResponse=HttpMethod.execute();
		try {
			response = mapper.readValue(httpResponse.getEntity().getContent(),BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
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
