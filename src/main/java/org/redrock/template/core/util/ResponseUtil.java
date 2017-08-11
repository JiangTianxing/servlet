package org.redrock.template.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {

    public static void setCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        response.addCookie(cookie);
    }



}
