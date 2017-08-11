package org.redrock.template.core.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 请求包装器
 * 利用装饰者模式 与 正则替换 实现xss过滤
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    //实例化
    public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    //对请求参数进行过滤
    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values==null)  {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = StringUtil.cleanXSS(values[i]);
        }
        return encodedValues;
    }

    //对请求参数过滤
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return StringUtil.cleanXSS(value);
    }

    //对header值进行过滤
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null)
            return null;
        return StringUtil.cleanXSS(value);
    }
}
