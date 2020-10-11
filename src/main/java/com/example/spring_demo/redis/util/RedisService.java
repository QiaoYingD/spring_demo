package com.example.spring_demo.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @classDesc：功能描述：(redis 工具类，可支持主从复制，哨兵模式)
 * @createTime：2020/9/3
 */
@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void set(String key, Object value) {
        set(key, value, null);
    }


    /**
     * 将值设置到redis中
     *
     * @param key     键
     * @param obj     值
     * @param timeOut 超时时间
     */
    public void set(String key, Object obj, Long timeOut) {
        if (obj == null) {
            return;
        }
        if (obj instanceof String) {
            String value = (String) obj;
            redisTemplate.opsForValue().set(key, value);
        } else if (obj instanceof Map) {
            Map value = (Map) obj;
            redisTemplate.opsForHash().putAll(key, value);
        } else if (obj instanceof Set) {
            Set value = (Set) obj;
            redisTemplate.opsForSet().add(key, value.stream().collect(Collectors.joining(",")).toString());
        } else if (obj instanceof List) {
            List value = (List) obj;
            redisTemplate.opsForList().leftPushAll(key, value);
        }
        if (timeOut != null) {
            redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
        }
    }

    /**
     * 通过键获取String
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public long getListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public String getList(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    public List getList(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> getHashMap(String key) {

        return redisTemplate.opsForHash().entries(key);

    }

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */

    public Set<String> getSet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        RedisService redisUtil = new RedisService();
        redisUtil.set("stringName", "zs");
    }
}
