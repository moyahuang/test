package cn.scu.petcommunity.onenet.utils;

import cn.scu.petcommunity.onenet.model.Location;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by songyangguang on 2017/7/15.
 */
public class getLocationData {
    private Location location = new Location();
    private String lon = null;
    private String lat = null;
    private String date;
    private double longitude;
    private double latitude;
    public Location getDoubleData (String jsonData) {
        JSONObject jsonObject = JSONObject.parseObject(jsonData);

        //获得json中的经纬度值
        String data = jsonObject.getString("data");
        JSONObject jsonObject_currentValue = JSONObject.parseObject(data);
        String current_value = jsonObject_currentValue.getString("current_value");
        JSONObject lon_lat = JSONObject.parseObject(current_value);
        String lon = lon_lat.getString("lon");
        String lat = lon_lat.getString("lat");
        longitude = Double.parseDouble(lon);
        latitude = Double.parseDouble(lat);
        location.setLon(longitude);
        location.setLat(latitude);
        return location;
    }

    //获取Json中的数据集
    public List<Location> getLocationDates(String stringJson) {
        List<Location> locationArray = new ArrayList<Location>();
        Location locations = new Location();


        //一步一步解析Json
        JSONObject jsonObject = JSONObject.parseObject(stringJson);
        String string_data = jsonObject.getString("data");

        JSONObject json_data = JSONObject.parseObject(string_data);
        int count = json_data.getInteger("count");//数据点的个数

        JSONArray json_datastreams = json_data.getJSONArray("datastreams");
        //获得datapoints数据集节点（时间、经度、纬度）
        JSONArray json_datapoints = json_datastreams.getJSONObject(0).getJSONArray("datapoints");
        System.out.println("**********"+json_datapoints+"***************");


        //将数据点依次存入到ArrayList集合中
        for(int i = 0; i < count; i++) {
            Location location = new Location();
            date = json_datapoints.getJSONObject(i).getString("at");
            String value = json_datapoints.getJSONObject(i).getString("value");
            JSONObject json_value = JSONObject.parseObject(value);
            lon = json_value.getString("lon");
            lat = json_value.getString("lat");
            longitude = Double.parseDouble(lon);
            latitude = Double.parseDouble(lat);
            location.setLon(longitude);
            location.setLat(latitude);
            location.setDate(date);
            locationArray.add(location);
        }
        return locationArray;
    }
}
