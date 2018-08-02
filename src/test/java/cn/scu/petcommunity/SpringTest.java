package cn.scu.petcommunity;


import cn.scu.petcommunity.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by luohui on 2017/7/12 15:33.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class SpringTest {
    private static final Logger logger = LoggerFactory.getLogger(SpringTest.class);

    @Resource
    private UserService userService;

    @Test
    public void userServiceTest(){

    }

}
