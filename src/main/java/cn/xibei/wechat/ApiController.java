package cn.xibei.wechat;

import cn.xibei.wechat.dto.res.ResBase;
import cn.xibei.wechat.manager.Pusher;
import cn.xibei.wechat.manager.WechatApi;
import cn.xibei.wechat.utils.EncUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jiabing")
@Slf4j
public class ApiController {


    @GetMapping(value = "/push")
    public String push(String userId){
        Pusher.push(userId);
        return "success";
    }

    @PostMapping(value = "/api",consumes = "text/xml",produces = "text/xml; charset=UTF-8")
    public String api(@RequestBody ResBase resBase){
        log.info("入参："+ JSON.toJSONString(resBase));
        return WechatApi.handle(resBase);
    }

    @GetMapping(value = "/api")
    public String api2(String signature,String timestamp,String nonce,String echostr){
        String token = "xibeihouse";
        String s = EncUtils.sortAndEncrypt(token, timestamp, nonce);
        if (s.equals(signature)){
            return echostr;
        }
        return "失败";
    }

}
