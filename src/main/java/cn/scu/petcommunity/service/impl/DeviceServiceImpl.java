package cn.scu.petcommunity.service.impl;

import cn.scu.petcommunity.dao.DeviceDao;
import cn.scu.petcommunity.domain.dao.DeviceDO;
import cn.scu.petcommunity.domain.view.DeviceVO;
import cn.scu.petcommunity.service.DeviceService;
import cn.scu.petcommunity.util.TransformBeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luohui on 2017/7/23 11:27.
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private DeviceDao deviceDao;

    @Override
    public void addDevice(DeviceVO deviceVO) {
        DeviceDO deviceDO = TransformBeanUtil.transformBean(deviceVO, DeviceDO.class);
        deviceDao.addDevice(deviceDO);
    }
    
    
    @Override
    public List<DeviceVO> getDevicesByDeviceId(String deviceids) {
        List<DeviceVO> devices = new ArrayList<DeviceVO>();
        for(DeviceDO deviceDO : deviceDao.getDevicesByDeviceId(deviceids.split(","))){
            devices.add(TransformBeanUtil.transformBean(deviceDO, DeviceVO.class));
        }
        return devices;
    }
}
