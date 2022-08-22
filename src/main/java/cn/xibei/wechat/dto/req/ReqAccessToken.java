package cn.xibei.wechat.dto.req;

import lombok.Data;

@Data
public class ReqAccessToken {

    /**
     * 获取access_token填写client_credential
     */
    private String grant_type;
    /**
     * 第三方用户唯一凭证
     */
    private String appid;
    /**
     * 第三方用户唯一凭证密钥，即appsecret
     */
    private String secret;

}
