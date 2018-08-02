package cn.scu.petcommunity.onenet.model;

/**
 * 历史数据请求的数据格式封装
 * Created by songyangguang on 2017/7/26.
 */
public class TrailDataRequest {
    private String start = "2017-07-25T17:10:41";//提取数据点的开始时间
    private String end = "2017-07-25T17:20:41";//提取数据点的结束时间
    private int duration = 0;//查询时间区间
    private String limit = null;//限定本次请求最多返回的数据点数
    private String cursor = null;//指定本次请求继续从cursor位置开始提取数据（可选）
    private int interval = 0;//通过采样方式返回数据点，interval值指定采样的时间间隔（可选）
    private String meth = null;//指定在返回数据点时，同时返回统计结果，可能的值为（可选）
    private int frist = 0;//返回结果中最值的时间点。1-最早时间，0-最近时间，默认为1（可选）
    private String sort = null;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getMeth() {
        return meth;
    }

    public void setMeth(String meth) {
        this.meth = meth;
    }

    public int getFrist() {
        return frist;
    }

    public void setFrist(int frist) {
        this.frist = frist;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
