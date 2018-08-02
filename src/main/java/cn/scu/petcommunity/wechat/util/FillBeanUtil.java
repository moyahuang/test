package cn.scu.petcommunity.wechat.util;

import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 将request的所有参数封装成bean
 * Created by luohui on 2017/7/17 15:34.
 */
public class FillBeanUtil {

    public static <T> T fillBean(HttpServletRequest request, Class<T> clazz){
        T bean = null;
        try {
            bean = clazz.newInstance();
        }catch( Exception e) {
            throw new RuntimeException(e);
        }
        BeanUtils.copyProperties(bean, request.getParameterMap());
        return bean;
    }
}
