package cn.xibei.wechat.config.constants;

public enum BirthdayEnum {

    bing(1,17,"正月十七","帅冰"),
    ma_ma(4,10,"四月初十","老妈"),
    ba_ba(12,27,"腊月二十七","老爸"),
    jie_jie(8,2,"八月初二","姐姐"),
    jie_fu(8,1,"八月初一","姐夫"),
    zhang_xue_yan(10,6,"十月初六","雪燕"),
    dou_yao_qiang(2,23,"二月二十三","小强"),
    hu_xiao_qing(1,20,"正月二十","小青"),
    yang_jiang_feng(10,19,"十月十九","江丰"),
    hao_shao_chen(4,20,"四月二十","大辰"),
    hao_xi_nuo(2,25,"二月二十五","大诺"),
    han_shi_jie(3,19,"三月十九","仕杰"),

    ;

    private Integer month;
    private Integer day;
    private String desc;
    private String name;

    BirthdayEnum(Integer month, Integer day, String desc, String name) {
        this.month = month;
        this.day = day;
        this.desc = desc;
        this.name = name;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getDay() {
        return day;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }
}
