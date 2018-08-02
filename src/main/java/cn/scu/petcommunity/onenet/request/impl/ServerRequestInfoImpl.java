package cn.scu.petcommunity.onenet.request.impl;

import cn.scu.petcommunity.onenet.api.datapoints.GetDatapointsListApi;
import cn.scu.petcommunity.onenet.api.datastreams.GetDatastreamApi;
import cn.scu.petcommunity.onenet.model.Location;
import cn.scu.petcommunity.onenet.model.TrailDataRequest;
import cn.scu.petcommunity.onenet.request.ServerRequestInfo;
import cn.scu.petcommunity.onenet.response.BasicResponse;
import cn.scu.petcommunity.onenet.response.datapoints.DatapointsList;
import cn.scu.petcommunity.onenet.response.datastreams.DatastreamsResponse;
import cn.scu.petcommunity.onenet.utils.Config;
import cn.scu.petcommunity.onenet.utils.getLocationData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songyangguang on 2017/7/22.
 */
@Service("serverRequestInfo")
public class ServerRequestInfoImpl implements ServerRequestInfo {
    public Location getGpsData(String deviceID, String dataStream) {
        String devId = deviceID;//获取设备ID
        String id = dataStream;//获取经度这一个数据流*/

        String key = Config.getString("APIKey");//获取设备APIKey


        System.out.println("deviceID="+deviceID);
        System.out.println("Stream_id="+dataStream);
        System.out.println("APIKey="+key);
        /**
         * 查询单个数据流
         * @param devid:设备ID,String
         * @param datastreamid:数据流名称 ,String
         * @param key:masterkey 或者 设备apikey
         */
        GetDatastreamApi api = new GetDatastreamApi(devId, id, key);
        BasicResponse<DatastreamsResponse> response = api.executeApi();
        System.out.println("errno:"+response.errno+" error:"+response.error);

        System.out.println(response.getJson());
        String result = response.getJson();

        //获取json数据中的精度、纬度值
        getLocationData locationData = new getLocationData();
        Location location = locationData.getDoubleData(result);


        System.out.println("***lon="+location.getLon());
        System.out.println("***lat="+location.getLat());

        return location;
    }

    public List<Location> getGpsDatas(String startTime, String endTime, String deviceID, String dataStream) {

        String datastreamids = dataStream;
        String devid = deviceID;
        String key = Config.getString("APIKey");

        getLocationData getlocationdata = new getLocationData();
        //记录所有的运动轨迹点
        List<Location> list = new ArrayList<Location>();

        TrailDataRequest testDataPoints = new TrailDataRequest();
        testDataPoints.setStart(startTime);
        testDataPoints.setEnd(endTime);
        /**
         * 数据点查询
         * @param datastreamids:查询的数据流，多个数据流之间用逗号分隔（可选）,String
         * @param start:提取数据点的开始时间（可选）,String
         * @param end:提取数据点的结束时间（可选）,String
         * @param devid:设备ID,String
         *
         * @param duration:查询时间区间（可选，单位为秒）,Integer
         *  start+duration：按时间顺序返回从start开始一段时间内的数据点
         *  end+duration：按时间倒序返回从end回溯一段时间内的数据点
         *
         * @param limit:限定本次请求最多返回的数据点数，0<n<=6000（可选，默认1440）,Integer
         * @param cursor:指定本次请求继续从cursor位置开始提取数据（可选）,Integer（String）
         * @param interval:通过采样方式返回数据点，interval值指定采样的时间间隔（可选）,Integer
         * @param metd:指定在返回数据点时，同时返回统计结果，可能的值为（可选）,String
         * @param first:返回结果中最值的时间点。1-最早时间，0-最近时间，默认为1（可选）,Integer
         * @param sort:值为DESC|ASC时间排序方式，DESC:倒序，ASC升序，默认升序,String
         * @param key:masterkey 或者 设备apikey
         */
        GetDatapointsListApi api = new GetDatapointsListApi(datastreamids, testDataPoints.getStart(), testDataPoints.getEnd(), devid, testDataPoints.getDuration(),
                testDataPoints.getLimit(), testDataPoints.getCursor(), testDataPoints.getInterval(), testDataPoints.getMeth(), testDataPoints.getFrist(), testDataPoints.getSort(), key);
        BasicResponse<DatapointsList> response = api.executeApi();
        System.out.println(response.getJson());
        String result = response.getJson();
        list = getlocationdata.getLocationDates(result);

        for (Location location:list) {
            System.out.print("日期："+location.getDate());
            System.out.print("lon="+location.getLon());
            System.out.println("lat="+location.getLat());
        }
        return list;
    }
}
