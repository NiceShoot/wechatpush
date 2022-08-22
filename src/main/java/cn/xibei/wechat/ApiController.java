package cn.xibei.wechat;

import cn.xibei.wechat.config.constants.MsgTypeEnum;
import cn.xibei.wechat.dto.res.ResBase;
import cn.xibei.wechat.manager.WechatApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/jiabing")
public class ApiController {



    @PostMapping(value = "/api",consumes = "application/xml",produces = "application/xml; charset=UTF-8")
    public String api(@RequestBody ResBase resBase){
        return WechatApi.handle(resBase);
    }

//    @RequestMapping(value = "/check/signature", method = RequestMethod.POST, produces = {"application/xml; charset=UTF-8"})
//    public void wechatEvent(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            // 为了防止消息乱码
//            request.setCharacterEncoding("UTF-8");
//            response.setCharacterEncoding("UTF-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        WechatApi.handle(request, response);
//    }

}
