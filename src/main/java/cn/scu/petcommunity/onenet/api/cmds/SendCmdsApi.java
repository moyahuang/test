package cn.scu.petcommunity.onenet.api.cmds;

import cn.scu.petcommunity.onenet.api.AbstractAPI;
import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.http.HttpPostMethod;
import cn.scu.petcommunity.onenet.request.RequestInfo;
import cn.scu.petcommunity.onenet.response.BasicResponse;
import cn.scu.petcommunity.onenet.response.cmds.NewCmdsResponse;
import cn.scu.petcommunity.onenet.utils.Config;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class SendCmdsApi  extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String devId;
	private Integer qos;
	private Integer timeOut;
	private Integer type;
	private Object contents;//用户自定义数据
	private HttpPostMethod HttpMethod;
	/**
	 * @param devId：接收该数据的设备ID（必选），String
	 * @param qos:是否需要响应，默认为0,Integer
	 * 0：不需要响应，即最多发送一次，不关心设备是否响应；
	 * 1：需要响应，如果设备收到命令后没有响应，则会在下一次设备登陆时若命令在有效期内(有效期定义参见timeout参数）则会继续发送。
	 * 对响应时间无限制，多次响应以最后一次为准。
	 * 本参数仅当type=0时有效；
	 * @param timeOut:命令有效时间，默认0,Integer
	 * 0：在线命令，若设备在线,下发给设备，若设备离线，直接丢弃；
	 *  >0： 离线命令，若设备在线，下发给设备，若设备离线，在当前时间加timeout时间内为有效期，有效期内，若设备上线，则下发给设备。单位：秒，有效围：0~2678400。
	 *  本参数仅当type=0时有效；
	 * @param type://默认0。0：发送CMD_REQ包，1：发送PUSH_DATA包
	 * @param contents:用户自定义数据：json、string、二进制数据（小于64K）
	 * @param key:masterkey或者设备apikey
	 */
	public SendCmdsApi(String devId, Integer qos, Integer timeOut, Integer type, Object contents,String key) {
		this.devId = devId;
		this.qos = qos;
		this.timeOut = timeOut;
		this.type = type;
		this.contents=contents;
		this.key = key;
		this.method = RequestInfo.Method.POST;

        Map<String, Object> headmap = new HashMap<String, Object>();
        Map<String, Object> urlmap = new HashMap<String, Object>();
        HttpMethod=  new HttpPostMethod(method);
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url= Config.getString("test.url")+"/cmds";
        if(devId!=null){
            urlmap.put("device_id", devId);
        }
        if(qos!=null){
            urlmap.put("qos", qos);
        }
        if(timeOut!=null){
            urlmap.put("timeout", timeOut);
        }
        if(type!=null){
            urlmap.put("type", type);
        }
        //body参数处理
        if(contents instanceof byte[]){
            try {
                String s = new String((byte[]) contents, "UTF-8");
                ((HttpPostMethod)HttpMethod).setEntity(s);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
                logger.error("bytes[]  error", e.getMessage());
                throw new OnenetApiException();
            }
        }
        if(contents instanceof String){
            ((HttpPostMethod)HttpMethod).setEntity((String)contents);
        }
        HttpMethod.setcompleteUrl(url,urlmap);
	}

	public BasicResponse<NewCmdsResponse> executeApi(){
		BasicResponse response=null;
		HttpResponse httpResponse=HttpMethod.execute();
		try {
			response = mapper.readValue(httpResponse.getEntity().getContent(),BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
			Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), NewCmdsResponse.class);
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
