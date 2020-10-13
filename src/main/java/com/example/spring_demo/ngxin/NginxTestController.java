package com.example.spring_demo.ngxin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @classDesc：功能描述：(nginx基于实现session共享)
 * @createTime：2020/9/3
 *
 * 1、加入依赖jar
 * <!-- session 共享 start-->
 *         <!--spring session 与redis应用基本环境配置,需要开启redis后才可以使用，不然启动Spring boot会报错 -->
 *         <!--spring boot 与redis应用基本环境配置 -->
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-data-redis</artifactId>
 *         </dependency>
 *         <dependency>
 *             <groupId>org.springframework.session</groupId>
 *             <artifactId>spring-session-data-redis</artifactId>
 *         </dependency>
 *         <!-- session 共享 end-->
 * 2、启动类加入注解
 * @EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
 * 3、可以直接创建session获取session了
 */
@RestController
public class NginxTestController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/getIndex")
    public String getIndex() {
        return "success index port：" + port;
    }

    /**
     * 创建session
     *
     * @param request
     * @param response
     * @param key
     * @param value
     * @return
     */
    @GetMapping("/createNginxSession")
    public String createSession(HttpServletRequest request, HttpServletResponse response, String key, String value) {
        HttpSession session = request.getSession(true);
        session.setAttribute(key, value);
        return "port：" + port + "，key：" + key;
    }

    /**
     * 获取session值
     *
     * @param request
     * @param response
     * @param key
     * @return
     */
    @GetMapping("/getNginxSession")
    public String getSession(HttpServletRequest request, HttpServletResponse response, String key) {
        HttpSession session = request.getSession(false);
        String value = null;
        if (session != null) {
            value = (String) session.getAttribute(key);
            return "port：" + port + "，value：" + value;
        } else {
            return "port：" + port + "，value：" + value;
        }

    }

}
