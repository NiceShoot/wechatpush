package cn.xibei.wechat.config.constants;

public enum ApiUrlEnum {

    GET_ACCESS_TOKEN("https://api.weixin.qq.com/cgi-bin/token",
            "获取accessToken",
            "https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html"),




    ;

    private String url;

    private String desc;

    private String docUrl;


    ApiUrlEnum(String url, String desc, String docUrl) {
        this.url = url;
        this.desc = desc;
        this.docUrl = docUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }

    public String getDocUrl() {
        return docUrl;
    }
}
