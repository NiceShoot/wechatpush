package cn.xibei.wechat;

import cn.xibei.wechat.dto.res.ResAccessToken;
import cn.xibei.wechat.manager.Pusher;
import cn.xibei.wechat.manager.WechatApi;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String push(){
        ResAccessToken accessToken = WechatApi.findAccessToken();

        return JSON.toJSONString(accessToken);
    }

}
