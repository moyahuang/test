package cn.scu.petcommunity.onenet.request;

import cn.scu.petcommunity.onenet.model.Location;

import java.util.List;

/**
 * Created by songyangguang on 2017/7/22.
 */
public interface ServerRequestInfo {

    /**
     * 获取实时的GPS数据流
     * @param deviceID 项圈对应的设备ID号（OneNET平台上的设备ID号）
     * @param dataStream 对应的设备数据流（OneNET平台上的数据流名称）
     * @return 实时GPS数据（经度、纬度）
     */
    public Location getGpsData(String deviceID,String dataStream);

    /**
     * 获取历 史数据
     * @param startTime 历史GPS数据查询开始时间
     * @param endTime 历史GPS数据查询结束时间
     * @param deviceID 项圈对应的设备ID号（OneNET平台上的设备ID号）
     * @param dataStream 对应的设备数据流（OneNET平台上的数据流名称）
     * @return GPS历史数据集合
     */
    public List<Location> getGpsDatas(String startTime, String endTime,String deviceID,String dataStream);

}
