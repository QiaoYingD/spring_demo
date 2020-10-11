package com.example.spring_demo.cookieandsession.session;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 服务端创建session，将sessionId通过响应给客户端
 * 默认浏览器关闭cookie失效，因浏览器关闭cookie失效sessionId丢失，导致服务器端session查不到
 * session保存在服务器端，浏览器关闭session不会消失
 *
 * @classDesc：功能描述：(创建session)
 * @createTime：2020/9/3
 */
@RestController
public class CreateSession {


    @GetMapping("/createSession")
    public void createCookie(HttpServletRequest request, HttpServletResponse response) {
        // getSession 默认true，代表new Session()   false：代表获取Session信息
        HttpSession session = request.getSession();
        session.setAttribute("name", "张三");
        System.out.println("session创建成功");
    }


}
