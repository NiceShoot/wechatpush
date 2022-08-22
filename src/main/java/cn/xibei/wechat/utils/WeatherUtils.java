package cn.xibei.wechat.utils;

import cn.xibei.wechat.dto.res.ResWeather;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiabing
 * @create 2022-08-04 22:02
 */
public class WeatherUtils {
    public static void main(String[] args) {
        System.out.println(getWeather());
    }
    public static ResWeather getWeather(){
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> header = new HashMap<String,String>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("district_id","110108"); // 北京市海淀区
        map.put("data_type","all");//这个是数据类型
        map.put("ak","pQCsWPoPNupn0NDaPYaYymD432B0ZAcG");
        String res = RestTemplateUtil.doGet("https://api.map.baidu.com/weather/v1/",map,header);
        JSONObject json = JSONObject.parseObject(res);
        JSONArray forecasts = json.getJSONObject("result").getJSONArray("forecasts");
        List<ResWeather> weathers = forecasts.toJavaList(ResWeather.class);
        JSONObject now = json.getJSONObject("result").getJSONObject("now");
        ResWeather weather = weathers.get(0);
        weather.setText_now(now.getString("text"));
        weather.setTemp(now.getString("temp"));
        weather.setWind_class(now.getString("wind_class"));
        weather.setWind_dir(now.getString("wind_dir"));
        return weather;
    }
}
