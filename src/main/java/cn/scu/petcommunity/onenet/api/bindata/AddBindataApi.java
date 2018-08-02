package cn.scu.petcommunity.onenet.api.bindata;


import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpPostMethod;
import cn.scu.petcommunity.onenet.request.RequestInfo;
import cn.scu.petcommunity.onenet.response.BasicResponse;
import cn.scu.petcommunity.onenet.response.bindata.NewBindataResponse;
import cn.scu.petcommunity.onenet.utils.Config;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AddBindataApi extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private  String devId;
	private  String datastreamId;
	private  String filename;
	private  String filepath;
	private HttpPostMethod HttpMethod;
	/**
	 * @param devId:数据所属设备（必选）,String
	 * @param datastreamId:该数据所属已经存在的数据流（必选）,String
	 * @param key:masterkey 或者 该设备的设备key
	 * @param filename:文件名,String
	 * @param filepath：路径,String
	 */
	public AddBindataApi(String devId, String datastreamId,String key, String filename,String filepath) {
		this.devId = devId;
		this.datastreamId = datastreamId;
		this.key=key;
		this.filename=filename;
		this.filepath=filepath;
		this.method= RequestInfo.Method.POST;

        Map<String, Object> headmap = new HashMap<String, Object>();
        HttpMethod=  new HttpPostMethod(method);
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url= Config.getString("test.url")+"/bindata";
        Map<String, Object> urlmap = new HashMap<String, Object>();
        if(devId!=null){
            urlmap.put("device_id", devId);
        }
        if(datastreamId!=null){
            urlmap.put("datastream_id", datastreamId);
        }
        Map<String, String> fileMap=new HashMap<String, String>();
        fileMap.put(filename, filepath);
        ((HttpPostMethod)HttpMethod).setEntity(null,fileMap);
        HttpMethod.setcompleteUrl(url,urlmap);
	}

	public BasicResponse<NewBindataResponse> executeApi(){
		BasicResponse response=null;
		HttpResponse httpResponse=HttpMethod.execute();
		try {
			response = mapper.readValue(httpResponse.getEntity().getContent(),BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
			Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), NewBindataResponse.class);
			response.setData(newData);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
