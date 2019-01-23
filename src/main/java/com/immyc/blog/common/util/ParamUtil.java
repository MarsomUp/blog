package com.immyc.blog.common.util;

import org.springframework.util.Assert;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author:mayc
 * @Date:2019/1/21 21:48
 */
public class ParamUtil {

    public static <T> Object parseMapToObject(Map<String, String[]> maps, Class<T> clazz) {
        Assert.notEmpty(maps, "无效的参数maps");
        if (clazz == null || "HashMap".equals(clazz.getName())) {
            Map<String, Object> returnMaps = new HashMap<>(16);
            for(Map.Entry<String, String[]> entry : maps.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                if (value == null || value.length == 0) {
                    returnMaps.put(key, null);
                } else {
                    String val = "";
                    for (String s : value) {
                        val += s + ",";
                    }
                    returnMaps.put(key, val.substring(0, val.length() - 1));
                }
            }
            return returnMaps;
        } else {
            Constructor[] constructors = clazz.getConstructors();
            Constructor noParamConstructor = null;
            for (Constructor c : constructors) {
                if (c.getParameterCount() == 0) {
                    noParamConstructor = c;
                    break;
                }
            }
            if (noParamConstructor == null) {
                throw new RuntimeException("返回的实体类必须有无参构造法");
            }
            Object noParamInstance = null;
            try {
                noParamInstance = noParamConstructor.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            Field[] fields = clazz.getFields();
            for (Field f : fields) {
                String fieldName = f.getName();
                String[] fieldVal = maps.get(fieldName);
                if (fieldVal != null && fieldVal.length > 0) {
                    String fVal = "";
                    for (String str : fieldVal) {
                        fVal += str + ",";
                    }
                    try {
                        PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
                        Method setMethod = pd.getWriteMethod();
                        setMethod.invoke(noParamInstance, fVal.substring(0, fVal.length() - 1));
                    } catch (IntrospectionException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            return noParamInstance;
        }
    }
}
