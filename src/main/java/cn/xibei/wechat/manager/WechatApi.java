package cn.xibei.wechat.manager;

import cn.xibei.wechat.config.constants.ApiUrlEnum;
import cn.xibei.wechat.config.constants.CommonConstant;
import cn.xibei.wechat.config.constants.MsgTypeEnum;
import cn.xibei.wechat.dto.req.ReqAccessToken;
import cn.xibei.wechat.dto.req.ReqReplayMsg;
import cn.xibei.wechat.dto.res.ResAccessToken;
import cn.xibei.wechat.dto.res.ResBase;
import cn.xibei.wechat.utils.ConvertUtil;
import cn.xibei.wechat.utils.RestTemplateUtil;
import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WechatApi {

    /**
     * 获取accessToken
     *
     * @return
     */
    public static ResAccessToken findAccessToken() {
        ReqAccessToken token = new ReqAccessToken();
        token.setGrant_type("client_credential");
        token.setSecret(CommonConstant.SECRET_TEST);
        token.setAppid(CommonConstant.APPID_TEST);
        Map<String, Object> conversion = ConvertUtil.conversion(token, Map.class);
        String res = RestTemplateUtil.doGet(ApiUrlEnum.GET_ACCESS_TOKEN.getUrl(), conversion, new HashMap<>());
        ResAccessToken accessToken = JSON.parseObject(res, ResAccessToken.class);
        return accessToken;
    }

    /**
     *
     */
    public static String handle(ResBase resBase) {
        String msgType = resBase.getMsgType();

        if (msgType.equals(MsgTypeEnum.TEXT.getType())) {
            ReqReplayMsg reqReplayMsg = new ReqReplayMsg();
            reqReplayMsg.setMsgType("text");
            reqReplayMsg.setCreateTime(new Date().getTime());
            reqReplayMsg.setFromUserName(resBase.getToUserName());
            reqReplayMsg.setToUserName(resBase.getFromUserName());
            reqReplayMsg.setContent("今天是：{{riqi.DATA}} \n" +
                    "天气：{{tianqi.DATA}} \n" +
                    "温度：{{temp.DATA}} \n" +
                    "最低温度：{{low.DATA}} \n" +
                    "最高温度：{{high.DATA}} \n" +
                    "风向：{{winddir.DATA}} \n" +
                    "风级：{{windclass.DATA}} \n" +
                    "今天是我们恋爱的第{{lianai.DATA}}天\n" +
                    "你知道吗，{{caihongpi.DATA}} {{beizhu.DATA}} \n" +
                    "\n" +
                    "{{zh.DATA}}\n" +
                    "{{en.DATA}} ");
            return getXmlString(reqReplayMsg);
        }
        return "";
    }


    public static String getXmlString(ReqReplayMsg textMessage) {
        String xml = "";
        if (textMessage != null) {
            xml = "<xml>";
            xml += "<ToUserName><![CDATA[";
            xml += textMessage.getToUserName();
            xml += "]]></ToUserName>";
            xml += "<FromUserName><![CDATA[";
            xml += textMessage.getFromUserName();
            xml += "]]></FromUserName>";
            xml += "<CreateTime>";
            xml += textMessage.getCreateTime();
            xml += "</CreateTime>";
            xml += "<MsgType><![CDATA[";
            xml += textMessage.getMsgType();
            xml += "]]></MsgType>";
            xml += "<Content><![CDATA[";
            xml += textMessage.getContent();
            xml += "]]></Content>";
            xml += "</xml>";
        }
        return xml;
    }

}


