package cn.scu.petcommunity.onenet.api.device;


import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpPutMethod;
import cn.scu.petcommunity.onenet.model.Location;
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

public class ModifyDevicesApi extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String devId;
	private String title;
	private String protocol;
	private String desc;
	private String idsn;
	private List<String> tags;
	private Location location;
	private Boolean isPrivate;
	private Object authInfo;
	private Map<String, Object> other;
	private Integer interval;
	private HttpPutMethod HttpMethod;
	private Object isPrivateSet;
	
	
	/***
	 * 设备更新
	 * 参数顺序与构造函数顺序一致
	 * @param devId： 设备ID,String
	 * @param title： 设备名，String
	 * @param protocol： 接入协议（可选，默认为HTTP），String
	 * @param desc： 设备描述（可选），String
	 * @param tags： 设备标签（可选，可为一个或多个），List<String>
	 * @param location： 设备位置{"纬度", "精度", "高度"}（可选）,Location类型
	 * @param isPrivate： 设备私密性，Boolean类型
	 * @param authInfo： 设备唯一编号 ，String
	 * @param other：  其他信息，Map<String, Object>
	 * @param interval： MODBUS设备 下发命令周期,Integer类型
	 * @param key ：masterkey 或者 设备apikey
	 */
	public ModifyDevicesApi(String devId,String title, String protocol, String desc, List<String> tags, Location location,
			Boolean isPrivate, Object authInfo, Map<String, Object> other,Integer interval, String key) {
		this.devId=devId;
		this.title = title;
		this.protocol = protocol;
		this.desc = desc;
		this.tags = tags;
		this.location = location;
		this.isPrivate = isPrivate;
		this.authInfo = authInfo;
		this.other = other;
		this.interval = interval;
		this.key = key;
		this.method = RequestInfo.Method.PUT;
        Map<String, Object> headmap = new HashMap<String, Object>();
        HttpMethod=  new HttpPutMethod(method);
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url= Config.getString("test.url")+"/devices"+"/"+devId;
        // body参数

        Map<String, Object> bodymap = new HashMap<String, Object>();
        if (title != null) {
            bodymap.put("title", title);
        }
        if (desc != null) {
            bodymap.put("desc", desc);
        }
        if (tags != null) {
            bodymap.put("tags", tags);
        }

        if (idsn != null) {
            bodymap.put("idsn", idsn);
        }
        if (location != null) {
            bodymap.put("location", location.toMap());
        }
        if (isPrivateSet!=null) {
            bodymap.put("private", isPrivate);
        }
        if (protocol != null) {
            bodymap.put("protocol", protocol);
        }
        if (authInfo != null) {
            bodymap.put("auth_info", authInfo);

        }
        if (interval !=null) {
            bodymap.put("interval", interval);
        }
        if (other != null) {
            bodymap.put("other", other);
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
        ((HttpPutMethod)HttpMethod).setEntity(json);
        //System.out.println(url);
        HttpMethod.setcompleteUrl(url,null);
	}

	public BasicResponse executeApi(){
		BasicResponse response=null;
		HttpResponse httpResponse=HttpMethod.execute();
		try {
			
			response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
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
