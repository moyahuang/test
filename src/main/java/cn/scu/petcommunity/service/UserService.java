package cn.scu.petcommunity.service;


import cn.scu.petcommunity.domain.view.UserVO;

/**
 * Created by luohui on 2017/7/12 15:26.
 */
public interface UserService {
    void addUser(UserVO userVO);
    UserVO getUserByOpenid(String openid);
    void updateUser(String openid,String deviceid);
}
