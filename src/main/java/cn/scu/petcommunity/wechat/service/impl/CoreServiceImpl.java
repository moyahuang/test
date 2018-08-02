package cn.scu.petcommunity.wechat.service.impl;

import cn.scu.petcommunity.wechat.model.Configuration;
import cn.scu.petcommunity.wechat.model.TextMessage;
import cn.scu.petcommunity.wechat.model.view.TextMessageVo;
import cn.scu.petcommunity.wechat.service.CoreService;
import cn.scu.petcommunity.wechat.util.MessageUtil;
import cn.scu.petcommunity.wechat.util.TokenUtil;
import cn.scu.petcommunity.wechat.util.TransformBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CoreService的实现
 * Created by luohui on 2017/7/19 9:15.
 */
@Service("coreService")
public class CoreServiceImpl implements CoreService {

    private static final Logger logger = LoggerFactory.getLogger(CoreServiceImpl.class);

    @Override
    public void confirmServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取配置文件配置的Token
        String token = Configuration.getProperty("weixin4j.token");
        if (Configuration.isDebug()) {
            logger.info("取出公众号Token:" + token);
        }
        //成为开发者验证
        String signature = request.getParameter("signature");   // 微信加密签名
        String timestamp = request.getParameter("timestamp");   // 时间戳
        String nonce = request.getParameter("nonce");           // 随机数
        String echostr = request.getParameter("echostr");       //
        //确认此次GET请求来自微信服务器，原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
        if (TokenUtil.checkSignature(token, signature, timestamp, nonce)) {
            response.getWriter().write(echostr);
        } else {
            response.getWriter().write("error");
        }
    }

    @Override
    public void responseServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //微信服务器发来的消息
    	
        TextMessageVo textMessageVo = MessageUtil.xml2Object(MessageUtil.stream2String(request.getInputStream()),
                TextMessageVo.class);
        //系统回复粉丝的消息
        TextMessage textMessage = TransformBeanUtil.textMessageVo2TextMessage(textMessageVo);
        switch (textMessageVo.getMsgType()) {
            case MessageUtil.MESSAGE_SUBSCRIBE:
                //对用户的进行保存，alive = 1
                textMessage.setContent("谢谢您关注宠物社区公众号，您可以实时查看宠物位置");
                break;
            case MessageUtil.MESSAGE_UNSUBSCRIBE:
                //更新用户，alive = 0
                textMessage.setContent("");
                break;
            default:
                textMessage.setContent("欢迎您来到宠物社区！");
                break;
        }
        String xml = MessageUtil.object2Xml(textMessage);
        logger.info("发送给微信服务器的消息：" + xml);
        response.getWriter().write(xml);
    }
}
