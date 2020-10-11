package com.example.spring_demo.cookieandsession.zidingyihuancun;

import lombok.Data;

/**
 *
 * @classDesc：功能描述：(自定义缓存实体类)
 * @createTime：2020/9/3
 */
@Data
public class CacheEntity {

    private String key;
    private Object value;
    private Long timeOut;

    @Override
    public String toString() {
        return "CacheEntity{" +
                "key='" + key + '\'' +
                ", value=" + value +
                ", timeOut=" + timeOut +
                '}';
    }
}
