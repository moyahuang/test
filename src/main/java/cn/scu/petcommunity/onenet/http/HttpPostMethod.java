package cn.scu.petcommunity.onenet.http;

import cn.scu.petcommunity.onenet.exception.OnenetApiException;
import cn.scu.petcommunity.onenet.request.RequestInfo.Method;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HttpPostMethod extends BasicHttpMethod{



	public HttpPostMethod(Method method) {
		super(method);
		// TODO Auto-generated constructor stub
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public void setEntity(String json) {
		// TODO Auto-generated method stub
		// System.out.println(json);
		if(json!=null)
		{
				StringEntity entity=new StringEntity(json, Charset.forName("UTF-8"));
				((HttpPost) httpRequestBase).setEntity(entity);//向下转型
		}
		
	}

	public void setEntity(Map<String, String> stringMap, Map<String, String> fileMap) {
		// TODO Auto-generated method stub
		this.upload=true;
		Set<Entry<String,StringBody>> stringBodySet=null;
		if(stringMap!=null){
			Set<Entry<String, String>> stringSet=stringMap.entrySet();
			Map<String, StringBody> stringEntityMap=new HashMap<String, StringBody>();
			for(Entry<String, String> entry:stringSet)
			{
				try {
					stringEntityMap.put(entry.getKey(),new StringBody(entry.getValue(), Charset.forName("UTF-8")));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					logger.error("error:"+e.getMessage());
					throw new OnenetApiException();
				}
			}
			stringBodySet=stringEntityMap.entrySet();
		}
		Set<Entry<String, FileBody>> fileBodySet=null;
	if(fileMap!=null){
		Set<Entry<String, String>> fileSet=fileMap.entrySet();
		
		Map<String, FileBody> fileBodyMap=new HashMap<String, FileBody>();
		
		for(Entry<String, String> entry:fileSet)
		{
			fileBodyMap.put(entry.getKey(),new FileBody(new File(entry.getValue())));
		}
		fileBodySet=fileBodyMap.entrySet();
	}
		
		MultipartEntityBuilder builder=MultipartEntityBuilder.create();
		
		if(fileBodySet!=null){
			for(Entry<String, FileBody> entry:fileBodySet)
			{
				builder.addPart(entry.getKey(), entry.getValue());
			}
		}
		if(stringBodySet!=null){
			for(Entry<String, StringBody> entry:stringBodySet)
			{
				builder.addPart(entry.getKey(), entry.getValue());
			}
		}
		
		HttpEntity reqEntity = builder.build();
		((HttpPost) httpRequestBase).setEntity(reqEntity);
	}


	public HttpResponse execute(){
		HttpResponse httpResponse =null;
		httpClient= HttpClients.createDefault();
		try {
		        
				httpResponse = httpClient.execute(httpRequestBase);
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				if (statusCode != HttpStatus.SC_OK&&statusCode !=221) {
					logger.error("request failed: {}", statusCode);
					throw new OnenetApiException();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error:" + e.getMessage());
				throw new OnenetApiException();
			}
				  return  httpResponse;
	     
	 } 


}
