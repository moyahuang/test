package cn.scu.petcommunity.service;

import cn.scu.petcommunity.domain.view.DeviceVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by luohui on 2017/7/22 17:30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class DeviceServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceTest.class);

    @Resource
    private DeviceService deviceService;

    @Test
    public void addDeviceTest() {
        DeviceVO deviceVO = new DeviceVO();
        deviceVO.setDeviceid("12");
        deviceVO.setDevicename("test");
        deviceService.addDevice(deviceVO);
    }

    @Test
    public void getDeviceTest() {
        String deviceids = "11,12";
        List<DeviceVO> devices =  deviceService.getDevicesByDeviceId(deviceids);
        System.out.println(devices.size());
    }

}
