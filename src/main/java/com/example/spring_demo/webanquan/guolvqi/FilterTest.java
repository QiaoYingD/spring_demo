package com.example.spring_demo.webanquan.guolvqi;

import com.example.spring_demo.webanquan.xssfangzhuru.XssAndSqlHttpServletRequestWrapper;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 *  请求步骤，，  请求 →  过滤器 → 拦截器  → 控制器
 *  过滤器实现原理
 *  1、项目启动
 *  2、读取过滤器配置
 *  3、通过反射机制，调用class.forName(),clazz.newInstance(),无参构造函数创建对象
 *  4、执行初始化方法
 *  5、用户发送请求，过滤器处理请求
 *  6、项目停止后过滤器执行销毁
 *
 * @classDesc：功能描述：(过滤器，过滤)
 * @createTime：2020/9/3
 */
@Component
public class FilterTest implements Filter {

    public FilterTest() {
        System.out.println("过滤器无参构造函数");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器处理请求");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        /**
         * XSS是脚本注入，就是用户输入<script>alert("aaaaa")</script>，浏览器会执行这个script脚本语句，怎么防止XSS攻击，可以使用过滤器，将所有提交的参数的value值，转换成html代码执行
         */
        XssAndSqlHttpServletRequestWrapper xssRequestWrapper = new XssAndSqlHttpServletRequestWrapper(request);
        filterChain.doFilter(xssRequestWrapper,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
