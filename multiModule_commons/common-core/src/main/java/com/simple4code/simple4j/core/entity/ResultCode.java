package com.simple4code.simple4j.core.entity;

/**
 * 公共的返回码
 * 返回码code：
 * 成功：10000
 * 失败：10001
 * 未登录：10002
 * 未授权：10003
 * 抛出异常：99999
 */
public enum ResultCode {

    /* 默认失败 */
    COMMON_FAIL(false,999, "失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(false,1001, "参数无效"),
    PARAM_IS_BLANK(false,1002, "参数为空"),
    PARAM_TYPE_ERROR(false,1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(false,1004, "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN(false,2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(false,2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(false,2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(false,2004, "密码过期"),
    USER_ACCOUNT_DISABLE(false,2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(false,2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(false,2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(false,2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(false,2009, "账号下线"),
    USER_TOKEN_EXPIRED(false,2010, "token过期"),
    /* 业务错误 */
    NO_PERMISSION(false,3001, "没有权限"),


    SUCCESS(true, 10000, "操作成功！"),
    //---系统错误返回码-----
    FAIL(false, 10001, "操作失败"),
    UNAUTHENTICATED(false, 10002, "您还未登录"),
    UNAUTHORISE(false, 10003, "权限不足"),
    SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！"),

    //---用户操作返回码  2xxxx----
    MOBILEORPASSWORDERROR(false, 20001, "用户名或密码错误");

    //---企业操作返回码  3xxxx----
    //---权限操作返回码----
    //---其他操作返回码----

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    ResultCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean success() {
        return success;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }
}
