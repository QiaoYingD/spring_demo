package com.example.spring_demo.cookieandsession.session;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class GetSession {


    @GetMapping("/getSession")
    public void createCookie(HttpServletRequest request, HttpServletResponse response) {
        // getSession 默认true，代表new Session()   false：代表获取Session信息
        HttpSession session = request.getSession(false);
        if (session != null) {
            String name = (String) session.getAttribute("name");
            System.out.println("session -- name：" + name);
        }else{
            System.out.println("未有session信息！！！！！！！");
        }


    }

}
