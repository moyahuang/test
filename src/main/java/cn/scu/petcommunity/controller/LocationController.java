package cn.scu.petcommunity.controller;

import cn.scu.petcommunity.onenet.model.Location;
import cn.scu.petcommunity.onenet.model.TrailDataRequest;
import cn.scu.petcommunity.onenet.request.ServerRequestInfo;
import cn.scu.petcommunity.onenet.request.dateform.RequestDateForm;
import cn.scu.petcommunity.onenet.utils.Config;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by songyangguang on 2017/7/22.
 */
@Controller
@RequestMapping("/location")
public class LocationController {
    @Resource
    private ServerRequestInfo serverRequestInfo;

    @ResponseBody
    @RequestMapping("/getLocation")
    public Location getLocation(String lid){
        System.out.println("###########"+lid);
        String devId = "9836847";//获取设备ID
        String id = "GPS";//获取经度这一个数据流
        return serverRequestInfo.getGpsData(devId, id);
    }

    @ResponseBody
    @RequestMapping("/getHistoryLocations")
    public List<Location> getHistoryLocations(String lid) {
       System.out.print("lid="+lid);

       //获取当前一天的运动时间
       RequestDateForm requestDateForm = new RequestDateForm();
       TrailDataRequest trailDataRequest = requestDateForm.dateTransform();
       /* String startTime = trailDataRequest.getStart();
        String endTime = trailDataRequest.getEnd();*/

       System.out.print("**********||||"+trailDataRequest.getEnd()+"||||");
       String startTime = "2017-07-27T20:35:41";
       String endTime = "2017-07-27T20:50:41";
        //System.out.print("**********"+startTime);
       String devID = "9836847";
       String id = "GPS";
        return serverRequestInfo.getGpsDatas(startTime,endTime,devID,id);
    }
}
