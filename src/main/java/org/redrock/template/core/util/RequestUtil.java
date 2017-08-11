package org.redrock.template.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 请求相关方法
 */
public class RequestUtil {
    /**
     * 获取当前项目url根路径
     * @param request
     * @return
     */
    public static String getContextPath(HttpServletRequest request) {
        //获取协议
        String protocol = request.getScheme().toLowerCase();
        //获取端口号
        int port = request.getServerPort();
        //获取域名
        String domain = request.getServerName();
        //获取项目根路径
        String context = request.getContextPath();
        //拼接项目路径
        StringBuilder temp = new StringBuilder();
        //http://hongyan.cqupt.edu.cn/MagicLoop
        temp.append(protocol).append("://")
            .append(domain);
        if (port != 80) temp.append(":").append(port);
        if (StringUtil.isNotBlank(context)) temp.append(context);
        return temp.toString();
    }

    /**
     * 获取当前项目所在服务器实际路径
     */
    public static String getContextRealPath(HttpServletRequest request) {
        return request.getServletContext().getRealPath("/");
    }

    /**
     * 获取参数
     */
    public static Map getParameters(HttpServletRequest request, Set<String> paramNames) {
        Map data = new HashMap();
        for (String paramName : paramNames) {
            String paramValue = request.getParameter(paramName);
            data.put(paramName, paramValue);
        }
        return data;
    }

    /**
     * 设置session
     * @param request
     * @param name
     * @param value
     */
    public static void setSessionItem(HttpServletRequest request, String name, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(name, value);
    }

    /**
     * 获取session中的值
     * @param request
     * @param name
     * @return
     */
    public static Object getSessionItem(HttpServletRequest request, String name) {
        HttpSession session = request.getSession();
        return session.getAttribute(name);
    }

    /**
     * 移除session中的值
     * @param request
     * @param name
     */
    public static void removeSessionItem(HttpServletRequest request, String name) {
        HttpSession session = request.getSession();
        session.removeAttribute(name);
    }

    /**
     * 获取cookie
     * @param request
     * @param name
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
