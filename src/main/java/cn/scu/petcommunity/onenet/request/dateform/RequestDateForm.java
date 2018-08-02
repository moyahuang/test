package cn.scu.petcommunity.onenet.request.dateform;

import cn.scu.petcommunity.onenet.model.TrailDataRequest;

import java.util.Calendar;

/**
 * 将时间转化为数据请求需要的格式2017-12-12T24:00:00
 * Created by songyangguang on 2017/7/27.
 */
public class RequestDateForm {
    public TrailDataRequest dateTransform() {
        TrailDataRequest trailDataRequest = new TrailDataRequest();

        //获取到时间的每个时间域，然后对相应的时间域进行修改
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String smonth,sday,shour,sminute,ssecond,ssday;
        if(month >=0 && month <=9) {
            smonth = "0"+String.valueOf(month);
        } else {
            smonth = String.valueOf(month);
        }
        if(day >= 0 && day <= 9) {
            sday = "0"+String.valueOf(day);
        } else {
            sday = String.valueOf(day);
        }
        if(hour >= 0 && hour <= 9) {
            shour = "0"+String.valueOf(hour);
        } else {
            shour = String.valueOf(hour);
        }
        if(minute >= 0 && minute <= 9) {
            sminute = "0"+String.valueOf(minute);
        } else {
            sminute = String.valueOf(minute);
        }
        if(second >= 0 && second <= 9) {
            ssecond = "0"+String.valueOf(second);
        } else {
            ssecond = String.valueOf(second);
        }

        if((day-2) >= 0 && (day-2) <= 9) {
            day = day-2;
            ssday = "0"+String.valueOf(day);
        } else {
            ssday = String.valueOf(day);
        }

        String string_start = year+"-"+smonth+"-"+sday+"T"+shour+":"+sminute+":"+ssecond;
        String string_end = year+"-"+smonth+"-"+ssday+"T"+shour+":"+sminute+":"+ssecond;

        trailDataRequest.setStart(string_end);
        trailDataRequest.setEnd(string_start);

        return  trailDataRequest;
    }
}
