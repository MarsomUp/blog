package com.immyc.blog.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Description:
 * @Author: mayc
 * @Date: 2019/01/16 17:46
 */
public class BaseController<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    public T mapToBean(HttpServletRequest request, Class<T> clazz) {
        Map<String, String[]> maps = request.getParameterMap();
        if (maps == null || maps.isEmpty()) {
            return null;
        }
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length == 0) {
            return null;
        }
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException e) {
            LOGGER.error("构造实体类异常1", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("构造实体类异常2", e);
        }
        if (t == null) return null;
        for (Field f : fields) {
            for (Map.Entry<String, String[]> entry : maps.entrySet()) {
                String key = entry.getKey();
                if (key.equals(f.getName())) {

                }
            }
        }
        return t;
    }
}
