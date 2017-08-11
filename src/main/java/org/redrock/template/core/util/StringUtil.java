package org.redrock.template.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static boolean isBlank(String str) {
        return str == null || str.trim().equals("");
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 判断是否是数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str){
        String regex = "^[0-9]+\\.{0,1}[0-9]{0,2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 判断是否是整数
     * @param str
     * @return
     */
    public static boolean isInteger(String str){
        String regex = "^[0-9]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 至少num个字符
     * @param str
     * @param num
     * @return
     */
    public static boolean atLeast(String str,int num){
        String regex = "^\\d{"+num+",}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 是否是邮箱
     * @param str
     * @return
     */
    public static boolean isEmail(String str){
        String regex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 是否是字母或数字
     * @param str
     * @return
     */
    public static boolean isLetterOrDigit(String str){
        String regex = "^[A-Za-z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 是否是字母
     * @param str
     * @return
     */
    public static boolean isLetter(String str){
        String regex = "^[A-Za-z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 判断昵称是否符合，只能中文、字母、数字、下划线(_)、短横线(-)
     * @param str
     * @return
     */
    public static boolean checkNickname(String str){
        String regex = "^[a-zA-Z0-9_\\-\\u4e00-\\u9fa5]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static String cleanXSS(String value) {
        //(?i)忽略大小写
        value = value.replaceAll("(?i)<style>", "&lt;style&gt;").replaceAll("(?i)</style>", "&lt;&#47;style&gt;");
        value = value.replaceAll("(?i)<script>", "&lt;script&gt;").replaceAll("(?i)</script>", "&lt;&#47;script&gt;");
        value = value.replaceAll("(?i)<script", "&lt;script");
        value = value.replaceAll("(?i)eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        return value;
    }
}
