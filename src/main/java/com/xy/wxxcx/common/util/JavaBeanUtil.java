package com.xy.wxxcx.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xy
 * 2018/1/5
 */
public class JavaBeanUtil {

    public static Map<String,Object> beanToMap(Object obj) throws IllegalAccessException {
        Map<String,Object> map=new HashMap<>();
        Class cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true);
            Object val=f.get(obj);
            if(null==val){
                continue;
            }
            map.put(f.getName(),val);
        }
        return map;
    }
}
