package cn.xibei.wechat.config.constants;

public enum EventTypeEnum {

    subscribe("subscribe","订阅"),
    unsubscribe("unsubscribe","取消订阅"),
    ;

    private String type;

    private String desc;



    EventTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
