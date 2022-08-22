package cn.xibei.wechat.dto.res;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement(name = "xml")
public class ResBase  implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 开发者微信号
     */
    @XmlElement(name = "ToUserName")
    private String ToUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    @XmlElement(name = "FromUserName")
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    @XmlElement(name = "CreateTime")
    private Long CreateTime;
    /**
     * 消息类型，文本为text
     */
    @XmlElement(name = "MsgType")
    private String MsgType;
    /**
     * 文本消息内容
     */
    @XmlElement(name = "Content")
    private String Content;
    /**
     * 消息id，64位整型
     */
    @XmlElement(name = "MsgId")
    private Long MsgId;
    /**
     * 消息的数据ID（消息如果来自文章时才有）
     */
    @XmlElement(name = "MsgDataId")
    private Long MsgDataId;
    /**
     * 多图文时第几篇文章，从1开始（消息如果来自文章时才有）
     */
    @XmlElement(name = "Idx")
    private Integer Idx;
    /**
     * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
     */
    @XmlElement(name = "Event")
    private String Event;
}
