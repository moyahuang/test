package cn.scu.petcommunity.wechat.model.view;

/**
 * 接收来自服务器端的信息
 * Created by luohui on 2017/7/17 16:17.
 */
public class TextMessageVo {
    /**
     * 消息接受者
     */
    private String ToUserName;

    /**
     * 消息发送者
     */
    private String FromUserName;

    /**
     * 消息接收者
     */
    private String CreateTime;

    /**
     * 消息类型
     */
    private String MsgType;

    /**
     * 消息内容
     */
    private String Content;

    /**
     * 消息Id
     */
    private String MsgId;

    public TextMessageVo(String toUserName, String fromUserName, String createTime, String msgType, String content, String msgId) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        Content = content;
        MsgId = msgId;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
