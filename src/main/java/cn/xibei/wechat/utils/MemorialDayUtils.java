package cn.xibei.wechat.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/** 纪念日工具类
 * @author jiabing
 * @create 2022-08-04 22:58
 */
public class MemorialDayUtils {

    private static String LIAN_AI_BEGIN_DATE = "2022-01-14";

    /**
     * 恋爱统计
     * @return
     */
    public static int getLianAiDays(){
        return calculationLianAi(LIAN_AI_BEGIN_DATE);
    }


    /**
     * 统计天数
     * @param date 该日期 到 今天 已经多少天
     * @return
     */
    public static int calculationLianAi(String date) {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int day = 0;
        try {
            long time = System.currentTimeMillis() - simpleDateFormat.parse(date).getTime();
            day = (int) (time / 86400000L);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

}
