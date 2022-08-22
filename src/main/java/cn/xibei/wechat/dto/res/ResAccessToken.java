package cn.xibei.wechat.dto.res;

import lombok.Data;

@Data
public class ResAccessToken {

    // {"access_token":"ACCESS_TOKEN","expires_in":7200}
    /**
     * 获取到的凭证
     */
    private String access_token;
    /**
     * 凭证有效时间，单位：秒
     */
    private Long expires_in;

}
