package com.example.spring_demo.cookieandsession.cookie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 服务端创建cookie，将cookie信息通过响应给客户端，cookie保存在客户端本地硬盘上
 * 默认浏览器关闭cookie失效，设置cookie失效时间，失效之间内cookie不会失效
 * 客户端通过请求头将cookie发送给服务器端
 *
 * @classDesc：功能描述：(创建cookie)
 * @createTime：2020/9/3
 */
@RestController
public class CreateCookie {


    @GetMapping("/createCookie")
    public void createCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie;
        cookie = new Cookie("name", "zhangsan");
        //默认浏览器关闭cookie失效，除非设置cookie显示时间，cookie不会失效
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        System.out.println("创建cookie成功！！！！！！");
    }


}



