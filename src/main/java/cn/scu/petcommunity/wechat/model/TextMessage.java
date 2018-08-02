package cn.scu.petcommunity.wechat.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by luohui on 2017/7/17 16:21.
 */
@XStreamAlias("xml")
public class TextMessage {
    /**
     * 消息接受者
     */
    @XStreamAlias("ToUserName")
    private String ToUserName;

    /**
     * 消息发送者
     */
    @XStreamAlias("FromUserName")
    private String FromUserName;

    /**
     * 消息接收者
     */
    @XStreamAlias("CreateTime")
    private Long CreateTime;

    /**
     * 消息类型
     */
    @XStreamAlias("MsgType")
    private String MsgType;

    /**
     * 消息内容
     */
    @XStreamAlias("Content")
    private String Content;

    /**
     * 消息Id
     */
    @XStreamAlias("MsgId")
    private String MsgId;

    public TextMessage() {
    }

    public TextMessage(String toUserName, String fromUserName, long createTime, String msgType, String content, String msgId) {
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

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
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
