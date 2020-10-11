package com.example.spring_demo.webanquan.xssfangzhuru;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssAndSqlHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private HttpServletRequest request;

    public XssAndSqlHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    /**
     * 将request中的value值重写，将一些脚本参数，非法参数转换成html元素
     *
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        String value = request.getParameter(name);
        if (StringUtils.isNotEmpty(value)) {
            StringEscapeUtils.escapeHtml4(value);
        }
        return value;
    }
}
