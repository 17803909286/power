package com.power.home.common;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by ZHP on 2017/6/24.
 */
public abstract class BaseSingleton {

    private static final ConcurrentMap<Class, Object> map = new ConcurrentHashMap<>();

    public static <T> T getSingleton(Class<T> type) {
        Object ob = map.get(type);

        try {
            if (ob == null) {
                synchronized (map) {
                    ob = type.newInstance();
                    map.put(type, ob);
                }
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) ob;
    }

    public static <T> void Remove(Class<T> type) {
        map.remove(type);

    }
}
