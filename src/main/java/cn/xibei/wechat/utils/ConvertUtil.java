package cn.xibei.wechat.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *  转换工具类型
 **/
public class ConvertUtil {

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(ConvertUtil.class);

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    // main函数
    public static void main(String[] args) {
    }

    /**
     * 源数据转换为目标对象
     * @param source    源数据
     * @param target    目标数据
     */
    public static void conversion(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        mapper.map(source, target);
    }

    /**
     * 源数据转换为目标class
     * @param source    源数据
     * @param target    目标class
     * @param <T>
     * @return
     */
    public static <T> T conversion(Object source, Class<T> target) {
        if (source == null) {
            return null;
        }
        return mapper.map(source, target);
    }

    /**
    * @Description: list格式对象转换
    * @Author: jiabing
    * @Date: 2019/11/15
    */
    public static <T> List<T> conversionList(List<?> entityList, Class<T> voClass) {
        List<T> list = new ArrayList<>();
        if (entityList == null) {
            return list;
        }
        for (Object o : entityList) {
            T t = conversion(o, voClass);
            list.add(t);
        }
        return list;
    }


}
