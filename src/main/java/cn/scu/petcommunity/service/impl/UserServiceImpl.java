package cn.scu.petcommunity.service.impl;

import cn.scu.petcommunity.dao.UserDao;
import cn.scu.petcommunity.domain.dao.UserDO;
import cn.scu.petcommunity.domain.view.UserVO;
import cn.scu.petcommunity.service.UserService;
import cn.scu.petcommunity.util.TransformBeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by luohui on 2017/7/12 15:41.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public void addUser(UserVO userVO){
        UserDO userDO = TransformBeanUtil.transformBean(userVO, UserDO.class);
        userDO.setAlive(1);
        userDao.addUser(userDO);
    }

    @Override
    public UserVO getUserByOpenid(String openid) {
        UserDO userDO = userDao.getUserByOpenid(openid);
        return TransformBeanUtil.transformBean(userDO, UserVO.class);
    }

    @Override
    public void updateUser(String openid, String deviceid) {
        //首先得到当前用户原来的的devices后更新
        UserDO userDO = userDao.getUserByOpenid(openid);
        String devices = userDO.getDevices();
        devices = devices == null ? deviceid : devices + "," + deviceid;
        userDao.updateUser(userDO.getUid(), devices);
    }


}
