package cn.xibei.wechat.dto.res;

import lombok.Data;

@Data
public class ResAcceptMsg {

    /**
     * 开发者微信号
     */
    private String ToUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private Long CreateTime;
    /**
     * 消息类型，文本为text
     */
    private String MsgType;
    /**
     * 文本消息内容
     */
    private String Content;
    /**
     * 消息id，64位整型
     */
    private Long MsgId;
    /**
     * 消息的数据ID（消息如果来自文章时才有）
     */
    private Long MsgDataId;
    /**
     * 多图文时第几篇文章，从1开始（消息如果来自文章时才有）
     */
    private Integer Idx;


}
