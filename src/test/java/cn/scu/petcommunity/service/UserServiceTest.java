package cn.scu.petcommunity.service;

import cn.scu.petcommunity.domain.view.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by luohui on 2017/7/22 17:30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class UserServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Resource
    private UserService userService;

    @Test
    public void addUserTest() {
        UserVO userVO = new UserVO();
        userVO.setOpenid("aaaabbbbcc");
        userVO.setNickname("你好");
        userVO.setSex(1);
        userService.addUser(userVO);
    }

    @Test
    public void getUserTest() {
        UserVO userVO = userService.getUserByOpenid("aaaabbbbcc");
        System.out.println(userVO);
    }

    @Test
    public void updateUserTest() {
//       String openid = "aaaabbbb";
    	String openid="aaaabbbbcc";
       String deviceid = "123";
       userService.updateUser(openid, deviceid);
    }


}
