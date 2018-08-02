package cn.scu.petcommunity.dao;


import cn.scu.petcommunity.domain.dao.UserDO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by luohui on 2017/7/12 15:46.
 */
public interface UserDao {
    /**
     * 保存用户
     * @param userDO
     */
    void addUser(UserDO userDO);

    /**
     * 根据用户的openid查询某个用户
     * @param openid
     * @return
     */
    UserDO getUserByOpenid(String openid);

    /**
     * 更新用户的设备信息
     * @param uid 用户的openid
     * @param devices 用户的新的设备信息
     */
    void updateUser(@Param("uid") Integer uid, @Param("devices")String devices);
}
