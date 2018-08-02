package cn.scu.petcommunity.wechat.service.impl;

import cn.scu.petcommunity.wechat.model.AccessToken;
import cn.scu.petcommunity.wechat.service.WeChatTaskService;
import cn.scu.petcommunity.wechat.util.WechatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 启动服务器时向微信服务器请求access_token
 * Created by luohui on 2017/7/17 20:58.
 */

@Service("weChatTaskService")
public class WeChatTaskServiceImpl implements WeChatTaskService {

    private static final Logger logger = LoggerFactory.getLogger(WeChatTaskServiceImpl.class);


    @Override
    public void execute() throws Exception {
        logger.info("***************开始获取access_token:" + new Date() + "***************");
         WechatUtil.getAccessToken();
        logger.info("获取到的access_token：" + AccessToken.getAccess_token());

        logger.info("***************开始获取jsapiTicket:" + new Date() + "***************");
        WechatUtil.getJsapiTicket();
    }


}
