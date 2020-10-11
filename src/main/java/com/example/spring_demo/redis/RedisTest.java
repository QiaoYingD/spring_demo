package com.example.spring_demo.redis;

import com.example.spring_demo.redis.util.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTest {

    @Autowired
    private RedisService redisUtil;

    @GetMapping("/setString")
    public String setString(String key, String value) {
        redisUtil.set(key, value);
        return key;
    }

    @GetMapping("/getString")
    public String setString(String key) {
        return redisUtil.getString(key);
    }

}
