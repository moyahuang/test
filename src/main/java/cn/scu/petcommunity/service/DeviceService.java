package cn.scu.petcommunity.service;

import cn.scu.petcommunity.domain.view.DeviceVO;

import java.util.List;

/**
 * Created by luohui on 2017/7/23 11:24.
 */
public interface DeviceService {
    void addDevice(DeviceVO deviceVO);
    List<DeviceVO> getDevicesByDeviceId(String deviceids);
}
