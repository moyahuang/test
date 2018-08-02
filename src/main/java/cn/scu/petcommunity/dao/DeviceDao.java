package cn.scu.petcommunity.dao;

import cn.scu.petcommunity.domain.dao.DeviceDO;

import java.util.List;

/**
 * Created by luohui on 2017/7/22 16:14.
 */
public interface DeviceDao {
    /**
     * 保存设备
     * @param deviceDO
     */
    void addDevice(DeviceDO deviceDO);

    /**
     * 根据deviceid查找设备
     * @param deviceids
     */
     List<DeviceDO> getDevicesByDeviceId(String[] deviceids);
}
