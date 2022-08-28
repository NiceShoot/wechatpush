package cn.xibei.wechat.manager;


import cn.xibei.wechat.config.constants.CommonConstant;
import cn.xibei.wechat.dto.res.ResWeather;
import cn.xibei.wechat.utils.BirthdayUtils;
import cn.xibei.wechat.utils.CaiHongPiUtils;
import cn.xibei.wechat.utils.MemorialDayUtils;
import cn.xibei.wechat.utils.WeatherUtils;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author jiabing
 * @create 2022-08-04 21:09
 */
public class Pusher {

    public static void main(String[] args) {
        push(null);
    }

    public static void push(String userId){

        String relUserId = "oqFra6TIEjhgDa65lENKckMPXj3o";// 雪燕
        String relUserIdBing = "oqFra6ZIV5nsNxLbb3yDp1idu1Fg";
        if (StringUtils.isNotBlank(userId)){
            relUserId = userId;
        }
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(CommonConstant.APPID_TEST);
        wxStorage.setSecret(CommonConstant.SECRET_TEST);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(relUserId)
                .templateId("S1kPMXlTnwb2PAS8xNq2Dsw1k7fMhynlTXipcaaoycU")
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        ResWeather weather = WeatherUtils.getWeather();
        Map<String, String> map = CaiHongPiUtils.getEnsentence();

        templateMessage.addData(new WxMpTemplateData("riqi",weather.getDate() + "  "+ weather.getWeek(),"#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi",weather.getText_now(),"#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("low",weather.getLow() + "","#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("temp",weather.getTemp() + "","#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("high",weather.getHigh()+ "","#00BFFF" ));
        templateMessage.addData(new WxMpTemplateData("windclass",weather.getWind_class()+ "","#00BFFF" ));
        templateMessage.addData(new WxMpTemplateData("winddir",weather.getWind_dir()+ "","#00BFFF" ));
        templateMessage.addData(new WxMpTemplateData("caihongpi",CaiHongPiUtils.getCaiHongPi(),"#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("lianai", MemorialDayUtils.getLianAiDays()+"","#FF1493"));
        templateMessage.addData(new WxMpTemplateData("en",map.get("en") +"","#C71585"));
        templateMessage.addData(new WxMpTemplateData("zh",map.get("zh") +"","#C71585"));
        String beizhu = "";
        if(MemorialDayUtils.getLianAiDays() % 365 == 0){
            beizhu = "今天是恋爱" + (MemorialDayUtils.getLianAiDays() / 365) + "周年纪念日！";
        }
        templateMessage.addData(new WxMpTemplateData("beizhu",beizhu,"#FF69B4"));

        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void pushBirthday(String userId){
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(CommonConstant.APPID_TEST);
        wxStorage.setSecret(CommonConstant.SECRET_TEST);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userId)
                .templateId("Wcx6sTiCu5o0cQyMB1sEOUxG4B7gRopnA2I9ufoFkqM")
                .build();
        List<Map<String,Object>> list = BirthdayUtils.birthdaySoon();
        if (CollectionUtils.isEmpty(list)){
            return;
        }
        StringBuilder shengRi = new StringBuilder();
        StringBuilder shengRiFuture = new StringBuilder();
        for (Map<String, Object> map : list) {
            String name = map.get("name").toString();
            Integer days = (int) map.get("days");
            if (days == 0) {
                shengRi.append("今天是").append(name).append("的生日! 不要忘了哦\n");
            } else {
                shengRiFuture.append("距离").append(name).append("的生日还差 ").append(days).append(" 天\n");
            }
        }
        templateMessage.addData(new WxMpTemplateData("shengri",shengRi.toString(),"#C71585"));
        templateMessage.addData(new WxMpTemplateData("shengriFuture",shengRiFuture.toString(),"#00BFFF"));
        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
