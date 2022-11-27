package aojie.love.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: JieGe
 * @time:
 * @function: bean拷贝
 */
public class BeanCopyUtils {

    private BeanCopyUtils(){}


    /**
     *  单个bean实现属性值拷贝
     * @param source 复制来源
     * @param clazz 复制类
     * @param <T>
     * @return
     */
    public static <T> T copyBean(Object source,Class<T> clazz) {
        T result = null;
        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *  集合bean实现属性值拷贝
     * @param list
     * @param clazz
     * @param <T>>
     * @param <V>
     * @return
     */
    public static <T,V> List<V> copyBeanList(List<T> list ,Class<V> clazz){
        return list.stream().map((item)->{
            V result = null;
            try {
                result = clazz.newInstance();
                BeanUtils.copyProperties(item, result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }).collect(Collectors.toList());
    }
}
