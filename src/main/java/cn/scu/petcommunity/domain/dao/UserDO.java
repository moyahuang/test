package cn.scu.petcommunity.domain.dao;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by luohui on 2017/7/20 14:20.
 */
public class UserDO {

    private Integer uid;

    private String openid;

    private String nickname;

    private String province;

    private String city;

    private String country;

    private String headimgurl;

    private String unionid;

    private Integer sex;

    private Integer alive;

    /**
     * 用户绑定的设备，可绑定多个设备格式deviceId,deviceId,
     */
    private String devices;

    public Integer getUid() {
        return uid;
    }

    
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAlive() {
        return alive;
    }

    public void setAlive(Integer alive) {
        this.alive = alive;
    }

    public String getDevices() {
        return devices;
    }

    public void setDevices(String devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
