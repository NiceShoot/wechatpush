package cn.xibei.wechat.utils;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUtil;
import cn.xibei.wechat.config.constants.BirthdayEnum;
import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 计算生日的工具类
 * 统一 都是阴历
 */
public class BirthdayUtils {

    private static Integer currentYear = Integer.valueOf(DateUtil.format(new Date(), "yyyy"));

    public static void main(String[] args) {
        ChineseDate chineseDate = new ChineseDate(2021, 2, 29);

        System.out.println(JSON.toJSONString(chineseDate));
    }

    /**
     * 获取即将过生日的人
     * @return
     */
    public static List<Map<String,Object>> birthdaySoon(){
        List<Map<String,Object>> list = new ArrayList<>();
        BirthdayEnum[] birthdayEnums = BirthdayEnum.values();
        long nowTime = DateUtil.beginOfDay(new Date()).getTime();
        for (BirthdayEnum t : birthdayEnums) {
            ChineseDate chineseDate = new ChineseDate(currentYear, t.getMonth(), t.getDay());
            Date birthdayDate = chineseDate.getGregorianDate();
            if (nowTime <= birthdayDate.getTime()){
                long dayNum = DateUtil.betweenDay( new Date(),birthdayDate, true);
                int day = t.getName().equals(BirthdayEnum.zhang_xue_yan.getName()) ? 100 : 30;
                if (dayNum >=0 && dayNum <= day) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("days",(int)dayNum);
                    map.put("name",t.getName());
                    list.add(map);
                }
            }
        }
        return list.stream().sorted(Comparator.comparing(s->Integer.parseInt(s.get("days").toString()))).collect(Collectors.toList());
    }





}
