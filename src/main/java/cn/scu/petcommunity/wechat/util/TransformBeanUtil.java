package cn.scu.petcommunity.wechat.util;

import cn.scu.petcommunity.wechat.model.TextMessage;
import cn.scu.petcommunity.wechat.model.view.TextMessageVo;

/**
 * 用于不同modelVo与model之间相互转换
 * Created by luohui on 2017/7/17 20:04.
 */
public class TransformBeanUtil {

    public static TextMessage textMessageVo2TextMessage(TextMessageVo textMessageVo){
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(textMessageVo.getFromUserName());
        textMessage.setFromUserName(textMessageVo.getToUserName());
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setMsgType(MessageUtil.MESSAGE_TEXT);
        return textMessage;
    }
}
