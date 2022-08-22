package cn.xibei.wechat.config.constants;

public enum MsgTypeEnum {

    EVENT("event","事件"),
    TEXT("text","文本"),
    ;

    private String type;

    private String desc;



    MsgTypeEnum(String type, String desc) {
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
