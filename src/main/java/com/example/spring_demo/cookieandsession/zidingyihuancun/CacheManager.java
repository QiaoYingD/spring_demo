package com.example.spring_demo.cookieandsession.zidingyihuancun;

import java.util.HashMap;
import java.util.Map;

/**
 * 因为线程不安全，所以使用了synchronized同步锁
 *
 * @classDesc：功能描述：(自定义缓存类，超过时间自动删除数据)
 * @createTime：2020/9/3
 */
public class CacheManager {

    Map<String, CacheEntity> cacheMap = new HashMap<String, CacheEntity>();

    /**
     * @classDesc：功能描述：(添加缓存数据)
     * @createTime：2020/9/3
     */
    public void put(String key, String value) {
        put(key, value, null);
    }

    /**
     * @classDesc：功能描述：(添加缓存数据)
     * @createTime：2020/9/3
     */
    public synchronized void put(String key, String value, Long timeOut) {
        CacheEntity cacheEntity = new CacheEntity();
        cacheEntity.setKey(key);
        cacheEntity.setValue(value);
        if (timeOut != null) {
            cacheEntity.setTimeOut(System.currentTimeMillis() + timeOut);
        }
        cacheMap.put(key, cacheEntity);
    }

    /**
     * @classDesc：功能描述：(获取缓存数据)
     * @createTime：2020/9/3
     */
    public synchronized CacheEntity get(String key) {
        if (key != null && key != "") {
            CacheEntity cacheEntity = cacheMap.get(key);
            return cacheEntity;
        }
        return null;
    }

    /**
     * @classDesc：功能描述：(删除缓存数据)
     * @createTime：2020/9/3
     */
    public synchronized void remove(String key) {
        if (key != null && key != "") {
            System.out.println("key：" + key + "删除成功！！！");
            cacheMap.remove(key);
        }
    }

    /**
     * @classDesc：功能描述：(定时检查有效期的值)
     * @createTime：2020/9/3
     */
    public synchronized void checkValidityData() {
        for (String key : cacheMap.keySet()) {
            CacheEntity cacheEntity = cacheMap.get(key);
            if (cacheMap == null) {
                continue;
            }
            //获取设置的失效时间毫秒数
            Long timeOut = cacheEntity.getTimeOut();
            //获取当前系统毫秒数
            long currentTime = System.currentTimeMillis();
            //当前毫秒数-设置的失效毫秒数 > 0 就代表失效了
            if (currentTime - timeOut > 0) {
                remove(key);
            }
        }
    }


}
