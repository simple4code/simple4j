package com.simple4j.core.common.constant;

import java.util.concurrent.TimeUnit;

/**
 * 过期时间相关枚举
 *
 * @author simple4j
 * @created 2020-10-15-0:46.
 */
public enum ExpireEnum {
    //未读消息的有效期为30天
    UNREAD_MSG(30L, TimeUnit.DAYS);

    /**
     * 过期时间
     */
    private Long time;
    /**
     * 时间单位
     */
    private TimeUnit timeUnit;

    ExpireEnum(Long time, TimeUnit timeUnit) {
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public Long getTime() {
        return time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
