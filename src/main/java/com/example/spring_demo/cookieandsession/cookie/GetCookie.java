package com.example.spring_demo.cookieandsession.cookie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 服务端创建cookie，将cookie信息通过响应给客户端
 * 默认浏览器关闭cookie失效，设置cookie失效时间，失效之间内cookie不会失效
 * 客户端通过请求头将cookie发送给服务器端
 *
 *
 * @classDesc：功能描述：(获取cookie)
 * @createTime：2020/9/3
 */
@RestController
public class GetCookie {


    @GetMapping("/getCookie")
    public void createCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("cookie------key:" + cookie.getName() + " value:" + cookie.getValue());
            }
        } else {
            System.out.println("没有cookie信息!!!!!!!!!!!");
        }
    }


}



