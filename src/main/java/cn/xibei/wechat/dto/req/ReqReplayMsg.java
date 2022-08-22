package cn.xibei.wechat.dto.req;

import lombok.Data;

@Data
public class ReqReplayMsg {
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
     * 回复的消息内容（换行：在 content 中能够换行，微信客户端就支持换行显示）
     */
    private String Content;
}
