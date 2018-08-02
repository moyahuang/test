package cn.scu.petcommunity.util;

import org.springframework.beans.BeanUtils;

/**
 * Created by luohui on 2017/7/22 16:49.
 */

public class TransformBeanUtil {
    public static <T> T transformBean(Object source, Class<T> clazz){
        T bean = null;
        try {
            bean = clazz.newInstance();
        }catch( Exception e) {
            throw new RuntimeException(e);
        }
        BeanUtils.copyProperties(source, bean);
        return bean;
    }
    


}
