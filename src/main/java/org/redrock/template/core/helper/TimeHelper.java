package org.redrock.template.core.helper;

/**
 * 模板可能用到的时间函数类库
 */
public class TimeHelper {

    /**
     * 获取时间戳
     * @return
     */
    public String getTime() {
        return System.currentTimeMillis() / 1000 + "";
    }
}