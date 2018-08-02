package cn.scu.petcommunity.wechat.service;


/**
 * 启动服务器时向微信服务器请求access_token
 * Created by luohui on 2017/7/17 20:58.
 */


public interface WeChatTaskService {
    /**
     * 获取access_token
     */
   void execute() throws Exception, Exception;
}
